package cn.zyt.springbootlearning.domain.mybatis.discriminator;

import lombok.Data;

import java.util.Date;

@Data
public class Vehicle {
    private Integer id; // id
    private String brand;
    private String color;
    private Date productDate;
    private Integer vehicleType;

    public Vehicle() {

    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", productDate=" + productDate +
                ", type='" + vehicleType + '\'' +
                '}';
    }
}
