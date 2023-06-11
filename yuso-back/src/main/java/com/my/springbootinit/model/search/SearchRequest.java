package com.my.springbootinit.model.search;

import com.my.springbootinit.common.PageRequest;
import lombok.Data;

import java.io.Serializable;


@Data
public class SearchRequest extends PageRequest implements Serializable {

    /*
    搜索词
     */
    private String SearchText;

    private String type;

    private static final long serialVersionUID = 1L;
}
