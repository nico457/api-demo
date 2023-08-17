package com.example.apidemo;

import com.example.apidemo.entities.Book;
import com.example.apidemo.repositories.BookRepository;
import com.example.apidemo.services.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ApiDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(ApiDemoApplication.class, args);
		BookRepository bookRepository = context.getBean(BookRepository.class);
		BookService bookService = context.getBean(BookService.class);

		// Create a few books
		Book book1 = new Book(null, "The Alchemist", "Paulo Coelho", 200, 9.99, LocalDate.of(2018,12,1), false);
		Book book2 = new Book(null,"Alice's Adventures in Wonderland", "Lewis Carroll", 300, 19.99, LocalDate.of(2019,1,1), true);
		Book book3 = new Book(null,"The Adventures of Tom Sawyer", "Mark Twain", 400, 29.99, LocalDate.of(2019,2,1), true);
		Book book4 = new Book(null,"The Adventures of Sherlock Holmes", "Arthur Conan Doyle", 500, 39.99, LocalDate.of(2019,3,1), false);
		Book book5 = new Book(null,"The Adventures of Huckleberry Finn", "Mark Twain", 600, 49.99, LocalDate.of(2019,4,1), true);
		Book book6 = new Book(null,"Pinocchio", "Carlo Collodi", 700, 59.99, LocalDate.of(2019,5,1), true);
		Book book7 = new Book(null,"Robin Hood", "Howard Pyle", 800, 69.99, LocalDate.of(2019,6,1), true);
		Book book8 = new Book(null,"Sherlock Holmes", "Arthur Conan Doyle", 900, 79.99, LocalDate.of(2019,7,1), true);
		Book book9 = new Book(null,"The Call of the Wild", "Jack London", 1000, 89.99, LocalDate.of(2019,8,1), true);
		Book book10 = new Book(null,"The Count of Monte Cristo", "Alexandre Dumas", 1100, 99.99, LocalDate.of(2019,9,1), true);
		// Save the books
		bookRepository.save(book1);
		bookRepository.save(book2);
		bookRepository.save(book3);
		bookRepository.save(book4);
		bookRepository.save(book5);
		bookRepository.save(book6);
		bookRepository.save(book7);
		bookRepository.save(book8);
		bookRepository.save(book9);
		bookRepository.save(book10);
	}

}
