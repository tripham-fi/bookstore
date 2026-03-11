package fi.haagahelia.bookstore.repository;


import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.domain.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);

}
