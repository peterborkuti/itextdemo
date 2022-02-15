package com.example.itextdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PdfControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PdfService pdfService;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        byte[] expected = "EXPECTED".getBytes();
        when(pdfService.getPdf()).thenReturn(expected);

        this.mockMvc.perform(get("/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/pdf")))
                .andExpect(content().bytes(expected));
    }

}