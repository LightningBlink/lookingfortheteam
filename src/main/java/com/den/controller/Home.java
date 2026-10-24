package com.den.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {


    @RequestMapping(value = {
            "/news",
            "/posts",
            "/mymessages",
            "/looking",
            "/looking/*/*",
            "/profile",
            "/login",
            "/item/*",
            "/profile/*",
            "/team/*",
            "/about",
            "/confirmation/*"
    })
    public String index() {
        return "forward:/index";
    }


    @RequestMapping("/index")
    public String main() {
        return "index";
    }


    @RequestMapping("/error.html")
    public ModelAndView error(@RequestParam(required = false) String message) {
        return new ModelAndView("error", "message", message);
    }


    @RequestMapping("/profile.html")
    public String profile() {
        return "profile/main";
    }



    @RequestMapping("/news.html")
    public String news() {
        return "news/news";

    }

    @RequestMapping("/template1.html")
    public String template1() {
        return "posts/template1";

    }

    @RequestMapping("/template2.html")
    public String template2() {
        return "posts/template2";

    }
    @RequestMapping("/template3.html")
    public String template3() {
        return "posts/template3";

    }
    @RequestMapping("/template5.html")
    public String template5() {
        return "posts/template5";

    }
    @RequestMapping("/template4.html")
    public String template4() {
        return "posts/template4";

    }
    @RequestMapping("/posts.html")
    public String posts() {
        return "posts/posts";

    }



    @RequestMapping("/mymessages.html")
    public String messages() {
        return "messages/main";
    }


    @RequestMapping("/looking.html")
    public String looking() {
        return "teams/main";
    }


    @RequestMapping("/new.html")
    public String newPage() {
        return "news/new";

    }

    @RequestMapping("/team.html")
    public String getTeam() {
        return "teams/team";

    }


    @RequestMapping("/looking/createteam.html")
    public String createTeam() {
        return "teams/createteam";

    }


    @RequestMapping("/about.html")
    public String about() {
        return "about";

    }


    @RequestMapping("/looking/teams.html")
    public String lookingTeam() {
        return "teams/teams";
    }



    @RequestMapping("/looking/teammates.html")
    public String lookingTeammate() {
        return "teams/teammates";
    }


    @RequestMapping("/user_found.html")
    public String userFound() {
        return "profile/found";
    }


    @RequestMapping("/user_not_found.html")
    public String userNotFound() {
        return "profile/not_found";
    }

    @RequestMapping("/games_not_found.html")
    public String gamesNotFound() {
        return "teams/games_not_found";
    }


    @RequestMapping("/games_found.html")
    public String gamesFound() {
        return "teams/games_found";
    }


    @RequestMapping("/team_exist.html")
    public String teamExist() {
        return "teams/team_exist";
    }


    @RequestMapping("/team_not_exist.html")
    public String teamNotExist() {
        return "teams/team_not_exist";
    }


    @RequestMapping("/google31cafd8c652ea2a2.html")
    public String google31cafd8c652ea2a2() {
        return "google31cafd8c652ea2a2";
    }


}