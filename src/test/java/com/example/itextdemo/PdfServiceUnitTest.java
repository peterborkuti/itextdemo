package com.example.itextdemo;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PdfServiceUnitTest {
    @Mock
    ItextService itextService;

    @Mock
    Document document;

    @Mock
    Table table;

    @InjectMocks
    PdfService pdfService;

    @Test
    void getPdfAddsATableToTheDocument() {
        when(itextService.getDocument(any())).thenReturn(document);
        when(table.useAllAvailableWidth()).thenReturn(table);
        when(itextService.getTable(any())).thenReturn(table);

        pdfService.getPdf();

        verify(document).add(any(Table.class));
    }
}