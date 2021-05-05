package com.yousry.bookstore.mapper;

import com.yousry.bookstore.dal.domainobject.Author;
import com.yousry.bookstore.dal.domainobject.Book;
import com.yousry.bookstore.dal.domainobject.Classification;
import com.yousry.bookstore.dto.BookDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
/**
 * mapper class for mapping between ENTITY, DTO and vice versa
 * @author : yousry
 * @version : 1.0
 */
@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDTO bookToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .authorId(book.getAuthor().getId())
                .authorName(book.getAuthor().getName())
                .classificationId(book.getClassification().getId())
                .classificationName(book.getClassification().getName())
                .isbn(book.getIsbn())
                .description(book.getDescription())
                .price(book.getPrice())
                .title(book.getTitle())
                .build();
    }

    @Override
    public List<BookDTO> booksToDTOs(List<Book> books) {
        return books.stream().map(book -> bookToDTO(book)).collect(Collectors.toList());
    }

    @Override
    public Book dtoToBook(BookDTO dto) {
        return Book.builder()
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .author(Author.builder().id(dto.getAuthorId()).name(dto.getAuthorName()).build())
                .classification(Classification.builder().id(dto.getClassificationId()).name(dto.getClassificationName()).build())
                .build();
    }

    @Override
    public List<Book> dtosToBooks(List<BookDTO> dtos) {
        return dtos.stream().map(dto -> dtoToBook(dto)).collect(Collectors.toList());
    }
}
