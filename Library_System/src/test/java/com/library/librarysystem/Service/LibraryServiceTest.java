package com.library.librarysystem.Service;

import com.library.librarysystem.Model.Book;
import com.library.librarysystem.Model.Loan;
import com.library.librarysystem.Model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService libraryService;

    private Book availableBook;
    private Book unavailableBook;
    private Member member;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryService();
        availableBook = new Book("book 1", "ghaida", true);
        unavailableBook = new Book("book 2", "ghaida", false);
        member = new Member("Ghaida", "ghaida@test.com");
    }

    @Test
    void getAllBooks() {
        libraryService.addBook(availableBook);

        List<Book> books = libraryService.getAllBooks();

        assertNotNull(books);
        assertEquals(books.size(), 1);
        assertEquals(availableBook.getTitle(), books.get(0).getTitle());
    }

    @Test
    void addBook() {
        libraryService.addBook(availableBook);

        List<Book> books = libraryService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("book 1", books.get(0).getTitle());
    }

    @Test
    void removeBook() {
        libraryService.addBook(availableBook);

        boolean result = libraryService.removeBook("book 1");

        assertTrue(result);
        assertTrue(libraryService.getAllBooks().isEmpty());
    }

    @Test
    void borrowBook() {
        Loan loan = libraryService.borrowBook(availableBook, member);
        assertNotNull(loan);
    }

    @Test
    void returnBook() {
        Loan loan = libraryService.borrowBook(availableBook, member);
        libraryService.returnBook(loan);

        assertTrue(availableBook.isAvailable());
    }
}