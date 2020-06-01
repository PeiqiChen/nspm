package com.nspm.antlr4.common.utils;

import com.nspm.antlr4.common.deviceInterface.DeviceInterfacePO;
import com.nspm.antlr4.common.deviceInterface.InterfaceGroupPO;
import com.nspm.antlr4.common.domain.filter.*;
import com.nspm.antlr4.common.domain.obj.*;
import com.nspm.antlr4.common.domain.routing.RoutingTablePO;
import com.nspm.antlr4.common.enums.SystemRuleListTypeEnum;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;

import java.util.*;

/**
 * @author luwei
 * @date 2019/9/11
 */
public class GenericDeviceValidator {

    public static void validateGenericDevice(GenericDevicePO mygd, List<ZeeError> result) {
        validateRoutingTables(mygd, result);
        validateFilterLists(mygd, result);
        validateDeviceObject(mygd, result);
    }

    private static void validateRoutingTables(GenericDevicePO mygd, List<ZeeError> result) {
        LinkedHashMap<String, RoutingTablePO> routingTables = mygd.getRoutingTables();

        if ((routingTables == null) || (routingTables.isEmpty())) {
            return;
        }

        Map<String, InterfaceGroupPO> ifmap = mygd.getZones();
        Map<String, Set<String>> ifRtMap = new HashMap();

        for (Map.Entry<String, RoutingTablePO> myentry : routingTables.entrySet()) {
            String key = (String) myentry.getKey();
            RoutingTablePO myrt = (RoutingTablePO) myentry.getValue();

            String rtName = myrt.getName();
            if (!key.equals(rtName)) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTING_TABLE_KEY_NOT_MATCH_NAME, null, "Routing table '" + rtName + "' doesn't match key '" + key + "' in routingTables map.");

                result.add(mye);
            }

