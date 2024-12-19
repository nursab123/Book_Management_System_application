package com.example.bookmanagementsystem.book;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {return bookService.getBooks();}

    @GetMapping(path = "{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
   public Book addNewBook(@RequestBody Book book) {
        return bookService.addNewBook(book);
   }

   @PutMapping(path = "{bookId}")
   public Book updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        return bookService.updateBook(bookId, updatedBook);
   }

   @DeleteMapping(path = "{bookId}")
   public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
   }

//   @GetMapping(path = "{genre}")
//   public List<Book> filterBooksByGenre(@PathVariable String genre) {
//        return bookService.filterBooksByGenre(genre);
//   }
//
//   @GetMapping(path = "{minimumRating}")
//   public List<Book> filterBooksByRating(@PathVariable Double minimumRating) {
//        return bookService.filterBooksByRating(minimumRating);
//   }

    @GetMapping("filter")
    public List<Book> filterBooksByGenreAndRating(
            @RequestParam String genre,
            @RequestParam Double minimumRating) {
        return bookService.filterBooksByGenreAndRating(genre, minimumRating);
    }
}
