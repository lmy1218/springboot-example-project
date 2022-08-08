package com.yang.api.service;

import com.yang.api.pojo.bo.PageUserListBo;
import com.yang.api.pojo.bo.SaveUserBo;
import com.yang.api.pojo.vo.ApiPageVo;
import com.yang.api.pojo.vo.PageUserListResultVo;

/**
 * @author Yang
 * @version 1.0.0
 * @createTime 2022年08月05日 18:01:00
 */
public interface IApiUserService {

    /**
     * 保存用户
     *
     * @author Yang
     * @date 2022/8/5 18:07
     * @param userBo:
     * @return void
     **/
    void saveUser(SaveUserBo userBo);

    /**
     * 分页查询
     *
     * @author Yang
     * @date 2022/8/5 18:31
     * @param pageBo:
     * @return ApiPageVo<PageUserListResultVo>
     **/
    ApiPageVo<PageUserListResultVo> pageUserList(PageUserListBo pageBo);
}
