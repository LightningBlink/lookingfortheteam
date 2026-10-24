package com.den.controller;

import com.den.handler.ErrorMessageHandler;
import com.den.handler.MessageHandler;
import com.den.handler.SuccessMessageHandler;
import com.den.model.Order;
import com.den.model.UserTeam;
import com.den.model.enumes.POST;
import com.den.model.ext.Message;
import com.den.model.ext.Team;
import com.den.model.ext.User;
import com.den.model.id.UserTeamID;
import com.den.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Denis on 06.07.2015.
 */
@Controller
@Secured("ROLE_USER")
public class MessageController extends BaseController {


    @Autowired
    private MessageService messageService;

    @Autowired
    private GameService gameService;


    @Autowired
    private UserService userService;


    @Autowired
    private StoreService storeService;

    @Autowired
    private TeamService teamService;


    private ExecutorService service = Executors.newCachedThreadPool();


    @RequestMapping("/sendmessage")
    @ResponseBody
    public MessageHandler sendMessage(@RequestParam String userFrom, @RequestParam String userTo, @RequestParam String message) {

        ifNullException(userFrom, userTo, message);

        Message message1 = messageService.save(new Message(userFrom, userService.get(userTo), message));

        if (message1 != null) {
            storeService.registerOrder(new Order(userTo));
            return new SuccessMessageHandler(getMessage("messageController.message.sent"));
        }

        return new ErrorMessageHandler(getMessage("messageController.somethinghappenwrong"));
    }


    @RequestMapping("/message/accept/{id}")
    @ResponseBody
    public MessageHandler acceptInvite(@PathVariable Long id) {

        Message message = messageService.get(id);

        ifNullException(message);

        final User user = userService.get(getUsername());
        if (message.getUser().equals(user)) {


            if (message.getDate().compareTo(LocalDateTime.now().minusDays(1)) == -1) {

                messageService.remove(message.getId());

                return new ErrorMessageHandler(getMessage("messageController.24hourinvication"));

            }

            if (user.getUserTeams().stream().anyMatch(ut -> ut.getGame().getName().equals(message.getGame()))) {
                return new ErrorMessageHandler(getMessage("messageController.pleaseleaveteamiuwannajoin"));
            }


            Team team = teamService.get(message.getTeamId());
            UserTeam ut = new UserTeam();
            ut.setPost(POST.PLAYER);

            UserTeamID userTeamID = new UserTeamID();
            userTeamID.setTeam(team);
            userTeamID.setUser(user);
            userTeamID.setGame(gameService.getByName(message.getGame()));
            ut.setPk(userTeamID);
            team.getUserTeams().add(ut);

            if (teamService.save(team) == null) {
                return new ErrorMessageHandler(getMessage("messageController.somethinghappenwrong"));
            }

            messageService.remove(message.getId());

            MessageHandler mh = new SuccessMessageHandler(getMessage("messageController.ujointotheteam"));
            mh.setJson(team);
            return mh;

        } else {
            return new ErrorMessageHandler(getMessage("messageController.ucantjointotheteam"));
        }


    }

    @RequestMapping("/message/decline/{id}")
    @ResponseBody
    public MessageHandler decline(@PathVariable("id") Long id) {


        Message message = messageService.get(id);

        if (message != null) {
            message.setIsShow(false);
            messageService.save(message);
            return new SuccessMessageHandler(StringUtils.EMPTY);
        }
        return new ErrorMessageHandler(getMessage("messageController.somethinghappenwrong"));

    }


    @RequestMapping("/message/invite/{username}")
    @ResponseBody
    public MessageHandler sendInvite(@PathVariable String username, @RequestParam("game") String game) {

        final User user = userService.get(getUsername());

        ifNullException(user);

        Team team = null;
        boolean isLeader = false;
        for (UserTeam ut : user.getUserTeams()) {
            if (ut.getGame().getName().equals(game)) {
                team = ut.getTeam();
                if (ut.getPost().equals(POST.LEADER)) {
                    isLeader = true;
                }
            }
        }

        ifNullException(team);

        Message message1;
        String whoGet;
        if (isLeader) {
            message1 = new Message(user.getUsername(), userService.get(username), createLink("/profile/" + user.getUsername(), user.getUsername(), true) + getMessage("messageController.invites")  + " " + game  + "   " + createLink("/team/" + team.getId(), team.getName(), false), true);
            if (messageService.notExist(message1)) {

                return new ErrorMessageHandler(getMessage("messageController.submittedrequest"));
            }
            whoGet = username;


        } else {
            User leader = null;
            ifNullException(team.getUserTeams());
            for (UserTeam ut : team.getUserTeams()) {
                if (ut.getGame().getName().equals(game) && ut.getPost().equals(POST.LEADER)) {
                    leader = ut.getUser();
                }
            }

            message1 = new Message(user.getUsername(), leader, getMessage("messageController.user") + createLink("/profile/" + user.getUsername(), user.getUsername(), true) + getMessage("messageController.recommends") + "  " + createLink("/profile/" + username, username, true) + getMessage("messageController.invitestoclan") + " " + game);
            if (messageService.notExist(message1)) {

                return new ErrorMessageHandler(getMessage("messageController.submittedduringtheday"));
            }
            whoGet = leader.getUsername();
        }


        storeService.registerOrder(new Order(whoGet));

        if (message1.getInvitation()) {
            message1.setGame(game);
            message1.setTeamId(team.getId());
        }

        messageService.save(message1);


        return new SuccessMessageHandler(getMessage("messageController.message.sent"));


    }

