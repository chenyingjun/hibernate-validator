package com.chenyingjun.validator.rest;

import com.chenyingjun.validator.entity.User;
import com.chenyingjun.validator.utils.FormValid;
import com.chenyingjun.validator.utils.RestObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 参数检验相关api
 *
 * @author chenyingjun
 * @version 2017年05月05日
 * @since 1.0
 */
@Api(description = "参数检验相关api")
@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value = "检验用户", notes = "检验用户")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true)
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public RestObject testUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FormValid formValid = new FormValid(bindingResult);
            return RestObject.newRestObject(RestObject.STATUS_PARAMETER_ERROR, "", formValid);
        }

        RestObject rest = RestObject.newOk("11111");
        System.out.println(" user:" + user);
        rest.setData(user);
        return rest;
    }
}
