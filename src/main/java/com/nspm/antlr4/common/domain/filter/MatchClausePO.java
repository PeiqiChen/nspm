package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.obj.IPItemPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel("策略匹配条款模型")
public class MatchClausePO implements Serializable {

    public static final MatchClausePO IP4_ANY = new MatchClausePO(IPItemPO.IP4_ANY, IPItemPO.IP4_ANY);

    public static final MatchClausePO IP6_ANY = new MatchClausePO(IPItemPO.IP6_ANY, IPItemPO.IP6_ANY);

    @ApiModelProperty("引用策略集")
    private String filterRuleListRef;

    @Valid
    @ApiModelProperty("协议集")
    private ProtocolMatchSpecPO[] protocol;

    @Valid
    private UserMatchSpecPO[] srcUser;

    @Valid
    @ApiModelProperty("源IP地址集")
    private IPItemPO[] srcIp;
    @Valid
    @ApiModelProperty("源端口集")
    private PortMatchSpecPO[] srcPort;
    @ApiModelProperty("源的其余信息")
    private String[] miscSrcOptions;

    private Boolean isSrcIpNegate;

    private UserMatchSpecPO[] dstUser;

    @Valid
    @ApiModelProperty("目的IP地址集")
    private IPItemPO[] dstIp;

    @Valid
    @ApiModelProperty("目的端口集")
    private PortMatchSpecPO[] dstPort;
    @ApiModelProperty("目的其余的信息")
    private String[] miscDstOptions;

    private Boolean isDstIpNegate;

    @Valid
    private AppMatchSpecPO[] app;
    @ApiModelProperty("多余的信息")
    private String[] miscOptions;
    @ApiModelProperty("服务信息集")
    private ServiceMatchSpecPO[] services;
    @ApiModelProperty("排除的协议集")
    private ProtocolMatchSpecPO[] excludeProtocol;
    @ApiModelProperty("排除的源IP地址集")
    private IPItemPO[] excludeSrcIp;
    @ApiModelProperty("排除的目的IP地址集")
    private IPItemPO[] excludeDstIp;
    @ApiModelProperty("排除的源端口集")
    private PortMatchSpecPO[] excludeSrcPort;
    @ApiModelProperty("排除的目的端口集")
    private PortMatchSpecPO[] excludeDstPort;

    public MatchClausePO() {
    }

    public MatchClausePO(String filterRuleListRef) {
        this.filterRuleListRef = filterRuleListRef;
    }

    public MatchClausePO(IPItemPO srcIp, IPItemPO dstIp) {
        this.srcIp = new IPItemPO[]{srcIp};
        this.dstIp = new IPItemPO[]{dstIp};
    }

    public MatchClausePO(IPItemPO[] srcIp, IPItemPO[] dstIp) {
        this.srcIp = srcIp;
        this.dstIp = dstIp;
    }

    protected MatchClausePO(String filterRuleListRef, List<IPItemPO> srcIPItemsList, List<IPItemPO> dstIPItemsList) {
        this.filterRuleListRef = filterRuleListRef;
        this.srcIp = (srcIPItemsList == null ? null : srcIPItemsList.toArray(new IPItemPO[0]));
        this.dstIp = (dstIPItemsList == null ? null : dstIPItemsList.toArray(new IPItemPO[0]));
    }

    public String getFilterRuleListRef() {
        return this.filterRuleListRef;
    }

    public void setFilterRuleListRef(String filterRuleListRef) {
        this.filterRuleListRef = filterRuleListRef;
    }

    public ProtocolMatchSpecPO[] getProtocol() {
        return this.protocol;
    }

    public void setProtocol(ProtocolMatchSpecPO[] protocol) {
        this.protocol = protocol;
    }

    public void addProtocol(ProtocolMatchSpecPO item) {
        if (item == null) {
            return;
        }
        if (this.protocol == null) {
            this.protocol = new ProtocolMatchSpecPO[]{item};
        } else {
            ProtocolMatchSpecPO[] newArr = new ProtocolMatchSpecPO[this.protocol.length + 1];
            System.arraycopy(this.protocol, 0, newArr, 0, this.protocol.length);
            newArr[(newArr.length - 1)] = item;
            this.protocol = newArr;
        }
    }

