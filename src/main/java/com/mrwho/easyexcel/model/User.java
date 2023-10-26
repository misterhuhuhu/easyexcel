package com.mrwho.easyexcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.mrwho.easyexcel.converters.ClientStringConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class User {
    
    
    @ExcelIgnore
    private String Id;
    
    @ExcelProperty(value = "类型",converter = ClientStringConverter.class )
    private String type;
    
    public static List<User> getUserList(){
        
        return new ArrayList<>(){{
            add(new User(){{
                setId("1");
                setType("1");
            }});
            add(new User(){{
                setId("2");
                setType("2");
            }});
        }};
    }
}
