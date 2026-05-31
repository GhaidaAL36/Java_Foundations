package com.library.librarysystem.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {

    private UUID id = UUID.randomUUID();
    private Book book;
    private Member member;
    private LocalDate loanDate = LocalDate.now();
    private LocalDate returnDate;

    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
