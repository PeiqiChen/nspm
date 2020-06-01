package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.obj.IPItemPO;
import com.nspm.antlr4.common.utils.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/10
 */
@ApiModel("nat策略匹配条款")
@Data
public class NATClausePO implements Serializable {

    @NotNull
    @ApiModelProperty("类型，可能的值有 STATIC, DYNAMIC, IDENTITY")
    private NATType type;

    private String filterRuleListRef;
    @Valid
    @ApiModelProperty("协议集")
    private ProtocolMatchSpecPO[] protocols;
    @Valid
    @ApiModelProperty("在源地址转换中，该字段表示转换前的源地址；在目的地址转换中，该字段仅用于过滤匹配。")
    private IPItemPO[] preSrcIPItems;
    private Boolean isPreSrcIpNegate;
    @Valid
    @ApiModelProperty("在目的地址转换中，该字段表示转换前的目的地址；在源地址转换中，该字段仅用于过滤匹配。")
    private IPItemPO[] preDstIPItems;
    private Boolean isPreDstIpNegate;
    @Valid
    @ApiModelProperty("在源地址转换中，该字段表示转换后的源地址；在目的地址转换中，该字段仅用于过滤匹配。")
    private IPItemPO[] postSrcIPItems;
    @Valid
    @ApiModelProperty("在目的地址转换中，该字段表示转换后的目的地址；在源地址转换中，该字段仅用于过滤匹配。")
    private IPItemPO[] postDstIPItems;
    @ApiModelProperty("在源地址转换中，该字段表示转换前的端口；在目的地址转换中，该字段仅用于过滤匹配。")
    private PortMatchSpecPO[] preSrcPortSpec;
    @ApiModelProperty("在目的地址转换中，该字段表示转换前的端口；在源地址转换中，该字段仅用于过滤匹配。")
    private PortMatchSpecPO[] preDstPortSpec;
    @Valid
    @ApiModelProperty("转换前的服务匹配")
    private ServiceMatchSpecPO[] preServices;
    @Valid
    @ApiModelProperty("转换后的服务匹配")
    private ServiceMatchSpecPO[] postServices;
    @ApiModelProperty("与preSrcPortSpec不同，该字段仅用于源地址转换，且仅用于过滤匹配。")
    private PortMatchSpecPO[] postSrcPortSpec;
    @ApiModelProperty("在目的地址转换中，该字段表示转换后的目的地址；在源地址转换中，该字段仅用于过滤匹配。")
    private PortMatchSpecPO[] postDstPortSpec;
    @ApiModelProperty("NAT将使用指定的出接口IP地址作为转换后的地址。该字段用于源地址转换，即用于outbound接口上。")
    private String outInterfaceRef;
    @ApiModelProperty("NAT将使用入接口的IP地址作为待转换的地址，该字段用于目的地址转换，即用于inbound接口上。")
    private String preDstInInterfaceRef;
    private Boolean useEifAsPostSrcIp;
    @ApiModelProperty("是否可逆的")
    private Boolean isReverse;
    @ApiModelProperty("nat策略类型")
    private NATField natField;

    public NATClausePO() {
    }

    public NATClausePO(NATType type, String filterRuleListRef, List<ProtocolMatchSpecPO> protocolsList, List<IPItemPO> preSrcIPItemsList, List<IPItemPO> preDstIPItemsList, List<IPItemPO> postSrcIPItemsList, List<IPItemPO> postDstIPItemsList, List<PortMatchSpecPO> preSrcPortsList, List<PortMatchSpecPO> preDstPortsList, List<ServiceMatchSpecPO> preServiceList, List<PortMatchSpecPO> postSrcPortsList, List<PortMatchSpecPO> postDstPortsList, String outInterfaceRef) {
        this.type = type;
        this.filterRuleListRef = filterRuleListRef;
        this.protocols = (protocolsList == null ? null : protocolsList.toArray(new ProtocolMatchSpecPO[protocolsList.size()]));
        this.preSrcIPItems = (preSrcIPItemsList == null ? null : preSrcIPItemsList.toArray(new IPItemPO[preSrcIPItemsList.size()]));
        this.preDstIPItems = (preDstIPItemsList == null ? null : preDstIPItemsList.toArray(new IPItemPO[preDstIPItemsList.size()]));
        this.postSrcIPItems = (postSrcIPItemsList == null ? null : postSrcIPItemsList.toArray(new IPItemPO[postSrcIPItemsList.size()]));
        this.postDstIPItems = (postDstIPItemsList == null ? null : postDstIPItemsList.toArray(new IPItemPO[postDstIPItemsList.size()]));
        this.preSrcPortSpec = (preSrcPortsList == null ? null : preSrcPortsList.toArray(new PortMatchSpecPO[preSrcPortsList.size()]));
        this.preDstPortSpec = (preDstPortsList == null ? null : preDstPortsList.toArray(new PortMatchSpecPO[preDstPortsList.size()]));
        this.preServices = (preServiceList == null ? null : preServiceList.toArray(new ServiceMatchSpecPO[preServiceList.size()]));
        this.postSrcPortSpec = (postSrcPortsList == null ? null : postSrcPortsList.toArray(new PortMatchSpecPO[postSrcPortsList.size()]));
        this.postDstPortSpec = (postDstPortsList == null ? null : postDstPortsList.toArray(new PortMatchSpecPO[postDstPortsList.size()]));
        this.outInterfaceRef = outInterfaceRef;
    }

