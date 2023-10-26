package com.mrwho.easyexcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.mrwho.easyexcel.converters.ClientStringConverter;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class UserExcelVO {
    
    
    @ExcelIgnore
    @NotBlank
    private String Id;
    
    @ExcelProperty(value = "类型", converter = ClientStringConverter.class)
    @NotBlank
    private String type;
    
}
