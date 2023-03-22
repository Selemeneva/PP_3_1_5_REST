package ru.kata.spring.boot_bootstrap.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kata.spring.boot_bootstrap.demo.util.RoleConverter;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new RoleConverter());
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("/login");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/admins").setViewName("admin");
    }

}
