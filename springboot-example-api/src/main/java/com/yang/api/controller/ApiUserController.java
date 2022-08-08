package com.yang.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yang.api.pojo.bo.PageUserListBo;
import com.yang.api.pojo.bo.SaveUserBo;
import com.yang.api.pojo.vo.ApiPageVo;
import com.yang.api.pojo.vo.PageUserListResultVo;
import com.yang.api.pojo.vo.PageUserListVo;
import com.yang.api.pojo.vo.SaveUserVo;
import com.yang.api.service.IApiUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.awt.*;

/**
 * @author Yang
 * @version 1.0.0
 * @description 示例
 * @createTime 2022年08月05日 17:59:00
 */
@Api("示例")
@RestController("/api/user")
public class ApiUserController {

    @Resource private IApiUserService userServiceImpl;

    @ApiOperation("保存用户")
    @PostMapping("/save")
    public void saveUser(@RequestBody @Valid SaveUserVo userVo) {

        userServiceImpl.saveUser(BeanUtil.copyProperties(userVo, SaveUserBo.class));
    }


    @ApiOperation("分页查询用户")
    @GetMapping
    public ApiPageVo<PageUserListResultVo> pageUserList(@Valid PageUserListVo userVo) {

        return userServiceImpl.pageUserList(BeanUtil.copyProperties(userVo, PageUserListBo.class));
    }



}
