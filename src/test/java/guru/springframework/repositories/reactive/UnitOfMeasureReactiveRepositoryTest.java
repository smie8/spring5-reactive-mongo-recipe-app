package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        // by using block() here, we are blocking the execution of the test until the deleteAll() method is complete
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveUnitOfMeasure() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Cup");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        assertEquals(1, unitOfMeasureReactiveRepository.findAll().count().block().longValue());
    }

    @Test
    public void testFindUnitOfMeasureByDescription() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Cup");

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure fetchedUnitOfMeasure = unitOfMeasureReactiveRepository.findByDescription("Cup").block();

        assertNotNull(fetchedUnitOfMeasure.getId());
    }
}