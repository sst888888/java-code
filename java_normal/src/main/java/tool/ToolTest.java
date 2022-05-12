package tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ToolTest {
    @Autowired
    private SerialNumberGenerator serialNumberGenerator;

    @Test
    public void testGenerateID() {
        String id = serialNumberGenerator.generate("cs");
        log.info("id is {}", id);
    }

}
