package com.nspm.antlr4.common.interfaces;

import com.nspm.antlr4.common.domain.ip.IPAddressPO;
import com.nspm.antlr4.common.enums.IPTypeEnum;

/**
 * @author luwei
 * @date 2019/9/5
 */
public interface IPAddressRange extends Range{

    IPAddressPO getIPStart();

    IPAddressPO getIPEnd();

    IPTypeEnum getIPType();

    boolean contains(IPAddressPO paramIPAddress);
}
