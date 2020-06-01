package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.ip.*;
import com.nspm.antlr4.common.enums.IPItemTypeEnum;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.utils.IPUtil;
import com.nspm.antlr4.validation.ZeeCustomConstraint;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "地址组")
@Data
@ZeeCustomConstraint
public class IPItemPO implements ZeeCustomValidation, Serializable {

    public static IPItemPO IP4_ANY = createAny4Type();
    public static IPItemPO IP6_ANY = createAny6Type();

    @ApiModelProperty("类型")
    private IPItemTypeEnum type;

    @ApiModelProperty("名称")
    private String name;


    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("标签集")
    private String[] tags;

    @ApiModelProperty("是否固定")
    private Boolean isNegate;


    @ApiModelProperty("ipv4长度")
    private Integer ip4Length;

    @ApiModelProperty("ipv6长度")
    private Integer ip6Length;


    @ApiModelProperty("ip4主机地址")
    private IP4AddressPO ip4Base;

    @ApiModelProperty("ip4反掩码地址")
    private IP4AddressPO ip4WildCardMask;


    @ApiModelProperty("ipv4网络地址")
    private IP4AddressPO  ip4Prefix;

    @ApiModelProperty("ipv6网络地址")
    private IP6AddressPO ip6Prefix;


    @ApiModelProperty("ipv4范围")
    private IP4AddressRangePO ip4Range;

    @ApiModelProperty("ipv6范围")
    private IP6AddressRangePO ip6Range;

    @ApiModelProperty("fqdn")
    private String fqdn;

    @ApiModelProperty("fqdn IP类型：ip4、ip6")
    private IPTypeEnum fqdnIpType;

    @ApiModelProperty("ipv4地址组")
    private IP4AddressPO[] ip4Addresses;

    @ApiModelProperty("ipv6地址组")
    private IP6AddressPO[] ip6Addresses;

    @ApiModelProperty("接口名称")
    private String interfaceName;

    @ApiModelProperty("名称参照")
    private String nameRef;

    @JsonView
    @ApiModelProperty("对象在配置文件中的行")
    private ConfigLinePO lineNumbers;

    public static IPItemPO createObjectType(String objectName) {
        IPItemPO result = new IPItemPO(IPItemTypeEnum.OBJECT);
        Assert.notNull(objectName, "ObjectName couldn't be empty");
        result.nameRef = objectName;
        return result;
    }

    public static IPItemPO createObjectGroupType(String objectGroupName) {
        IPItemPO result = new IPItemPO(IPItemTypeEnum.OBJECT_GROUP);
        Assert.notNull(objectGroupName, "ObjectGroupName couldn't be empty");
        result.nameRef = objectGroupName;
        return result;
    }

    public static IPItemPO createAnyType() {
        return new IPItemPO(IPItemTypeEnum.ANY);
    }

    public static IPItemPO createAny4Type() {
        return new IPItemPO(IPItemTypeEnum.ANY4);
    }

    public static IPItemPO createAny6Type() {
        return new IPItemPO(IPItemTypeEnum.ANY6);
    }

