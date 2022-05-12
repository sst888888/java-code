import common.aspect.ApiMonitorAspect;
import common.util.BeanFactory;
import common.util.BeanUtils;
import org.junit.Test;
import service.ISingleDemoSrv;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName BeanUtilsTest
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 17:04
 * @Version 1.0
 **/
public class BeanUtilsTest {

    @Test
    public void test1(){
        Set<Class<?>> typesAnnotatedSet = (Set<Class<?>>) BeanUtils.scanAnnotationByPackageName("service.impl", ElementType.TYPE);
        Set<Method> methodsAnnotatedSet = (Set<Method>) BeanUtils.scanAnnotationByPackageName("service.impl", ElementType.METHOD);
        System.out.println(typesAnnotatedSet.size());
        System.out.println(methodsAnnotatedSet.size());
        methodsAnnotatedSet.forEach(System.out::println);
    }

    @Test
    public void test2() {
        try {
            BeanFactory.init("service", ApiMonitorAspect.class);
            ISingleDemoSrv singleDemoSrv = (ISingleDemoSrv) BeanFactory.getBean("singleDemoSrv");
            singleDemoSrv.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        List<Integer> collect1 = IntStream.rangeClosed(1, 24).mapToObj(__ -> 0).collect(Collectors.toList());
        System.out.println("====" + collect1);
        int oldCapacity = 8;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
            List<String> l1 = new ArrayList<String>();
            l1.add("A");
            l1.add("B");
            l1.add("C");
            l1.add("D");
            l1.add("E");
            l1.add("F");
            List<String> l2 = new ArrayList<String>();
            l2.add("E");
            l2.add("F");
            l2.add("G");
            l2.add("H");
            l2.add("I");
            l2.add("J");
            // List差集
            l1.removeAll(l2);
            System.out.println("List差集：" + l1);

        List<Integer> collect = IntStream.rangeClosed(1, 24).mapToObj(__ -> __).collect(Collectors.toList());
        System.out.println(collect);

        String str = "2019-09-08 01:00:00";
        String str1 = "2019-09-08 11:00:00";
        String str2 = "2019-09-08 1";
        List<String> strList = Arrays.asList(str,str1,str2);
//        int i = str.indexOf(":");
//        String[] split = str.split(" ");
//        String s = split[1];
        strList.forEach(item -> {
            int i = item.indexOf(":");
            if(i > 0) {
                String s = item.substring(item.indexOf(":") - 2, item.indexOf(":"));
                if (s.startsWith("0")) {
                    s = s.replace("0", "");
                }
                System.out.println(s);
            }

            String[] split = item.split(" ");
            String s = split[1];
            s = s.substring(0,2);
            if (s.startsWith("0")) {
                s = s.replace("0", "");
            }
            System.out.println("split " + s);
        });

    }


    }


