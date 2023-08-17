package com.example.apidemo.controllers;

import com.example.apidemo.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    @DisplayName("Find all books")
    @Test
    void findAll() {

       ResponseEntity<Book[]> response = testRestTemplate.getForEntity("/api/books", Book[].class);
       if (response.getBody() == null) {
           assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
       }else {
              assertEquals(HttpStatus.OK, response.getStatusCode());
       }

    }

    @Test
    void findById() {
    }

    @DisplayName("Create a book")
    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "title": "El señor de los anillos",
                    "author": "J.R.R Tolkien",
                    "pages": 1000,
                    "price": 1000.0,
                    "releaseDate": "1954-07-29",
                    "online": true
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);
        Book result= response.getBody();
        assertEquals(1L,result.getId());
    }
}