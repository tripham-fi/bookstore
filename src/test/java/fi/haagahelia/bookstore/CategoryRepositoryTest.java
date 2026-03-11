package fi.haagahelia.bookstore;

import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        Category category = new Category("Sci-Fi");

        categoryRepository.save(category);

        assertThat(category.getCategoryId()).isNotNull();
    }

    @Test
    public void testGetCategory() {
        List<Category> categories = categoryRepository.findByName("Fantasy");

        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Fantasy");
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category("Temp");
        categoryRepository.save(category);

        Long id = category.getCategoryId();
        categoryRepository.deleteById(id);

        assertThat(categoryRepository.findById(id)).isEmpty();
    }
}
