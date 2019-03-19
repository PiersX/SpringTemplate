package com.piers.template.controller;

import com.piers.template.data.response.BaseResponse;
import com.piers.template.data.response.PageResponse;
import com.piers.template.data.vo.UserVO;
import com.piers.template.service.UserService;
import com.piers.template.utils.Const;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author GuoXueqiang
 * @date 19-3-11 下午2:42
 */
@Controller
@RequestMapping(value = "/test")
@Api(value="Test API", description="Template相关API")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/user/id",method = RequestMethod.GET)
    @ApiOperation(notes="Get User Info", httpMethod="GET", value="Get User Info")
    public BaseResponse<UserVO> getUserInfo(
            @ApiParam(value="user id", required=true) @RequestParam(required=true) int userId
    ) {
        BaseResponse<UserVO> baseResponse = new BaseResponse<>();
        UserVO userVO = userService.getById(userId);
        baseResponse.setResultCode(Const.SUCCESS);
        baseResponse.setData(userVO);
        return baseResponse;
    }





}
