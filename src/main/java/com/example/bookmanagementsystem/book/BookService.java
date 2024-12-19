package com.example.bookmanagementsystem.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book with ID " + bookId + " not found"));
    }

    public Book addNewBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("Book with ID " + bookId + " not found"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPublishedDate(updatedBook.getPublishedDate());
        existingBook.setRating(updatedBook.getRating());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException(
                    "book with id" + bookId + " does not exists");
        }
        bookRepository.deleteById(bookId);
    }

    public List<Book> filterBooksByGenreAndRating(String genre, Double minimumRating) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .filter(book -> book.getRating() >= minimumRating)
                .collect(Collectors.toList());
    }

//    public List<Book> filterBooksByGenre(String genre) {
//        return bookRepository.findAll().stream()
//                .filter(book -> book.getGenre() == genre)
//                .collect(Collectors.toList());
//    }
//
//    public List<Book> filterBooksByRating(Double minimumRating) {
//        return bookRepository.findAll().stream()
//                .filter(book -> book.getRating() >= minimumRating)
//                .collect(Collectors.toList());
//    }


}
