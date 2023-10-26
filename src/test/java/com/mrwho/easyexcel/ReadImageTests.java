package com.mrwho.easyexcel;

import lombok.SneakyThrows;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadImageTests {
    @Test
    @SneakyThrows
    void name() {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("C:\\Users\\mr.who\\Desktop\\副本水源统计表.xlsx"));
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        List<POIXMLDocumentPart> relations = sheet.getRelations();
        
        for (POIXMLDocumentPart dr : relations) {
            if (dr instanceof XSSFDrawing) {
                List<XSSFShape> shapes = ((XSSFDrawing) dr).getShapes();
                System.out.println(shapes.size());
                //顺序读取
                List<XSSFShape> collect = shapes.stream().sorted(Comparator.comparing(item -> ((XSSFClientAnchor)item.getAnchor()).getRow2())).toList();
                for (int i = 0; i < collect.size(); i++) {
                    XSSFPicture xssfShape = (XSSFPicture) collect.get(i);
                    System.out.printf("Row2 %s,Col1 %s\n" ,xssfShape.getClientAnchor().getRow2(),xssfShape.getClientAnchor().getCol1());
                    byte[] data = xssfShape.getPictureData().getData();
                    String path = String.format("C:\\Users\\mr.who\\Desktop\\%s.jpg", i);
                    Files.write(Paths.get(path),data);
                }
            }
        }
    }
    
    
}
