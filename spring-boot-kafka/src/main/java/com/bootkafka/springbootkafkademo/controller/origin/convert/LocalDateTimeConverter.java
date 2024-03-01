package com.bootkafka.springbootkafkademo.controller.origin.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {
    @Override
    public Class<LocalDateTime> supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                           GlobalConfiguration globalConfiguration) {
        String value = cellData.getStringValue();
        BigDecimal numberValue = cellData.getNumberValue();
        if (null != numberValue) { //TODO时间戳处理
            long second = numberValue.multiply(new BigDecimal("86400")).longValue();
//            dd(numberValue);
//            System.out.println("second:"+second);
            Instant instant = Instant.ofEpochSecond(second-2209190400L);
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//            double doubleValue = numberValue.doubleValue();
//            Date javaDate = DateUtil.getJavaDate(doubleValue, contentProperty.getDateTimeFormatProperty().getUse1904windowing(), null);
//            String format = DateUtils.format(javaDate, contentProperty.getDateTimeFormatProperty().getFormat());
            return localDateTime;
        }

        int length = value.length();
        if (length == 26 ) {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")); // 26位
        }

        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 19位
    }


    @Override
    public CellData<String> convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) {
        return new CellData<>(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private void dd(BigDecimal numberValue) {
        int days = numberValue.intValue();
        int mills = (int)Math.round(numberValue.subtract(new BigDecimal(days)).doubleValue() * 24 * 3600);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900, 0,1);
        calendar.add(Calendar.DATE, days);
        int hour = mills/ 3600;
        int minute = (mills - hour * 3600) / 60;
        int second = mills - hour * 3600 - minute * 60;
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Date time = calendar.getTime();
        long longValue = numberValue.longValue();
        long longValue2 = numberValue.longValue();
    }

    public static void main(String[] args) {
        // 1696422983268
        // 3902667635
        // 45169.76430011574
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now());
        String string = "2023-08-31 18:20:35.530000";
        LocalDateTime localDateTime = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        System.out.println(localDateTime);
    }

}
