package ch.keepcalm.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Created by marcelwidmer on 21/03/16.
 */
@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Content negotiation are mechanisms defined as a part of HTTP that make it possible to serve
     * different versions of a document (or more generally, representations of a resource) at the same URI
     *
     * @param configurer
     */
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaTypes.HAL_JSON);
    }

    /**
     * Global CORS configuration.
     * <p>
     * Cross-origin resource sharing (CORS) is a mechanism that allows restricted resource (e.g. fonts)
     * on a web page to be requested from another domain outside the domain from which the resource originated.
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }



}