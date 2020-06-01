package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.enums.PortOperatorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author luwei
 * @date 2019/9/10
 */
@ApiModel("端口匹配对象")
public class PortMatchSpecPO implements Serializable {

    @ApiModelProperty("端口的保存类型")
    private PortMatchSpecType type;
    private String nameRef;
    @ApiModelProperty("端口范围类型")
    private PortOperatorEnum portOp;
    @ApiModelProperty("端口范围值")
    private String[] portValues;

    public PortMatchSpecPO() {
    }

    public PortMatchSpecPO(PortMatchSpecType type) {
        this.type = type;
    }

    public static PortMatchSpecPO createObjectType(String objectName) {
        PortMatchSpecPO result = new PortMatchSpecPO(PortMatchSpecType.OBJECT);
        result.nameRef = objectName;
        return result;
    }

    public static PortMatchSpecPO createObjectGroupType(String objectGroupName) {
        PortMatchSpecPO result = new PortMatchSpecPO(PortMatchSpecType.OBJECT_GROUP);
        result.nameRef = objectGroupName;
        return result;
    }

    public static void validatePortValues(PortOperatorEnum portOp, String... portVals) {
        if ((portOp == PortOperatorEnum.RANGE) && (portVals.length != 2)) {
            throw new IllegalArgumentException("Port operator 'RANGE' requires two port values.");
        }
        if ((portOp == PortOperatorEnum.MULTI_RANGE) && (portVals.length % 2 != 0)) {
            throw new IllegalArgumentException("Port operator 'MULTI_RANGE' requires even number of port values.");
        }
        if (((portOp == PortOperatorEnum.GT) || (portOp == PortOperatorEnum.LT) || (portOp == PortOperatorEnum.GE) || (portOp == PortOperatorEnum.LE)) && (portVals.length != 1)) {

            throw new IllegalArgumentException("Port operator " + portOp + " requires one and only one port value.");
        }
    }

    public static PortMatchSpecPO createPortValueType(PortOperatorEnum portOp, String... portVals) {
        validatePortValues(portOp, portVals);

        PortMatchSpecPO result = new PortMatchSpecPO(PortMatchSpecType.PORT_VALUE);
        result.portOp = portOp;
        result.portValues = portVals;

        return result;
    }


    public PortMatchSpecType getType() {
        return this.type;
    }

    public String getNameRef() {
        return this.nameRef;
    }

    public PortOperatorEnum getPortOp() {
        return this.portOp;
    }

    public String[] getPortValues() {
        return this.portValues;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.nameRef == null ? 0 : this.nameRef.hashCode());
        result = 31 * result + (this.portOp == null ? 0 : this.portOp.hashCode());
        result = 31 * result + Arrays.hashCode(this.portValues);
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
        PortMatchSpecPO other = (PortMatchSpecPO) obj;
        if (this.nameRef == null) {
            if (other.nameRef != null) {
                return false;
            }
        } else if (!this.nameRef.equals(other.nameRef)) {
            return false;
        }
        if (this.portOp != other.portOp) {
            return false;
        }
        if (!Arrays.equals(this.portValues, other.portValues)) {
            return false;
        }
        return this.type == other.type;
    }

    public enum PortMatchSpecType {
        OBJECT,
        OBJECT_GROUP,
        PORT_VALUE
    }

}
