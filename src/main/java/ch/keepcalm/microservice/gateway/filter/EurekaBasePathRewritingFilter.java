package ch.keepcalm.microservice.gateway.filter;


import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

/**
 * Zuul filter to rewrite Eureka URL Base Path.
 */
@Slf4j
public class EurekaBasePathRewritingFilter extends SendResponseFilter {

    @Value("${gateway.urlRewriteFilter.eureka:eureka-server}")
    private String gatewayUrlRewriteFilterUrl;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    /**
     * Filter requests to micro-services Swagger docs.
     */
    @Override
    public boolean shouldFilter() {
        return gatewayUrlRewriteFilterUrl.equals(RequestContext.getCurrentContext().get("proxy"));
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();

        if (!context.getResponseGZipped()) {
            context.getResponse().setCharacterEncoding("UTF-8");
        }

        String rewrittenResponse = rewriteBasePath(context);
        context.setResponseBody(rewrittenResponse);
        return null;
    }

    @SuppressWarnings("unchecked")
    private String rewriteBasePath(RequestContext context) {
        InputStream responseDataStream = context.getResponseDataStream();
        try {
            if (context.getResponseGZipped()) {
                responseDataStream = new GZIPInputStream(context.getResponseDataStream());
            }
            String response = StreamUtils.copyToString(responseDataStream, StandardCharsets.UTF_8);
            if (response != null) {
                return response.replace("<base href=\"", "<base href=\".");
            }
        } catch (IOException e) {
            log.error("Eureka filter error", e);
        }
        return null;
    }
}
