package com.nspm.antlr4.common.domain;

import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP4AddressRangePO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressRangePO;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.interfaces.Node;
import com.nspm.antlr4.common.utils.IPUtil;
import com.nspm.antlr4.common.utils.UUIDUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/10
 */
@ApiModel(value = "子网")
@Document(collection = "SUBNET")
@Data
public class SubnetPO extends COWAuditBaseEntity implements Node {

    @ApiModelProperty(value = "子网uuid")
    @Indexed
    private String uuid;

    @ApiModelProperty(value = "子网名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;
    private List<String> tags;
    private String subnetUserType;
    @NotNull
    private SubnetType type;
    private SubnetMark subnetMark;
    private TrustLevel trustLevel;
    @NotNull
    private IPTypeEnum ipType;
    @Indexed
    private IP4AddressPO ip4BaseAddress;

    @ApiModelProperty(value = "IPv4掩码")
    private Integer ip4MaskLength;
    @Indexed
    @JsonIgnore
    private Long ip4Start;
    @Indexed
    @JsonIgnore
    private Long ip4End;
    @Indexed
    private IP6AddressPO ip6BaseAddress;

    @ApiModelProperty(value = "IPv6掩码")
    private Integer ip6MaskLength;
    @Indexed
    private String adhocLinkText;
    private List<IP4AddressRangePO> validIp4Ranges;
    private List<IP6AddressRangePO> validIp6Ranges;

    @ApiModelProperty(value = "连接子网标识")
    private Boolean connectionFlag;



    private SubnetPO() {
    }

    public SubnetPO(Boolean connectionFlag) {
        this.connectionFlag = connectionFlag;
    }

    public SubnetPO(SubnetType type, IPTypeEnum ipType, String name, String description, List<String> tagsList) {

        this.type = type;
        this.ipType = ipType;
        this.trustLevel = TrustLevel.TRUSTED;
        this.uuid = UUIDUtil.getUuid();
        this.name = name;
        this.description = description;
        this.tags = tagsList;
    }

    public SubnetPO(String name, String description, List<String> tagsList, IP4AddressPO ip4BaseAddress, int ip4MaskLength) {
        if (ip4BaseAddress == null) {
            throw new IllegalArgumentException("Base IP address required.");
        }
        if ((ip4MaskLength < 0) || (ip4MaskLength > 32)) {
            throw new IllegalArgumentException("Invalid subnet mask length. It should be between 0 to 32.");
        }

        this.ipType = IPTypeEnum.IP4;
        this.type = SubnetType.NUMBERED;
        this.trustLevel = TrustLevel.TRUSTED;
        this.uuid = UUIDUtil.getUuid();
        this.name = name;
        this.description = description;
        this.tags = tagsList;

        this.ip4BaseAddress = IPUtil.getBaseAddress(ip4BaseAddress, ip4MaskLength);
        this.ip4MaskLength = Integer.valueOf(ip4MaskLength);

        IP4AddressRangePO myrange = this.ip4BaseAddress.toRange(ip4MaskLength);
        this.ip4Start = Long.valueOf(myrange.getStartAsLong());
        this.ip4End = Long.valueOf(myrange.getEndAsLong());
        this.connectionFlag = false;
    }

    public SubnetPO(String name, String description, List<String> tagsList, IP6AddressPO ip6BaseAddress, int ip6MaskLength) {
        if (ip6BaseAddress == null) {
            throw new IllegalArgumentException("Base IP address required.");
        }
        if ((ip6MaskLength < 0) || (ip6MaskLength > 128)) {
            throw new IllegalArgumentException("Invalid subnet mask length. It should be between 0 to 128.");
        }

        this.ipType = IPTypeEnum.IP6;
        this.type = SubnetType.NUMBERED;
        this.trustLevel = TrustLevel.TRUSTED;
        this.uuid = UUIDUtil.getUuid();
        this.name = name;
        this.description = description;
        this.tags = tagsList;

        this.ip6BaseAddress = IPUtil.getBaseAddress(ip6BaseAddress, ip6MaskLength);
        this.ip6MaskLength = Integer.valueOf(ip6MaskLength);
        this.connectionFlag = false;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public SubnetType getType() {
        return this.type;
    }

    public IPTypeEnum getIpType() {
        return this.ipType;
    }

    public IP4AddressPO getIp4BaseAddress() {
        return this.ip4BaseAddress;
    }

    public int getIp4MaskLength() {
        if (this.ip4MaskLength == null) {
            return -1;
        }
        return this.ip4MaskLength.intValue();
    }

    public IP6AddressPO getIp6BaseAddress() {
        return this.ip6BaseAddress;
    }

    public int getIp6MaskLength() {
        if (this.ip6MaskLength == null) {
            return -1;
        }
        return this.ip6MaskLength.intValue();
    }

    public IPAddressRange getIPRange() {
        if (isUnnumbered()) {
            return null;
        }

        if (this.ipType == IPTypeEnum.IP4) {
            return this.ip4BaseAddress.toRange(this.ip4MaskLength.intValue());
        }
        return this.ip6BaseAddress.toRange(this.ip6MaskLength.intValue());
    }

    public boolean isUnnumbered() {
        if (this.ipType == IPTypeEnum.IP4) {
            if (this.ip4BaseAddress == null) {
                return true;
            }
            return false;
        }
        if (this.ipType == IPTypeEnum.IP6) {
            if (this.ip6BaseAddress == null) {
                return true;
            }
            return false;
        }

        /*     */
        return false;
    }

    public boolean isTrusted() {
        return this.trustLevel == TrustLevel.TRUSTED;
    }

    public TrustLevel getTrustLevel() {
        return this.trustLevel;
    }

    public String getAdhocLinkText() {
        return this.adhocLinkText;
    }

    public void setAdhocLinkText(String adhocLinkText) {
        this.adhocLinkText = adhocLinkText;
    }

    public Long getIp4Start() {
        return this.ip4Start;
    }

    public Long getIp4End() {
        return this.ip4End;
    }

    public boolean populateIp4StartEnd() {
        if (this.ip4Start != null) {
            return false;
        }

        if ((this.ip4BaseAddress != null) && (this.ip4MaskLength != null)) {
            IP4AddressRangePO myrange = this.ip4BaseAddress.toRange(this.ip4MaskLength.intValue());
            this.ip4Start = Long.valueOf(myrange.getStartAsLong());
            this.ip4End = Long.valueOf(myrange.getEndAsLong());
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
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
        SubnetPO other = (SubnetPO) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public String getUniqueName() {
        return getDisplayName();
    }

    public String getDisplayIp() {
        StringBuilder sb = new StringBuilder();

        if (this.ipType == IPTypeEnum.IP4) {
            if (this.ip4BaseAddress != null) {
                sb.append(this.ip4BaseAddress.toDisplayString());
                sb.append("/");
                sb.append(this.ip4MaskLength);
            }
        } else if ((this.ipType == IPTypeEnum.IP6) &&
                (this.ip6BaseAddress != null)) {
            sb.append(this.ip6BaseAddress.toDisplayString());
            sb.append("/");
            sb.append(this.ip6MaskLength);
        }

        /*     */
        return sb.toString();
    }

    public String getDisplayName() {
        StringBuilder sb = new StringBuilder();
        if (this.name != null) {
            sb.append(this.name);
        }

        if (this.ipType == IPTypeEnum.IP4) {
            if (this.ip4BaseAddress != null) {
                if (this.name != null) {
                    sb.append("(");
                }
                sb.append(this.ip4BaseAddress.toDisplayString());
                sb.append("/");
                sb.append(this.ip4MaskLength);
                if (this.name != null) {
                    sb.append(")");
                }
            }
        } else if ((this.ipType == IPTypeEnum.IP6) &&
                (this.ip6BaseAddress != null)) {
            if (this.name != null) {
                sb.append("(");
            }
            sb.append(this.ip6BaseAddress.toDisplayString());
            sb.append("/");
            sb.append(this.ip6MaskLength);
            if (this.name != null) {
                sb.append(")");
            }
        }

        /*     */
        if (sb.length() == 0) {
            if (this.ipType == IPTypeEnum.IP4) {
                sb.append("Unnumbered IP4");
            } else if (this.ipType == IPTypeEnum.IP6) {
                sb.append("Unnumbered IP6");
            }
        }

        return sb.toString();
    }

    @Override
    public String getObjectId() {
        return this.id;
    }

    @Override
    public String getNodeId() {
        return this.uuid;
    }

    @Override
    public Node.NodeType getNodeType() {
        return Node.NodeType.SUBNET;
    }

    public void setTrustLevel(TrustLevel trustLevel) {
        this.trustLevel = trustLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<IP4AddressRangePO> getValidIp4Ranges() {
        return this.validIp4Ranges;
    }

    public void setValidIp4Ranges(List<IP4AddressRangePO> validIp4Ranges) {
        this.validIp4Ranges = validIp4Ranges;
    }

    public List<IP6AddressRangePO> getValidIp6Ranges() {
        return this.validIp6Ranges;
    }

    public void setValidIp6Ranges(List<IP6AddressRangePO> validIp6Ranges) {
        this.validIp6Ranges = validIp6Ranges;
    }

    public String getSubnetUserType() {
        return this.subnetUserType;
    }

    public void setSubnetUserType(String subnetUserType) {
        this.subnetUserType = subnetUserType;
    }

    public SubnetMark getSubnetMark() {
        return this.subnetMark;
    }

    public void setSubnetMark(SubnetMark subnetMark) {
        this.subnetMark = subnetMark;
    }

    public static enum TrustLevel {
        UNTRUSTED_SPOOF_SRC,
        INTERNET,
        UNTRUSTED_LOCAL,
        TRUSTED;

        private TrustLevel() {
        }
    }

    public static enum SubnetType {
        //以接口、address 衔接的子网
        NUMBERED,
        MPLS_CLOUD,
        //RT衔接的子网
        GENERAL_LINK,
        IPSEC,
        MANUAL_VPN_TUNNEL;

        private SubnetType() {
        }
    }

    public static enum SubnetMark {
        LINK_SUBNET,
        SYS_LINK_SUBNET,

        /*     */
        REDUNDANT_SUBNET,
        MGMT_SUBNET;

        private SubnetMark() {
        }
    }

    public static Comparator<SubnetPO> SORT_BY_NAME = new Comparator<SubnetPO>() {
        @Override
        public int compare(SubnetPO left, SubnetPO right) {
            return left.getUniqueName().compareTo(right.getUniqueName());
        }
    };

    public static Comparator<SubnetPO> SORT_BY_IP = new Comparator<SubnetPO>() {
        @Override
        public int compare(SubnetPO left, SubnetPO right) {
            String leftIp = left.getDisplayIp();
            String rightIp = right.getDisplayIp();

            if (leftIp.isEmpty()) {
                leftIp = "ID:" + left.getId();
            }
            if (rightIp.isEmpty()) {
                rightIp = "ID:" + right.getId();
            }

            return leftIp.compareTo(rightIp);
        }
    };

    @Override
    public String toString() {
        return getDisplayName();
    }

    public SubnetPO shallowCopy() {
        SubnetPO result = new SubnetPO();
        result.uuid = UUIDUtil.getUuid();
        result.name = this.name;
        result.description = this.description;
        result.tags = this.tags;
        result.type = this.type;
        result.subnetMark = this.subnetMark;
        result.trustLevel = this.trustLevel;
        result.ipType = this.ipType;
        result.ip4BaseAddress = this.ip4BaseAddress;
        result.ip4MaskLength = this.ip4MaskLength;
        result.ip4Start = this.ip4Start;
        result.ip4End = this.ip4End;
        result.ip6BaseAddress = this.ip6BaseAddress;
        result.ip6MaskLength = this.ip6MaskLength;
        result.adhocLinkText = this.adhocLinkText;
        result.validIp4Ranges = this.validIp4Ranges;
        result.validIp6Ranges = this.validIp6Ranges;

        return result;
    }
}
