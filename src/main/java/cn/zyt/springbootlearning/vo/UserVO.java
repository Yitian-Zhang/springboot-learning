package cn.zyt.springbootlearning.vo;

import lombok.Data;

/**
 * User View Object
 * @author yitian
 */
@Data
public class UserVO {
    private Long id;
    private String userName;
    private int sexCode;
    private String sexName;
    private String note;

    public UserVO(Long id, String userName, int sexCode, String sexName, String note) {
        this.id = id;
        this.userName = userName;
        this.sexCode = sexCode;
        this.sexName = sexName;
        this.note = note;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sexCode=" + sexCode +
                ", sexName='" + sexName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
