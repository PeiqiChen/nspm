package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.filter.PortMatchSpecPO;
import com.nspm.antlr4.common.enums.*;
import com.nspm.antlr4.exceptions.ZeeValidationException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "详细的服务信息")
@Data
public class FilterServicePO implements Serializable {


    @ApiModelProperty("名称")
    private String name;
    
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("标签集")
    private String[] tags;

    @ApiModelProperty("对象所在行")
    @JsonView
    private ConfigLinePO lineNumbers;

    @ApiModelProperty("是否默认")
    private Boolean isImplicit;

    @ApiModelProperty("服务类型")
    private FilterServiceTypeEnum type;

    @ApiModelProperty("协议名称")
    private ProtocolNameEnum protocolName;

    @ApiModelProperty("协议号")
    private Integer protocolNum;
    @ApiModelProperty("协议范围")
    private ProtocolRangePO protocolRange;
    @ApiModelProperty("源端口的范围类型")
    private PortOperatorEnum srcPortOp;
    @ApiModelProperty("源端口的范围值")
    private String[] srcPortValues;
    @ApiModelProperty("目的端口的范围类型")
    private PortOperatorEnum dstPortOp;
    @ApiModelProperty("目的端口的范围值")
    private String[] dstPortValues;
    @ApiModelProperty("icmp类型")
    private ICMPTypeEnum icmpType;
    @ApiModelProperty("icmp类型字符串")
    private String icmpStr;
    @ApiModelProperty("icmp6类型")
    private ICMP6TypeEnum icmp6Type;
    @ApiModelProperty("icmp type")
    private Integer icmpTypeNum;
    @ApiModelProperty("icmp code")
    private Integer icmpCode;
    @ApiModelProperty("icmp code范围")
    private IntegerRangePO icmpCodeRange;
    @ApiModelProperty("igmp type")
    private Integer igmpTypeNum;
    @ApiModelProperty("端口范围类型")
    private PortOperatorEnum portOp;
    @ApiModelProperty("端口值（源端口或者目的端口）")
    private String[] portValues;
    @ApiModelProperty("通用的TCP/UDP服务")
    private List<ServiceNameEnum> serviceNames;
    @ApiModelProperty("超时时间")
    private Long idleTimeout;

