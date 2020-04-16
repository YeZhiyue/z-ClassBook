package cn.ye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class PerPassage implements Serializable {
    private Integer id;
    private Integer edit_user;
    private Date date;
    private String content;
    private User user;

    public PerPassage() {
    }

    public PerPassage(Integer edit_user, Date date, String content) {
        this.edit_user = edit_user;
        this.date = date;
        this.content = content;
    }
}
