package map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;


public class HashMap {
    private static final Logger logger = LoggerFactory.getLogger(HashMap.class);

    public static void main(String[] args) {

        // 10是初始大小，0.75是装载因子，true是表示按照访问时间排序
        java.util.HashMap<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f, true);
        m.put(3, 11);
        m.put(1, 12);
        m.put(5, 23);
        m.put(2, 22);

        m.put(3, 26);

        for (Map.Entry e : m.entrySet()) {
            logger.info("key={}",e.getKey());
        }

    }
}
