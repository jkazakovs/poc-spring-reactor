package nl.abnamro.mondrian.gas.route;

import nl.abnamro.mondrian.gas.route.handler.GasHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class GasRoute {

    @Bean
    public RouterFunction<ServerResponse> route(GasHandler gasHandler) {
        return RouterFunctions
                .route(GET("/accept").and(accept(MediaType.TEXT_PLAIN)), gasHandler::allocateTradeConfirmation);
    }
}
