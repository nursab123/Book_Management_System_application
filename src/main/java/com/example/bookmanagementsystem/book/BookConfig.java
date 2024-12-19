package com.example.bookmanagementsystem.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            BookRepository repository) {
        return args -> {
            Book Pride_and_Prejudice = new Book(
                    "Pride and Prejudice",
                    "Jane Austen",
                    "Romance novel",
                    LocalDate.of(1813, JANUARY, 28),
                    4.3
            );

            Book Harry_Potter = new Book(
                    "Harry Potter and the Philosopher's Stone",
                    "J.K.Rowling",
                    "Fantasy",
                    LocalDate.of(1997, JUNE, 26),
                    4.5
            );

            repository.saveAll(
                    List.of(Pride_and_Prejudice, Harry_Potter)
            );
        };
    }
}
