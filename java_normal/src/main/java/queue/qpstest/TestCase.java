package queue.qpstest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TestCase
 * @Description
 * @Author Chaiphat
 * @Date 2020/7/21 20:42
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCase {

    private int elementCount;
    private Mode mode;
    private int producerCount;
    private int consumerCount;
}
