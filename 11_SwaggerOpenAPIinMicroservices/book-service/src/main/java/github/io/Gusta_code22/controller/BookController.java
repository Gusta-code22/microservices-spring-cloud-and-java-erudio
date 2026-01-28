package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.dto.Exchange;
import github.io.Gusta_code22.enviroment.InstanceInformationService;
import github.io.Gusta_code22.model.Book;
import github.io.Gusta_code22.proxy.ExchangeProxy;
import github.io.Gusta_code22.repository.BookRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book Service API", description = "Book management endpoints with currency conversion")
@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository repository;

    @Autowired
    private ExchangeProxy proxy;

    @Operation(
            summary = "Find book by ID and convert price to target currency",
            description = "Retrieves book from database and converts USD price to target currency using Exchange service (Circuit Breaker + Retry)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Book found and price converted successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404",
                    description = "Book not found"),
            @ApiResponse(responseCode = "503",
                    description = "Exchange service unavailable (fallback applied)")
    })
    @GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = "exchange", fallbackMethod = "fallbackExchange")
    @Retry(name = "exchange")
    public Book findBook(
            @Parameter(description = "Book ID", required = true, example = "1")
            @PathVariable("id") Long id,

            @Parameter(description = "Target currency (USD, EUR, BRL, GBP...)",
                    required = true, example = "BRL")
            @PathVariable("currency") String currency){

        String port = informationService.retrieveServerPort();
        var book = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Book not found: " + id));

        Exchange exchange = proxy.getExchange(book.getPrice(), "USD", currency);

        book.setEnviroment("BOOK PORT: " + port + " EXCHANGE PORT: " + exchange.getEnvironment());
        book.setPrice(exchange.getConvertedValue());
        book.setCurrency(currency);
        return book;
    }

    // Circuit Breaker fallback
    Exchange fallbackExchange(Double price, String from, String to, Exception ex) {
        Exchange fallback = new Exchange();
        fallback.setConvertedValue(price);
        fallback.setEnvironment("EXCHANGE SERVICE UNAVAILABLE - FALLBACK");
        return fallback;
    }
}
