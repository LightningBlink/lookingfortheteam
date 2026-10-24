package com.den.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

import java.util.ArrayList;
import java.util.Locale;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages={"com.den.controller"})
public class 	WebModule extends WebMvcConfigurerAdapter
{

    @Bean
    public AbstractHandlerMethodAdapter requestMappingHandlerAdapter(){
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setMessageConverters(new ArrayList<HttpMessageConverter<?>>(){{
            add(new MappingJackson2HttpMessageConverter());
        }});

        requestMappingHandlerAdapter.setCustomArgumentResolvers(new ArrayList<HandlerMethodArgumentResolver>(){{
            add(new PageableHandlerMethodArgumentResolver());
        }});

        return requestMappingHandlerAdapter;
    }

	@Override
	public void addViewControllers(ViewControllerRegistry aRegistry)
	{
		aRegistry.addViewController("/").setViewName("index");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry aRegistry)
	{
		aRegistry.addResourceHandler("/fonts/**").addResourceLocations("fonts/");
		aRegistry.addResourceHandler("/sitemap.xml").addResourceLocations("sitemap.xml");
		aRegistry.addResourceHandler("/robots.txt").addResourceLocations("robots.txt");
		aRegistry.addResourceHandler("/images/**").addResourceLocations("images/");
		aRegistry.addResourceHandler("/xml/**").addResourceLocations("xml/");
		aRegistry.addResourceHandler("/css/**").addResourceLocations("css/");
		aRegistry.addResourceHandler("/js/**").addResourceLocations("js/");
		aRegistry.addResourceHandler("/WEB-INF/**").addResourceLocations("WEB-INF/");
		aRegistry.addResourceHandler("/favicon.ico").addResourceLocations("images/favicon.ico");
	}

	@Bean
	public ViewResolver viewResolver1()
	{
		UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}


	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	@Bean
	public LocaleResolver localeResolver(){
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("myLocaleCookie");
		resolver.setCookieMaxAge(2592000);
		return resolver;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
	}
}
