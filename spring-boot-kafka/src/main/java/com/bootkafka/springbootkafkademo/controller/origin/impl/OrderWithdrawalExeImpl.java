package com.bootkafka.springbootkafkademo.controller.origin.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.bootkafka.springbootkafkademo.controller.origin.ExeFactory;
import com.bootkafka.springbootkafkademo.controller.origin.ExeService;
import com.bootkafka.springbootkafkademo.controller.origin.ToLocalDateTimeUtil;
import com.bootkafka.springbootkafkademo.controller.origin.entity.OrderWithdrawal;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.OrderWithdrawalSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OrderWithdrawalExeImpl implements ExeService, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        ExeFactory.register("order_withdrawal", this);
    }

    @Override
    public boolean exe(MultipartFile file) {
        try (BufferedInputStream bs = new BufferedInputStream(file.getInputStream())) {
            // 定义计数器
//            AtomicInteger count = new AtomicInteger(importReocrd.getTotalSize());
            List<OrderWithdrawalSource> readList = new ArrayList<>();
            // 同步方式读取
            EasyExcel.read(bs, OrderWithdrawalSource.class, new AnalysisEventListener<OrderWithdrawalSource>() {
                @Override
                public void invoke(OrderWithdrawalSource orderWithdrawalSource, AnalysisContext context) {
//                    log.info("getDataFromFileAndProc invoke start");
                    readList.add(orderWithdrawalSource);
                    int size = readList.size();
                    if (size >= BATCH_COUNT) {
                        saveList(readList);
                        readList.clear();
                    }
                }

                @Override
                public void onException(Exception ex, AnalysisContext context) throws Exception {
                    log.error("getDataFromFileAndProc onException:{}", ex);
                    throw ex;
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info("getDataFromFileAndProc doAfterAllAnalysed start");
                    if (CollectionUtils.isNotEmpty(readList)) {
                        saveList(readList);
                    }
                    log.info("getDataFromFileAndProc doAfterAllAnalysed end");
                }
            }).excelType(ExcelTypeEnum.XLS).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private void saveList(List<OrderWithdrawalSource> orderWithdrawalSourceList) {
        OrderWithdrawalSource orderWithdrawalSource = orderWithdrawalSourceList.get(0);
        log.info("orderWithdrawalSource = {}", orderWithdrawalSource.toString());
        log.info("orderWithdrawalSourceList size is {}", orderWithdrawalSourceList.size());
        List<OrderWithdrawal> orderWithdrawalList = convertToEntity(orderWithdrawalSourceList);
        log.info("convertToEntity orderWithdrawal = {}", orderWithdrawalList.get(0).toString());
    }

    private List<OrderWithdrawal> convertToEntity(List<OrderWithdrawalSource> orderWithdrawalSourceList) {
        List<OrderWithdrawal> orderWithdrawalList = new ArrayList<>(orderWithdrawalSourceList.size());
        orderWithdrawalSourceList.forEach(item -> {
            OrderWithdrawal orderWithdrawal = new OrderWithdrawal();
            BeanUtils.copyProperties(item, orderWithdrawal);
            orderWithdrawal.setCreateTime(ToLocalDateTimeUtil.parse(item.getCreateTime()));
            orderWithdrawal.setModifyTime(ToLocalDateTimeUtil.parse(item.getModifyTime()));
            orderWithdrawal.setFinishedTime(ToLocalDateTimeUtil.parse(item.getFinishedTime()));
            orderWithdrawal.setCallbackTime(ToLocalDateTimeUtil.parse(item.getCallbackTime()));
            orderWithdrawal.setAuditTime(ToLocalDateTimeUtil.parse(item.getAuditTime()));
            orderWithdrawal.setReceiveTime(ToLocalDateTimeUtil.parse(item.getReceiveTime()));
            orderWithdrawalList.add(orderWithdrawal);
        });
        return orderWithdrawalList;
    }



}
