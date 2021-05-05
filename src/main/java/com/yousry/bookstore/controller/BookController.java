package com.yousry.bookstore.controller;

import com.yousry.bookstore.dal.domainobject.Book;
import com.yousry.bookstore.dto.ApiErrorResponse;
import com.yousry.bookstore.dto.BookDTO;
import com.yousry.bookstore.dto.CheckoutDTO;
import com.yousry.bookstore.exception.EntityCanNotBeSavedException;
import com.yousry.bookstore.mapper.BookMapper;
import com.yousry.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Book Controller, all the operations related to Book Entity
 * @author : yousry
 * @version : 1.0
 */
@RestController
@RequestMapping("/v1/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    @Operation(summary = "Used to list all available Books", security = @SecurityRequirement(name = "bearerAuth"))
    public @ResponseBody
    List<BookDTO> all() {
        return bookMapper.booksToDTOs(bookService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Used to get one Book by its Id", security = @SecurityRequirement(name = "bearerAuth"))
    public @ResponseBody
    BookDTO get(@PathVariable Long id) throws EntityNotFoundException {
        return bookMapper.bookToDTO(bookService.getById(id));
    }

    @PostMapping
    @ResponseBody
    @Operation(summary = "Used to add new Book", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity add(@Valid @RequestBody BookDTO book) throws EntityCanNotBeSavedException {
        bookService.add(bookMapper.dtoToBook(book));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    @Operation(summary = "Used to amend an existing Book", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity update(@Valid @RequestBody BookDTO book) throws EntityCanNotBeSavedException {
        bookService.update(bookMapper.dtoToBook(book));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Used to delete a Book", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity delete(@PathVariable Long id) {
        bookService.delete(Book.builder().id(id).build());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public @ResponseBody
    @Operation(summary = "Used to search for books by title, search is use LIKE and not EXACT match. try to search for java", security = @SecurityRequirement(name = "bearerAuth"))
    List<BookDTO> search(@RequestParam String title) {
        return bookMapper.booksToDTOs(bookService.searchByTitle(title));
    }

    @PostMapping("/checkout")
    @Operation(summary = "Used to checkout one or more book, calculate the price with and without promotion.", security = @SecurityRequirement(name = "bearerAuth"))
    public BigDecimal checkout(@RequestBody CheckoutDTO checkout) {
        return bookService.checkout(checkout.getBookIdList().stream()
                .map(id -> Book.builder().id(id).build())
                .collect(Collectors.toList()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiErrorResponse handleEntityNotFoundException(final EntityNotFoundException ex) {
        return new ApiErrorResponse(new Date(),
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage());
    }

    @ExceptionHandler(EntityCanNotBeSavedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiErrorResponse handleEntityCanNotBeSavedException(final EntityCanNotBeSavedException ex) {
        return new ApiErrorResponse(new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage());
    }
}
