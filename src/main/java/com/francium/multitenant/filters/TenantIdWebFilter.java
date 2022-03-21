
package com.francium.multitenant.filters;

import com.francium.multitenant.contant.AppConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TenantIdWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

        var headerValues = serverWebExchange.getRequest().getHeaders().get(AppConstants.TenantId);

        if(headerValues == null || headerValues.size() == 0) {
            return webFilterChain.filter(serverWebExchange);
        }

        String tenantKey = headerValues.get(0);
        return webFilterChain
                .filter(serverWebExchange)
                .contextWrite(ctx -> ctx.put(AppConstants.TenantKey, tenantKey));
    }
}