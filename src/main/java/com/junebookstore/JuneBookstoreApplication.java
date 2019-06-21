package com.junebookstore;

import com.junebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication()
public class JuneBookstoreApplication {
    private BookService bookService;

    public JuneBookstoreApplication(
            @Autowired BookService bookService
    ) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(JuneBookstoreApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initLoader() {
        bookService.cacheBooks();
    }
}
