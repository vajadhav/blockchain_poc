package com.mmfsl.controller;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mmfsl.domain.Book;
import com.mmfsl.domain.ObjectWithId;
import com.mmfsl.service.BookService;

@RestController
@RequestMapping("/book")
public class BookRestController {
	private BookService bookService;

    private Logger logger=LoggerFactory.getLogger(BookRestController.class);


    //Note: The @Named("bookService") is not required in this example (as there only instance of BookService around)
    @Inject
    public BookRestController(@Named("bookService") BookService bookService )
    {
        this.bookService=bookService;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable("id") Long id) {
        logger.debug("Provider has received request to get person with id: " + id);
        return bookService.getBook(id);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectWithId addBook(@RequestBody Book book)
    {
    	System.out.println(book.getAuthor());
    	System.out.println(book.getTitle());
        return new ObjectWithId(bookService.addBook(book));
    }
}
