package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.enviroment.InstanceInformationService;
import github.io.Gusta_code22.model.Exchange;

import github.io.Gusta_code22.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange-service")
public class ExchangeController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private ExchangeRepository repository;

    @GetMapping("/{amount}/{from}/{to}")
    public Exchange getExchange(@PathVariable("amount") Double amount,
                                @PathVariable("from") String from,
                                @PathVariable("to") String to){

        Exchange exchange = repository.findByFromAndTo(from, to);

        if (exchange == null){
            throw new RuntimeException("Currency Unsuported!");
        }
        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(BigDecimal.valueOf(amount));

        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment(informationService.retrieveServerPort());



        return exchange;

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
}
