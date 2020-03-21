package com.iljun.webfluxexample.domain.book.handler;

import com.iljun.webfluxexample.domain.book.dto.BookRequestDto;
import com.iljun.webfluxexample.domain.book.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;
import java.net.URI;

@Component
public class BookHandler {
    private final BookService bookService;
    private final Validator validator;
    public BookHandler(final BookService bookService,
                       final Validator validator) {
        this.bookService = bookService;
        this.validator = validator;
    }

    public Mono<ServerResponse> getBooks(ServerRequest serverRequest) {
        return bookService
                .findBooks()
                .collectList()
                .flatMap(result -> ServerResponse
                                    .ok()
                                    .bodyValue(result)
                );
    }

    public Mono<ServerResponse> getBook(ServerRequest serverRequest) {
        return bookService
                .findBook(Long.parseLong(serverRequest.pathVariable("bookId")))
                .flatMap(result -> ServerResponse.ok().bodyValue(result));
    }

    public Mono<ServerResponse> createBook(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(BookRequestDto.class)
                .flatMap(body ->
                    bookService
                            .createBook(body)
                            .flatMap(result ->
                                ServerResponse
                                        .created(URI.create("/book/" + result.getBookId()))
                                        .build()
                            )
                );
    }

    public Mono<ServerResponse> updateBook(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(BookRequestDto.class)
                .flatMap(body ->
                        bookService
                                .updateBook(body)
                                .flatMap(result ->
                                        ServerResponse
                                                .created(URI.create("/book/" + result.getBookId()))
                                                .build()
                                )
                );
    }

    public Mono<ServerResponse> deleteBook(ServerRequest request) {
        return bookService
                .deleteBook(Long.parseLong(request.pathVariable("bookId")))
                .then(ServerResponse.accepted().build());
    }
}
