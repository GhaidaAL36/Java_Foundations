package com.library.librarysystem.Service;

import com.library.librarysystem.Model.Book;
import com.library.librarysystem.Model.Loan;
import com.library.librarysystem.Model.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(String title) {
        return books.removeIf(b -> b.getTitle().equals(title));
    }

    public Loan borrowBook(Book book, Member member) {
        if (!book.isAvailable()) {
            System.out.println("Book is not available");
            return null;
        }
        book.setAvailable(false);
        Loan loan = new Loan(book, member);
        loans.add(loan);
        return loan;
    }


    public void returnBook(Loan loan) {
        loan.getBook().setAvailable(true);
        loan.setReturnDate(LocalDate.now());
    }
}

