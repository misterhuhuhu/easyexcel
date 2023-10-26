package com.mrwho.easyexcel.readlistener;

import com.mrwho.easyexcel.model.User;
import com.mrwho.easyexcel.model.UserExcelVO;
import com.mrwho.easyexcel.model.WaterSource;
import com.mrwho.easyexcel.model.WaterSourceExcelVO;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class WaterSourceExcelReadListener extends ExcelAbstractReadListener<WaterSource,WaterSourceExcelVO> {
    private static final Validator validator;
    @Resource
    private WaterSourceService waterSourceService;
    static {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();) {
            validator = validatorFactory.getValidator();
        }
    }
    
    /**
     * 使用 validation 校验
     * @param data
     * @return
     */
    @Override
    List<String> getValidVOResultMessage(WaterSourceExcelVO data) {
        Set<ConstraintViolation<WaterSourceExcelVO>> validate = validator.validate(data);
        return validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
    
    @Override
    WaterSource coverVODataPO(WaterSourceExcelVO data) {
        WaterSource waterSource = new WaterSource();
        BeanUtils.copyProperties(data,waterSource);
        return waterSource;
    }
    
    @Override
    boolean saveBatch(List<WaterSource> cachList) {
        //丢弃数据
        log.info(cachList.toString());
        return true;
    }
}
