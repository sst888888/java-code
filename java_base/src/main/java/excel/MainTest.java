package excel;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * @author: cp
 * @date: 2024-01-26 19:54
 */
public class MainTest {

    public static void main(String[] args) {
        // 设定Excel文件所在路径
        String excelFileName = "/Users/nuolan/Downloads/200521.xlsx";
        // 读取Excel文件内容
        List<ExcelDataVO> readResult = ExcelReader.readExcel(excelFileName);
        System.out.println(readResult);


        // 写入数据到工作簿对象内
        Workbook workbook = ExcelWriter.exportData(readResult);

//        // 以文件的形式输出工作簿对象
//        FileOutputStream fileOut = null;
//        try {
//            String exportFilePath = "/Users/nuolan/Downloads/200521-01.xlsx";
//            File exportFile = new File(exportFilePath);
//            if (!exportFile.exists()) {
//                exportFile.createNewFile();
//            }
//
//            fileOut = new FileOutputStream(exportFilePath);
//            workbook.write(fileOut);
//            fileOut.flush();
//        } catch (Exception e) {
//            log.warn("输出Excel时发生错误，错误原因：" + e.getMessage());
//        } finally {
//            try {
//                if (null != fileOut) {
//                    fileOut.close();
//                }
//                if (null != workbook) {
//                    workbook.close();
//                }
//            } catch (IOException e) {
//                log.warn("关闭输出流时发生错误，错误原因：" + e.getMessage());
//            }
//        }

    }


}
