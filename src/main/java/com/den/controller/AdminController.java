package com.den.controller;

import com.den.handler.MessageHandler;
import com.den.handler.SuccessMessageHandler;
import com.den.model.ext.New;
import com.den.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Denis on 13.03.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/addnew.html", method = RequestMethod.GET)
    public String main(){
        return "news/add_new";
    }

    @RequestMapping(value = "/savenew" ,method = RequestMethod.POST)
    public String saveNew(@RequestParam(value = "editor1") String newContent, @RequestParam String header, @RequestParam(required = false) Long id){

        New entity = new New(header, newContent, SecurityContextHolder.getContext().getAuthentication().getName());
        if(id == null) {
            newsService.save(entity);
        }
        else
        {
            entity.setId(id);
            newsService.save(entity);
        }

        return "redirect:/news";
    }

    @RequestMapping(value = "/deletenew/{id}" ,method = RequestMethod.DELETE)
    @ResponseBody
    public MessageHandler deleteNew(@PathVariable Long id){

        newsService.remove(id);
        return new SuccessMessageHandler();
    }


    @RequestMapping(value = "/shownew/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public MessageHandler showNew(@PathVariable Long id){

        final  New newObj = newsService.get(id);

        newObj.setShow(true);

        newsService.save(newObj);

        return new SuccessMessageHandler();
    }




}
