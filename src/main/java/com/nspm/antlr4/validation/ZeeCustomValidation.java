package com.nspm.antlr4.validation;

import com.nspm.antlr4.exceptions.ZeeError;

import java.util.List;

/**
 * @author zc
 * @date 2019/09/18
 */
public interface ZeeCustomValidation {
    List<ZeeError> validate();
}
