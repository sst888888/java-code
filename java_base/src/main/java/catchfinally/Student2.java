package catchfinally;

import lombok.Data;

/**
 * @author: cp
 * @date: 2024-07-23 13:19
 */
@Data
public class Student2 extends Student{
    private String name;

    public Student2(String name) {
        this.name = name;
    }
}
