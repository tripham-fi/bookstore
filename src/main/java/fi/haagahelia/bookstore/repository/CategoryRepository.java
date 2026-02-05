package fi.haagahelia.bookstore.repository;


import org.springframework.data.repository.CrudRepository;

import fi.haagahelia.bookstore.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
