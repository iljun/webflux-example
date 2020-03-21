package com.iljun.webfluxexample.domain.book.dto;

import com.iljun.webfluxexample.domain.book.domain.Book;
import lombok.Data;

@Data
public class BookRequestDto {

    private Long bookId;

    private String title;

    private String author;

    public Book toEntity() {
        return Book.ofCreate(bookId, title, author);
    }
}
