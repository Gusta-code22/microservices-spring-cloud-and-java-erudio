package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.dto.Exchange;
import github.io.Gusta_code22.enviroment.InstanceInformationService;
import github.io.Gusta_code22.model.Book;
import github.io.Gusta_code22.proxy.ExchangeProxy;
import github.io.Gusta_code22.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository repository;

    @Autowired
    private ExchangeProxy proxy;


    @GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency){

        String port = informationService.retrieveServerPort();


        var book = repository.findById(id).orElseThrow();



        Exchange exchange = proxy.getExchange(
                book.getPrice(),
                "USD",
                currency);


        book.setEnviroment("BOOK PORT: " + port + " EXCHANGE PORT: " + exchange.getEnvironment());
        book.setPrice(exchange.getConvertedValue());
        book.setCurrency(currency);
        return book;
    }

//    @GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
//public Book findBook(
//        @PathVariable("id") Long id,
//        @PathVariable("currency") String currency){
//
//    String port = informationService.retrieveServerPort();
//
//
//    var book = repository.findById(id).orElseThrow();
//
//    HashMap<String, String> params = new HashMap<>();
//    params.put("amount", book.getPrice().toString());
//    params.put("from", "USD");
//    params.put("to", currency);
//
//    var response = new RestTemplate()
//            .getForEntity("http://localhost:8000/exchange-service/{amount}/{from}/{to}",
//                    Exchange.class, params);
//
//    Exchange exchange;
//    exchange = response.getBody();
//
//    book.setEnviroment(port);
//    book.setPrice(exchange.getConvertedValue());
//    book.setCurrency(currency);
//    return book;
//}
}

//@GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
//public Book findBook(
//        @PathVariable("id") Long id,
//        @PathVariable("currency") String currency){
//
//    String port = informationService.retrieveServerPort();
//
//
//    var book = repository.findById(id).orElseThrow();
//
//    HashMap<String, String> params = new HashMap<>();
//    params.put("amount", book.getPrice().toString());
//    params.put("from", "USD");
//    params.put("to", currency);
//
//    var response = new RestTemplate()
//            .getForEntity("http://localhost:8000/exchange-service/{amount}/{from}/{to}",
//                    Exchange.class, params);
//
//    Exchange exchange;
//    exchange = response.getBody();
//
//    book.setEnviroment(port);
//    book.setPrice(exchange.getConvertedValue());
//    book.setCurrency(currency);
//    return book;
//}


//*@GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
//public Book findBook(
//        @PathVariable("id") Long id,
//        @PathVariable("currency") String currency){
//
//    String port = informationService.retrieveServerPort();
//
//
//    var book = repository.findById(id).orElseThrow();
//
//    book.setEnviroment(port);
//    book.setCurrency(currency);
//    return book;
//}

// MOCKS
//@GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
//public Book findBook(
//        @PathVariable("id") Long id,
//        @PathVariable("currency") String currency){
//
//    String port = informationService.retrieveServerPort();
//
//    return new Book(
//            1L,
//            "Lobo branco",
//            "The Wicther",
//            new Date(),
//            15.8,
//            "BRL",
//            port
//    );
//}