package com.yang.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yang.api.dao.domain.ApiUser;
import com.yang.api.dao.mapper.ApiUserMapper;
import com.yang.api.manager.ApiUserManager;
import com.yang.api.pojo.bo.PageUserListBo;
import com.yang.api.pojo.bo.SaveUserBo;
import com.yang.api.pojo.vo.ApiPageVo;
import com.yang.api.pojo.vo.PageUserListResultVo;
import com.yang.api.service.IApiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yang
 * @version 1.0.0
 * @description User-Service
 * @createTime 2022年08月05日 18:02:00
 */
@Slf4j
@Service
public class ApiUserServiceImpl implements IApiUserService {

    @Resource private ApiUserMapper userMapper;
    @Resource private ApiUserManager userManager;

    @Override
    public void saveUser(SaveUserBo userBo) {
        ApiUser user = BeanUtil.copyProperties(userBo, ApiUser.class);
        // 验证用户是否存在
        ApiUser dbUser = userManager.getUserByAccount(userBo.getAccount());
        if (ObjectUtil.isNull(dbUser)) {
            // 新增
            userMapper.insert(user);
            return;
        }

        // 更新
        user.setId(dbUser.getId());
        user.setDeleted(false);
        userMapper.updateById(user);
    }

    @Override
    public ApiPageVo<PageUserListResultVo> pageUserList(PageUserListBo pageBo) {
      // 分页查询



        return null;
    }

}
