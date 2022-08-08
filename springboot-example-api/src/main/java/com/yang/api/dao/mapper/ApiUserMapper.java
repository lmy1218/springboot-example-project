package com.yang.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.api.dao.domain.ApiUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 17:29:00
 */
@Mapper
public interface ApiUserMapper extends BaseMapper<ApiUser> {
}
