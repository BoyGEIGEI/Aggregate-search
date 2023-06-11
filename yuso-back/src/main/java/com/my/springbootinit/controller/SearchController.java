package com.my.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.springbootinit.common.BaseResponse;
import com.my.springbootinit.common.ErrorCode;
import com.my.springbootinit.common.ResultUtils;
import com.my.springbootinit.esdao.PostEsDao;
import com.my.springbootinit.exception.BusinessException;
import com.my.springbootinit.exception.ThrowUtils;
import com.my.springbootinit.manager.SearchFacade;
import com.my.springbootinit.model.dto.picture.PictureQueryRequest;
import com.my.springbootinit.model.dto.post.PostEsDTO;
import com.my.springbootinit.model.dto.post.PostQueryRequest;
import com.my.springbootinit.model.dto.user.UserQueryRequest;
import com.my.springbootinit.model.entity.Picture;
import com.my.springbootinit.model.entity.Post;
import com.my.springbootinit.model.enums.SearchTypeEnum;
import com.my.springbootinit.model.search.SearchRequest;
import com.my.springbootinit.model.vo.PostVO;
import com.my.springbootinit.model.vo.SearchVO;
import com.my.springbootinit.model.vo.UserVO;
import com.my.springbootinit.service.PictureService;
import com.my.springbootinit.service.PostService;
import com.my.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private SearchFacade searchFacade;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    @Resource
    private PictureService pictureService;


    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }
}