    public NATType getType() {
        return this.type;
    }

    public void setType(NATType type) {
        this.type = type;
    }

    public String getFilterRuleListRef() {
        return this.filterRuleListRef;
    }

    public void setFilterRuleListRef(String filterRuleListRef) {
        this.filterRuleListRef = filterRuleListRef;
    }

    public ProtocolMatchSpecPO[] getProtocols() {
        return this.protocols;
    }

    public void setProtocols(ProtocolMatchSpecPO[] protocols) {
        this.protocols = protocols;
    }

    public IPItemPO[] getPreSrcIPItems() {
        return this.preSrcIPItems;
    }

    public void setPreSrcIPItems(IPItemPO[] preSrcIPItems) {
        this.preSrcIPItems = preSrcIPItems;
    }

    public List<IPItemPO> getPreSrcIPItemsAsList() {
        return this.preSrcIPItems == null ? null : Collections.unmodifiableList(Arrays.asList(this.preSrcIPItems));
    }

    public List<IPItemPO> getPreDstIPItems() {
        return this.preDstIPItems == null ? null : Collections.unmodifiableList(Arrays.asList(this.preDstIPItems));
    }

    public void setPreDstIPItems(IPItemPO[] preDstIPItems) {
        this.preDstIPItems = preDstIPItems;
    }

    public IPItemPO[] getPreDstIPItemsAsArray() {
        return this.preDstIPItems;
    }

    public List<IPItemPO> getPostSrcIPItems() {
        return this.postSrcIPItems == null ? null : Collections.unmodifiableList(Arrays.asList(this.postSrcIPItems));
    }

    public void setPostSrcIPItems(IPItemPO[] postSrcIPItems) {
        this.postSrcIPItems = postSrcIPItems;
    }

    public IPItemPO[] getPostSrcIPItemsAsArray() {
        return this.postSrcIPItems;
    }

    public IPItemPO[] getPostDstIPItems() {
        return this.postDstIPItems;
    }

    public void setPostDstIPItems(IPItemPO[] postDstIPItems) {
        this.postDstIPItems = postDstIPItems;
    }

    public List<IPItemPO> getPostDstIPItemsAsList() {
        return this.postDstIPItems == null ? null : Collections.unmodifiableList(Arrays.asList(this.postDstIPItems));
    }

    private PortMatchSpecPO[] addPortMatchSpec(PortMatchSpecPO[] oldArr, PortMatchSpecPO item) {
        if (item == null) {
            return oldArr;
        }

        PortMatchSpecPO[] result = null;

        if (oldArr == null) {
            result = new PortMatchSpecPO[]{item};
        } else {
            PortMatchSpecPO[] newArr = new PortMatchSpecPO[oldArr.length + 1];
            System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);
            newArr[(newArr.length - 1)] = item;
            result = newArr;
        }

