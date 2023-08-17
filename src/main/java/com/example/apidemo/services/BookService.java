package com.example.apidemo.services;

import com.example.apidemo.controllers.BookController;
import com.example.apidemo.entities.Book;
import com.example.apidemo.repositories.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(BookController.class);

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }




    public ResponseEntity<List<Book>> findAll() {
        //findall de los registros con atributo online = true
        List<Book> books = bookRepository.findAll().stream().filter(book -> book.isOnline()).toList();
        if(books.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }


    public ResponseEntity<Book> findById(Long id) {

        Optional<Book> bookOpt = bookRepository.findById(id);

        if (bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Book> create(Book book){
        if(book.getId() != null){
            log.warn("Trying to create a new book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Book> update(Book book){
        if(book.getId() == null){
            log.warn("Trying to update a book without id");
            return ResponseEntity.badRequest().build();
        }if(!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a book with non existent id");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Book> delete(Long id){

        if(!bookRepository.existsById(id)){
            log.warn("Trying to delete a book with non existent id");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
