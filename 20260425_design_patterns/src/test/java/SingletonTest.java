import com.org.StartApp;
import com.org.creational.DclSingleton;
import com.org.creational.EagerSingleton;
import com.org.creational.GlobalCounter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@SpringBootTest(classes = StartApp.class)
public class SingletonTest {


    @Test
    public void test01() {
        GlobalCounter.INSTANCE.getNumber();
        System.out.println("(EagerSingleton.getInstance() == EagerSingleton.getInstance()) = " + (EagerSingleton.getInstance() == EagerSingleton.getInstance()));
    }


    @Test
    public void testReflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<DclSingleton> clazz = DclSingleton.class;
        Constructor<DclSingleton> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        boolean flag = DclSingleton.getInstance() == constructor.newInstance();
        log.info("flag -> {}",flag);
    }
}
