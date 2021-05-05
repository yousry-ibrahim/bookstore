package com.yousry.bookstore.service;

import com.google.common.collect.Lists;
import com.yousry.bookstore.dal.domainobject.Book;
import com.yousry.bookstore.dal.repository.BookRepository;
import com.yousry.bookstore.exception.EntityCanNotBeSavedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * get all available books in the store
     *
     * @return list of books
     */
    public List<Book> getAll() {
        List<Book> books = Lists.newArrayList(bookRepository.findAll());
        log.info("number of fetched books are {}",books.size());
        return books;
    }

    /**
     * get one book by its id
     *
     * @param id
     * @return
     * @throws EntityNotFoundException
     */
    public Book getById(long id) throws EntityNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            log.info("Book Id {} retrieved successfully",id);
            return bookOptional.get();
        } else {
            log.error("Book with id {} does not exist", id);
            throw new EntityNotFoundException("sorry, this book does not exist.");
        }
    }

    public Book add(Book book) throws EntityCanNotBeSavedException {
        Book savedBook = bookRepository.save(book);
        if (savedBook == null || savedBook.getId() == null) {
            log.error("Book with id {} can not be saved", book.getId());
            throw new EntityCanNotBeSavedException("Book can not be saved.");
        } else {
            log.info("Book with id {} is saved successfully", book.getId());
            return savedBook;
        }
    }

    public Book update(Book book) throws EntityCanNotBeSavedException {
        Book updatedBook = bookRepository.save(book);
        if (updatedBook != null || updatedBook.getId() != null) {
            log.error("Book with id {} is updated successfully", book.getId());
            return updatedBook;
        } else {
            log.error("Book with id {} can not be updated", book.getId());
            throw new EntityCanNotBeSavedException("Book can not be updated.");
        }
    }

    public void delete(Book book) {
        bookRepository.delete(book);
        log.info("Book Id {} is deleted successfully",book.getId());
    }

    public List<Book> searchByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        log.info("Number of Books that match {}  is {}",title,books.size());
        return books;
    }

    public BigDecimal checkout(List<Book> books) {
        return books.stream().map(book -> {
            Book fullBookObject = getById(book.getId());
            if (fullBookObject.getClassification().getPromotion() != null &&
                fullBookObject.getClassification().getPromotion().getPercentage() != null &&
                fullBookObject.getClassification().getPromotion().getPercentage().compareTo(BigDecimal.ZERO) > 0){
                return fullBookObject.getPrice().subtract(fullBookObject.getPrice().multiply(fullBookObject.getClassification().getPromotion().getPercentage()));
            }else{
                return fullBookObject.getPrice();
            }
        })
         .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
