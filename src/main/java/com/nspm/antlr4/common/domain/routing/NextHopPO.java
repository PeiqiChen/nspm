package com.nspm.antlr4.common.domain.routing;

import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.domain.ip.IPAddressPO;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
@Data
@ApiModel("下一跳")
public class NextHopPO implements Serializable {

    @ApiModelProperty("ip4网关地址")
    private IP4AddressPO ip4Gateway;
    @ApiModelProperty("ip6网关地址")
    private IP6AddressPO ip6Gateway;
    @ApiModelProperty("接口名字")
    private String interfaceName;
    private String routingTable;
    private Boolean toNull;
    private Boolean unknown;

    public NextHopPO() {
    }

    public NextHopPO(IPAddressPO gateway, String interfaceName, String routingTable) {
        if (gateway != null) {
            if (gateway.getType() == IPTypeEnum.IP4) {
                this.ip4Gateway = ((IP4AddressPO) gateway);
            } else {
                this.ip6Gateway = ((IP6AddressPO) gateway);
            }
        }
        this.interfaceName = interfaceName;
        this.routingTable = routingTable;
    }

    public NextHopPO(IP4AddressPO ip4Gateway, String interfaceName, String routingTable) {
        this.ip4Gateway = ip4Gateway;
        this.interfaceName = interfaceName;
        this.routingTable = routingTable;
    }

    public NextHopPO(IP6AddressPO ip6Gateway, String interfaceName, String routingTable) {
        this.ip6Gateway = ip6Gateway;
        this.interfaceName = interfaceName;
        this.routingTable = routingTable;
    }

    public NextHopPO(IP4AddressPO ip4Gateway) {
        this.ip4Gateway = ip4Gateway;
    }

    public NextHopPO(IP6AddressPO ip6Gateway) {
        this.ip6Gateway = ip6Gateway;
    }

    public NextHopPO(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public NextHopPO(IP4AddressPO ip4Gateway, String interfaceName) {
        this.ip4Gateway = ip4Gateway;
        this.interfaceName = interfaceName;
    }

    public NextHopPO(IP6AddressPO ip6Gateway, String interfaceName) {
        this.ip6Gateway = ip6Gateway;
        this.interfaceName = interfaceName;
    }

    public static NextHopPO createToNullNextHop() {
        NextHopPO result = new NextHopPO();
        result.toNull = Boolean.TRUE;
        return result;
    }

    public static NextHopPO createUnknownNextHop() {
        NextHopPO result = new NextHopPO();
        result.unknown = Boolean.TRUE;
        return result;
    }

    public IP4AddressPO getIp4Gateway() {
        return this.ip4Gateway;
    }

    public IP6AddressPO getIp6Gateway() {
        return this.ip6Gateway;
    }

    public IPAddressPO getGateway() {
        return this.ip4Gateway != null ? this.ip4Gateway : this.ip6Gateway;
    }

    public String getInterfaceName() {
        return this.interfaceName;
    }

    public String getRoutingTable() {
        return this.routingTable;
    }

    public boolean isToNull() {
        if ((this.toNull != null) && (this.toNull)) {
            return true;
        }
        return (this.interfaceName != null) && (this.interfaceName.equalsIgnoreCase("NULL0"));

    }

    public boolean isUnknown() {
        if (this.unknown == null) {
            return false;
        }
        return this.unknown;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = 31 * result + (this.interfaceName == null ? 0 : this.interfaceName.hashCode());

        result = 31 * result + (this.ip4Gateway == null ? 0 : this.ip4Gateway.hashCode());

        result = 31 * result + (this.ip6Gateway == null ? 0 : this.ip6Gateway.hashCode());

        result = 31 * result + (this.routingTable == null ? 0 : this.routingTable.hashCode());
        result = 31 * result + (this.toNull == null ? 0 : this.toNull.hashCode());
        result = 31 * result + (this.unknown == null ? 0 : this.unknown.hashCode());
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
        NextHopPO other = (NextHopPO) obj;
        if (this.interfaceName == null) {
            if (other.interfaceName != null) {
                return false;
            }
        } else if (!this.interfaceName.equals(other.interfaceName)) {
            return false;
        }
        if (this.ip4Gateway == null) {
            if (other.ip4Gateway != null) {
                return false;
            }
        } else if (!this.ip4Gateway.equals(other.ip4Gateway)) {
            return false;
        }
        if (this.ip6Gateway == null) {
            if (other.ip6Gateway != null) {
                return false;
            }
        } else if (!this.ip6Gateway.equals(other.ip6Gateway)) {
            return false;
        }
        if (this.routingTable == null) {
            if (other.routingTable != null) {
                return false;
            }
        } else if (!this.routingTable.equals(other.routingTable)) {
            return false;
        }
        if (this.toNull == null) {
            if (other.toNull != null) {
                return false;
            }
        } else if (!this.toNull.equals(other.toNull)) {
            return false;
        }
        if (this.unknown == null) {
            return other.unknown == null;
        } else {
            return this.unknown.equals(other.unknown);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NextHopPO [");
        if (this.ip4Gateway != null) {
            builder.append("ip4Gateway=").append(this.ip4Gateway).append(", ");
        }
        if (this.ip6Gateway != null) {
            builder.append("ip6Gateway=").append(this.ip6Gateway).append(", ");
        }
        if (this.interfaceName != null) {
            builder.append("interfaceName=").append(this.interfaceName).append(", ");
        }
        if (this.routingTable != null) {
            builder.append("routingTable=").append(this.routingTable).append(", ");
        }
        if (this.toNull != null) {
            builder.append("toNull=").append(this.toNull).append(", ");
            if (this.unknown != null) {
                builder.append("unknown=").append(this.unknown);
                builder.append("]");
            }
        }
        return builder.toString();
    }


}
