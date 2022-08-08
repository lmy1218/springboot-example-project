package com.yang.api.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.api.dao.domain.ApiUser;
import com.yang.api.dao.mapper.ApiUserMapper;
import org.springframework.stereotype.Component;

/**
 * @author Yang
 * @version 1.0.0
 * @description User-Service 业务下沉
 * @createTime 2022年08月05日 18:10:00
 */
@Component
public class ApiUserManager extends ServiceImpl<ApiUserMapper, ApiUser> {



    /**
     * 根据账号获取用户
     *
     * @author Yang
     * @date 2022/8/5 18:17
     * @param account:
     * @return ApiUser
     **/
    public ApiUser getUserByAccount(String account) {
        if (StrUtil.isBlank(account)) {
            return null;
        }

        return baseMapper.selectOne(Wrappers.lambdaQuery(ApiUser.class).eq(ApiUser::getAccount, account));
    }
}
