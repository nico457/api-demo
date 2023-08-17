package com.example.apidemo.controllers;

import com.example.apidemo.entities.Book;
import com.example.apidemo.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class BookController {


    private final BookService bookService;


    public BookController( BookService bookService) {
        this.bookService = bookService;
    }

    //CRUD
    //Buscar todos los libros
    @GetMapping("/api/books")

    public ResponseEntity<List<Book>> findAll() {
        return bookService.findAll();
    }

    //Buscar un libro por id
    @GetMapping("/api/books/{id}")

    @Operation(summary = "Find a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })


    public ResponseEntity<Book> findById(@PathVariable Long id) {

        return bookService.findById(id);
    }
    @Operation(summary="Create a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Trying to create a book with id",
                    content = @Content) })


    //Crear un libro
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book){
        return bookService.create(book);
    }
    //Actualizar un libro
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        return bookService.update(book);
    }
    //Borrar un libro
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        return bookService.delete(id);
    }

}
