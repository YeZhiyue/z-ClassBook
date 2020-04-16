package cn.ye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Img implements Serializable {
    private Integer id;
    private String img_name;
    private String img_type;
    private String  is_use;
//    private Boolean is_use;

}
