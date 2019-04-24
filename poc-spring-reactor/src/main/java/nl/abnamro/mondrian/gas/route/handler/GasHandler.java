package nl.abnamro.mondrian.gas.route.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.abnamro.mondrian.gas.model.TradeConfirmation;
import nl.abnamro.mondrian.gas.route.binding.JsonWriter;
import nl.abnamro.mondrian.gas.route.handler.exception.MissingQueryParameterException;
import nl.abnamro.mondrian.gas.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class GasHandler {

    @Autowired
    private GasService gasService;

    public Mono<ServerResponse> allocateTradeConfirmation(ServerRequest request) {
        return Mono.just(request.queryParam("externalAccountId"))
                .flatMap(externalAccountId ->
                        Mono.just(new TradeConfirmation(
                                externalAccountId.orElseThrow(() -> new MissingQueryParameterException("externalAccountId")))))
                .flatMap(gasService::allocateTradeConfirmation)
                .flatMap(JsonWriter::write)
                .flatMap(result ->
                        ServerResponse.ok()
                                .body(Mono.just(result), String.class))
                .onErrorResume(MissingQueryParameterException.class,
                        e -> ServerResponse
                                .status(HttpStatus.BAD_REQUEST)
                                .body(Mono.just(e.getErrorMessageAsJson()), String.class))
                .onErrorResume(JsonProcessingException.class, e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                .subscribeOn(Schedulers.elastic());
    }
}
