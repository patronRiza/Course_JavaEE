package ru.prj.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("sandbox")
class NoteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterAll
    static void tearDown(@Autowired JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("drop schema public cascade");
        jdbcTemplate.execute("create schema public");
        jdbcTemplate.execute("grant all on schema public to public ");
    }

    @Test
    @DisplayName("Integration test - creating note")
    void createNote_ShouldReturnRedirect() throws Exception {
        mockMvc.perform(post("/mvc/create")
                        .param("title", "Test Title")
                        .param("content", "Test Content"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/all"));
    }

    @Test
    @DisplayName("Integration test of repository - get notes")
    void getAllNotes_ShouldReturnAllNotes() throws Exception {
        mockMvc.perform(get("/mvc/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Все записи")));
    }

    @Test
    @DisplayName("Integration test - update note")
    void updateNote_ShouldReturnRedirect() throws Exception {
        mockMvc.perform(post("/mvc/create")
                        .param("title", "Test Title2")
                        .param("content", "Test Content2"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/mvc/update/2")
                        .param("title", "UpdatedTitle2")
                        .param("content", "UpdatedContent2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/all"));

        mockMvc.perform(get("/mvc/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("UpdatedTitle2")))
                .andExpect(content().string(containsString("UpdatedContent2")));
    }

    @Test
    @DisplayName("Integration test - delete note")
    void deleteNote_ShouldReturnRedirect() throws Exception {
        mockMvc.perform(delete("/mvc/delete/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/all"));

        mockMvc.perform(get("/mvc/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("UpdatedTitle2"))))
                .andExpect(content().string(not(containsString("UpdatedContent2"))));
    }
}
