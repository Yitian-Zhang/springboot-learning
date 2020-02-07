package cn.zyt.springbootlearning.dao;

import cn.zyt.springbootlearning.domain.mybatis.discriminator.Vehicle;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMapper {

    @Select("select * from tb_vehicle where id=#{id}")
    @ResultMap("vehicleMap")
    Vehicle getVehicle(Integer id);

    @Select("select * from tb_vehicle")
    @ResultMap("vehicleMap")
    List<Vehicle> getVehicleList();
}
