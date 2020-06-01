package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "服务对象")
@Document(collection = "FILTER_SERVICE_OBJECT")
@Data
public class FilterServiceObjectPO extends FilterServiceGroupPO implements Serializable {

//    public FilterServiceObjectPO() {
//        this.setDeviceObjectType(DeviceObjectTypeEnum.SERVICE_OBJECT);
//    }
//
//    public FilterServiceObjectPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, List<String> includeFilterServiceNamesList, List<String> includeFilterServiceGroupNamesList) {
//        super(name, description, tagsList, lineNumbers, includeFilterServiceNamesList, includeFilterServiceGroupNamesList);
//        this.setDeviceObjectType(DeviceObjectTypeEnum.SERVICE_OBJECT);
//    }
//
//    public FilterServiceObjectPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, Boolean isImplicit, List<String> includeFilterServiceNamesList, List<String> includeFilterServiceGroupNamesList) {
//        super(name, description, tagsList, lineNumbers, isImplicit, includeFilterServiceNamesList, includeFilterServiceGroupNamesList);
//        this.setDeviceObjectType(DeviceObjectTypeEnum.SERVICE_OBJECT);
//    }
//
//    public FilterServiceObjectPO(FilterServiceGroupPO item) {
//        super(item);
//        this.setDeviceObjectType(DeviceObjectTypeEnum.SERVICE_OBJECT);
//    }
}
