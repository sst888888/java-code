package guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Slf4j
public class demoMap {

    public static void main(String[] args) {
        ArrayListMultimap<Object, Object> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 8);
        multimap.put("month", 3);
        log.info("multimap = {}" , multimap);

        List<Object> list = multimap.get("day");
        log.info("multimap value by key = {}", list);

        Collection<Object> values = multimap.values();
        log.info("multimap values = {}", values);

        Map<Object, Collection<Object>> map = multimap.asMap();
        for (Object key : map.keySet()) {
            log.info(key + " : " + map.get(key));
        }

        // 改变原map
        map.get("day").add(20);
        log.info("" + multimap);

        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0,60),"fail");
        rangeMap.put(Range.closed(60,90),"satisfactory");
        rangeMap.put(Range.openClosed(90,100),"excellent");

        System.out.println(rangeMap.get(59));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(90));
        System.out.println(rangeMap.get(91));

        rangeMap.remove(Range.closed(70,80));
        System.out.println(rangeMap.get(75));

    }
}
