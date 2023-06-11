package com.my.springbootinit.esdao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.springbootinit.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 * @author <a href="https://github.com/limy">程序员鱼皮</a>
 * @from <a href="https://my.icu">编程导航知识星球</a>
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);


}