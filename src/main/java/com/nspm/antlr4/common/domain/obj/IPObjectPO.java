package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import io.swagger.annotations.ApiModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "地址对象")
@Document(collection = "IP_OBJECT")
public class IPObjectPO extends IPGroupPO implements Serializable {
    public IPObjectPO() {
        this.setDeviceObjectType(DeviceObjectTypeEnum.NETWORK_OBJECT);
    }

    public IPObjectPO(String name) {
        super(name);
        this.setDeviceObjectType(DeviceObjectTypeEnum.NETWORK_OBJECT);
    }

    public IPObjectPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, Boolean isImplicit, IPTypeEnum type, List<IPItemPO> includeItemsList, List<String> includeItemNamesList, List<String> includeGroupNamesList, List<IPItemPO> excludeItemsList, List<String> excludeItemNamesList, List<String> excludeGroupNamesList) {
        super(name, description, tagsList, lineNumbers, isImplicit, type, includeItemsList, includeItemNamesList, includeGroupNamesList, excludeItemsList, excludeItemNamesList, excludeGroupNamesList);
        this.setDeviceObjectType(DeviceObjectTypeEnum.NETWORK_OBJECT);
    }
}
