package cn.zyt.springbootlearning.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class ValidatorPojo {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotNull
    @Future(message = "需要一个未来的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "10000.00")
    private Double doubleValue;


    @NotNull
    @Min(value = 1, message = "最小值为1")
    @Max(value = 88, message = "最大值为88")
    private Integer integer;

    @Range(min = 1, max = 888, message = "取值范围为1到888")
    private Long range;

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度要求为[20, 30]")
    private String size;

}
