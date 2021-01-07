package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));

    }

    @Configuration
    @ComponentScan( // type = FilterType.ANNOTATION 기본값 생략가능
            includeFilters = @Filter(classes = MyIncludeComponent.class),
            excludeFilters = @Filter(classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
