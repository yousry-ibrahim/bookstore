package com.yousry.bookstore.dal.repository;

import com.yousry.bookstore.dal.domainobject.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * Spring JPA DATA repository for BOOK entity
 * @author : yousry
 * @version : 1.0
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
}
