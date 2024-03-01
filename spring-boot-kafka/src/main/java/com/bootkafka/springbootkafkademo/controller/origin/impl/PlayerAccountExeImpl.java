package com.bootkafka.springbootkafkademo.controller.origin.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.bootkafka.springbootkafkademo.controller.origin.ExeFactory;
import com.bootkafka.springbootkafkademo.controller.origin.ExeService;
import com.bootkafka.springbootkafkademo.controller.origin.ToLocalDateTimeUtil;
import com.bootkafka.springbootkafkademo.controller.origin.entity.Player;
import com.bootkafka.springbootkafkademo.controller.origin.entity.PlayerAccount;
import com.bootkafka.springbootkafkademo.controller.origin.entity.PlayerBankCard;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.PlayerAccountSource;
import com.bootkafka.springbootkafkademo.controller.origin.entity.source.PlayerBankCardSource;
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
public class PlayerAccountExeImpl implements ExeService, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        ExeFactory.register("player_account", this);
    }

    @Override
    public boolean exe(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        log.info("file name is {}", originalFilename);

        try (BufferedInputStream bs = new BufferedInputStream(file.getInputStream())) {
            List<PlayerAccountSource> readList = new ArrayList<>();
            // 同步方式读取
            EasyExcel.read(bs, PlayerAccountSource.class, new AnalysisEventListener<PlayerAccountSource>() {
                @Override
                public void invoke(PlayerAccountSource playerAccountSource, AnalysisContext context) {
                    readList.add(playerAccountSource);
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

    private void saveList(List<PlayerAccountSource> playerAccountSourceList) {
        PlayerAccountSource playerAccountSource = playerAccountSourceList.get(0);
        log.info("playerAccountSource = {}", playerAccountSource.toString());
        log.info("playerAccountSourceList size is {}", playerAccountSourceList.size());
        List<PlayerAccount> playerAccountList = convertToEntity(playerAccountSourceList);
        log.info("convertToEntity playerAccount = {}", playerAccountList.get(0).toString());
    }

    private List<PlayerAccount> convertToEntity(List<PlayerAccountSource> playerAccountSourceList) {
        List<PlayerAccount> playerAccountList = new ArrayList<>(playerAccountSourceList.size());
        playerAccountSourceList.forEach(item -> {
            PlayerAccount playerAccount = new PlayerAccount();
            BeanUtils.copyProperties(item, playerAccount);
            playerAccount.setCreateTime(ToLocalDateTimeUtil.parse(item.getCreateTime()));
            playerAccount.setModifyTime(ToLocalDateTimeUtil.parse(item.getModifyTime()));
            playerAccount.setFirstDepositTime(ToLocalDateTimeUtil.parse(item.getFirstDepositTime()));
            playerAccount.setLastDepositTime(ToLocalDateTimeUtil.parse(item.getLastDepositTime()));
            playerAccount.setLastWithdrawalTime(ToLocalDateTimeUtil.parse(item.getLastWithdrawalTime()));
            playerAccount.setLastLoginTime(ToLocalDateTimeUtil.parse(item.getLastLoginTime()));
            playerAccount.setLastContactTime(ToLocalDateTimeUtil.parse(item.getLastContactTime()));
            playerAccount.setNextFollowTime(ToLocalDateTimeUtil.parse(item.getNextFollowTime()));
            playerAccount.setLastRebateTime(ToLocalDateTimeUtil.parse(item.getLastRebateTime()));
            playerAccount.setFetchTime(ToLocalDateTimeUtil.parse(item.getFetchTime()));
            playerAccountList.add(playerAccount);
        });
        return playerAccountList;
    }



}
