package com.example.itextdemo;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PdfServiceIntegrationTest {
    private PdfService service;

    @BeforeEach
    void init() {
        service = new PdfService(new ItextService());
    }

    @Test
    void testTableIs2x8() throws IOException {
        byte[] pdf = service.getPdf();
        PdfReader reader = new PdfReader(new ByteArrayInputStream(pdf));
        PdfDocument document = new PdfDocument(reader);

        assertEquals(1, document.getNumberOfPages());
        PdfPage page = document.getFirstPage();
        String text = PdfTextExtractor.getTextFromPage(page);

        String[] rows = text.split("\n");
        assertEquals(2, rows.length);

        assertEquals(8, rows[0].split(" ").length);
        assertEquals(8, rows[1].split(" ").length);
    }
}