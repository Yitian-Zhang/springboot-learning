package cn.zyt.springbootlearning.ioc.injection;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public void use() {
        System.out.println("DOG can save person!");
    }
}
