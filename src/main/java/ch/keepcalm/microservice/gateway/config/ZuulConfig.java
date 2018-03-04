package ch.keepcalm.microservice.gateway.config;

import ch.keepcalm.microservice.gateway.filter.EurekaBasePathRewritingFilter;
import ch.keepcalm.microservice.gateway.filter.ZipkinUrlRewritingFilter;
import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public LocationRewriteFilter locationRewriteFilter() {
        return new LocationRewriteFilter();
    }

    @Bean
    public EurekaBasePathRewritingFilter eurekaBasePathRewritingFilter() {
        return new EurekaBasePathRewritingFilter();
    }

    @Bean
    public ZipkinUrlRewritingFilter zipkinUrlRewritingFilter() {
        return new ZipkinUrlRewritingFilter();
    }
}
