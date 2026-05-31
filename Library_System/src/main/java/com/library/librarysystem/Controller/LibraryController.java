package com.library.librarysystem.Controller;

import com.library.librarysystem.Model.Book;
import com.library.librarysystem.Model.BorrowRequest;
import com.library.librarysystem.Model.Loan;
import com.library.librarysystem.Model.Member;
import com.library.librarysystem.Service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = libraryService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        libraryService.addBook(book);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> remove(@PathVariable String title) {
        boolean removed = libraryService.removeBook(title);
        if (!removed) return ResponseEntity.notFound().build(); // 404
        return ResponseEntity.ok().build();
    }

    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrow(@RequestBody BorrowRequest request) {
        Loan loan = libraryService.borrowBook(request.getBook(), request.getMember());
        if (loan == null) return ResponseEntity.badRequest().build(); // 400
        return ResponseEntity.ok(loan);
    }

    @PutMapping
    public ResponseEntity<Void> returnBook(@RequestBody Loan loan) {
        libraryService.returnBook(loan);
        return ResponseEntity.ok().build();
    }
}
