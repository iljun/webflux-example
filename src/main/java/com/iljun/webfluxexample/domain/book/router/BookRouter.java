package com.iljun.webfluxexample.domain.book.router;

import com.iljun.webfluxexample.domain.book.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BookHandler bookHandler) {
        return RouterFunctions
                .route(GET("/api/books").and(accept(MediaType.APPLICATION_JSON)), bookHandler::getBooks)
                .andRoute(GET("/api/book/{bookId}").and(accept(MediaType.APPLICATION_JSON)), bookHandler::getBook)
                .andRoute(POST("/api/book").and(accept(MediaType.APPLICATION_JSON)), bookHandler::createBook)
                .andRoute(PATCH("/api/book").and(accept(MediaType.APPLICATION_JSON)), bookHandler::updateBook)
                .andRoute(DELETE("/api/book/{bookId}").and(accept(MediaType.APPLICATION_CBOR)), bookHandler::deleteBook);
    }
}
