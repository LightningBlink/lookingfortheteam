package com.den.controller;

import com.den.handler.ErrorMessageHandler;
import com.den.handler.MessageHandler;
import com.den.handler.SuccessMessageHandler;
import com.den.model.enumes.POST;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.den.model.ext.UserHash;
import com.den.model.wrapper.UserInfoResponse;
import com.den.model.wrapper.UserModifiedRequest;
import com.den.repository.UserPersistence;
import com.den.service.*;
import com.den.util.ReflectionUtils;
import com.den.utils.HttpUtils;
import com.den.utils.PasswordUtils;
import com.den.wrapper.UserTransfer;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Denis on 30.09.2014.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Value("${application.temp}")
    private  String pathAvatars;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private EmailConfirmationService emailConfirmationService;


    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private UserHashService userHashService;



    @RequestMapping("/confirm")
    public ModelAndView confirmation(@RequestParam("token") String token) {
       UserHash userHash = userHashService.findByHash(token);

        if(userHash != null){
            if(DateUtils.addDays(userHash.getDate(), 1).compareTo(new Date()) == -1){
                userService.remove(userHash.getUser().getId());
                userHashService.remove(userHash.getId());
                return new ModelAndView("comfirmation", "text", getMessage("userController.time.expired"));
            } else {
                userHash.getUser().setEnabled(true);
                userService.save(userHash.getUser());
                userHashService.remove(userHash.getId());
                return new ModelAndView("comfirmation", "text", getMessage("userController.usersaved"));
            }
        } else {
           return new ModelAndView("comfirmation", "text", getMessage("userController.token.is.not.exist"));
        }
    }


    @RequestMapping
    @ResponseBody
    public UserTransfer getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof String && principal.equals("anonymousUser")) {
            return new UserTransfer("ANONYMOUS_USER", new HashMap<String, Boolean>() {{
                put(RoleService.ROLE_ANONYMOUS, Boolean.TRUE);
            }});
        }
        UserDetails userDetails = (UserDetails) principal;

        return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
    }

    @RequestMapping("/get/{name}")
    @ResponseBody
    public User getUser(@PathVariable String name) {
        return userService.get(name);
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @ResponseBody
    public MessageHandler registration(@ModelAttribute("user")  User user) {
        user.setRoles(RoleService.ROLE_USER_SET);

        return userService.saveWithLetter(user);

    }


    @RequestMapping(value = "/info/{name}", method = RequestMethod.GET)
    @ResponseBody
    public UserInfoResponse info(@PathVariable String name) {
        return userService.getUserInfo(name);
    }


    //TODO temp folder path more flexible and change compare of two files
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MessageHandler save(@RequestBody UserModifiedRequest userModifiedRequest) throws IOException {

        if (!Objects.equals(userModifiedRequest.getId(), userService.get(getUsername()).getId())) {
            return new ErrorMessageHandler(getMessage("userController.cantmodify"));
        }

        User user = userService.get(userModifiedRequest.getId());

        final String fileName = getUsername() + ".jpg";

        File source = new File(pathAvatars + "/avatarstmp/" + fileName);

        File target = new File(pathAvatars + "/avatars/" + fileName);


        if (userModifiedRequest.getHasAvatar()) {

            if (!FileUtils.contentEquals(source, target)) {
                FileUtils.copyFile(source, target);
            }

        } else if (target.exists()) {

            target.delete();
            source.delete();
        }

        if (user == null) {
            return new ErrorMessageHandler();
        }


        ReflectionUtils.modify(userModifiedRequest, user);

        MessageHandler handler = validate(user);

        if (!handler.getSuccess()) {
            return handler;
        }

        User saved = userService.update(user);

        if (saved == user) {
            ((SuccessMessageHandler) handler).setMessage(getMessage("userController.usersaved"));
            return handler;
        }

        return new ErrorMessageHandler(getMessage("userController.errorsaveuser"));
    }
//TODO Path to temp dir should be in application.properties

    @RequestMapping(value = "/uploadavatar", method = RequestMethod.POST)
    @ResponseBody
    @Secured("ROLE_USER")
    @Transactional(rollbackFor = Exception.class)
    public void uploadAvaPost(HttpServletRequest request,
                              @RequestParam("file") MultipartFile file) throws IOException, InterruptedException {

        if (!file.isEmpty()) {

            int resumableChunkSize = HttpUtils.toInt(request.getParameter("flowChunkSize"), -1);
            int resumableIdentifier = Integer.parseInt(request.getParameter("flowChunkNumber"));
            User user = userService.get(getUsername());
            String path;
            if (Boolean.parseBoolean(request.getParameter("isTeam"))) {
                String teamName = request.getParameter("teamName");
                final Team team = teamService.findByName(teamName);
                if (teamService.findByName(teamName).getUserTeams().stream().noneMatch(ut -> user.equals(ut.getUser()) && ut.getPost().equals(POST.LEADER))) {
                    return;
                }
                path = pathAvatars + "/teamavatars/" + teamName;
                team.setHasAvatar(true);
                teamService.save(team);
            } else {

                path = pathAvatars + "/avatarstmp/" + user.getUsername();
            }

            try (RandomAccessFile storage = new RandomAccessFile(path
                    + ".jpg", "rw")) {
                long start = resumableChunkSize * (resumableIdentifier - 1);

                storage.seek(start);
                storage.write(file.getBytes());
            } catch (IOException ignore) {
            }
        }
    }


    @RequestMapping("/avatarimage")
    public void avatar(@RequestParam("name") String name, @RequestParam(required = false, defaultValue = "false") Boolean isTeam, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        String path;
        if (isTeam) {
            path = pathAvatars + "/teamavatars/";
        } else path = pathAvatars + "/avatars/";

        File file = new File(path + name + ".jpg");
        if (file.exists()) {
            BufferedImage bi = ImageIO.read(file);
            if(bi.getWidth() > 160){
                int widthO = bi.getWidth()/160;
                bi = resize(bi,160, bi.getHeight()/widthO);
                ImageIO.write(bi,"jpg",file);
            }

            OutputStream out = response.getOutputStream();

            ImageIO.write(bi, "jpg", out);
        } else {

            if (isTeam) {
                IOUtils.copy(getClass().getResourceAsStream("/webapp/images/Group-512.png"), response.getOutputStream());
            } else {
                IOUtils.copy(getClass().getResourceAsStream("/webapp/images/user.png"), response.getOutputStream());
            }
        }

    }

    @RequestMapping("/get/lookingplayers")
    @Secured("ROLE_USER")
    @ResponseBody
    public Page<User> getLookingPlayers(@RequestParam String game, @PageableDefault Pageable pageable, @RequestParam(defaultValue = StringUtils.EMPTY) String keyword, @RequestParam(defaultValue = "") String country) {

        if(country.equals("INT")){
            country = "";
        }
        return userService.getLookingUsers(game, pageable, keyword, country);

    }

    @RequestMapping("/sendpasswordtomail")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public MessageHandler sendPasswordToMail(@RequestParam(required = true) String emailSendPassword) {


        User user = userService.findByEmail(emailSendPassword);

        if (user != null) {
            String password = PasswordUtils.generatePassword(15);
            user.setPassword(password);
            userService.save(user);
            new Thread(() -> emailConfirmationService.send(user, new HashMap<>(), "resendpassword", getMessage("userController.resendpassword"))).start();
            return new SuccessMessageHandler(getMessage("userController.password.sent"));
        }

        return new ErrorMessageHandler(getMessage("userController.mail.not.exist"));

    }


    @RequestMapping("/changepasstoserver")
    @Secured("ROLE_USER")
    @ResponseBody
    public MessageHandler changePasswordToServer(@RequestParam(required = true) String oldpass,
                                                 @RequestParam(required = true) String newpass,
                                                 @RequestParam(required = true) String repeatnewpass) {


        if(validateLength(4,20, oldpass) && validateLength(4,20, newpass) && Objects.equals(newpass, repeatnewpass)){
            User user = userService.get(getUsername());

            if(!user.getPassword().equals(oldpass)){
                return new ErrorMessageHandler(getMessage("userController.oldpassword.wrong"));
            }
            user.setPassword(newpass);
            user = userPersistence.save(user);
            if(user != null){
                return new SuccessMessageHandler(getMessage("userController.password.sent"));
            }
        }

        return new ErrorMessageHandler(getMessage("userController.password.error"));



    }

    private static BufferedImage resize(BufferedImage img, int newW, int newH) throws IOException {
        return Thumbnails.of(img).forceSize(newW, newH).asBufferedImage();
    }
}


