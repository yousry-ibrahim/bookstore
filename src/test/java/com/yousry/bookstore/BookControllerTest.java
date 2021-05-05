package com.yousry.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yousry.bookstore.dto.BookDTO;
import com.yousry.bookstore.dto.CheckoutDTO;
import com.yousry.bookstore.security.JwtUtil;
import com.yousry.bookstore.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookService bookService;

    @Autowired
    private JwtUtil jwtUtil;

    private static String token = "Bearer ";

    @Before
    public void init() {
        token = token.concat(jwtUtil.generateToken("smartD"));
    }

    @Test
    public void testGetAllBooks() throws Exception {

        mvc.perform(get("/v1/books")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBook() throws Exception {
        mvc.perform(get("/v1/books/1")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(notNullValue())))
                .andExpect(jsonPath("$.isbn", is(notNullValue())))
                .andExpect(jsonPath("$.authorId", is(notNullValue())));
    }

    @Test
    public void testFailedGetBook() throws Exception {

        mvc.perform(get("/v1/books/1000")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddNewBook() throws Exception {
        BookDTO request = BookDTO.builder()
                .isbn("isbn3000")
                .title("testing title")
                .price(BigDecimal.valueOf(23.5))
                .description("testing one")
                .authorId(Long.parseLong("1"))
                .classificationId(Long.parseLong("1"))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.post("/v1/books")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookDTO request = BookDTO.builder()
                .id(Long.parseLong("1"))
                .isbn("New ISBN")
                .title("New Title")
                .price(BigDecimal.valueOf(23.5))
                .description("testing one")
                .authorId(Long.parseLong("1"))
                .classificationId(Long.parseLong("1"))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.put("/v1/books")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBook() throws Exception {

        int numberBeforeDeletion = bookService.getAll().size();

        mvc.perform(delete("/v1/books/1")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int numberAfterDeletion = bookService.getAll().size();

        assertTrue(numberBeforeDeletion - numberAfterDeletion == 1);
    }

    @Test
    public void testSearch() throws Exception {
        mvc.perform(get("/v1/books/search?title=java")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title", is(notNullValue())))
                .andExpect(jsonPath("$.[0].title").value(containsString("java")))
                .andDo(print());
    }

    @Test
    public void testCheckoutWithPromotion() throws Exception {
        CheckoutDTO request = CheckoutDTO.builder()
                .bookIdList(Arrays.asList(Long.parseLong("1"), Long.parseLong("3")))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.post("/v1/books/checkout")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(66.5)));
    }

}
