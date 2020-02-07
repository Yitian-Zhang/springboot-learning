package cn.zyt.springbootlearning.domain.mybatis.discriminator;

import lombok.Data;

@Data
public class Car extends Vehicle {

    private Integer doorCount;

    public Car() {

    }

    @Override
    public String toString() {
        return "Car{" +
                "doorCount='" + doorCount + '\'' +
                '}';
    }
}
