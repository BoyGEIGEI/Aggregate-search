package com.my.springbootinit.service;



import com.my.springbootinit.model.entity.Picture;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface PictureService {
    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);
}
