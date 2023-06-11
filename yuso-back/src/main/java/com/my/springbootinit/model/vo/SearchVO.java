package com.my.springbootinit.model.vo;



import com.my.springbootinit.model.entity.Picture;
import com.my.springbootinit.model.entity.Post;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * 集合搜索
 *
 */
@Data
public class SearchVO implements Serializable {

    private List<UserVO> userList;

    private List<PostVO> postList;

    private List<Picture> pictureList;

    private List<?> dataList;

    private static final long serialVersionUID = 1L;


}