    public static FilterServicePO createTcpUdpService(ProtocolNameEnum proto) {
        Assert.notNull(proto, "proto shouldn't be null");
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_TCP_UDP);
        result.protocolName = proto;
        return result;
    }

    public static FilterServicePO createIcmpService(ICMPTypeEnum type, Integer icmpTypeNum, Integer icmpCodeNum) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_ICMP);
        result.protocolName = ProtocolNameEnum.ICMP;
        result.icmpType = type;
        result.icmpTypeNum = icmpTypeNum;
        result.icmpCode = icmpCodeNum;
        return result;
    }

    public static FilterServicePO createIcmpServiceWithCodeRange(ICMPTypeEnum type, Integer icmpTypeNum, IntegerRangePO icmpCodeRange) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_ICMP);
        result.protocolName = ProtocolNameEnum.ICMP;
        result.icmpType = type;
        result.icmpTypeNum = icmpTypeNum;
        result.icmpCodeRange = icmpCodeRange;
        return result;
    }

    public static FilterServicePO createIcmp6ServiceWithCodeRange(ICMP6TypeEnum type, Integer icmpTypeNum, IntegerRangePO icmpCodeRange) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_ICMP);
        result.protocolName = ProtocolNameEnum.ICMP6;
        result.icmp6Type = type;
        result.icmpTypeNum = icmpTypeNum;
        result.icmpCodeRange = icmpCodeRange;
        return result;
    }

    public static FilterServicePO createIgmpService(Integer igmpTypeNum) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_IGMP);
        result.protocolName = ProtocolNameEnum.IGMP;
        result.igmpTypeNum = igmpTypeNum;
        return result;
    }

    public static FilterServicePO createIcmpService(String icmpTypeStr) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_ICMP);
        result.protocolName = ProtocolNameEnum.ICMP;
        result.icmpStr = icmpTypeStr;
        return result;
    }

    public static FilterServicePO createIcmp6Service(ICMP6TypeEnum type, Integer icmpTypeNum, Integer icmpCodeNum) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_ICMP);
        result.protocolName = ProtocolNameEnum.ICMP6;
        result.icmp6Type = type;
        result.icmpTypeNum = icmpTypeNum;
        result.icmpCode = icmpCodeNum;
        return result;
    }

    public static FilterServicePO createIcmp6Service(String icmpTypeStr) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_ICMP);
        result.protocolName = ProtocolNameEnum.ICMP6;
        result.icmpStr = icmpTypeStr;
        return result;
    }

    public static FilterServicePO createOtherService(ProtocolNameEnum proto, Integer protoNum) {
        if (proto == null && protoNum == null) {
            throw new ZeeValidationException("proto and protoNum are all null.");
        } else {
            FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_OTHER);
            result.protocolName = proto;
            result.protocolNum = protoNum;
            return result;
        }
    }

    public static FilterServicePO createPortObject(ProtocolNameEnum proto, PortOperatorEnum op, String... portValues) {
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.PORT_OBJECT);
        result.protocolName = proto;
        if (proto != ProtocolNameEnum.TCP && proto != ProtocolNameEnum.UDP && proto != ProtocolNameEnum.TCP_UDP && proto != null) {
            throw new ZeeValidationException("Port object only support TCP/UDP/TCP_UDP protocol.");
        } else {
            PortMatchSpecPO.validatePortValues(op, portValues);
            result.portOp = op;
            result.portValues = portValues;
            return result;
        }
    }

    public static FilterServicePO createProtocolObject(ProtocolRangePO protoRange) {
        Assert.notNull(protoRange, "protoRange shouldn't be null");
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.PROTOCOL_OBJECT);
        result.protocolRange = protoRange;
        return result;
    }

    public static FilterServicePO createByServiceName(ServiceNameEnum... serviceNames) {
        Assert.notEmpty(serviceNames, "serviceNames shouldn't be null or empty.");
        FilterServicePO result = new FilterServicePO(FilterServiceTypeEnum.SERVICE_NAME);
        result.serviceNames = new ArrayList();
        ServiceNameEnum[] var2 = serviceNames;
        int var3 = serviceNames.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ServiceNameEnum myname = var2[var4];
            result.serviceNames.add(myname);
        }

        return result;
    }

    private FilterServicePO() {
    }

    private FilterServicePO(FilterServiceTypeEnum type) {
        this.type = type;
    }

    private FilterServicePO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers,
                            List<ProtocolRangePO> protocolsList, List<PortRangePO> srcPortsList,
                            List<PortRangePO> dstPortsList, Boolean isImplicit) {
        this.name = name;
        this.description = description;
        this.tags = tagsList == null ? null : (String[])tagsList.toArray(new String[tagsList.size()]);
        if (!isImplicit && lineNumbers == null) {
            throw new IllegalArgumentException("Line number is required for non-implicit service object.");
        } else {
            this.isImplicit = isImplicit;
            this.lineNumbers = lineNumbers;
            if (protocolsList == null && srcPortsList == null && dstPortsList == null) {
                throw new IllegalArgumentException("At least one of them can not be null: protocols, srcPorts, or dstPorts.");
            }
        }
    }

    public List<String> getTags() {
        return this.tags == null ? null : Collections.unmodifiableList(Arrays.asList(this.tags));
    }

    public void setSrcPortOp(PortOperatorEnum srcPortOp) {
        if (this.type != FilterServiceTypeEnum.SERVICE_TCP_UDP) {
            throw new ZeeValidationException("Only SERVICE_TCP_UDP type allows to set port op");
        } else {
            this.srcPortOp = srcPortOp;
        }
    }

    public void setIcmpType(ICMPTypeEnum icmpType) {
        if (this.type != FilterServiceTypeEnum.SERVICE_ICMP) {
            throw new ZeeValidationException("Only SERVICE_ICMP type allows setting icmp type.");
        } else {
            this.icmpType = icmpType;
        }
    }

    public void setIcmp6Type(ICMP6TypeEnum icmp6Type) {
        if (this.type != FilterServiceTypeEnum.SERVICE_ICMP) {
            throw new ZeeValidationException("Only SERVICE_ICMP type allows setting icmp6 type.");
        } else {
            this.icmp6Type = icmp6Type;
        }
    }

    public void setIcmpTypeNum(Integer icmpTypeNum) {
        if (this.type != FilterServiceTypeEnum.SERVICE_ICMP) {
            throw new ZeeValidationException("Only SERVICE_ICMP type allows setting icmp type number.");
        } else {
            this.icmpTypeNum = icmpTypeNum;
        }
    }

    public void setIcmpCode(Integer icmpCode) {
        if (this.type != FilterServiceTypeEnum.SERVICE_ICMP) {
            throw new ZeeValidationException("Only SERVICE_ICMP type allows setting icmp code.");
        } else {
            this.icmpCode = icmpCode;
        }
    }

    public void setIcmpCodeRange(IntegerRangePO icmpCodeRange) {
        if (this.type != FilterServiceTypeEnum.SERVICE_ICMP) {
            throw new ZeeValidationException("Only SERVICE_ICMP type allows setting icmp code range.");
        } else {
            this.icmpCodeRange = icmpCodeRange;
        }
    }

    public void setIgmpTypeNum(Integer igmpTypeNum) {
        if (this.type != FilterServiceTypeEnum.SERVICE_IGMP) {
            throw new ZeeValidationException("Only SERVICE_IGMP type allows setting icmp6 type.");
        } else {
            this.igmpTypeNum = igmpTypeNum;
        }
    }

    public void setSrcPortValues(PortOperatorEnum portOp, String... srcPortValues) {
        if (this.type != FilterServiceTypeEnum.SERVICE_TCP_UDP) {
            throw new ZeeValidationException("Only SERVICE_TCP_UDP type allows to set port values");
        } else {
            PortMatchSpecPO.validatePortValues(portOp, srcPortValues);
            this.srcPortOp = portOp;
            this.srcPortValues = srcPortValues;
        }
    }

    public void setDstPortValues(PortOperatorEnum portOp, String... portValues) {
        if (this.type != FilterServiceTypeEnum.SERVICE_TCP_UDP) {
            throw new ZeeValidationException("Only SERVICE_TCP_UDP type is allowed to set port values");
        } else {
            PortMatchSpecPO.validatePortValues(portOp, portValues);
            this.dstPortOp = portOp;
            this.dstPortValues = portValues;
        }
    }

    public void setPortValues(PortOperatorEnum portOp, String... portValues) {
        if (this.type != FilterServiceTypeEnum.PORT_OBJECT && this.type != FilterServiceTypeEnum.SERVICE_TCP_UDP) {
            throw new ZeeValidationException("Only PORT_OBJECT/SERVICE_TCP_UDP type is allowed to set port values");
        } else {
            PortMatchSpecPO.validatePortValues(portOp, portValues);
            this.portOp = portOp;
            this.portValues = portValues;
        }
    }
}
