package com.mrwho.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.mrwho.easyexcel.model.User;
import com.mrwho.easyexcel.model.UserExcelVO;
import com.mrwho.easyexcel.model.WaterSourceExcelVO;
import com.mrwho.easyexcel.readlistener.WaterSourceExcelReadListener;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class EasyexcelApplicationTests {
    
    private static final File tempFile;
    
    static {
        try {
            tempFile = File.createTempFile("Easyexceltest", ".xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    @SneakyThrows
    void useConvertWriteExcel() {
        
        
        EasyExcel.write(tempFile).head(User.class).sheet().doWrite(User.getUserList());
        System.out.println(tempFile.getPath());
        
    }
    
    @Test
    @SneakyThrows
    void useUserExcelReadListenerReadExcel() {
        EasyExcel.read("C:\\Users\\mr.who\\Desktop\\统计表.xlsx").head(WaterSourceExcelVO.class).registerReadListener(new WaterSourceExcelReadListener()).sheet().doRead();
    }
    
    
}
