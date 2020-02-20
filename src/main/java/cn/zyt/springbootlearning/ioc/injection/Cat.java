package cn.zyt.springbootlearning.ioc.injection;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Cat implements Animal {
    @Override
    public void use() {
        System.out.println("CAT can catch the mice!");
    }
}
