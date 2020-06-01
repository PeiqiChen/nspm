package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.obj.ProtocolRangePO;
import com.nspm.antlr4.common.enums.ProtocolNameEnum;
import com.nspm.antlr4.common.enums.ServiceNameEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/10
 */
@ApiModel("协议匹配对象")
public class ProtocolMatchSpecPO implements Serializable {
    @ApiModelProperty("协议匹配类型")
    private ProtocolMatchSpecType type;
    private String nameRef;
    @ApiModelProperty("协议名")
    private ProtocolNameEnum protocolName;
    private int[] protocolNums;
    private ProtocolRangePO[] protocolRanges;
    private ServiceNameEnum serviceName;

    private ProtocolMatchSpecPO() {
    }

    public ProtocolMatchSpecPO(ProtocolMatchSpecType type) {
        this.type = type;
    }

    public static ProtocolMatchSpecPO createObjectType(String nameRef) {
        ProtocolMatchSpecPO result = new ProtocolMatchSpecPO(ProtocolMatchSpecType.OBJECT);
        result.nameRef = nameRef;
        return result;
    }

    public static ProtocolMatchSpecPO createObjectGroupType(String nameRef) {
        ProtocolMatchSpecPO result = new ProtocolMatchSpecPO(ProtocolMatchSpecType.OBJECT_GROUP);
        result.nameRef = nameRef;
        return result;
    }

    public static ProtocolMatchSpecPO createProtocolNameType(ProtocolNameEnum protoName) {
        ProtocolMatchSpecPO result = new ProtocolMatchSpecPO(ProtocolMatchSpecType.PROTOCOL_NAME);
        result.protocolName = protoName;
        return result;
    }

    public static ProtocolMatchSpecPO createServiceNameType(ServiceNameEnum serviceName) {
        ProtocolMatchSpecPO result = new ProtocolMatchSpecPO(ProtocolMatchSpecType.SERVICE_NAME);
        result.serviceName = serviceName;
        return result;
    }

    public static ProtocolMatchSpecPO createProtocolNumType(int... numbers) {
        if (numbers == null) {
            return null;
        }
        ProtocolMatchSpecPO result = new ProtocolMatchSpecPO(ProtocolMatchSpecType.PROTOCOL_NUM);
        result.protocolNums = numbers;
        return result;
    }

    public static ProtocolMatchSpecPO createProtocolRangeType(ProtocolRangePO... ranges) {
        if (ranges == null) {
            return null;
        }
        ProtocolMatchSpecPO result = new ProtocolMatchSpecPO(ProtocolMatchSpecType.PROTOCOL_RANGE);
        result.protocolRanges = ranges;
        return result;
    }

    public ProtocolMatchSpecType getType() {
        return this.type;
    }

    public void setType(ProtocolMatchSpecType type) {
        this.type = type;
    }

    public String getNameRef() {
        return this.nameRef;
    }

    public void setNameRef(String nameRef) {
        this.nameRef = nameRef;
    }

    public ProtocolNameEnum getProtocolName() {
        return this.protocolName;
    }

    public void setProtocolName(ProtocolNameEnum protocolName) {
        this.protocolName = protocolName;
    }

    public int[] getProtocolNums() {
        return this.protocolNums;
    }

    public void setProtocolNums(int[] protocolNums) {
        this.protocolNums = protocolNums;
    }

    public ProtocolRangePO[] getProtocolRanges() {
        return this.protocolRanges;
    }

    public void setProtocolRanges(ProtocolRangePO[] protocolRanges) {
        this.protocolRanges = protocolRanges;
    }

    public ServiceNameEnum getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(ServiceNameEnum serviceName) {
        this.serviceName = serviceName;
    }

    public enum ProtocolMatchSpecType {
        OBJECT,
        OBJECT_GROUP,
        PROTOCOL_NAME,
        PROTOCOL_NUM,
        PROTOCOL_RANGE,
        SERVICE_NAME
    }

}
