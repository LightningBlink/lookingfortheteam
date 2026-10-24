package com.den.controller;

import com.den.model.ext.New;
import com.den.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Denis on 30.09.2014.
 */
@Controller
@RequestMapping("/new")
public class NewController {

    @Autowired
    private NewsService newsService;

    @Value("${application.news.separator}")
    private String separator;


    @RequestMapping(value = "/getall", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<New> getAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Page<New> news;

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            news = newsService.getAll(pageable);
        } else news = newsService.getAllShow(pageable);


        if (news.hasContent()) {
            Iterator<New> newIterable = news.iterator();
            newIterable.forEachRemaining(obj -> {
                int index = obj.getDescription().indexOf(separator);
                if (index != -1) {
                    obj.setDescription(obj.getDescription().substring(0, index));
                }
            });
        }

            return news;

    }


    @RequestMapping(value = "/get/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional(readOnly = true)
    public New get(@PathVariable Long id) {
        return newsService.get(id);
    }
}
