package com.example.itextdemo;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ItextServiceTest {
    ItextService service;

    @BeforeEach
    void init() {
        service = new ItextService();
    }

    @Test
    void documentIsaUsableItexDocument() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = service.getDocument(out);

        document.add(getTable());

        document.close();

        String expected = removeDatesAndID(getExampleDoc().toString());
        String got = removeDatesAndID(out.toString());

        assertEquals(expected, got);
    }

    @Test
    void tableIsWellInitialized() {
        UnitValue[] unitValues = UnitValue.createPercentArray(8);

        Table table = service.getTable(unitValues);

        assertEquals(unitValues[0], table.getColumnWidth(0));
        assertEquals(8, table.getNumberOfColumns());
    }

    private String removeDatesAndID(String pdf) {
        return pdf.replaceAll("<</CreationDate.*", "").
                replaceAll("<</ID .*", "");

    }

    ByteArrayOutputStream getExampleDoc() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);

        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        doc.add(getTable());

        doc.close();

        return out;
    }

    private Table getTable() {
        Table table = service.getTable(UnitValue.createPercentArray(8)).useAllAvailableWidth();

        for (int i = 0; i < 16; i++) {
            table.addCell("hi");
        }

        return table;
    }

}