    @RequestMapping(value = "/message/accession/{id}")
    @ResponseBody
    public MessageHandler sendAccession(@PathVariable Long id, @RequestParam(required = true) String game) {

        Team team = teamService.get(id);

        ifNullException(team);

        User leader = null;

        for (UserTeam ut : team.getUserTeams()) {
            if (POST.LEADER.equals(ut.getPost())) {
                leader = ut.getUser();
                break;
            }
        }

        Message message = new Message(getUsername(), leader, getMessage("messageController.user") + createLink("/profile/" + getUsername(), getUsername(), true) + getMessage("messageController.wanttojoin") + " " + game + createLink("/team/" + team.getId(), team.getName(), false) + "</a>");

        if (messageService.notExist(message)) {

            return new ErrorMessageHandler(getMessage("messageController.submittedduringtheday"));
        }

        Message message1 = messageService.save(message);

        if (message1 != null) {
            assert leader != null;
            storeService.registerOrder(new Order(leader.getUsername()));
            return new SuccessMessageHandler(getMessage("messageController.requestofaccesion"));
        }

        return new ErrorMessageHandler(getMessage("messageController.somethinghappenwrong"));
    }

    @RequestMapping("/firstcheckmessages")
    @ResponseBody
    public Set<Message> firstCheckMessages() {
        final String username = getUsername();
        User user = userService.get(username);
        return messageService.getUnreadMessages(user);
    }

    @RequestMapping(value = "/messages")
    public void messages(HttpServletResponse response, HttpServletRequest request) throws IOException {
        final String username = getUsername();
        User user = userService.get(username);
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        final PrintWriter writer = response.getWriter();
        final int port = request.getRemotePort();

        final AsyncContext ac = request.startAsync();
        ac.setTimeout(300000);


        service.submit(() -> {
            for (int i = 0; i < 50; i++) {
                Optional<Order> order = storeService.getReceivedOrder(username);
                if (order.isPresent() && !order.get().getPorts().contains(port)) {

                    Set<Message> messages = messageService.getUnreadMessages(user);

                    try {
                        writer.write("data: " + objectMapper.writeValueAsString(messages) + "\n\n");
                        storeService.checkByPort(username, port);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    } finally {
                        writer.flush();
                    }

                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            ac.complete();
        });
    }


    @RequestMapping("/getmessages")
    @ResponseBody
    public Page<Message> getLookingPlayers(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {

        return messageService.findByUserDistinct(userService.get(getUsername()), pageable);

    }

    @RequestMapping("/correspondence")
    @ResponseBody
    public List<Message> getLookingPlayers(@RequestParam String userName) {

        User user = userService.get(getUsername());
        return messageService.findByUserAndUserFrom(user, userName, user.getId(), userService.get(userName), user.getUsername(), user.getId());

    }


    @RequestMapping("/deletemessage")
    @ResponseBody
    public MessageHandler deleteMessage(@RequestParam(required = true) Long id) {
        Message message = messageService.get(id);
        final User user = userService.get(getUsername());
        if (message.getUser() != user && !message.getUserFrom().equals(user.getUsername())) {
            return new ErrorMessageHandler(getMessage("messageController.uhavenorights"));
        }

        Long idDeleted = message.getDeleted();

        if (idDeleted == 0) {

            messageService.deleteByUserMessages(user.getId(), message.getUser(), message.getUserFrom(),
                    userService.get(message.getUserFrom()), message.getUser().getUsername());
        } else {

            messageService.deleteMessages(message.getUser(), message.getUserFrom(),
                    userService.get(message.getUserFrom()), message.getUser().getUsername());
        }


        return new SuccessMessageHandler(getMessage("messageController.messagedeleted"));

    }

    @RequestMapping("/messageseen")
    @ResponseBody
    public void messageHasWasSeen(@RequestParam(required = true) Long id) {

        Message message = messageService.get(id);

        if (message != null && message.getUser().getUsername().equals(getUsername())) {

            messageService.setMessagesWereSeen(message.getUserFrom(), message.getUser());
        }
    }
//todo html should building by js, worst method

    private String createLink(String link, String text, boolean isUser) {
        StringBuilder buffer = new StringBuilder();
        if (isUser) {
            buffer.append("<img height=\"20\" src=\"/user/avatarimage?name=").append(text).append("\">");
        }

        else

        {
            buffer.append("<img height=\"20\" src=\"/user/avatarimage?name=").append(text).append("&isTeam=true").append("\">");

        }
        return buffer.append(" <a href='").append(link).append("'>").append(text).append("</a> ").toString();

    }
}
