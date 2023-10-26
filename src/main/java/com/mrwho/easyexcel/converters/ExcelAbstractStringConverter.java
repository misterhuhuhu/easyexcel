package com.mrwho.easyexcel.converters;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.StringUtils;

import java.util.Map;
import java.util.function.Supplier;

public abstract class ExcelAbstractStringConverter implements Converter<String> {
    
    /**
     * 用于excel转java
     * @return
     */
    abstract Supplier<? extends Map<String, String>> keyValueSupplier();
    /**
     * 用于java转excel
     * @return
     */
    abstract Supplier<? extends Map<String, String>> valueKeySupplier();
    
    @Override
    public String convertToJavaData(ReadConverterContext<?> context) throws Exception {
        String s = keyValueSupplier().get().get(context.getReadCellData().getStringValue());
        if (StringUtils.isEmpty(s)) {
            throw new RuntimeException("字典中没有此键值对");
        }
        return s;
    }
    
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<String> context) throws Exception {
        String s =  valueKeySupplier().get().get(context.getValue());
        if (StringUtils.isEmpty(s)) {
            throw new RuntimeException("字典中没有此键值对");
        }
        return new WriteCellData<>(s);
    }
}
