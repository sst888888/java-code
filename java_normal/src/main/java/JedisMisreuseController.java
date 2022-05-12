import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName JedisMisreuseController
 * @Description TODO
 * @Author Chaiphat
 * @Date 2020/3/18 10:37
 * @Version 1.0
 **/
public class JedisMisreuseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(JedisMisreuseController.class);

    private static JedisPool jedisPool = new JedisPool("192.168.60.228",6379);

    @PostConstruct
    public void init(){
        try(Jedis jedis = new Jedis("192.168.60.228",6379)){
            Assert.isTrue("OK".equals(jedis.set("testa","1")),"set a = 1 return OK");
            Assert.isTrue("OK".equals(jedis.set("testb","2")),"set b = 2 return OK");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(()->jedisPool.close()));
    }

    public static void wrong() throws InterruptedException{
        try(Jedis jedis = new Jedis("192.168.60.228",6379)){
            Assert.isTrue("OK".equals(jedis.set("testa","1")),"set a = 1 return OK");
            Assert.isTrue("OK".equals(jedis.set("testb","2")),"set b = 2 return OK");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(()->jedisPool.close()));

        Jedis jedis = new Jedis("192.168.60.228",6379);

        new Thread(()->{
            for(int i = 0;i < 1000;i++){
                String result = jedis.get("testa");
                if(!"1".equals(result)){
                    LOGGER.warn("Expect testa to be 1 but found {}",result);
                    return;
                }
            }
        }).start();

        new Thread(()->{
            for(int i = 0;i < 1000;i++){
                String result = jedis.get("testb");
                if(!"2".equals(result)){
                    LOGGER.warn("Expect testb to be 1 but found {}",result);
                    return;
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);
    }

    public static void main(String[] args) {
        try {
            wrong();
        } catch (InterruptedException e) {
            //
        }
    }

}