            String ifgName = myrt.getInterfaceGroupName();
            if (ifgName != null) {
                InterfaceGroupPO ifg = null;

                if (ifmap != null) {
                    ifg = (InterfaceGroupPO) ifmap.get(ifgName);
                }

                if (ifg == null) {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Interface group '" + ifgName + "' used in routing table '" + rtName + "', but not found.");

                    result.add(mye);
                } else {
                    List<String> myifs = ifg.getInterfaceNames();
                    if (myifs != null) {
                        for (String myif : myifs) {
                            Set<String> myset = (Set) ifRtMap.get(myif);
                            if (myset == null) {
                                myset = new HashSet(3);
                                ifRtMap.put(myif, myset);
                            }

                            myset.add(rtName);
                        }
                    }
                }
            }
        }

        //TODO 都是?，改写的
        Iterator iterator = ifRtMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> myentry = (Map.Entry<String, Set<String>>) iterator.next();

            Set<String> myset = (Set) myentry.getValue();
            if (myset.size() > 1) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTING_TABLE_INTERFACE_NOT_UNIQUE, null, "Interface '" + (String) myentry.getKey() + "' shouldn't belong to more than one routing table: " + myset);
                result.add(mye);
            }
        }


        String defaultRt = null;
        for (Map.Entry<String, RoutingTablePO> myentry : routingTables.entrySet()) {
            RoutingTablePO myrt = (RoutingTablePO) myentry.getValue();

            if (myrt.isDefault()) {
                if (defaultRt == null) {
                    defaultRt = myrt.getName();
                } else {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_MORE_THAN_ONE_DEFAULT_ROUTING_TABLE, null, "More than one routing table are set to default : '" + defaultRt + "', '" + myrt.getName() + "'");
                    result.add(mye);
                    break;
                }
            }
        }
    }

    /*     */
    private static void validateFilterLists(GenericDevicePO mygd, List<ZeeError> result) {
        LinkedHashMap<String, FilterRuleListPO> filterRuleLists = mygd.getFilterLists();

        if ((filterRuleLists == null) || (filterRuleLists.isEmpty())) {
            return;
        }

        Set<String> ifNames = new HashSet();

        DevicePO myd = mygd.getDevice();
        List<DeviceInterfacePO> iflist = myd.getDeviceInterfaces();
        if (iflist != null) {
            for (DeviceInterfacePO myi : iflist) {
                ifNames.add(myi.getName());
                if (myi.getAlias() != null) {
                    ifNames.add(myi.getAlias());
                }
            }
        }

        for (Map.Entry<String, FilterRuleListPO> myentry : filterRuleLists.entrySet()) {
            String key = (String) myentry.getKey();
            FilterRuleListPO mylist = (FilterRuleListPO) myentry.getValue();

            String listName = mylist.getName();
            if (!key.equals(listName)) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_FILTER_LIST_KEY_NOT_MATCH_NAME, null, "Filter list '" + listName + "' doesn't match key '" + key + "' in filterLists map.");

                result.add(mye);
            }

            validateOneFilterList(mylist, ifNames, mygd, result);
        }
    }

    private static void validateOneFilterList(FilterRuleListPO filterList, Set<String> ifNames, GenericDevicePO mygd, List<ZeeError> result) {
        List<FilterRulePO> rules = filterList.getRuleList();
        if ((rules == null) || (rules.isEmpty())) {
            return;
        }

        String filterListName = filterList.getName();

        Map<String, InterfaceGroupPO> ifgs = mygd.getZones();

        String flin = filterList.getInInterfaceGroupRef();
        if ((flin != null) && (
                (ifgs == null) || (!ifgs.containsKey(flin)))) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Interface group '" + flin + "' not found, but referenced in filter list '" + filterListName + "' ");

            result.add(mye);
        }

        String flout = filterList.getOutInterfaceGroupRef();
        ZeeError mye;
        if ((flout != null) && (
                (ifgs == null) || (!ifgs.containsKey(flout)))) {
            mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Interface group '" + flout + "' not found, but referenced in filter list '" + filterListName + "' ");

            result.add(mye);
        }

        for (FilterRulePO myrule : rules) {
            String[] inifg = myrule.getInInterfaceGroupRefs();
            Integer str1;
            String myifg;
            if (inifg != null) {
                String[] arrayOfString1 = inifg;
                int i = arrayOfString1.length;
                for (str1 = 0; str1 < i; str1++) {
                    myifg = arrayOfString1[str1];
                    boolean isOk = false;
                    if (ifNames.contains(myifg)) {
                        isOk = true;
                    } else if ((ifgs != null) && (ifgs.containsKey(myifg))) {
                        isOk = true;
                    } else if (myifg.equalsIgnoreCase("local")) {
                        isOk = true;
                    } else {
                        isOk = false;
                    }

                    if (!isOk) {
                        mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Interface group '" + myifg + "' not found, but referenced in Filter Rule :" + myrule.getDisplayName() + " from filter list '" + filterListName + "' ");
                        result.add(mye);
                    }
                }
            }

            String[] outifg = myrule.getOutInterfaceGroupRefs();
            if (outifg != null) {
                String[] arrayOfString2 = outifg;
                str1 = arrayOfString2.length;
                for (int m = 0; m < str1; m++) {
                    myifg = arrayOfString2[m];
                    boolean isOk = false;
                    if (ifNames.contains(myifg)) {
                        isOk = true;
                    } else if ((ifgs != null) && (ifgs.containsKey(myifg))) {
                        isOk = true;
                    } else if (myifg.equalsIgnoreCase("local")) {
                        isOk = true;
                    } else {
                        isOk = false;
                    }

                    if (!isOk) {
                        mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Interface group '" + myifg + "' not found, but referenced in Filter Rule :" + myrule.getDisplayName() + " from filter list '" + filterListName + "' ");
                        result.add(mye);
                    }
                }
            }

            validateMatchClause(myrule.getMatchClause(), myrule, filterListName, mygd, result);

            validateMatchGroup(myrule.getMatchGroup(), myrule, filterListName, mygd, result);

            validateNatClause(myrule.getNatClause(), myrule, filterListName, filterList.getRuleListType(), ifNames, mygd, result);
        }
    }

    private static void validateMatchClause(MatchClausePO mc, FilterRulePO rule, String filterListName, GenericDevicePO mygd, List<ZeeError> result) {
        if (mc == null) {
            return;
        }

        String ruleListRef = mc.getFilterRuleListRef();
        if (ruleListRef != null) {
            LinkedHashMap<String, FilterRuleListPO> filterList = mygd.getFilterLists();
            if ((filterList == null) || (!filterList.containsKey(ruleListRef))) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Filter list '" + ruleListRef + "' not found, but referenced in the match clause of Filter Rule :" + rule.getDisplayName() + " from filter list '" + filterListName + "' ");
                result.add(mye);
            }
        }

        DevicePO myd = mygd.getDevice();
    }

    private static void validateMatchGroup(MatchGroupPO mg, FilterRulePO rule, String filterListName, GenericDevicePO mygd, List<ZeeError> result) {
        if (mg == null) {
            return;
        }

        Set<MatchGroupPO> matchGroupInProcess = Collections.newSetFromMap(new IdentityHashMap());

        validateMatchGroupNested(matchGroupInProcess, mg, rule, filterListName, mygd, result);
    }

    private static void validateMatchGroupNested(Set<MatchGroupPO> matchGroupInProcess, MatchGroupPO mg, FilterRulePO rule, String filterListName, GenericDevicePO mygd, List<ZeeError> result) {
        if (matchGroupInProcess.contains(mg)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_CIRCULAR_REFERENCE, null, "Circular reference in processing MatchClausePO:'" + mg.getName() + "' in filter rule '" + rule.getDisplayName() + " from filter list '" + filterListName + "' ");

            result.add(mye);
            return;
        }

        matchGroupInProcess.add(mg);
        try {
            List<MatchClausePO> mcs = mg.getMatchClauses();
            Iterator localIterator;
            if (mcs != null) {
                for (localIterator = mcs.iterator(); localIterator.hasNext(); ) {
                    MatchClausePO mymc = (MatchClausePO) localIterator.next();
                    validateMatchClause(mymc, rule, filterListName, mygd, result);
                }
            }

            List<MatchGroupPO> mgs = mg.getNestedMatchGroup();
            if (mgs != null) {
                for (MatchGroupPO mymg : mgs) {
                    validateMatchGroupNested(matchGroupInProcess, mymg, rule, filterListName, mygd, result);
                }
            }
        } finally {

            matchGroupInProcess.remove(mg);
        }
    }

    private static void validateNatClause(NATClausePO nc, FilterRulePO rule, String filterListName, String filterListType, Set<String> ifNames, GenericDevicePO mygd, List<ZeeError> result) {
        if (nc == null) {
            return;
        }

        String ruleListRef = nc.getFilterRuleListRef();
        if (ruleListRef != null) {
            LinkedHashMap<String, FilterRuleListPO> filterList = mygd.getFilterLists();
            if ((filterList == null) || (!filterList.containsKey(ruleListRef))) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Filter list '" + ruleListRef + "' not found, but referenced in the NAT clause of Filter Rule :" + rule.getDisplayName() + " from filter list '" + filterListName + "' ");
                result.add(mye);
            }
        }

        DevicePO myd = mygd.getDevice();

        String ifRef = nc.getOutInterfaceRef();
        if ((ifRef != null) && (!ifNames.contains(ifRef)) && (mygd.getZone(ifRef) == null)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Interface '" + ifRef + "' not found, but referenced in the NAT clause of Filter Rule :" + rule.getDisplayName() + " from filter list '" + filterListName + "' ");
            result.add(mye);
        }

        String inIfRef = nc.getPreDstInInterfaceRef();
        if ((inIfRef != null) && (!ifNames.contains(inIfRef))) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "Interface '" + inIfRef + "' not found, but referenced in the NAT clause of Filter Rule :" + rule.getDisplayName() + " from filter list '" + filterListName + "' ");
            result.add(mye);
        }

        if (filterListType.equals(SystemRuleListTypeEnum.SYSTEM__NAT_LIST)) {
            if (nc.getNatField() == NATClausePO.NATField.UNKNOWN_IDENTITY) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_DEFAULT, null, "NAT rule in this list should have NATField set. Otherwise the system wouldn't know if this is part of a preRouting NAT(dst NAT) or postRouting NAT(src NAT). Rule: " + rule.getLineNumbers());

                result.add(mye);
            }
        }
    }

    private static void validateServiceSpec(ServiceMatchSpecPO[] serviceSpecs, FilterRulePO rule, String filterListName, DevicePO myd, GenericDevicePO gd, List<ZeeError> result) {
        if ((serviceSpecs == null) || (serviceSpecs.length == 0)) {
            return;
        }

        for (ServiceMatchSpecPO myref : serviceSpecs) {
            String name = myref.getNameRef();
            ServiceMatchSpecPO.ServiceMatchSpecType type = myref.getType();

            if (type == ServiceMatchSpecPO.ServiceMatchSpecType.SERVICE_OBJECT_GROUP) {
                boolean found = false;
                Map<String, FilterServiceGroupPO> mymap = gd.getServiceGroups();
                if ((mymap != null) && (mymap.containsKey(name))) {
                    found = true;
                } else {
                    Map<String, FilterServiceObjectPO> mymap2 = gd.getServices();
                    if ((mymap2 != null) && (mymap2.containsKey(name))) {
                        found = true;
                    }
                }
                if (!found) {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "FilterServiceObjectPO or FilterServiceGroupPO '" + name + "' not found. But referenced by match clause in filter rule: " + rule.getDisplayName() + " from filter list '" + filterListName + "' ");

                    result.add(mye);
                }
            } else if (type == ServiceMatchSpecPO.ServiceMatchSpecType.SERVICE_OBJECT) {
                Map<String, FilterServiceObjectPO> mymap = gd.getServices();
                if ((mymap == null) || (!mymap.containsKey(name))) {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, "FilterServicePO '" + name + "' not found. But referenced by match clause in filter rule: " + rule.getDisplayName() + " from filter list '" + filterListName + "' ");

                    result.add(mye);
                }
            }
        }
    }

    private static void validateDeviceObject(GenericDevicePO mygd, List<ZeeError> result) {
        validateNameMap(mygd.getIpItems(), result);
        validateNameMap(mygd.getIpGroups(), result);
        validateNameMap(mygd.getZones(), result);
        validateNameMap(mygd.getServices(), result);
        validateNameMap(mygd.getServiceGroups(), result);

        validateIPGroup(mygd, result);
        validateInterfaceGroup(mygd, result);
    }

    private static void validateNameMap(Map<String, ?> mymap, List<ZeeError> result) {
        if (mymap == null) {
            return;
        }

        for (Map.Entry<String, ?> myentry : mymap.entrySet()) {
            String key = (String) myentry.getKey();
            if (key == null) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_DEFAULT, null, "Key shouldn't be null in map : " + mymap);
                result.add(mye);
            } else {
                Object value = myentry.getValue();

                String name = null;
                String type = null;
                if ((value instanceof IPItemPO)) {
                    IPItemPO myobj = (IPItemPO) value;
                    name = myobj.getName();
                    type = "IPItemPO";
                } else if ((value instanceof IPGroupPO)) {
                    IPGroupPO myobj = (IPGroupPO) value;
                    name = myobj.getName();
                    type = "IPGroupPO";
                } else if ((value instanceof InterfaceGroupPO)) {
                    InterfaceGroupPO myobj = (InterfaceGroupPO) value;
                    name = myobj.getName();
                    type = "InterfaceGroupPO";
                } else if ((value instanceof FilterServicePO)) {
                    FilterServicePO myobj = (FilterServicePO) value;
                    name = myobj.getName();
                    type = "FilterServicePO";
                } else if ((value instanceof FilterServiceGroupPO)) {
                    FilterServiceGroupPO myobj = (FilterServiceGroupPO) value;
                    name = myobj.getName();
                    type = "FilterServiceGroupPO";
                }

                if (!key.equals(name)) {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_MAP_KEY_NOT_MATCH_NAME, null, type + "map key '" + key + "' doesn't match object name '" + name + "'");

                    result.add(mye);
                }
            }
        }
    }

    private static void validateIPGroup(GenericDevicePO mygd, List<ZeeError> result) {
        Map<String, IPGroupPO> ipgs = mygd.getIpGroups();
        if ((ipgs == null) || (ipgs.isEmpty())) {
            return;
        }

        for (IPGroupPO ipg : ipgs.values()) {
            String path = "IPGroupPO:" + ipg.getName();

            List<String> itemNames = ipg.getIncludeItemNames();
            checkNameReference(mygd.getIpItems(), itemNames, "IPItemPO", path, result);
            itemNames = ipg.getExcludeItemNames();
            checkNameReference(mygd.getIpItems(), itemNames, "IPItemPO", path, result);
        }
    }

    private static void validateInterfaceGroup(GenericDevicePO mygd, List<ZeeError> result) {
        Map<String, InterfaceGroupPO> mygroups = mygd.getZones();
        if ((mygroups == null) || (mygroups.isEmpty())) {
            return;
        }

        DevicePO myd = mygd.getDevice();
        Map<String, DeviceInterfacePO> mymap = new HashMap();
        List<DeviceInterfacePO> ifList = myd.getDeviceInterfaces();
        if (ifList != null) {
            for (DeviceInterfacePO myif : ifList) {
                mymap.put(myif.getName(), myif);
            }
        }

        for (InterfaceGroupPO myg : mygroups.values()) {
            String path = "InterfaceGroupPO:" + myg.getName();
            List<String> names = myg.getInterfaceNames();
            checkNameReference(mymap, names, "DeviceInterfacePO", path, result);
        }
    }

    private static void validateFilterServiceGroup(GenericDevicePO myd, List<ZeeError> result) {
        Map<String, FilterServiceGroupPO> mygs = myd.getServiceGroups();
        if ((mygs == null) || (mygs.isEmpty())) {
            return;
        }

        for (FilterServiceGroupPO myg : mygs.values()) {
            String path = "FilterServiceGroupPO:" + myg.getName();

            List<String> itemNames = myg.getIncludeFilterServiceNames();
            checkNameReference(myd.getServices(), itemNames, "FilterServicePO", path, result);
            itemNames = myg.getIncludeFilterServiceGroupNames();
            checkNameReference(myd.getServiceGroups(), itemNames, "FilterServicePO", path, result);
        }
    }

    private static void checkNameReference(Map<String, ?> mymap, List<String> names, String type, String path, List<ZeeError> result) {
        if ((names == null) || (names.isEmpty())) {
            return;
        }

        for (String myname : names) {
            if ((mymap == null) || (!mymap.containsKey(myname))) {
                ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_OBJECT_NOT_FOUND, null, type + " '" + myname + "' used in " + path + ". But not found.");
                result.add(mye);
            }
        }
    }


}
