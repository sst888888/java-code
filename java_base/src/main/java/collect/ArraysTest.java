package collect;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ArraysTest {

    private static final Logger logger = LoggerFactory.getLogger(ArraysTest.class);

    private static void asListTest() {

        String[] strArray = {"aa", "bb", "cc"};
        List<String> sList = Arrays.asList(strArray);
        for (String str : sList) {//能遍历出各个元素
            logger.info(str);
        }
        logger.info("sList size is {}", sList.size());


        logger.info("- - - - - - - - - - -");

        int[] intArray = {11, 22, 33};
        List intList = Arrays.asList(intArray);
        for (Object o : intList) {//就一个元素
            logger.info("---{}", o);
        }


        logger.info("- - - - - - - - - - -");

        Integer[] integerArray = {11, 22, 33};
        List<Integer> objList = Arrays.asList(integerArray); //数组里的每一个元素都是作为list中的一个元素
        for (int a : objList) {
            logger.info("---{}", a);
        }

    }


    public static void main(String[] args) {

        asListTest();
        Map<String, String> map = new HashMap<>();
        map.put("a","A");
        map.put("b","B");
        String v = map.put("b","v"); // 输出 B
        System.out.println(v);
        System.out.println(map.get("b"));
        String v1 = map.put("c","v");
        System.out.println(v1); // 输出：NULL
    }

}