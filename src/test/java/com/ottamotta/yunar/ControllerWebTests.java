package com.ottamotta.yunar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerWebTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    AppRepository repository;

    private static final int TEST_TIMESTAMP = 1000;
    private static final String TEST_VALUE = "somevalue";

    @Before
    public void setUp() throws Exception {
        Entity entity = new Entity(TEST_TIMESTAMP, TEST_VALUE);

        when(repository.save(any(Entity.class)))
                .thenReturn(entity);

        when(repository.findAllByTimestampBetweenOrderByTimestamp(anyLong(), anyLong()))
                .thenReturn(Collections.singletonList(entity));
    }

    @Test
    public void testRetrieve() throws Exception {
        mvc.perform(get("")
                .param("from", String.valueOf(TEST_TIMESTAMP))
                .param("to", "2000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println("Response retrieve is " + result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].timestamp", is(TEST_TIMESTAMP)));
    }

    @Test
    public void testSave() throws Exception {
        mvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"timestamp\": " + TEST_TIMESTAMP + ", \"value\": \"" + TEST_VALUE + "\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println("Response save is " + result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.timestamp", is(TEST_TIMESTAMP)))
                .andExpect(jsonPath("$.value", is(TEST_VALUE)));
    }
}
