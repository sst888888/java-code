package com.bootkafka.springbootkafkademo.controller.origin.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.bootkafka.springbootkafkademo.controller.origin.ExeFactory;
import com.bootkafka.springbootkafkademo.controller.origin.ExeService;
import com.bootkafka.springbootkafkademo.controller.origin.ToLocalDateTimeUtil;
import com.bootkafka.springbootkafkademo.controller.origin.entity.OrderDeposit;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.OrderDepositSource;
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
public class OrderDepositExeImpl implements ExeService, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        ExeFactory.register("order_deposit", this);
    }

    @Override
    public boolean exe(MultipartFile file) {
        try (BufferedInputStream bs = new BufferedInputStream(file.getInputStream())) {
            // 定义计数器
//            AtomicInteger count = new AtomicInteger(importReocrd.getTotalSize());
            List<OrderDepositSource> readList = new ArrayList<>();
            // 同步方式读取
            EasyExcel.read(bs, OrderDepositSource.class, new AnalysisEventListener<OrderDepositSource>() {
                @Override
                public void invoke(OrderDepositSource orderDepositSource, AnalysisContext context) {
//                    log.info("getDataFromFileAndProc invoke start");
                    readList.add(orderDepositSource);
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

    private void saveList(List<OrderDepositSource> orderDepositSourceList) {
        OrderDepositSource orderDepositSource = orderDepositSourceList.get(0);
        log.info("orderDepositSource = {}", orderDepositSource.toString());
        log.info("orderDepositSourceList size is {}", orderDepositSourceList.size());
        List<OrderDeposit> orderDepositList = convertToEntity(orderDepositSourceList);
        log.info("convertToEntity orderDeposit = {}", orderDepositList.get(0).toString());
    }

    private List<OrderDeposit> convertToEntity(List<OrderDepositSource> orderDepositSourceList) {
        List<OrderDeposit> orderDepositList = new ArrayList<>(orderDepositSourceList.size());
        orderDepositSourceList.forEach(item -> {
            OrderDeposit orderDeposit = new OrderDeposit();
            BeanUtils.copyProperties(item, orderDeposit);
            orderDeposit.setCreateTime(ToLocalDateTimeUtil.parse(item.getCreateTime()));
            orderDeposit.setModifyTime(ToLocalDateTimeUtil.parse(item.getModifyTime()));
            orderDeposit.setFinishedTime(ToLocalDateTimeUtil.parse(item.getFinishedTime()));
            orderDeposit.setCallbackTime(ToLocalDateTimeUtil.parse(item.getCallbackTime()));
            orderDeposit.setRegistTime(ToLocalDateTimeUtil.parse(item.getRegistTime()));
            orderDepositList.add(orderDeposit);
        });
        return orderDepositList;
    }



}
