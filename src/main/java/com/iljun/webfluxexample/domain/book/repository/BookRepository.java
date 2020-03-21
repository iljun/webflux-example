package com.iljun.webfluxexample.domain.book.repository;

import com.iljun.webfluxexample.domain.book.domain.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

}
