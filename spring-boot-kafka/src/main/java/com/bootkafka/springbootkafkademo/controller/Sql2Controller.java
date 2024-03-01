package com.bootkafka.springbootkafkademo.controller;

import com.bootkafka.springbootkafkademo.util.JdbcUtils;
import com.bootkafka.springbootkafkademo.util.JdbcUtils4Saas;
import com.bootkafka.springbootkafkademo.util.JdbcUtils4Saas111;
import com.bootkafka.springbootkafkademo.util.JdbcUtils4YnDs3;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController

@Slf4j
@RequestMapping("/sql2")
public class Sql2Controller {

    @PostMapping(value = "/test")
    public void test() throws IOException {
        String fileName = "/Users/nuolan/Desktop/yn-saas/table-has-yh.txt";
        File file = new File(fileName);
        FileReader fr = null;
        List<String> tableName = new ArrayList<>();
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            while (true) {
                try {
                    if (!((line = br.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // 一行一行地处理...
                String[] split = line.split(" ");
                System.out.println(split[0]);
                if (!split[0].startsWith("4")) {
                    tableName.add(split[0]);
                }
            }
        }

        generateCreateTableDDL(tableName);

    }


    private void generateCreateTableDDL(List<String> allTableName) {
        String filePath = "/Users/nuolan/Desktop/yn-saas/sql2023/saas_prod_42_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4YnDs3.getCreateTableDDL(tableName);
//            log.info("createTableDDL == {}", createTableDDL);
            if (createTableDDL != null) {
                int lastIndexOf = createTableDDL.lastIndexOf(") ENGINE=InnoDB");
                createTableDDL = createTableDDL.substring(0, lastIndexOf);
            }
            createTableDDL += ";";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            StringToSql(createTableDDL, filePath + ".sql");
        }

        String filePath2 = "/Users/nuolan/Desktop/yn-saas-new/sql2023/saas_prod_42_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath2);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4Saas111.getCreateTableDDL(tableName);
//            log.info("createTableDDL == {}", createTableDDL);
            if (createTableDDL != null) {
                int lastIndexOf = createTableDDL.lastIndexOf(") ENGINE=InnoDB");
                createTableDDL = createTableDDL.substring(0, lastIndexOf);
            }
            createTableDDL += ";";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            StringToSql(createTableDDL, filePath2 + ".sql");
        }
    }


    /**
     * 将生成等String字符串 写进sql文件
     *
     * @param str  String字符串
     * @param path sql文件路径路径
     */
    public static void StringToSql(String str, String path) {
        byte[] sourceByte = str.getBytes();
        FileOutputStream os = null;
        if (null != sourceByte) {
            try {
                //文件路径（路径+文件名）
                File file = new File(path);
                //文件不存在则创建文件，先创建目录
                if (!file.exists()) {
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                //文件输出流用于将数据写入文件
                os = new FileOutputStream(file, true);
                os.write(sourceByte);
                os.flush();
                log.info("生成成功!!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭文件输出流
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 表数据统计
     */
    @PostMapping(value = "/table/size")
    public void test3() {
        List<String> allTableName = JdbcUtils4Saas.getAllTableName();
        List<String> moreTable = new ArrayList<>();
        System.out.println("allTableName == " + allTableName.size());

        for (String tableName : allTableName) {
            int count = JdbcUtils4Saas.getCountOfTable(tableName);
            if (count > 0) {
                moreTable.add(tableName);
            }
        }

        System.out.println("moreTable size == " + moreTable.size());
        System.out.println("moreTable == " + moreTable);

        for (int i = 0; i < moreTable.size(); i++) {
            System.out.println(moreTable.get(i));
        }

        moreTable.forEach(System.out::println);

    }

}
