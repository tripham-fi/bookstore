package fi.haagahelia.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);
}