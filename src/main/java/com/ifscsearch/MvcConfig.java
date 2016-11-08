package com.ifscsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
		MustacheViewResolver mvr = new MustacheViewResolver();
		mvr.setPrefix("/views/");
		mvr.setSuffix(".html");
		mvr.setCache(false);
		mvr.setContentType("text/html;charset=utf-8");

		MustacheTemplateLoader mtl = new MustacheTemplateLoader();
		mtl.setResourceLoader(resourceLoader);
		mvr.setTemplateLoader(mtl);
		return mvr;
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
