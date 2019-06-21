package com.junebookstore.common.transform;

import com.junebookstore.business.entity.BookEntity;
import com.junebookstore.common.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookTransform {
    public static List<BookEntity> toEntity(List<Book> bookList, boolean isRecommendation) {
        return bookList.stream().map(book -> new BookEntity(
                book.getId(),
                book.getPrice(),
                book.getBookName(),
                book.getAuthorName(),
                isRecommendation
        )).collect(Collectors.toList());
    }
}