        return result;
    }

    public PortMatchSpecPO[] getPreSrcPortSpec() {
        return this.preSrcPortSpec;
    }

    public void setPreSrcPortSpec(PortMatchSpecPO[] preSrcPortSpec) {
        this.preSrcPortSpec = preSrcPortSpec;
    }

    public void addPreSrcPortSpec(PortMatchSpecPO item) {
        this.preSrcPortSpec = addPortMatchSpec(this.preSrcPortSpec, item);
    }

    public PortMatchSpecPO[] getPreDstPortSpec() {
        return this.preDstPortSpec;
    }

    public void setPreDstPortSpec(PortMatchSpecPO[] preDstPortSpec) {
        this.preDstPortSpec = preDstPortSpec;
    }

    public void addPreDstPortSpec(PortMatchSpecPO item) {
        this.preDstPortSpec = addPortMatchSpec(this.preDstPortSpec, item);
    }

    private ServiceMatchSpecPO[] addServiceMatchSpec(ServiceMatchSpecPO[] oldArr, ServiceMatchSpecPO item) {
        if (item == null) {
            return oldArr;
        }

        ServiceMatchSpecPO[] result = null;

        if (oldArr == null) {
            result = new ServiceMatchSpecPO[]{item};
        } else {
            ServiceMatchSpecPO[] newArr = new ServiceMatchSpecPO[oldArr.length + 1];
            System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);
            newArr[(newArr.length - 1)] = item;
            result = newArr;
        }

        return result;
    }

    public ServiceMatchSpecPO[] getPreServices() {
        return this.preServices;
    }

    public void setPreServices(ServiceMatchSpecPO[] preServices) {
        this.preServices = preServices;
    }

    public void addPreService(ServiceMatchSpecPO item) {
        this.preServices = addServiceMatchSpec(this.preServices, item);
    }

    public PortMatchSpecPO[] getPostSrcPortSpec() {
        return this.postSrcPortSpec;
    }

    public void setPostSrcPortSpec(PortMatchSpecPO[] postSrcPortSpec) {
        this.postSrcPortSpec = postSrcPortSpec;
    }

    public List<PortMatchSpecPO> getPostSrcPortSpecAsList() {
        return this.postSrcPortSpec == null ? null : Collections.unmodifiableList(Arrays.asList(this.postSrcPortSpec));
    }

    public void addPostSrcPortSpec(PortMatchSpecPO item) {
        this.postSrcPortSpec = addPortMatchSpec(this.postSrcPortSpec, item);
    }

    public PortMatchSpecPO[] getPostDstPortSpec() {
        return this.postDstPortSpec;
    }

    public void setPostDstPortSpec(PortMatchSpecPO[] postDstPortSpec) {
        this.postDstPortSpec = postDstPortSpec;
    }

    public List<PortMatchSpecPO> getPostDstPortSpecAsList() {
        return this.postDstPortSpec == null ? null : Collections.unmodifiableList(Arrays.asList(this.postDstPortSpec));
    }

    public void addPostDstPortSpec(PortMatchSpecPO item) {
        this.postDstPortSpec = addPortMatchSpec(this.postDstPortSpec, item);
    }

    public String getOutInterfaceRef() {
        return this.outInterfaceRef;
    }

    public void setOutInterfaceRef(String outInterfaceRef) {
        this.outInterfaceRef = outInterfaceRef;
    }

    public String getPreDstInInterfaceRef() {
        return this.preDstInInterfaceRef;
    }

    public void setPreDstInInterfaceRef(String preDstInInterfaceRef) {
        this.preDstInInterfaceRef = preDstInInterfaceRef;
    }

    public boolean isUseEifAsPostSrcIp() {
        if (this.useEifAsPostSrcIp == null) {
            return false;
        }
        return this.useEifAsPostSrcIp.booleanValue();
    }

    public Boolean getUseEifAsPostSrcIp() {
        return this.useEifAsPostSrcIp;
    }

    public void setUseEifAsPostSrcIp(Boolean useEifAsPostSrcIp) {
        this.useEifAsPostSrcIp = useEifAsPostSrcIp;
    }

    private IPItemPO[] addIPItem(IPItemPO[] oldArr, IPItemPO item) {
        if (item == null) {
            return oldArr;
        }

        IPItemPO[] result = null;

        if (oldArr == null) {
            result = new IPItemPO[]{item};
        } else {
            IPItemPO[] newArr = new IPItemPO[oldArr.length + 1];
            System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);
            newArr[(newArr.length - 1)] = item;
            result = newArr;
        }

        return result;
    }

    public void addPreSrcIPItem(IPItemPO item) {
        this.preSrcIPItems = addIPItem(this.preSrcIPItems, item);
    }

    public void addPreDstIPItem(IPItemPO item) {
        this.preDstIPItems = addIPItem(this.preDstIPItems, item);
    }

    public void addPostSrcIPItem(IPItemPO item) {
        this.postSrcIPItems = addIPItem(this.postSrcIPItems, item);
    }

    public void addPostDstIPItem(IPItemPO item) {
        this.postDstIPItems = addIPItem(this.postDstIPItems, item);
    }

    public boolean isReverse() {
        if (this.isReverse == null) {
            return false;
        }
        return this.isReverse;
    }

    public void setReverse(boolean isReverse) {
        this.isReverse = isReverse;
    }

    public NATField getNatField() {
        if (this.natField != null) {
            return this.natField;
        }

        return calculateNatField();
    }

    public void setNatField(NATField natField) {
        this.natField = natField;
    }

    public ServiceMatchSpecPO[] getPostServices() {
        return this.postServices;
    }

    public void setPostServices(ServiceMatchSpecPO[] postServices) {
        this.postServices = postServices;
    }

    public void addPostService(ServiceMatchSpecPO item) {
        this.postServices = addServiceMatchSpec(this.postServices, item);
    }

    public NATClausePO shallowCopy() {
        NATClausePO result = new NATClausePO();

        result.type = this.type;
        result.filterRuleListRef = this.filterRuleListRef;
        result.protocols = this.protocols;
        result.preSrcIPItems = this.preSrcIPItems;
        result.preDstIPItems = this.preDstIPItems;
        result.postSrcIPItems = this.postSrcIPItems;
        result.postDstIPItems = this.postDstIPItems;
        result.preSrcPortSpec = this.preSrcPortSpec;
        result.preDstPortSpec = this.preDstPortSpec;
        result.preServices = this.preServices;
        result.postServices = this.postServices;
        result.postSrcPortSpec = this.postSrcPortSpec;
        result.postDstPortSpec = this.postDstPortSpec;
        result.outInterfaceRef = this.outInterfaceRef;
        result.preDstInInterfaceRef = this.preDstInInterfaceRef;
        result.useEifAsPostSrcIp = this.useEifAsPostSrcIp;
        result.isReverse = this.isReverse;
        result.natField = this.natField;

        return result;
    }

    public boolean hasPreValue() {
        if (this.filterRuleListRef != null) {
            return true;
        }

        if (!ObjectUtil.isNullOrEmpty(this.protocols)) {
            return true;
        }
        if (!ObjectUtil.isNullOrEmpty(this.preSrcIPItems)) {
            return true;
        }
        if (!ObjectUtil.isNullOrEmpty(this.preDstIPItems)) {
            return true;
        }
        if (!ObjectUtil.isNullOrEmpty(this.preSrcPortSpec)) {
            return true;
        }
        if (!ObjectUtil.isNullOrEmpty(this.preDstPortSpec)) {
            return true;
        }
        if (!ObjectUtil.isNullOrEmpty(this.preServices)) {
            return true;
        }
        return this.preDstInInterfaceRef != null;

    }

    private NATField calculateNatField() {
        NATField result = null;

        boolean hasSrcNat = false;
        boolean hasDstNat = false;

        if ((!ObjectUtil.isNullOrEmpty(getPostSrcIPItems())) ||
                (!ObjectUtil.isNullOrEmpty(getPostSrcPortSpecAsList())) || (this.outInterfaceRef != null) ||

                (isUseEifAsPostSrcIp())) {
            if (!isReverse()) {
                hasSrcNat = true;
            } else {
                hasDstNat = true;
            }
        }

        if ((!ObjectUtil.isNullOrEmpty(getPostDstIPItemsAsList())) ||
                (!ObjectUtil.isNullOrEmpty(getPostDstPortSpecAsList())) || (
                (getPostServices() != null) && (getPostServices().length > 0))) {
            if (!isReverse()) {
                hasDstNat = true;
            } else {
                hasSrcNat = true;
            }
        }

        if ((hasSrcNat) && (!hasDstNat)) {
            result = NATField.SRC;
        } else if ((!hasSrcNat) && (hasDstNat)) {
            result = NATField.DST;
        } else if ((hasSrcNat) && (hasDstNat)) {
            result = NATField.BOTH;
        } else {
            result = NATField.UNKNOWN_IDENTITY;
        }

        return result;
    }

    public enum NATType {
        STATIC,
        DYNAMIC,
        IDENTITY;

        NATType() {
        }
    }

    public enum NATField {
        /**
         * 源nat
         */
        SRC,
        /**
         * 目的nat
         */
        DST,
        BOTH,
        BI_DIR_DST,
        BI_DIR_SRC,
        BI_DIR_BOTH,
        UNKNOWN_IDENTITY,

        BOTH_SRC,
        BOTH_DST;

        NATField() {
        }
    }
}
