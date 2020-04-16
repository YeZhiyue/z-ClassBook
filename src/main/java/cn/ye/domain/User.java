package cn.ye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 定义的User类，对应了数据库中的User表
 */
@Data
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String sex;
    private String phone;
    private String img;
    private String qq;
    private String wChat;
    private String className;
    private Date birthday;
    private Date login_data;
    private String hobby;
    private String introduce;
    private List<PerEmail> perEmails;
    private List<PerEmail> sendEmails;
    private List<PerPassage> perPassages;
    //比较特殊的用途，不是用于数据库操作，用于验证用户是否为登录用户
    private Boolean isHost;

    public User() {

    }

    //特殊情况需要定义该构造
    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String password, String email, String sex,
                String phone, String img, String qq, String wChat, String className,
                Date birthday, Date login_data, String hobby, String introduce) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.img = img;
        this.qq = qq;
        this.wChat = wChat;
        this.className = className;
        this.birthday = birthday;
        this.login_data = login_data;
        this.hobby = hobby;
        this.introduce = introduce;
    }
}
