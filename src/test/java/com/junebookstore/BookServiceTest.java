package com.junebookstore;

import com.junebookstore.business.entity.BookEntity;
import com.junebookstore.business.repository.BookRepository;
import com.junebookstore.business.service.BookService;
import com.junebookstore.common.model.Book;
import com.junebookstore.gateway.BookStoreGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookStoreGateway bookStoreGateway;

    @Before
    public void setUp() {
        bookService = new BookService(bookRepository, bookStoreGateway);
    }

    @Test
    public void CacheBooks_AddBooksIntoDB_SaveData2Times() {
        when(bookStoreGateway.getBooks()).thenReturn(Arrays.asList(
                new Book(1, 100, "A", "AA"),
                new Book(2, 200, "B", "BB"),
                new Book(3, 300, "C", "CC")
        ));

        when(bookStoreGateway.getBooksRecommendation()).thenReturn(Arrays.asList(
                new Book(4, 400, "D", "DD"),
                new Book(5, 500, "E", "EE")
        ));

        bookService.cacheBooks();

        verify(bookRepository, times(2)).saveAll(anyList());
    }

    @Test
    public void GetBooks_AddBooksIntoDBAndGet_BookList() {
        when(bookService.getBooks()).thenReturn(Arrays.asList(
                new BookEntity(4, 400, "D", "DD", true),
                new BookEntity(5, 500, "E", "EE", true),
                new BookEntity(1, 100, "A", "AA", false),
                new BookEntity(2, 200, "B", "BB", false),
                new BookEntity(3, 300, "C", "CC", false)
        ));

        assertEquals(5, bookService.getBooks().size());
        assertTrue(bookService.getBooks().get(0).isRecommended());
        assertTrue(bookService.getBooks().get(1).isRecommended());
    }
}
