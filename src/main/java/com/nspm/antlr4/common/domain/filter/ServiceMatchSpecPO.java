package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.obj.FilterServicePO;
import com.nspm.antlr4.common.enums.ProtocolNameEnum;
import com.nspm.antlr4.common.enums.ServiceNameEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/10
 */
@ApiModel("服务匹配对象")
public class ServiceMatchSpecPO implements Serializable {

    @NotNull
    @ApiModelProperty("服务保存类型")
    private ServiceMatchSpecType type;
    @ApiModelProperty("服务引用对象名")
    private String nameRef;
    @ApiModelProperty("TCP/UDP子协议名")
    private ServiceNameEnum serviceName;
    @ApiModelProperty("IP协议名")
    private ProtocolNameEnum protocolName;
    @ApiModelProperty("服务对象")
    private FilterServicePO serviceValue;

    public ServiceMatchSpecPO() {
    }

    public ServiceMatchSpecPO(ServiceMatchSpecType type) {
        this.type = type;
    }

    public static ServiceMatchSpecPO createServiceType(String serviceNameRef) {
        ServiceMatchSpecPO result = new ServiceMatchSpecPO(ServiceMatchSpecType.SERVICE_OBJECT);
        result.nameRef = serviceNameRef;
        return result;
    }

    public static ServiceMatchSpecPO createServiceGroupType(String serviceGroupNameRef) {
        ServiceMatchSpecPO result = new ServiceMatchSpecPO(ServiceMatchSpecType.SERVICE_OBJECT_GROUP);
        result.nameRef = serviceGroupNameRef;
        return result;
    }

    public static ServiceMatchSpecPO createProtocolNameType(ProtocolNameEnum protoName) {
        ServiceMatchSpecPO result = new ServiceMatchSpecPO(ServiceMatchSpecType.PROTOCOL_NAME);
        result.protocolName = protoName;
        return result;
    }

    public static ServiceMatchSpecPO createServiceNameType(ServiceNameEnum serviceName) {
        ServiceMatchSpecPO result = new ServiceMatchSpecPO(ServiceMatchSpecType.SERVICE_NAME);
        result.serviceName = serviceName;
        return result;
    }

    public static ServiceMatchSpecPO createServiceValueType(FilterServicePO serviceValue) {
        ServiceMatchSpecPO result = new ServiceMatchSpecPO(ServiceMatchSpecType.SERVICE_VALUE);
        result.serviceValue = serviceValue;
        return result;
    }

    public ServiceMatchSpecType getType() {
        return this.type;
    }

    public void setType(ServiceMatchSpecType type) {
        this.type = type;
    }

    public String getNameRef() {
        return this.nameRef;
    }

    public void setNameRef(String name) {
        this.nameRef = name;
    }

    public ServiceNameEnum getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(ServiceNameEnum serviceName) {
        this.serviceName = serviceName;
    }

    public ProtocolNameEnum getProtocolName() {
        return this.protocolName;
    }

    public void setProtocolName(ProtocolNameEnum protocolName) {
        this.protocolName = protocolName;
    }

    public FilterServicePO getServiceValue() {
        return this.serviceValue;
    }

    public void setServiceValue(FilterServicePO serviceValue) {
        this.serviceValue = serviceValue;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.nameRef == null ? 0 : this.nameRef.hashCode());
        result = 31 * result + (this.protocolName == null ? 0 : this.protocolName.hashCode());
        result = 31 * result + (this.serviceName == null ? 0 : this.serviceName.hashCode());
        result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ServiceMatchSpecPO other = (ServiceMatchSpecPO) obj;
        if (this.nameRef == null) {
            if (other.nameRef != null) {
                return false;
            }
        } else if (!this.nameRef.equals(other.nameRef)) {
            return false;
        }
        if (this.protocolName != other.protocolName) {
            return false;
        }
        if (this.serviceName != other.serviceName) {
            return false;
        }
        return this.type == other.type;
    }

    public enum ServiceMatchSpecType {
        SERVICE_OBJECT,
        SERVICE_OBJECT_GROUP,
        SERVICE_NAME,
        PROTOCOL_NAME,
        SERVICE_VALUE
    }
}
