import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ListRemove
 * @Description
 * @Author Chaiphat
 * @Date 2020/9/5 10:45
 * @Version 1.0
 **/
public class ListRemove {

    public static void main(String[] args) {
        List<Integer> oldList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> newList = new ArrayList<>(Arrays.asList(2, 5));

        oldList.removeAll(newList);
        System.out.println(newList + "----" + oldList);

        String str = "DLS_001";
        System.out.println(Integer.valueOf(str.substring(str.length()-2)));
    }

}
