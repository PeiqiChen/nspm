package com.nspm.antlr4.validation;

import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author zc
 * @date 2019/09/18
 */
public class ZeeCustomConstraintValidator implements ConstraintValidator<ZeeCustomConstraint, ZeeCustomValidation> {
    private static Logger logger = LoggerFactory.getLogger(ZeeCustomConstraintValidator.class);
    private static ZeeCustomConstraintValidator.MyObjectMapper jsonMapper = new ZeeCustomConstraintValidator.MyObjectMapper();

    public ZeeCustomConstraintValidator() {
    }

    @Override
    public void initialize(ZeeCustomConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(ZeeCustomValidation obj, ConstraintValidatorContext constraintContext) {
        List<ZeeError> errs = obj.validate();
        if (errs != null && !errs.isEmpty()) {
            String jsonStr = null;

            try {
                jsonStr = jsonMapper.writeValueAsString(errs);
            } catch (JsonProcessingException var7) {
                StringBuilder sb = new StringBuilder();
                sb.append("JSON error while writing ZeeError: ").append(var7.getMessage()).append(". ZeeErrors: ");
                sb.append(errs);
                sb.append("\n");
                sb.append(var7.getOriginalMessage());
                jsonStr = sb.toString();
                logger.warn(ZeeErrorName.JSON_WRITE_EXCEPTION + ":" + jsonStr);
            }

            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(jsonStr).addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }

    private static class MyObjectMapper extends ObjectMapper {
        private static final long serialVersionUID = 1L;

        public MyObjectMapper() {
            this.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            this.disable(new MapperFeature[]{MapperFeature.USE_GETTERS_AS_SETTERS});
            this.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        }
    }
}
