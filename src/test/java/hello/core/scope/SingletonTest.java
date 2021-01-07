package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Singleton.class);

        Singleton bean1 = ac.getBean(Singleton.class);
        Singleton bean2 = ac.getBean(Singleton.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        assertThat(bean1).isSameAs(bean2);

        ac.close();
    }

    @Scope("singleton")
    static class Singleton {

        @PostConstruct
        public void init() {
            System.out.println("Singleton.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Singleton.destroy");
        }

    }
}
