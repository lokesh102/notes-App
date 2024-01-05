package com.notesApp.notes.App;

import com.notesApp.notes.App.NotesApp.controllers.NotesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

    @WebMvcTest(NotesController.class)
    public class NotesControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testSayHello() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/notes"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
//                    .andExpect(MockMvcResultMatchers.content().string("Hello, world!"));
        }
    }
