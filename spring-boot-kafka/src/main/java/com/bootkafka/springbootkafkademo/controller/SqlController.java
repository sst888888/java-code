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
@RequestMapping("/sql")
public class SqlController {

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

    @PostMapping(value = "/generateDDP")
    public void generateDDP() throws SQLException {

        // 连接的数据库名
        String dataBase = JdbcUtils4Dddpay.getDataBase();

        // 生成sql的文件路径
        String filePath = "/Users/nuolan/Desktop/ddpay/sql2024/" + dataBase + "_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径 generateDDP :{}", filePath);

        List<String> allTableName = JdbcUtils4Dddpay.getAllTableName();
//        System.out.println("获取所有数据库的表名" + allTableName);

        for (String tableName : allTableName) {
            if (tableName.contains("2023")) {
                String createTableDDL = JdbcUtils4Dddpay.getCreateTableDDL(tableName);
                createTableDDL = createTableDDL.replace("2023", "2024");
                createTableDDL += ";";
                createTableDDL += "\r\n";
                createTableDDL += "\r\n";
                createTableDDL += "\r\n";
                StringToSql(createTableDDL, filePath + ".sql");
            }
        }
    }

    @PostMapping(value = "/test2")
    public void tes2t(@RequestParam String message) throws SQLException {

        // 连接的数据库名
        String dataBase = JdbcUtils.getDataBase();

        // 生成sql的文件路径
        String filePath = "/Users/nuolan/Desktop/ddpay/sql2024/cp_" + dataBase + "_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath);

        List<String> allTableName = JdbcUtils.getAllTableName();
//        System.out.println("获取所有数据库的表名" + allTableName);

        System.out.println("allTableName == " + allTableName.size());

        for (String tableName : allTableName) {
            System.out.println("---------------------------");
            if (tableName.contains("2023")) {
//                System.out.println("2023>>>>" + tableName);
                String createTableDDL = JdbcUtils.getCreateTableDDL(tableName);
//                log.info("获取建表语句：" + createTableDDL);

                createTableDDL = createTableDDL.replace("2023", "2024");
                createTableDDL += ";";
                createTableDDL += "\r\n";
                createTableDDL += "\r\n";
                createTableDDL += "\r\n";
                StringToSql(createTableDDL, filePath + ".sql");
            }
        }

        String tableName = "act_account_change_flow";
        System.out.println("获取建表语句：" + JdbcUtils.getCreateTableDDL(tableName));


        System.out.println("获取表的备注：" + JdbcUtils.getTableCommnet(tableName));

        System.out.println("打印字段信息：");
        List<String> columnComments = JdbcUtils.getColumnComments(tableName);
        ResultSetMetaData resultSetMetaData = JdbcUtils.getResultSetMetaData(tableName);
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println(tableName + "表中字段个数为：" + columnCount);
        for (int i = 1; i <= columnCount; i++) {
            System.out.print("java类型：" + resultSetMetaData.getColumnClassName(i));
            System.out.print("  数据库类型:" + resultSetMetaData.getColumnTypeName(i).toLowerCase());
            System.out.print("  字段名称:" + resultSetMetaData.getColumnName(i));
            System.out.print("  字段长度:" + resultSetMetaData.getColumnDisplaySize(i));
            System.out.print(" notNull：" + (resultSetMetaData.isNullable(i) == 1 ? false : true));
            System.out.println(" 注释为：" + columnComments.get(i - 1));
        }
    }

    /**
     * 表数据统计
     * @param message
     * @throws SQLException
     */
    @PostMapping(value = "/test3")
    public void test3(@RequestParam String message) throws SQLException {
        List<String> allTableName = JdbcUtils.getAllTableName();
        List<String> moreTable = new ArrayList<>();
        List<String> nullTable = new ArrayList<>();
        System.out.println("allTableName == " + allTableName.size());

        for (String tableName : allTableName) {
            int count = JdbcUtils.getCountOfTable(tableName);
            if (count > 0) {
                moreTable.add(tableName);
            } else {
                nullTable.add(tableName);
            }
        }

        System.out.println("moreTable == " + moreTable.size());
        for (int i = 0; i < moreTable.size(); i++) {
            System.out.println(moreTable.get(i));
        }
        System.out.println("moreTable == " + moreTable);
        System.out.println(nullTable);
    }

    /**
     * 所有表建表语句
     * @param message
     * @throws SQLException
     */
    @PostMapping(value = "/test4")
    public void test4(@RequestParam String message) throws SQLException {

        // 连接的数据库名
        String dataBase = JdbcUtils4Saas.getDataBase();

        // 生成sql的文件路径 yn-saas-new
        String filePath = "/Users/nuolan/Desktop/yn-saas-new/sql2023/" + dataBase + "_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath);

        List<String> allTableName = JdbcUtils4Saas.getAllTableName();
        System.out.println("获取所有数据库的表总数===" + allTableName.size()); // yn-saas-new prod-423  yn-saas prod-290
        System.out.println("获取所有数据库的表名===" + allTableName);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4Saas.getCreateTableDDL(tableName);
            createTableDDL += ";";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            StringToSql(createTableDDL, filePath + ".sql");
        }

        // 生成sql的文件路径 yn-saas
        filePath = "/Users/nuolan/Desktop/yn-saas/sql2023/" + dataBase + "_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");

        log.info("生成文件路径:{}", filePath);

        List<String> allTableNameYn = JdbcUtils4Yn.getAllTableName();
        System.out.println("获取所有数据库的表总数===" + allTableNameYn.size()); // yn-saas-new prod-423  yn-saas prod-290
        System.out.println("获取所有数据库的表名===" + allTableNameYn);

        for (String tableName : allTableNameYn) {
            String createTableDDL = JdbcUtils4Yn.getCreateTableDDL(tableName);
            createTableDDL += ";";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            StringToSql(createTableDDL, filePath + ".sql");
        }
    }

    @PostMapping(value = "/test5")
    public void test5(@RequestParam String message) throws SQLException {
        List<String> allTableNameYN = JdbcUtils4Yn.getAllTableName();
        List<String> allTableNameSAAS = JdbcUtils4Saas.getAllTableName();
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
        String filePath = "/Users/nuolan/Desktop/yn-saas/sql2023/saas_prod_same_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
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

        String filePath2 = "/Users/nuolan/Desktop/yn-saas-new/sql2023/saas_prod_same_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath2);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4Saas.getCreateTableDDL(tableName);
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
        String filePath = "/Users/nuolan/Desktop/yn-saas/sql2023/saas_prod_new_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
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


    @PostMapping(value = "/test6")
    public void test6(@RequestParam String message) throws SQLException {
        List<String> allTableNameYN = JdbcUtils4Yn.getAllTableNameFromDS();
        List<String> allTableNameSAAS = JdbcUtils4Saas.getAllTableNameFromDS();
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

        String filePath = "/Users/nuolan/Desktop/yn-saas/sql2023/saas_ds3_same_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath);

        for (String tableName : sameTables) {
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

        String filePath2 = "/Users/nuolan/Desktop/yn-saas-new/sql2023/saas_ds_same_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath2);

        for (String tableName : sameTables) {
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

        String filePath3 = "/Users/nuolan/Desktop/yn-saas/sql2023/saas_ds3_new_ddl_" + LocalDateTime.now().toLocalDate().toString().replace("-", "");
        log.info("生成文件路径:{}", filePath3);

        for (String tableName : diff) {
            String createTableDDL = JdbcUtils4YnDs3.getCreateTableDDL(tableName);
//            log.info("createTableDDL == {}", createTableDDL);
            createTableDDL += ";";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            StringToSql(createTableDDL, filePath3 + ".sql");
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


    @PostMapping(value = "/test8")
    public void test8(@RequestParam String message) throws SQLException {

        List<String> allTableName = JdbcUtils4YnDs3.getAllTableName();
        System.out.println("allTableName == " + allTableName.size());

        String fileName = "/Users/nuolan/Desktop/yn-saas/table-ds-all.txt";
        log.info("生成文件路径:{}", fileName);

        for (String tableName : allTableName) {
            String createTableDDL = JdbcUtils4YnDs3.getCreateTableDDL(tableName);
            if (createTableDDL != null) {
                int lastIndexOf = createTableDDL.lastIndexOf(") ENGINE=InnoDB");
                createTableDDL = createTableDDL.substring(lastIndexOf);
                int indexOf = createTableDDL.lastIndexOf("=");
                if (indexOf > 0) {
                    createTableDDL = createTableDDL.substring(indexOf + 1);
                }
                createTableDDL = tableName + " 注释 " + createTableDDL;
            }
            log.info("createTableDDL == {}", createTableDDL);
            createTableDDL += ";";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";
            createTableDDL += "\r\n";

            StringToSql(createTableDDL, fileName);
        }
    }


}
