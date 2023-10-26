package com.mrwho.easyexcel.readlistener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadWorkbook;
import com.alibaba.excel.read.metadata.holder.ReadWorkbookHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 从excelVO转换为PO
 *
 * @param <PO> 存储对象类行
 * @param <VO> excel数据对象类型
 */
@Data
@Slf4j
public abstract class ExcelAbstractReadListener<PO, VO> implements ReadListener<VO> {
    
    private int batchSize = 2000;
    private List<PO> cachList = new ArrayList<>(batchSize);
    private List<String> errors = new ArrayList<>(batchSize);
    
    abstract List<String> getValidVOResultMessage(VO vo);
    
    @Override
    public void invoke(VO vo, AnalysisContext context) {
        log.info(vo.toString());
        List<String> validResult = getValidVOResultMessage(vo);
        if (CollectionUtils.isNotEmpty(validResult)) {
            List<String> stringList = validResult.stream().map(result -> String.format("第%s行：%s", context.readRowHolder().getRowIndex() + 1, result)).collect(Collectors.toList());
            errors.addAll(stringList);
            return;
        }
        PO po = coverVODataPO(vo);
        cachList.add(po);
        if (cachList.size() >= batchSize) {
            saveBatch(cachList);
            cachList.clear();
        }
        
    }
    
    abstract PO coverVODataPO(VO vo);
    
    abstract boolean saveBatch(List<PO> poList);
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveBatch(cachList);
        cachList.clear();
    }
}
