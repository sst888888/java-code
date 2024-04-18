package com.example.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.example.entity.ExportRecord;
import com.example.entity.RobotKnowledgeExport;
import com.example.entity.RobotKnowledgeExportTemplate;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

 public class RobotKnowledgeAnalysisEventListener extends AnalysisEventListener<RobotKnowledgeExport> {
    private List<RobotKnowledgeExport> readList = new ArrayList<>();
    private int allCount = 0;
    private List<RobotKnowledgeExportTemplate> allFailures = new ArrayList<>();
    private Set<String> unrepeatedQuestions = new HashSet<>();
    private ExportRecord exportRecord;
    private Long userId;

     private static final int finished = 1;
    public RobotKnowledgeAnalysisEventListener(ExportRecord exportRecord, Long userId){
        this.exportRecord = exportRecord;
        this.userId = userId;
    }
    @Override
    public void invoke(RobotKnowledgeExport robotKnowledgeExport, AnalysisContext analysisContext) {
        Integer currentRowNum = analysisContext.getCurrentRowNum();
        if(currentRowNum == 0){
            if("渠道类型".equals(robotKnowledgeExport.getTypeName())
                    &&"问题".equals(robotKnowledgeExport.getQuestion())
                    &&"标答".equals(robotKnowledgeExport.getAnswer())
                    &&"业务场景".equals(robotKnowledgeExport.getScene())){
                return;
            }else{
                //更新导入记录
                exportRecord.setStatus(finished);
                exportRecord.setTotalNumber(0);
                exportRecord.setSuccessNumber(0);
                exportRecord.setFailNumber(0);
                exportRecord.setDescription("导入失败，不合法的表头");
//                exportRecordService.updateById(exportRecord);
//                isImporting.set(false);
//                throw new ServiceException("不合法的表头");
            }
        }
        if (robotKnowledgeExport != null) {
            String scene = robotKnowledgeExport.getScene();
            String typeName = robotKnowledgeExport.getTypeName();
            String question = robotKnowledgeExport.getQuestion();
            String answer = robotKnowledgeExport.getAnswer();
            if (typeName == null
//                    || UserClientTypeEnum.getByName(typeName) == null
//                    || StringUtils.isBlank(question) || question.length() > questionLengthLimit
//                    || StringUtils.isBlank(answer) || answer.length() > answerLengthLimit
                    || StringUtils.isBlank(scene) || scene.length() >20
                    || unrepeatedQuestions.contains(scene+typeName+question)) {
                RobotKnowledgeExportTemplate failure = new RobotKnowledgeExportTemplate();
                failure.setTypeName(robotKnowledgeExport.getTypeName());
                failure.setAnswer(robotKnowledgeExport.getAnswer());
                failure.setQuestion(robotKnowledgeExport.getQuestion());
                allFailures.add(failure);
            } else {
//                int type = UserClientTypeEnum.getByName(typeName).getType();
//                robotKnowledgeExport.setType(type);
                readList.add(robotKnowledgeExport);
                unrepeatedQuestions.add(scene+typeName+question);
//					if (readList.size() >= saveBatchMax) {
//						save();
//					}
            }
            allCount++;
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        if(readList.size() > importLimit){//超出最大导入数
//            exportRecord.setStatus(finished);
//            exportRecord.setTotalNumber(0);
//            exportRecord.setSuccessNumber(0);
//            exportRecord.setFailNumber(0);
//            exportRecord.setDescription("导入失败，超出最大导入数");
////            exportRecordService.updateById(exportRecord);
////            isImporting.set(false);
//            return;
//        }
        //保存问题标答
        if (readList.size() > 0) {
            save();
        }
        //更新导入记录
        exportRecord.setStatus(finished);
        exportRecord.setTotalNumber(allCount);
        exportRecord.setSuccessNumber(allCount - allFailures.size());
        exportRecord.setFailNumber(allFailures.size());
        //保存保存导入失败数据，以供下载
//        if (allFailures.size() > 0) {
//            ExtraExcelUtil.SimpleMultipartFile multipartFile = ExtraExcelUtil.extractExcelDataAsMultipartFile("机器人知识库-导入失败",
//                    "未导入数据", allFailures, RobotKnowledgeExportTemplate.class);
//            try (InputStream inputStream = multipartFile.getInputStream()) {
//                CscFile cscFile = ossTemplate.putFile(AttachConstant.BUCKET_NAME_AGENT, multipartFile.getOriginalFilename(), inputStream);
//                exportRecord.setUrl(cscFile.getLink());
//            } catch (Exception e) {
//                exportRecord.setDescription("上传导入失败文件失败");
//            }
//        }
//        exportRecordService.updateById(exportRecord);
//        isImporting.set(false);
    }

    private void save() {
//        List<RobotKnowledgeExportTemplate> failures = importRobotKnowledge(readList, userId);
//        if (failures.size() > 0) {
//            allFailures.addAll(failures);
//        }
        readList.clear();
    }

    @Override
    public void onException(Exception ex, AnalysisContext context) {
        String value = ((ExcelDataConvertException) ex).getCellData().getStringValue();
        exportRecord.setStatus(finished);
        exportRecord.setTotalNumber(0);
        exportRecord.setSuccessNumber(0);
        exportRecord.setFailNumber(0);
        exportRecord.setDescription("导入数据格式异常: "+value);
//        exportRecordService.updateById(exportRecord);
//        isImporting.set(false);
//        throw new ServiceException(StringUtils.join("导入数据格式异常: ", value));
    }
}