package nl.abnamro.mondrian.gas.service;

import nl.abnamro.mondrian.gas.model.AllocatedTradeConfirmation;
import nl.abnamro.mondrian.gas.model.TradeConfirmation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GasService {

    public Mono<AllocatedTradeConfirmation> allocateTradeConfirmation(TradeConfirmation aTradeConfirmation) {
        return Mono.just(aTradeConfirmation)
                .flatMap(tradeConfirmation ->
                        Mono.just(new AllocatedTradeConfirmation(tradeConfirmation.getExternalAccountId())));
    }
}
