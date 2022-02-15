package com.example.itextdemo;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final ItextService service;

    public byte[] getPdf() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = service.getDocument(out);

        document.add(getTable());

        document.close();

        return out.toByteArray();
    }

    private Table getTable() {
        Table table = service.getTable(UnitValue.createPercentArray(8)).useAllAvailableWidth();

        for (int i = 0; i < 16; i++) {
            table.addCell("hi");
        }

        return table;
    }
}
