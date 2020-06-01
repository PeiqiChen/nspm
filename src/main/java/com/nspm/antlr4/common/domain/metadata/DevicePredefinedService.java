package com.nspm.antlr4.common.domain.metadata;

import com.nspm.antlr4.common.domain.obj.FilterServiceGroupPO;

import java.util.Map;

/**
 * @author zc
 * @date 2019/09/17
 */
public class DevicePredefinedService {
    private Map<String, FilterServiceGroupPO> icmp;
    private Map<String, FilterServiceGroupPO> icmp6;
    private Map<String, FilterServiceGroupPO> services;

    public DevicePredefinedService() {
    }

    public DevicePredefinedService(Map<String, FilterServiceGroupPO> icmp, Map<String, FilterServiceGroupPO> icmp6, Map<String, FilterServiceGroupPO> services) {
        this.icmp = icmp;
        this.icmp6 = icmp6;
        this.services = services;
    }

    public Map<String, FilterServiceGroupPO> getIcmp() {
        return this.icmp;
    }

    public void setIcmp(Map<String, FilterServiceGroupPO> icmp) {
        this.icmp = icmp;
    }

    public Map<String, FilterServiceGroupPO> getIcmp6() {
        return this.icmp6;
    }

    public void setIcmp6(Map<String, FilterServiceGroupPO> icmp6) {
        this.icmp6 = icmp6;
    }

    public Map<String, FilterServiceGroupPO> getServices() {
        return this.services;
    }

    public void setServices(Map<String, FilterServiceGroupPO> services) {
        this.services = services;
    }
}
