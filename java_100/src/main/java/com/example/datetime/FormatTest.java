package com.example.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.*;

@Slf4j
public class FormatTest {


    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final ThreadLocal<SimpleDateFormat> threadSafeSimpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.MONTH_OF_YEAR)
            .appendLiteral("/")
            .appendValue(ChronoField.DAY_OF_MONTH)
            .appendLiteral(" ")
            .appendValue(ChronoField.HOUR_OF_DAY)
            .appendLiteral(":")
            .appendValue(ChronoField.MINUTE_OF_HOUR)
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE)
            .appendLiteral(".")
            .appendValue(ChronoField.MILLI_OF_SECOND)
            .toFormatter();

    public static void main(String[] args) throws Exception {

        Date in = new Date();
        log.info("{}", Instant.now().toEpochMilli());
        log.info("{}", in.toInstant().toEpochMilli());
        log.info("{}", System.currentTimeMillis());
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        log.info("{}", in);
        log.info("{}", out);
    }

    private static void test() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        log.info("{}", simpleDateFormat.format(calendar.getTime()));
        log.info("{}", dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())));
        log.info("{}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    private static void wrong1() throws ParseException {
        //三个问题，YYYY、线程不变安全、不合法格式
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        log.info("defaultLocale: {}", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 29, 0, 0, 0);
        SimpleDateFormat YYYY = new SimpleDateFormat("YYYY-MM-dd");
        log.info("格式化: {}", YYYY.format(calendar.getTime()));
        log.info("weekYear: {}", calendar.getWeekYear());
        log.info("firstDayOfWeek:{}", calendar.getFirstDayOfWeek());
        log.info("minimalDaysInFirstWeek:{}", calendar.getMinimalDaysInFirstWeek());
    }

    // 没有特殊需求，针对年份的日期格式化，应该一律使用 “y” 而非 “Y”。
    private static void wrong1fix() throws ParseException {
        SimpleDateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 29, 23, 24, 25);
        log.info("格式化: {}", yyyy.format(calendar.getTime()));

        String dateString = "20160901";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        log.info("result:{}", dateFormat.parse(dateString));
    }

    @Test
    public void better() throws ParseException {
        // 错误操作
        String dateString = "20160901";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        log.info("result:{}", dateFormat.parse(dateString));

        // 正确操作
        LocalDateTime localDateTime = LocalDateTime.parse("2020/1/2 12:34:56.789", dateTimeFormatter);
        log.info(localDateTime.format(dateTimeFormatter));

        // 报错
        String dt = "20160901";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        log.info("result is {}",formatter.parse(dt));
    }

    // 定义的 static 的 SimpleDateFormat 可能会出现线程安全问题。
    @Test
    public void wrong2() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        log.info("{}", simpleDateFormat.parse("2020-01-01 11:12:13"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);

    }

    // 通过 ThreadLocal 来存放 SimpleDateFormat
    @Test
    public void wrong2fix() throws InterruptedException {
        ExecutorService threadPool = new ThreadPoolExecutor(100, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        log.info("{}", threadSafeSimpleDateFormat.get().parse("2020-01-01 11:12:13"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } finally {
                        threadSafeSimpleDateFormat.remove();
                    }
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("{}", threadSafeSimpleDateFormat.get());
    }

}
