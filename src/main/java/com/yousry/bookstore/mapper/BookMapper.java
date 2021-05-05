package com.yousry.bookstore.mapper;

import com.yousry.bookstore.dal.domainobject.Book;
import com.yousry.bookstore.dto.BookDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    BookDTO bookToDTO(Book book);

    List<BookDTO> booksToDTOs(List<Book> books);

    Book dtoToBook(BookDTO dto);

    List<Book> dtosToBooks(List<BookDTO> dtos);
}
