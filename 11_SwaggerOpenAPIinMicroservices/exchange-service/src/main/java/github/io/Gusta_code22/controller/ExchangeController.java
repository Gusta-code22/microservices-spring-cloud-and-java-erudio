package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.enviroment.InstanceInformationService;
import github.io.Gusta_code22.model.Exchange;
import github.io.Gusta_code22.repository.ExchangeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "Exchange Service API", description = "Currency exchange rate endpoints")
@RestController
@RequestMapping("/exchange-service")
public class ExchangeController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private ExchangeRepository repository;

    @Operation(
            summary = "Convert amount from source currency to target currency",
            description = "Fetches exchange rate from database and converts amount using conversion factor"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Conversion successful",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Exchange.class))),
            @ApiResponse(responseCode = "500",
                    description = "Unsupported currency pair")
    })
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @Parameter(description = "Amount to convert", required = true, example = "10.0")
            @PathVariable("amount") Double amount,

            @Parameter(description = "Source currency (USD)",
                    required = true, example = "USD")
            @PathVariable("from") String from,

            @Parameter(description = "Target currency (BRL, EUR...)",
                    required = true, example = "BRL")
            @PathVariable("to") String to){

        Exchange exchange = repository.findByFromAndTo(from, to);

        if (exchange == null){
            throw new RuntimeException("Unsupported currency pair: " + from + " → " + to);
        }

        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(BigDecimal.valueOf(amount));

        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment(informationService.retrieveServerPort());

        return exchange;
    }
}
// //**
//    @GetMapping(value = "/{amount}/{from}/{to}")
//    public Exchange getExchange(@PathVariable("amount") BigDecimal amount,
//                                @PathVariable("from") String from,
//                                @PathVariable("to") String to){
//
//        return new Exchange(1L,
//                from,
//                to,
//                BigDecimal.ONE,
//                BigDecimal.ONE,
//                "PORT " + informationService.retrieveServerPort());
//
//    }
//    //**