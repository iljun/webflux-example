package com.iljun.webfluxexample.domain.book.service;

import com.iljun.webfluxexample.domain.book.domain.Book;
import com.iljun.webfluxexample.domain.book.dto.BookRequestDto;
import com.iljun.webfluxexample.domain.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Mono<Book> findBook(Long bookId) {
        return bookRepository
                .findById(bookId)
                .switchIfEmpty(this.notFound(bookId))
                .log();
    }

    public Mono<Book> createBook(BookRequestDto bookRequestDto) {
        return bookRepository.save(bookRequestDto.toEntity())
                .onErrorResume((error) -> {
                    System.out.println(error.toString());
                    return Mono.empty();
                })
                .log();
    }

    public Mono<Book> updateBook(BookRequestDto bookRequestDto) {
        return bookRepository
                .save(bookRequestDto.toEntity())
                .onErrorResume((error) -> {
                    System.out.println(error.toString());
                    return Mono.empty();
                })
                .log();
    }

    public Mono<Void> deleteBook(Long bookId) {
        return bookRepository
                .deleteById(bookId)
                .onErrorResume((error) -> {
                    System.out.println(error.toString());
                    return Mono.empty();
                })
                .log();
    }

    private <T> Mono<T> notFound(long id) {
        return Mono.error(new RuntimeException("not found id : " + id));
    }
}
