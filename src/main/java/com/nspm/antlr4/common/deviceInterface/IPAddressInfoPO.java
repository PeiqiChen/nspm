package com.nspm.antlr4.common.deviceInterface;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import com.nspm.antlr4.common.utils.IPUtil;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import com.nspm.antlr4.exceptions.ZeeError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/9
 */
@Data
@ApiModel("ip地址信息")
public class IPAddressInfoPO implements ZeeCustomValidation, Serializable {

    @NotNull
    @ApiModelProperty("ip类型")
    private IPTypeEnum ipType;
    @ApiModelProperty("行号")
    private ConfigLinePO lineNumbers;
    @ApiModelProperty("接口地址对象的IP地址")
    private IP4AddressPO ip4Address;
    @ApiModelProperty("接口地址对象的子网掩码长度")
    private Long ip4MaskLength;
    @ApiModelProperty("ipv6接口地址对象的IP地址")
    private IP6AddressPO ip6Address;
    @ApiModelProperty("ipv6接口地址对象的子网掩码长度")
    private Long ip6MaskLength;
    private Boolean isManagement;
    @ApiModelProperty("IP地址选项集合")
    private EnumSet<IPAddressOption> options;
    private LinkedHashMap<IPAddressSystemProperty, String> systemProperties;
    private LinkedHashMap<String, String> userProperties;


    public IPAddressInfoPO(IPTypeEnum ipType) {
        this.ipType = ipType;
    }

    public int getIp4MaskLength() {
        if (this.ip4MaskLength == null) {
            return -1;
        }
        return this.ip4MaskLength.intValue();
    }

    public void setIp4Mask(IP4AddressPO netmask) {
        this.ip4MaskLength = Long.valueOf(IPUtil.getNetmaskLength(netmask));
    }

    public int getIp6MaskLength() {
        if (this.ip6MaskLength == null) {
            return -1;
        }
        return this.ip6MaskLength.intValue();
    }

    public boolean isManagement() {
        if (this.isManagement != null) {
            return this.isManagement.booleanValue();
        }
        return false;
    }


    public void addOption(IPAddressOption opt) {
        if (this.options == null) {
            this.options = EnumSet.of(opt);
        } else {
            this.options.add(opt);
        }
    }


    public void addSystemProperty(IPAddressSystemProperty sysProp, String value) {

        if (this.systemProperties == null) {
            this.systemProperties = new LinkedHashMap(4);
        }
        this.systemProperties.put(sysProp, value);
    }

    public boolean hasModelImpactingChange(IPAddressInfoPO oldObj) {
        if (this == oldObj) {
            return false;
        }
        if (oldObj == null) {
            return true;
        }
        if (this.ip4Address == null) {
            if (oldObj.ip4Address != null) {
                return true;
            }
        } else if (!this.ip4Address.equals(oldObj.ip4Address)) {
            return true;
        }
        if (this.ip4MaskLength.equals(oldObj.ip4MaskLength)) {
            return true;
        }
        if (this.ip6Address == null) {
            if (oldObj.ip6Address != null) {
                return true;
            }
        } else if (!this.ip6Address.equals(oldObj.ip6Address)) {
            return true;
        }
        if (this.ip6MaskLength.equals(oldObj.ip6MaskLength)) {
            return true;
        }
        if (this.ipType != oldObj.ipType) {
            return true;
        }
        if (this.options == null) {
            if (oldObj.options != null) {
                return true;
            }
        } else if (!this.options.equals(oldObj.options)) {
            return true;
        }
        if (this.systemProperties == null) {
            if (oldObj.systemProperties != null) {
                return true;
            }
        } else if (!this.systemProperties.equals(oldObj.systemProperties)) {
            return true;
        }
        if (this.userProperties == null) {
            if (oldObj.userProperties != null) {
                return true;
            }
        } else if (!this.userProperties.equals(oldObj.userProperties)) {
            return true;
        }
        return false;
    }


    public static enum IPAddressOption {
        IP6_EUI64, IP6_AUTOCONFIG, IP6_LINK_LOCAL, IP6_LINK_LOCAL_AUTO, IP4_UNNUMBERED, IP4_NEGOTIATED, SECONDARY, DHCP, BOOTP, GLBP, HSRP, VRRP;

        private IPAddressOption() {
        }
    }

    public static enum IPAddressSystemProperty {
        VIP_GROUP_ID;

        private IPAddressSystemProperty() {
        }
    }

    public static boolean optionContainsAny(EnumSet<IPAddressOption> options, IPAddressOption first, IPAddressOption... rest) {
        if ((options == null) || (options.isEmpty())) {
            return false;
        }

        if (options.contains(first)) {
            return true;
        }

        if (rest == null) {
            return false;
        }

        for (IPAddressOption myopt : rest) {
            if (options.contains(myopt)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<ZeeError> validate() {
        List<ZeeError> result = new ArrayList();

        if (this.ipType == IPTypeEnum.IP4) {
            if (this.ip4Address != null) {
                if ((this.ip4MaskLength == null) || (this.ip4MaskLength.intValue() == -1)) {
                    if (!optionContainsAny(this.options, IPAddressOption.GLBP, new IPAddressOption[]{IPAddressOption.HSRP, IPAddressOption.VRRP})) {
                        ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_INTERFACE_IP_WITHOUT_MASK, null, "IP4 interface ip '" + this.ip4Address + "' missing mask length");

                        result.add(mye);
                    }
                } else if ((this.ip4MaskLength.intValue() < 0) || (this.ip4MaskLength.intValue() > 32)) {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_IP_MASK_LENGTH_OUT_OF_RANGE, null, "Mask length for ip4 address '" + this.ip4Address + "' is out of range:" + this.ip4MaskLength);

                    result.add(mye);
                }
            }

            if (this.ip6Address != null) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_IP_TYPE_AND_ADDRESS_MISMATCH, null, "IP type is IP4, but the address is IP6 address '" + this.ip6Address + "'");

                result.add(mye);
            }
        }

        /*     */
        if (this.ipType == IPTypeEnum.IP6) {
            if (this.ip6Address != null) {
                if ((this.ip6MaskLength == null) || (this.ip6MaskLength.intValue() == -1)) {
                    if (!optionContainsAny(this.options, IPAddressOption.GLBP, new IPAddressOption[]{IPAddressOption.HSRP, IPAddressOption.VRRP, IPAddressOption.IP6_LINK_LOCAL})) {
                        ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_INTERFACE_IP_WITHOUT_MASK, null, "IP6 interface ip '" + this.ip6Address + "' missing mask length");

                        result.add(mye);
                    }
                } else if ((this.ip6MaskLength.intValue() < 0) || (this.ip6MaskLength.intValue() > 128)) {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_IP_MASK_LENGTH_OUT_OF_RANGE, null, "Mask length for ip6 address '" + this.ip6Address + "' is out of range:" + this.ip6MaskLength);

                    result.add(mye);
                }
            }

            if (this.ip4Address != null) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_IP_TYPE_AND_ADDRESS_MISMATCH, null, "IP type is IP6, but the address is IP4 address '" + this.ip4Address + "'");

                result.add(mye);
            }
        }
        return result;
    }
}