    /*     */
    public IPItemPO[] getSrcIp() {
        return this.srcIp;
    }

    public void setSrcIp(IPItemPO[] srcIp) {
        this.srcIp = srcIp;
    }

    public void addSrcIpItem(IPItemPO item) {
        if (item == null) {
            return;
        }
        if (this.srcIp == null) {
            this.srcIp = new IPItemPO[]{item};
        } else {
            IPItemPO[] newArr = new IPItemPO[this.srcIp.length + 1];
            System.arraycopy(this.srcIp, 0, newArr, 0, this.srcIp.length);
            newArr[(newArr.length - 1)] = item;
            this.srcIp = newArr;
        }
    }

    public PortMatchSpecPO[] getSrcPort() {
        return this.srcPort;
    }

    public void setSrcPort(PortMatchSpecPO[] srcPort) {
        this.srcPort = srcPort;
    }

    public void addSrcPort(PortMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.srcPort == null) {
            this.srcPort = new PortMatchSpecPO[]{item};
        } else {
            PortMatchSpecPO[] newArr = new PortMatchSpecPO[this.srcPort.length + 1];
            System.arraycopy(this.srcPort, 0, newArr, 0, this.srcPort.length);
            newArr[(newArr.length - 1)] = item;
            this.srcPort = newArr;
        }
    }

    public String[] getMiscSrcOptions() {
        return this.miscSrcOptions;
    }

    public void setMiscSrcOptions(String[] miscSrcOptions) {
        this.miscSrcOptions = miscSrcOptions;
    }

    public void addMiscSrcOption(String srcOption) {
        if (srcOption == null) {
            return;
        }
        if (this.miscSrcOptions == null) {
            this.miscSrcOptions = new String[]{srcOption};
        } else {
            String[] newArr = new String[this.miscSrcOptions.length + 1];
            System.arraycopy(this.miscSrcOptions, 0, newArr, 0, this.miscSrcOptions.length);

            newArr[(newArr.length - 1)] = srcOption;
            this.miscSrcOptions = newArr;
        }
    }

    public boolean isSrcIPNegate() {
        if (this.isSrcIpNegate == null) {
            return false;
        }
        return this.isSrcIpNegate;
    }

    public Boolean getIsSrcIpNegate() {
        return this.isSrcIpNegate;
    }

    public void setIsSrcIpNegate(Boolean isSrcIPNegate) {
        this.isSrcIpNegate = isSrcIPNegate;
    }

    public UserMatchSpecPO[] getDstUser() {
        return this.dstUser;
    }

    public void setDstUser(UserMatchSpecPO[] dstUser) {
        this.dstUser = dstUser;
    }

    public void addDstUser(UserMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.dstUser == null) {
            this.dstUser = new UserMatchSpecPO[]{item};
        } else {
            UserMatchSpecPO[] newArr = new UserMatchSpecPO[this.dstUser.length + 1];
            System.arraycopy(this.dstUser, 0, newArr, 0, this.dstUser.length);
            newArr[(newArr.length - 1)] = item;
            this.dstUser = newArr;
        }
    }

    public IPItemPO[] getDstIp() {
        return this.dstIp;
    }

    public void setDstIp(IPItemPO[] dstIp) {
        this.dstIp = dstIp;
    }

    public void addDstIpItem(IPItemPO item) {
        if (item == null) {
            return;
        }
        if (this.dstIp == null) {
            this.dstIp = new IPItemPO[]{item};
        } else {
            IPItemPO[] newArr = new IPItemPO[this.dstIp.length + 1];
            System.arraycopy(this.dstIp, 0, newArr, 0, this.dstIp.length);
            newArr[(newArr.length - 1)] = item;
            this.dstIp = newArr;
        }
    }

    public void addIpItem(IPItemPO item, boolean isSrc) {
        if (isSrc) {
            addSrcIpItem(item);
        } else {
            addDstIpItem(item);
        }
    }

    public PortMatchSpecPO[] getDstPort() {
        return this.dstPort;
    }

    public void setDstPort(PortMatchSpecPO[] dstPort) {
        this.dstPort = dstPort;
    }

    public void addDstPort(PortMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.dstPort == null) {
            this.dstPort = new PortMatchSpecPO[]{item};
        } else {
            PortMatchSpecPO[] newArr = new PortMatchSpecPO[this.dstPort.length + 1];
            System.arraycopy(this.dstPort, 0, newArr, 0, this.dstPort.length);
            newArr[(newArr.length - 1)] = item;
            this.dstPort = newArr;
        }
    }

    public void addPort(PortMatchSpecPO portSpec, boolean isSrc) {
        if (isSrc) {
            addSrcPort(portSpec);
        } else {
            addDstPort(portSpec);
        }
    }

    public String[] getMiscDstOptions() {
        return this.miscDstOptions;
    }

    public void setMiscDstOptions(String[] miscDstOptions) {
        this.miscDstOptions = miscDstOptions;
    }

    public void addMiscDstOption(String dstOption) {
        if (dstOption == null) {
            return;
        }
        if (this.miscDstOptions == null) {
            this.miscDstOptions = new String[]{dstOption};
        } else {
            String[] newArr = new String[this.miscDstOptions.length + 1];
            System.arraycopy(this.miscDstOptions, 0, newArr, 0, this.miscDstOptions.length);

            newArr[(newArr.length - 1)] = dstOption;
            this.miscDstOptions = newArr;
        }
    }

    public String[] getMiscOptions() {
        return this.miscOptions;
    }

    public void setMiscOptions(String[] miscOptions) {
        this.miscOptions = miscOptions;
    }

    public void addMiscOption(String miscOption) {
        if (miscOption == null) {
            return;
        }
        if (this.miscOptions == null) {
            this.miscOptions = new String[]{miscOption};
        } else {
            String[] newArr = new String[this.miscOptions.length + 1];
            System.arraycopy(this.miscOptions, 0, newArr, 0, this.miscOptions.length);

            newArr[(newArr.length - 1)] = miscOption;
            this.miscOptions = newArr;
        }
    }

    public boolean isDstIPNegate() {
        if (this.isDstIpNegate == null) {
            return false;
        }
        return this.isDstIpNegate;
    }

    public Boolean getIsDstIpNegate() {
        return this.isDstIpNegate;
    }

    public void setIsDstIpNegate(Boolean isDstIPNegate) {
        this.isDstIpNegate = isDstIPNegate;
    }

    public AppMatchSpecPO[] getApp() {
        return this.app;
    }

    public void setApp(AppMatchSpecPO[] app) {
        this.app = app;
    }

    public void addApp(AppMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.app == null) {
            this.app = new AppMatchSpecPO[]{item};
        } else {
            AppMatchSpecPO[] newArr = new AppMatchSpecPO[this.app.length + 1];
            System.arraycopy(this.app, 0, newArr, 0, this.app.length);
            newArr[(newArr.length - 1)] = item;
            this.app = newArr;
        }
    }

    public ServiceMatchSpecPO[] getServices() {
        return this.services;
    }

    public void setServices(ServiceMatchSpecPO[] services) {
        this.services = services;
    }

    public void addService(ServiceMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.services == null) {
            this.services = new ServiceMatchSpecPO[]{item};
        } else {
            ServiceMatchSpecPO[] newArr = new ServiceMatchSpecPO[this.services.length + 1];
            System.arraycopy(this.services, 0, newArr, 0, this.services.length);
            newArr[(newArr.length - 1)] = item;
            this.services = newArr;
        }
    }

    public void setIPItem(IPItemPO item, boolean isSrc) {
        if (isSrc) {
            setSrcIp(new IPItemPO[]{item});
        } else {
            setDstIp(new IPItemPO[]{item});
        }
    }

    public UserMatchSpecPO[] getSrcUser() {
        return this.srcUser;
    }

    public void setSrcUser(UserMatchSpecPO[] srcUser) {
        this.srcUser = srcUser;
    }

    public void addSrcUser(UserMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.srcUser == null) {
            this.srcUser = new UserMatchSpecPO[]{item};
        } else {
            UserMatchSpecPO[] newArr = new UserMatchSpecPO[this.srcUser.length + 1];
            System.arraycopy(this.srcUser, 0, newArr, 0, this.srcUser.length);
            newArr[(newArr.length - 1)] = item;
            this.srcUser = newArr;
        }
    }

    public ProtocolMatchSpecPO[] getExcludeProtocol() {
        return this.excludeProtocol;
    }

    public void setExcludeProtocol(ProtocolMatchSpecPO[] excludeProtocol) {
        this.excludeProtocol = excludeProtocol;
    }

    public void addExcludeProtocol(ProtocolMatchSpecPO item) {
        if (item == null) {
            return;
        }
        if (this.excludeProtocol == null) {
            this.excludeProtocol = new ProtocolMatchSpecPO[]{item};
        } else {
            ProtocolMatchSpecPO[] newArr = new ProtocolMatchSpecPO[this.excludeProtocol.length + 1];
            System.arraycopy(this.excludeProtocol, 0, newArr, 0, this.excludeProtocol.length);
            newArr[(newArr.length - 1)] = item;
            this.excludeProtocol = newArr;
        }
    }

    public IPItemPO[] getExcludeSrcIp() {
        return this.excludeSrcIp;
    }

    public void setExcludeSrcIp(IPItemPO[] excludeSrcIp) {
        this.excludeSrcIp = excludeSrcIp;
    }

    public void addExcludeSrcIpItem(IPItemPO item) {
        if (item == null) {
            return;
        }
        if (this.excludeSrcIp == null) {
            this.excludeSrcIp = new IPItemPO[]{item};
        } else {
            IPItemPO[] newArr = new IPItemPO[this.excludeSrcIp.length + 1];
            System.arraycopy(this.excludeSrcIp, 0, newArr, 0, this.excludeSrcIp.length);
            newArr[(newArr.length - 1)] = item;
            this.excludeSrcIp = newArr;
        }
    }

    public IPItemPO[] getExcludeDstIp() {
        return this.excludeDstIp;
    }

    public void setExcludeDstIp(IPItemPO[] excludeDstIp) {
        this.excludeDstIp = excludeDstIp;
    }

    public void addExcludeDstIpItem(IPItemPO item) {
        if (item == null) {
            return;
        }
        if (this.excludeDstIp == null) {
            this.excludeDstIp = new IPItemPO[]{item};
        } else {
            IPItemPO[] newArr = new IPItemPO[this.excludeDstIp.length + 1];
            System.arraycopy(this.excludeDstIp, 0, newArr, 0, this.excludeDstIp.length);
            newArr[(newArr.length - 1)] = item;
            this.excludeDstIp = newArr;
        }
    }

    public PortMatchSpecPO[] getExcludeSrcPort() {
        return this.excludeSrcPort;
    }

    public void setExcludeSrcPort(PortMatchSpecPO[] excludeSrcPort) {
        this.excludeSrcPort = excludeSrcPort;
    }

    public void addExcludePort(PortMatchSpecPO item, boolean isSrc) {
        if (isSrc) {
            addExcludeSrcPort(item);
        } else {
            addExcludeDstPort(item);
        }
    }

    public void addExcludeSrcPort(PortMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.excludeSrcPort == null) {
            this.excludeSrcPort = new PortMatchSpecPO[]{item};
        } else {
            PortMatchSpecPO[] newArr = new PortMatchSpecPO[this.excludeSrcPort.length + 1];
            System.arraycopy(this.excludeSrcPort, 0, newArr, 0, this.excludeSrcPort.length);
            newArr[(newArr.length - 1)] = item;
            this.excludeSrcPort = newArr;
        }
    }

    public PortMatchSpecPO[] getExcludeDstPort() {
        return this.excludeDstPort;
    }

    public void setExcludeDstPort(PortMatchSpecPO[] excludeDstPort) {
        this.excludeDstPort = excludeDstPort;
    }

    public void addExcludeDstPort(PortMatchSpecPO item) {
        if (item == null) {
            return;
        }

        if (this.excludeDstPort == null) {
            this.excludeDstPort = new PortMatchSpecPO[]{item};
        } else {
            PortMatchSpecPO[] newArr = new PortMatchSpecPO[this.excludeDstPort.length + 1];
            System.arraycopy(this.excludeDstPort, 0, newArr, 0, this.excludeDstPort.length);
            newArr[(newArr.length - 1)] = item;
            this.excludeDstPort = newArr;
        }
    }

}
