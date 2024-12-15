package digital.paisley.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("PRODUCER-SERVICE", r -> r.path("/api/v1/producers/**")
                        .uri("lb://PRODUCER-SERVICE"))
                .route("PRODUCT-SERVICE", r -> r.path("/api/v1/products/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .route("CONSUMER-SERVICE", r -> r.path("/api/v1/consumers/**")
                        .uri("lb://CONSUMER-SERVICE"))
                .build();
    }

}
