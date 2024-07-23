package catchfinally;

import lombok.Data;

import java.util.Objects;

/**
 * @author: cp
 * @date: 2024-07-23 13:19
 */
@Data
public class Student1 extends Student{
    private String name;

    public Student1(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)    //检测this与obj是否指向同一对象。这条语句是一个优化，避免直接去比较同一对象的各个域
            return true;

        Student other = (Student) obj;  //将obj转换成相应的Student类型
        return Objects.equals(this.name, other.getName());

    }
}
