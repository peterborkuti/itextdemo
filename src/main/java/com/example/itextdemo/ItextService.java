package com.example.itextdemo;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

@Service
public class ItextService {
    public Document getDocument(OutputStream out) {
        PdfWriter writer = new PdfWriter(out);

        PdfDocument pdfDoc = new PdfDocument(writer);
        return new Document(pdfDoc);
    }

    public Table getTable(UnitValue[] columnWidths) {
        return new Table(columnWidths);
    }
}
