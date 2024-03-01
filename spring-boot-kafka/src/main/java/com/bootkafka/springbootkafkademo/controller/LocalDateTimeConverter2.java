//package com.bootkafka.springbootkafkademo.controller;
//
//import com.alibaba.excel.converters.Converter;
//import com.alibaba.excel.converters.ReadConverterContext;
//import com.alibaba.excel.enums.CellDataTypeEnum;
//import com.alibaba.excel.metadata.GlobalConfiguration;
//import com.alibaba.excel.metadata.data.WriteCellData;
//import com.alibaba.excel.metadata.property.ExcelContentProperty;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
///**
// * @author: cp
// * @date: 2023-10-04 13:55
// */
//public class LocalDateTimeConverter2 implements Converter<LocalDateTime> {
//
//    @Override
//    public Class<LocalDateTime> supportJavaTypeKey() {
//        return LocalDateTime.class;
//    }
//
//    @Override
//    public CellDataTypeEnum supportExcelTypeKey() {
//        return CellDataTypeEnum.STRING;
//    }
//
//    @Override
//    public LocalDateTime convertToJavaData(ReadConverterContext<?> context) {
//        String stringValue = context.getReadCellData().getStringValue();
//        if (stringValue == null) {
//            return null;
//        }
//        // csv文件 时间处理
//        if (stringValue.indexOf("/") > 0) {
//            String[] split = stringValue.split(" ");
//            String s = split[0];
//            String[] split1 = s.split("/");
//            String day = split1[0];
//            String month = split1[1];
//            String year = split1[2];
//            if (day.length()==1) {
//                day = "0" + day;
//            }
//            if (month.length()==1) {
//                month = "0" + month;
//            }
//            stringValue = year + "-" + month + "-" + day + " " + split[1].substring(0,8);
//        }
//
//        if (stringValue.length() > 20) {
//            stringValue = stringValue.substring(0, 19);
//        }
//
//        return LocalDateTime.parse(stringValue, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
//
//    @Override
//    public WriteCellData<String> convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty,
//                                                    GlobalConfiguration globalConfiguration) {
//        return new WriteCellData<String>(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//    }
//
//}
