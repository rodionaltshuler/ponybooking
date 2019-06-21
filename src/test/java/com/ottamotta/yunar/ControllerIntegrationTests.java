package com.ottamotta.yunar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerIntegrationTests {

    @Autowired
    AppRepository repository;

    @Autowired
    Controller controller;

    private static final int TEST_TIMESTAMP = 1000;
    private static final String TEST_VALUE = "somevalue";

    private Dto testDto1;
    private Entity testEntity1;

    @Before
    public void setUp() {
        testEntity1 = new Entity(TEST_TIMESTAMP, TEST_VALUE);
        testDto1 = new Dto(testEntity1);
    }

    @Test
    public void testGetData() {
        repository.deleteAll();
        repository.save(testEntity1);
        List<Dto> result = controller.get(TEST_TIMESTAMP, TEST_TIMESTAMP + 1)
                .collect(Collectors.toList());

        assert(result.size() == 1);
        assert(result.get(0).equals(testDto1));
    }

    @Test
    public void testSaveAndRetrieve() {
        repository.deleteAll();
        repository.save(testEntity1);

        Entity testEntity2 = new Entity(TEST_TIMESTAMP + 1, TEST_VALUE);
        controller.save(testEntity2);
        Dto testDto2 = new Dto(testEntity2);

        List<Dto> result = controller.get(TEST_TIMESTAMP, TEST_TIMESTAMP + 1)
                .collect(Collectors.toList());

        assert(result.size() == 2);
        assert(result.get(0).equals(testDto1));
        assert(result.get(1).equals(testDto2));
    }
}