    public static IPItemPO createHostIpType(IPAddressPO... ips) {
        if (ips == null) {
            return null;
        } else {
            IPItemPO result = new IPItemPO(IPItemTypeEnum.HOST_IP);
            IPAddressPO[] var2 = ips;
            int var3 = ips.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                IPAddressPO myobj = var2[var4];
                if (myobj.getType() == IPTypeEnum.IP4) {
                    result.addIp4Address((IP4AddressPO)myobj);
                } else {
                    result.addIp6Address((IP6AddressPO)myobj);
                }
            }

            return result;
        }
    }

    public static IPItemPO createFqdnType(String fqdn, IPTypeEnum type) {
        IPItemPO result = new IPItemPO(IPItemTypeEnum.FQDN);
        result.fqdn = fqdn;
        result.fqdnIpType = type;
        return result;
    }

    public static IPItemPO createRangeType(IPAddressRange range) {
        if (range == null) {
            return null;
        } else {
            IPItemPO result = new IPItemPO(IPItemTypeEnum.RANGE);
            if (range.getIPType() == IPTypeEnum.IP4) {
                result.ip4Range = (IP4AddressRangePO) range;
            } else {
                result.ip6Range = (IP6AddressRangePO)range;
            }

            return result;
        }
    }

    public static IPItemPO createSubnetType(IP4AddressPO prefix, int length) {
        IPItemPO result = new IPItemPO(IPItemTypeEnum.SUBNET);
        result.ip4Prefix = prefix;
        result.ip4Length = length;
        return result;
    }

    public static IPItemPO createSubnetType(IP4AddressPO prefix, IP4AddressPO mask) {
        IPItemPO result = new IPItemPO(IPItemTypeEnum.SUBNET);
        result.ip4Prefix = prefix;
        result.ip4Length = IPUtil.getNetmaskLength(mask);
        return result;
    }

    public static IPItemPO createIP4WildCardType(IP4AddressPO base, IP4AddressPO mask) {
        IPItemPO result = new IPItemPO(IPItemTypeEnum.IP4WILDCARD);
        result.ip4Base = base;
        result.ip4WildCardMask = mask;
        return result;
    }

    public static IPItemPO createInterfaceType(String interfaceName) {
        IPItemPO result = new IPItemPO(IPItemTypeEnum.INTERFACE);
        result.interfaceName = interfaceName;
        return result;
    }

    private IPItemPO() {
    }

    private IPItemPO(IPItemTypeEnum type) {
        this.type = type;
    }

    public void setIp4PrefixMask(IP4AddressPO prefix, IP4AddressPO mask) {
        this.ip4Prefix = prefix;
        this.ip4Length = IPUtil.getNetmaskLength(mask);
    }

    public void setIp4PrefixLength(IP4AddressPO prefix, int length) {
        this.ip4Prefix = prefix;
        this.ip4Length = length;
    }

    public void setIp6PrefixLength(IP6AddressPO ip6Prefix, int length) {
        this.ip6Prefix = ip6Prefix;
        this.ip6Length = length;
    }

    public void setIp4BaseWildcard(IP4AddressPO ip4Base, IP4AddressPO ip4WildCardMask) {
        this.ip4Base = ip4Base;
        this.ip4WildCardMask = ip4WildCardMask;
    }

    public void addIp4Address(IP4AddressPO ip4Addr) {
        if (ip4Addr != null) {
            if (this.ip4Addresses == null) {
                this.ip4Addresses = new IP4AddressPO[]{ip4Addr};
            } else {
                IP4AddressPO[] newArr = new IP4AddressPO[this.ip4Addresses.length + 1];
                System.arraycopy(this.ip4Addresses, 0, newArr, 0, this.ip4Addresses.length);
                newArr[newArr.length - 1] = ip4Addr;
                this.ip4Addresses = newArr;
            }

        }
    }

    public void addIp4Address(List<IP4AddressPO> ip4Addrs) {
        if (ip4Addrs != null) {
            if (this.ip4Addresses == null) {
                this.ip4Addresses = (IP4AddressPO[])ip4Addrs.toArray(new IP4AddressPO[ip4Addrs.size()]);
            } else {
                int i = this.ip4Addresses.length;
                IP4AddressPO[] newArr = new IP4AddressPO[i + ip4Addrs.size()];
                System.arraycopy(this.ip4Addresses, 0, newArr, 0, i);
                IP4AddressPO myobj;
                for(Iterator var4 = ip4Addrs.iterator(); var4.hasNext(); newArr[i++] = myobj) {
                    myobj = (IP4AddressPO)var4.next();
                }
                this.ip4Addresses = newArr;
            }
        }
    }

    public void addIp6Address(IP6AddressPO ip6Addr) {
        if (ip6Addr != null) {
            if (this.ip6Addresses == null) {
                this.ip6Addresses = new IP6AddressPO[]{ip6Addr};
            } else {
                IP6AddressPO[] newArr = new IP6AddressPO[this.ip6Addresses.length + 1];
                System.arraycopy(this.ip6Addresses, 0, newArr, 0, this.ip6Addresses.length);
                newArr[newArr.length - 1] = ip6Addr;
                this.ip6Addresses = newArr;
            }

        }
    }

    public void addIp6Address(List<IP6AddressPO> ip6Addrs) {
        if (ip6Addrs != null) {
            if (this.ip6Addresses == null) {
                this.ip6Addresses = (IP6AddressPO[])ip6Addrs.toArray(new IP6AddressPO[ip6Addrs.size()]);
            } else {
                int i = this.ip6Addresses.length;
                IP6AddressPO[] newArr = new IP6AddressPO[i + ip6Addrs.size()];
                System.arraycopy(this.ip6Addresses, 0, newArr, 0, i);

                IP6AddressPO myobj;
                for(Iterator var4 = ip6Addrs.iterator(); var4.hasNext(); newArr[i++] = myobj) {
                    myobj = (IP6AddressPO)var4.next();
                }

                this.ip6Addresses = newArr;
            }

        }
    }

    @Override
    public List<ZeeError> validate() {
        List<ZeeError> result = new ArrayList();
        ZeeError mye;
        if (this.ip4Prefix != null && this.ip4Length == null) {
            mye = new ZeeError(ZeeErrorName.VALIDATION_IP_PREFIX_WITHOUT_LENGTH, (Object[])null, "IP4 prefix '" + this.ip4Prefix + "' without mask length.");
            result.add(mye);
        }

        if (this.ip6Prefix != null && this.ip6Length == null) {
            mye = new ZeeError(ZeeErrorName.VALIDATION_IP_PREFIX_WITHOUT_LENGTH, (Object[])null, "IP6 prefix '" + this.ip4Prefix + "' without mask length.");
            result.add(mye);
        }

        if (this.ip4Length != null && (this.ip4Length < 0 || this.ip4Length > 32)) {
            mye = new ZeeError(ZeeErrorName.VALIDATION_IP_MASK_LENGTH_OUT_OF_RANGE, (Object[])null, "IP4 mask length is out of range (0-32): " + this.ip4Length);
            result.add(mye);
        }

        if (this.ip6Length != null && (this.ip6Length < 0 || this.ip6Length > 128)) {
            mye = new ZeeError(ZeeErrorName.VALIDATION_IP_MASK_LENGTH_OUT_OF_RANGE, (Object[])null, "IP6 mask length is out of range (0-128): " + this.ip6Length);
            result.add(mye);
        }

        if (this.ip4Base != null && this.ip4WildCardMask == null) {
            mye = new ZeeError(ZeeErrorName.VALIDATION_IP_BASE_WITHOUT_MASK, (Object[])null, "Missing wildcard mask for IP4 base '" + this.ip4Base + "'");
            result.add(mye);
        }

        return result;
    }
}
