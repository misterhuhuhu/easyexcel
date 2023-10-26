package com.mrwho.easyexcel.converters;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ClientStringConverter extends ExcelAbstractStringConverter {
    
    private final HashMap<String, String> clientResult = new HashMap<>() {
        {
            put("22", "2");
            put("11", "1");
        }
    };
    private final HashMap<String, String> clientResult2 = new HashMap<>() {
        {
            put("1", "11");
            put("2", "22");
        }
    };
    
    @Override
    Supplier<? extends Map<String, String>> keyValueSupplier() {
        return () -> clientResult;
    }
    
    @Override
    Supplier<? extends Map<String, String>> valueKeySupplier() {
        return () -> clientResult2;
    }
}
