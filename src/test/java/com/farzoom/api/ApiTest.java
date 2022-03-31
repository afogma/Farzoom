package com.farzoom.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createTaskAndGet() throws Exception {
        MockHttpServletRequestBuilder createTask = MockMvcRequestBuilders.post("/api/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"testTask\", \"description\":\"description\"}");

        mockMvc.perform(createTask)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        MockHttpServletRequestBuilder getTasks = MockMvcRequestBuilders.get("/api/tasks");
        mockMvc.perform(getTasks)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}