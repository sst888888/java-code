//package com.bootkafka.springbootkafkademo.controller;
//
//import com.alibaba.excel.converters.Converter;
//import com.alibaba.excel.converters.ReadConverterContext;
//import com.alibaba.excel.enums.CellDataTypeEnum;
//import com.alibaba.excel.metadata.GlobalConfiguration;
//import com.alibaba.excel.metadata.data.WriteCellData;
//import com.alibaba.excel.metadata.property.ExcelContentProperty;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
///**
// * @author: cp
// * @date: 2023-10-04 13:58
// */
//public class LocalDateConverter2 implements Converter<LocalDate> {
//    @Override
//    public Class<LocalDate> supportJavaTypeKey() {
//        return LocalDate.class;
//    }
//
//    @Override
//    public CellDataTypeEnum supportExcelTypeKey() {
//        return CellDataTypeEnum.STRING;
//    }
//
//    @Override
//    public LocalDate convertToJavaData(ReadConverterContext<?> context) {
//        return LocalDate.parse(context.getReadCellData().getStringValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    }
//
//    @Override
//    public WriteCellData<String> convertToExcelData(LocalDate value, ExcelContentProperty contentProperty,
//                                                    GlobalConfiguration globalConfiguration) {
//        return new WriteCellData<String>(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//    }
//}
