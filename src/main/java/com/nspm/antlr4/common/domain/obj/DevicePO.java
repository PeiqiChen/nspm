package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.UuidVersionIdPO;
import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.common.deviceInterface.DeviceInterfacePO;
import com.nspm.antlr4.common.deviceInterface.IPAddressInfoPO;
import com.nspm.antlr4.common.deviceInterface.NetInterfacePO;
import com.nspm.antlr4.common.plugin.PluginVersionPO;
import com.nspm.antlr4.common.domain.vpn.VPNInfoPO;
import com.nspm.antlr4.common.enums.ConfigTypeEnum;
import com.nspm.antlr4.common.enums.DeviceCollectionTypeEnum;
import com.nspm.antlr4.common.enums.DeviceTypeEnum;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.common.interfaces.UuidHolder;
import com.nspm.antlr4.common.utils.DeviceValidator;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "设备对象")
@Document(collection = "DEVICE")
@Data
@CompoundIndexes({@CompoundIndex(name = "uuid_idx", def = "{'uuid': 1}", unique = true), @CompoundIndex(name = "primaryId_idx", def = "{'primaryId': 1}", unique = true, sparse = true), @CompoundIndex(name = "subnet4Uuid_idx", def = "{'interfaces.ip4SubnetUuid': 1}", unique = false), @CompoundIndex(name = "subnet6Uuid_idx", def = "{'interfaces.ip6SubnetUuid': 1}", unique = false)})
public class DevicePO extends COWAuditBaseEntity implements UuidHolder, ZeeCustomValidation, Serializable {


    @JsonIgnore
    @Enumerated(EnumType.STRING)
    protected ConfigTypeEnum configType;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private DeviceCollectionTypeEnum collectType;
    @ApiModelProperty("primaryId 或管理地址")
    private String primaryId;
    @ApiModelProperty("设备唯一ID")
    protected String uuid;
    private Boolean ipv6Enabled;
    @JsonIgnore
    private SubDeviceVersionIdPO linkedSubDeviceVerId;
    private Boolean isVsys;
    private String rootDeviceUuid;
    private String vsysName;
    private String rootName;
    @JsonIgnore
    private String deviceLogUuid;
    @JsonIgnore
    @ApiModelProperty("插件版本对象")
    private PluginVersionPO pluginVersion;
    @JsonIgnore
    private String pluginGitId;
    @JsonIgnore
    private Set<String> changedBy;
    @NotNull
    @Size(max = 256)
    private String name;
    @Size(max = 1024)
    private String description;
    private String[] tags;
    @NotNull
    @Size(max = 256)
    @ApiModelProperty("厂商名称")
    private String manufacturer;
    @NotNull
    @Size(max = 256)
    @ApiModelProperty("厂商元数据名称")
    private String deviceMetadataName;
    @NotNull
    @Size(max = 256)
    @ApiModelProperty("操作系统平台")
    private String osPlatform;
    @Size(max = 256)
    @ApiModelProperty("操作系统版本")
    private String osVersion;
    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty("设备类型")
    private DeviceTypeEnum deviceType;
    @NotNull
    @ApiModelProperty("设备簇")
    private String deviceFamily;
    @ApiModelProperty(value = "配置文件对象id")
    private String rawConfigTextRefId;
    @JsonIgnore
    private String rawJsonTextRefId;
    private String rawImportTextRefId;
    @JsonIgnore
    private String lineNumberStoreRefId;
    @Valid
    private List<UuidVersionIdPO> ipItems;
    @Valid
    private List<UuidVersionIdPO> ipGroups;
    @Valid
    @ApiModelProperty("域uuid 集合")
    private List<UuidVersionIdPO> interfaceGroups;
    @Valid
    private List<UuidVersionIdPO> services;
    @Valid
    private List<UuidVersionIdPO> serviceGroups;
    @Valid
    private List<UuidVersionIdPO> userGroups;
    @Valid
    private List<UuidVersionIdPO> appGroups;
    @Valid
    private List<UuidVersionIdPO> timeGroups;
    @Valid
    @ApiModelProperty("设备接口集，源于用户配置，只有接口名称、地址")
    private List<DeviceInterfacePO> deviceInterfaces;
    @Valid
    private List<VPNInfoPO> vpnInfos;
    @ApiModelProperty("接口集，是系统内部处理的接口信息，包含接口、子网uuid")
    private List<NetInterfacePO> interfaces;

    @ApiModelProperty("策略集uuid")
    private List<UuidVersionIdPO> filterLists;
    private List<UuidVersionIdPO> routingSubnetGroups;

    @ApiModelProperty("静态路由集的uuid")
    private List<UuidVersionIdPO> routingTableIds;

    public Boolean getVsys() {
        if (StringUtils.isEmpty(this.vsysName)) {
            return false;
        }
        return true;
    }

    public boolean isIpv6Enabled() {
        return this.ipv6Enabled == null ? true : this.ipv6Enabled;
    }



    public String getDisplayName() {
        return this.name;
    }

    public List<IPAddressInfoPO> getIpAddressInfos() {
        List<IPAddressInfoPO> result = new ArrayList();
        if (this.deviceInterfaces == null) {
            return result;
        }
        Iterator iterator = this.deviceInterfaces.iterator();

        while (iterator.hasNext()) {
            DeviceInterfacePO mydi = (DeviceInterfacePO) iterator.next();
            List<IPAddressInfoPO> myinfos = mydi.getIpAddresses();
            if (myinfos != null) {
                result.addAll(myinfos);
            }
        }

        return result;
    }

    @Override
    public List<ZeeError> validate() {
        List<ZeeError> result = new ArrayList();

        DeviceValidator.validateDevice(this, result);
        return result;
    }
}
