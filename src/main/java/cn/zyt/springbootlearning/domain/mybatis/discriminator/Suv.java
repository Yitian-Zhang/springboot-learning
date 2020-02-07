package cn.zyt.springbootlearning.domain.mybatis.discriminator;

import lombok.Data;

@Data
public class Suv extends Vehicle {

    private Boolean allWheelDriver;

    public Suv() {}

    @Override
    public String toString() {
        return "Suv{" +
                "allWheelDriver=" + allWheelDriver +
                '}';
    }
}
