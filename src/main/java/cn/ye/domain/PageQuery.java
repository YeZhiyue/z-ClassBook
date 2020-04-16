package cn.ye.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PageQuery {
    private int msgNum;
    private int pageNum;
    private Map<String,Integer> eachClassNum;
    private List<User> users;
    private List<PerPassage> perPassages;
}
