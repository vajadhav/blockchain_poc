package com.mmfsl.service;

import com.mmfsl.domain.Book;

public interface BookService {
    public Book getBook(long id);

    public long addBook(Book book);

    public long getBookCount();
}
