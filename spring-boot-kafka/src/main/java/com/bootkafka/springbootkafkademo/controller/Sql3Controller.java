package com.bootkafka.springbootkafkademo.controller;

import com.bootkafka.springbootkafkademo.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/sql3")
public class Sql3Controller {

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


    @PostMapping(value = "/test")
    public void test() throws SQLException {
        List<String> allTableNameYN = JdbcUtils4Yn.getAllTableName();
        List<String> allTableNameSAAS = JdbcUtils4GjSaasMaster.getAllTableName();
        log.info("获取所有数据库的表总数 allTableNameYN ==={}", allTableNameYN.size()); //   yn-saas prod-290
        log.info("获取所有数据库的表总数 allTableNameSAAS ==={}", allTableNameSAAS.size()); // yn-saas-new prod-423

        List<String> diff = new ArrayList<>();
        List<String> sameTables = new ArrayList<>();
        for (String tableName : allTableNameYN) {
            if (allTableNameSAAS.contains(tableName)) {
                sameTables.add(tableName);
            } else {
                diff.add(tableName);
            }
        }

        log.info("not exist at allTableNameSAAS size == {}", diff.size());
        log.info("sameTables size == {}", sameTables.size());
        log.info("not exist at allTableNameSAAS == {}", diff);

        log.info("sameTables is == {}", sameTables);

        generateCreateTableDDL(sameTables);
        generateCreateTableDDLNotExist(diff);
    }

    private void generateCreateTableDDL(List<String> allTableName) {
        String filePath = "/Users/nuolan/Desktop/yn-saas/sql2023/10/saas_prod_same_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4Yn.getCreateTableDDL(tableName);
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

        String filePath2 = "/Users/nuolan/Desktop/yn-saas-new/sql2023/10/saas_prod_same_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath2);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4GjSaasMaster.getCreateTableDDL(tableName);
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

    private void generateCreateTableDDLNotExist(List<String> allTableName) {
        String filePath = "/Users/nuolan/Desktop/yn-saas/sql2023/10/saas_prod_new_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4Yn.getCreateTableDDL(tableName);
//            log.info("createTableDDL == {}", createTableDDL);
//            if (createTableDDL != null) {
//                int lastIndexOf = createTableDDL.lastIndexOf(")");
//                createTableDDL = createTableDDL.substring(0, lastIndexOf);
//            }

            createTableDDL += ";";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            StringToSql(createTableDDL, filePath + ".sql");
        }
    }




    public static void main(String[] args) throws IOException {
        String fileName = "/Users/nuolan/Desktop/yn-saas/table-has-db.txt";
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
                tableName.add(line.trim());
            }
        }

        System.out.println(tableName);
        System.out.println(tableName.size());

        fileName = "/Users/nuolan/Desktop/yn-saas/table-has-yh.txt";
        file = new File(fileName);
        List<String> tableName2 = new ArrayList<>();
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
                tableName2.add(line.trim());
            }
        }

        System.out.println(tableName2);
        System.out.println(tableName2.size());

        List<String> diff = new ArrayList<>();
        for (String s : tableName) {
            if (!tableName2.contains(s)) {
                diff.add(s);
            }
        }

        System.out.println(diff);
        System.out.println(diff.size());

        List<String> diff2 = new ArrayList<>();
        for (String s : tableName2) {
            if (!tableName.contains(s)) {
                diff2.add(s);
            }
        }

        System.out.println(diff2);
        System.out.println(diff2.size());
    }





}
