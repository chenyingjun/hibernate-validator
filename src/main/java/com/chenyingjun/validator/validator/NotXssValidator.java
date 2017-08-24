package com.chenyingjun.validator.validator;

import com.chenyingjun.validator.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Title: NotXssValidator.java
 * @Package com.red.p2p.validator
 * @Description: XSS验证注解验证器
 * @author: chenyingjun
 * @date: 2017年8月24日
 * @version V1.0
 */
public class NotXssValidator implements ConstraintValidator<NotXss, String> {

    /** maxLength:最大长度 */
    private int maxLength;

    /** minLength:最小长度 */
    private int minLength;

    @Override
    public void initialize(NotXss constraintAnnotation) {
        this.maxLength = constraintAnnotation.maxLength();
        this.minLength = constraintAnnotation.minLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.isBlank(value)) {

            return true;
        }

        if (minLength > 0) {
            if (value.length() < minLength) {
                context.buildConstraintViolationWithTemplate("${data.minLength}");
                return false;
            }
        }

        if (maxLength > 0) {
            if (value.length() > maxLength) {
                context.buildConstraintViolationWithTemplate("${data.maxLength}");
                return false;
            }
        }

        boolean status = StringUtil.checkXss(value);

        return status;
    }
}
