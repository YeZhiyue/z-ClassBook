package cn.ye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
//下面这个注解为了解决jason转换异常
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class PerEmail implements Serializable {
    private Integer id;
    private Integer user_from;
    private Integer user_to;
    private Date date;
    private String content;
    //后期弥补前期数据表设计的缺陷
    private User find_user_from;
    private User find_user_to;

    public PerEmail() {
    }

    public PerEmail(Integer user_from, Integer user_to, Date date, String content) {
        this.user_from = user_from;
        this.user_to = user_to;
        this.date = date;
        this.content = content;
    }
}
