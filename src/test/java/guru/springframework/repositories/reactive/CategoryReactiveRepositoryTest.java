package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        // by using block() here, we are blocking the execution of the test until the deleteAll() method is complete
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setDescription("American");

        categoryReactiveRepository.save(category).block();

        assertEquals(1, categoryReactiveRepository.findAll().count().block().longValue());
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription("American");

        categoryReactiveRepository.save(category).block();

        Category fetchedCat = categoryReactiveRepository.findByDescription("American").block();

        assertNotNull(fetchedCat.getId());
    }
}