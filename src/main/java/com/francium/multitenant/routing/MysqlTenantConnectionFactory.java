package com.francium.multitenant.routing;

import com.francium.multitenant.contant.AppConstants;
import io.r2dbc.spi.ConnectionFactoryMetadata;
import org.springframework.r2dbc.connection.lookup.AbstractRoutingConnectionFactory;
import reactor.core.publisher.Mono;

import static com.francium.multitenant.utils.ReactorUtils.errorIfEmpty;


public class MysqlTenantConnectionFactory extends AbstractRoutingConnectionFactory {

    static final class MysqlConnectionFactoryMetadata implements ConnectionFactoryMetadata {

        static final MysqlConnectionFactoryMetadata INSTANCE = new MysqlConnectionFactoryMetadata();
        public static final String NAME = "MYSQL";

        private MysqlConnectionFactoryMetadata() {
        }

        @Override
        public String getName() {
            return NAME;
        }
    }

    @Override
    protected Mono<Object> determineCurrentLookupKey() {
        return Mono
                .deferContextual(Mono::just)
                .filter(it -> it.hasKey(AppConstants.TenantKey))
                .map(it -> it.get(AppConstants.TenantKey))
                .transform(m -> errorIfEmpty(m, () -> new RuntimeException(String.format("ContextView does not contain the Lookup Key '%s'", AppConstants.TenantKey))));
    }

    @Override
    public ConnectionFactoryMetadata getMetadata() {
        return MysqlConnectionFactoryMetadata.INSTANCE;
    }
}
