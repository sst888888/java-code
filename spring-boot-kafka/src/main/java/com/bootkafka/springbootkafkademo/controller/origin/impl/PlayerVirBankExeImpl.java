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
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.PlayerSource;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.PlayerVirBankSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PlayerVirBankExeImpl implements ExeService, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        ExeFactory.register("player_vir_bank", this);
    }

    @Override
    public boolean exe(MultipartFile file) {
        try (BufferedInputStream bs = new BufferedInputStream(file.getInputStream())) {
            // 定义计数器
//            AtomicInteger count = new AtomicInteger(importReocrd.getTotalSize());
            List<PlayerVirBankSource> readList = new ArrayList<>();
            // 同步方式读取
            EasyExcel.read(bs, PlayerVirBankSource.class, new AnalysisEventListener<PlayerVirBankSource>() {
                @Override
                public void invoke(PlayerVirBankSource playerVirBankSource, AnalysisContext context) {
//                    log.info("getDataFromFileAndProc invoke start");
                    readList.add(playerVirBankSource);
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

    private void saveList(List<PlayerVirBankSource> playerVirBankSourceList) {
        PlayerVirBankSource playerVirBankSource = playerVirBankSourceList.get(0);
        log.info("playerVirBankSource = {}", playerVirBankSource.toString());
        log.info("playerVirBankSourceList size is {}", playerVirBankSourceList.size());
        List<PlayerVirBank> playerVirBankList = convertToEntity(playerVirBankSourceList);
        log.info("convertToEntity playerVirBank = {}", playerVirBankList.get(0).toString());
    }

    private List<PlayerVirBank> convertToEntity(List<PlayerVirBankSource> playerVirBankSourceList) {
        List<PlayerVirBank> playerVirBankList = new ArrayList<>(playerVirBankSourceList.size());
        playerVirBankSourceList.forEach(item -> {
            PlayerVirBank playerVirBank = new PlayerVirBank();
            BeanUtils.copyProperties(item, playerVirBank);
            playerVirBank.setCreateTime(ToLocalDateTimeUtil.parse(item.getCreateTime()));
            playerVirBank.setModifyTime(ToLocalDateTimeUtil.parse(item.getModifyTime()));
            playerVirBankList.add(playerVirBank);
        });
        return playerVirBankList;
    }



}
