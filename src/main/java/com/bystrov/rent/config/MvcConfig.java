package com.bystrov.rent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + uploadPath + "/");
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource mes = new ReloadableResourceBundleMessageSource();
        mes.setBasename("classpath:locale/messages");
        mes.setCacheSeconds(1);
        mes.setUseCodeAsDefaultMessage(true);
        mes.setDefaultEncoding("utf-8");
        return mes;
    }

    @Bean(name="localeResolver")
    public CookieLocaleResolver getCookieLocaleResolver() {
        CookieLocaleResolver lr = new CookieLocaleResolver();
        lr.setCookieName("cookie-locale-info");
        lr.setDefaultLocale(Locale.ENGLISH);
        lr.setCookieMaxAge(3600);

        return lr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
