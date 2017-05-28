package ro.anajianu.dingit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

/**
 * Created by ana on 28/05/2017.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    private static final String VIEWS_PREFIX = "/WEB-INF/views/";
    private static final String RESOURCES_LOCATION = "/resources/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resourceTemplateResolver = new SpringResourceTemplateResolver();
        resourceTemplateResolver.setPrefix(VIEWS_PREFIX);
        resourceTemplateResolver.setSuffix(".html");
        resourceTemplateResolver.setTemplateMode("HTML5");
        resourceTemplateResolver.setCharacterEncoding("UTF-8");
        resourceTemplateResolver.setCacheable(false);
        return resourceTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(new UrlTemplateResolver());
        springTemplateEngine.addTemplateResolver(templateResolver());
        return springTemplateEngine;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return  thymeleafViewResolver;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
    }
}
