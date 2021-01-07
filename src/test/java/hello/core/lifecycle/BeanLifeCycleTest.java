package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){

        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(NetworkClient.class); close 제공
//        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(NetworkClient.class); close 제공
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }


    @Configuration
    static class LifeCycleConfig{

//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://naver.com");
            return networkClient;
        }
    }
}
