package com.nspm.antlr4.h3c.device.impl;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.deviceInterface.DeviceInterfacePO;
import com.nspm.antlr4.common.domain.filter.FilterRuleListPO;
import com.nspm.antlr4.common.domain.metadata.DeviceMetadata;
import com.nspm.antlr4.common.domain.obj.DevicePO;
import com.nspm.antlr4.common.domain.obj.FilterServiceGroupPO;
import com.nspm.antlr4.common.domain.obj.GenericDevicePO;
import com.nspm.antlr4.common.domain.routing.RoutingTablePO;
import com.nspm.antlr4.common.plugin.VisitorHelper;
import com.nspm.antlr4.gen.H3CBaseVisitor;
import com.nspm.antlr4.gen.H3CParser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class H3CVisitorBasic<T> extends H3CBaseVisitor<T> {


    /**
     * 虚拟转发路由集的接口组名的后缀
     */
    static final String VRF_IF_GROUP_POSTFIX = "_vrf_interface_group";

    /**
     * nat策略集的key值
     */
    static final String NAT_ACL = "__NAT_LIST1";

    /**
     * nat地址池名字的前缀
     */
    static final String NEW_NAT_ADDR_GROUP = "NAT_ADDR_GROUP_";

    ParseTreeProperty<Object> parseTreeProperty = new ParseTreeProperty<>();
    GenericDevicePO genericDevice = new GenericDevicePO();
    protected DevicePO device = new DevicePO();
    protected List<DeviceInterfacePO> interfaces = new ArrayList<>();
    LinkedHashMap<String, FilterRuleListPO> aclMap = new LinkedHashMap<>();
    Map<String, String> aclNameMap = new HashMap<>();
    LinkedHashMap<String, RoutingTablePO> routingTables = new LinkedHashMap<>();
    LinkedHashMap<String, List<String>> routingTableInterfaceList = new LinkedHashMap<>();
    protected DeviceMetadata deviceMeta = null;
    protected Map<String, FilterServiceGroupPO> preDefinedService;
    protected Map<String, LinkedHashSet<String>> pbrIfMap = new LinkedHashMap<>();
    protected static final String PBR_IF_GROUP_PREFIX = "PBR_INTERFACE_GROUP_";
    boolean isDefaultPolicyPermitAll = false;
    String defaultPolicyPermitAllMsg = "";
    ConfigLinePO defaultPolicyPermitAllLine = null;
    FilterRuleListPO defaultPolicyRules = null;
    protected TimeZone timezone = TimeZone.getTimeZone("GMT");
    Map<String, List<String>> vsysIfs = new LinkedHashMap<>();
    Map<String, Integer> vsysNameToVirtualIfNum = new LinkedHashMap<>();


//    void initialize(DeviceMetadata dm, Map<String, Object> opts) throws Exception {
//        this.device.setName("Huawei");
//        this.device.setOsPlatform("Huawei-VRP");
//        this.device.setIpv6Enabled(false);
//        this.deviceMeta = dm;
//        DevicePredefinedService mypds = dm.getPredefinedService();
//        if (mypds != null) {
//            this.preDefinedService = mypds.getServices();
//        }
//
//        if (opts != null) {
//            String mydm = (String) opts.get("OPT_DEVICE_MODEL");
//            if ("DEVICE_MODEL_USG_BSR".equals(mydm)) {
//                this.isDefaultPolicyPermitAll = true;
//                this.defaultPolicyPermitAllMsg = "Default permit all for USG2100/2200/5100-BSR/HSR device";
//            }
//        }
//
//        FilterRuleListPO myrulelist = new FilterRuleListPO();
//        myrulelist.setName("__Firewall_Packet_Filter_Default_Rules");
//        myrulelist.setOption(FilterRuleListPO.FilterRuleListOption.IP4_IP6_MIX);
//        myrulelist.setRuleListType(SystemRuleListTypeEnum.SYSTEM__POLICY_2.name());
//        this.defaultPolicyRules = myrulelist;
//    }

//
//    @Override
//    public T visitVersion(H3CParser.VersionContext ctx) {
//        StringBuffer buf=new StringBuffer();
//        ctx.word().forEach(item->{
//            buf.append(item.getText());
//        });
//        String ss=buf.toString();//
////        device.setOsVersion("madeup version");
//        device.setOsVersion(ss);
//        return null;
//    }

    @Override
    public T visitConfig(H3CParser.ConfigContext ctx) {
        System.out.println("visit cfg "+ctx.toString());
//        return null;
        return visitChildren(ctx);
    }

    @Override
    public T visitLine(H3CParser.LineContext ctx){
        System.out.println("visit line "+ctx.toString());
//        return null;
        return visitChildren(ctx);
    }

    @Override // ctx上下文  PO设备接口模型
    public T visitInterfaceRule(H3CParser.InterfaceRuleContext ctx) {
        DeviceInterfacePO deviceInterface = new DeviceInterfacePO();
        // getChildCount()
        String interfaceName = ctx.INTERFACE().getText();
        if (interfaceName.toLowerCase().startsWith("loopback")) { //loop？
            deviceInterface.setIsLoopback(true); //loopback本地
        }
        deviceInterface.setName(interfaceName);
        deviceInterface.setLineNumbers(VisitorHelper.getConfigLine(ctx));
        parseTreeProperty.put(ctx, deviceInterface); // 向后传播
        super.visitInterfaceRule(ctx);
        parseTreeProperty.removeFrom(ctx);
        interfaces.add(deviceInterface);
//        return null;
        return visitChildren(ctx);
    }

    // ------------------------------ vsys end ------------------------------------




}
