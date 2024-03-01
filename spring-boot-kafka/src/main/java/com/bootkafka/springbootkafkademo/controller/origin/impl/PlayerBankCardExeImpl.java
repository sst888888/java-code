package com.bootkafka.springbootkafkademo.controller.origin.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.bootkafka.springbootkafkademo.controller.origin.ExeFactory;
import com.bootkafka.springbootkafkademo.controller.origin.ExeService;
import com.bootkafka.springbootkafkademo.controller.origin.ToLocalDateTimeUtil;
import com.bootkafka.springbootkafkademo.controller.origin.entity.Player;
import com.bootkafka.springbootkafkademo.controller.origin.entity.PlayerBankCard;
import com.bootkafka.springbootkafkademo.controller.origin.entity.PlayerVirBank;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.PlayerBankCardSource;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.PlayerVirBankSource;
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
public class PlayerBankCardExeImpl implements ExeService, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        ExeFactory.register("player_bank_card", this);
    }

    @Override
    public boolean exe(MultipartFile file) {
        try (BufferedInputStream bs = new BufferedInputStream(file.getInputStream())) {
            // 定义计数器
//            AtomicInteger count = new AtomicInteger(importReocrd.getTotalSize());
            List<PlayerBankCardSource> readList = new ArrayList<>();
            // 同步方式读取
            EasyExcel.read(bs, PlayerBankCardSource.class, new AnalysisEventListener<PlayerBankCardSource>() {
                @Override
                public void invoke(PlayerBankCardSource playerBankCardSource, AnalysisContext context) {
//                    log.info("getDataFromFileAndProc invoke start");
                    readList.add(playerBankCardSource);
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

    private void saveList(List<PlayerBankCardSource> playerBankCardSourceList) {
        PlayerBankCardSource playerBankCardSource = playerBankCardSourceList.get(0);
        log.info("playerBankCardSource = {}", playerBankCardSource.toString());
        log.info("playerBankCardSourceList size is {}", playerBankCardSourceList.size());
        List<PlayerBankCard> playerBankCardList = convertToEntity(playerBankCardSourceList);
        log.info("convertToEntity playerBankCard = {}", playerBankCardList.get(0).toString());
    }

    private List<PlayerBankCard> convertToEntity(List<PlayerBankCardSource> playerBankCardSourceList) {
        List<PlayerBankCard> playerBankCardList = new ArrayList<>(playerBankCardSourceList.size());
        playerBankCardSourceList.forEach(item -> {
            PlayerBankCard playerBankCard = new PlayerBankCard();
            BeanUtils.copyProperties(item, playerBankCard);
            playerBankCard.setCreateTime(ToLocalDateTimeUtil.parse(item.getCreateTime()));
            playerBankCard.setModifyTime(ToLocalDateTimeUtil.parse(item.getModifyTime()));
            playerBankCardList.add(playerBankCard);
        });
        return playerBankCardList;
    }


}
