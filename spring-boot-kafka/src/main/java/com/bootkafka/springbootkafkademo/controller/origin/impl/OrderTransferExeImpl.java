package com.bootkafka.springbootkafkademo.controller.origin.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.bootkafka.springbootkafkademo.controller.origin.ExeFactory;
import com.bootkafka.springbootkafkademo.controller.origin.ExeService;
import com.bootkafka.springbootkafkademo.controller.origin.ToLocalDateTimeUtil;
import com.bootkafka.springbootkafkademo.controller.origin.entity.OrderTransfer;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.OrderTransferSource;
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
public class OrderTransferExeImpl implements ExeService, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        ExeFactory.register("order_transfer", this);
    }

    @Override
    public boolean exe(MultipartFile file) {
        try (BufferedInputStream bs = new BufferedInputStream(file.getInputStream())) {
            // 定义计数器
//            AtomicInteger count = new AtomicInteger(importReocrd.getTotalSize());
            List<OrderTransferSource> readList = new ArrayList<>();
            // 同步方式读取
            EasyExcel.read(bs, OrderTransferSource.class, new AnalysisEventListener<OrderTransferSource>() {
                @Override
                public void invoke(OrderTransferSource orderTransferSource, AnalysisContext context) {
//                    log.info("getDataFromFileAndProc invoke start");
                    readList.add(orderTransferSource);
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

    private void saveList(List<OrderTransferSource> orderTransferSourceList) {
        OrderTransferSource orderTransferSource = orderTransferSourceList.get(0);
        log.info("orderTransferSource = {}", orderTransferSource.toString());
        log.info("orderTransferSourceList size is {}", orderTransferSourceList.size());
        List<OrderTransfer> orderTransferList = convertToEntity(orderTransferSourceList);
        log.info("convertToEntity orderTransfer = {}", orderTransferList.get(0).toString());
    }

    private List<OrderTransfer> convertToEntity(List<OrderTransferSource> orderTransferSourceList) {
        List<OrderTransfer> orderTransferList = new ArrayList<>(orderTransferSourceList.size());
        orderTransferSourceList.forEach(item -> {
            OrderTransfer orderTransfer = new OrderTransfer();
            BeanUtils.copyProperties(item, orderTransfer);
            orderTransfer.setCreateTime(ToLocalDateTimeUtil.parse(item.getCreateTime()));
            orderTransfer.setModifyTime(ToLocalDateTimeUtil.parse(item.getModifyTime()));
            orderTransferList.add(orderTransfer);
        });
        return orderTransferList;
    }



}
