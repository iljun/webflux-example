package com.iljun.webfluxexample.domain.book.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("book")
public class Book {

    @Id
    @Column("book_id")
    private Long bookId;

    @Column("title")
    private String title;

    @Column("author")
    private String author;

    public static Book ofCreate(Long bookId, String title, String author) {
        Book book = new Book();
        book.bookId = bookId;
        book.title = title;
        book.author = author;
        return book;
    }
}
