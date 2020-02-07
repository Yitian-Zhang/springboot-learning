package cn.zyt.springbootlearning.domain.mybatis.discriminator;

import lombok.Data;

@Data
public class Truck extends Vehicle {

    private Integer boxSize;
    private String capacity;

    public Truck() {

    }

    @Override
    public String toString() {
        return "Truck{" +
                "boxSize=" + boxSize +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
