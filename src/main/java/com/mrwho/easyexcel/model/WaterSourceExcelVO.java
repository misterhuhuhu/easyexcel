package com.mrwho.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaterSourceExcelVO {
    
    @ExcelProperty("编号")
    public String 编号;
    @ExcelProperty("水源名称")
    public String 水源名称;
    @ExcelProperty("水源类型")
    public String 水源类型;
    @ExcelProperty("使用状态")
    public String 使用状态;
    @ExcelProperty("所属区域（XX街道）")
    public String 所属区域;
    @ExcelProperty("水源地址")
    public String 水源地址;
    @ExcelProperty("联系人")
    public String 联系人;
    @ExcelProperty("联系电话")
    public String 联系电话;
    @ExcelProperty("水源图片")
    public String 水源图片;
    
}
