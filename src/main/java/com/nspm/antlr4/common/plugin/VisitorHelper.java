package com.nspm.antlr4.common.plugin;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.deviceInterface.DeviceInterfacePO;
import com.nspm.antlr4.common.deviceInterface.IPAddressInfoPO;
import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.domain.metadata.DevicePredefinedService;
import com.nspm.antlr4.common.domain.obj.FilterServiceGroupPO;
import com.nspm.antlr4.common.domain.obj.FilterServicePO;
import com.nspm.antlr4.common.domain.obj.IntegerRangePO;
import com.nspm.antlr4.common.domain.obj.PortRangePO;
import com.nspm.antlr4.common.enums.*;
import com.nspm.antlr4.exceptions.ZeePluginRuntimeException;
import com.nspm.antlr4.common.utils.TwoMemberObject;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author zc
 * @date 2019/09/18
 */
public class VisitorHelper {

    public static IntegerRangePO getConfigLineAsRange(ParserRuleContext ctx) {
        if (ctx == null) {
            return null;
        } else {
            int startLine = ctx.start.getLine();
            int endLine = ctx.stop.getLine();
            return new IntegerRangePO(startLine, endLine);
        }
    }

    public static ConfigLinePO getConfigLine(ParserRuleContext ctx) {
        return getConfigLine(ctx, null);
    }

    public static ConfigLinePO getConfigLine(ParserRuleContext ctx, String configName) {
        if (ctx == null) {
            return null;
        } else {
            int startLine = ctx.start.getLine();
            int endLine = ctx.stop.getLine();
            ConfigLinePO result = new ConfigLinePO(configName, startLine, endLine);
            return result;
        }
    }

    public static String getText(List<? extends ParserRuleContext> ctxList) {
        if (ctxList != null && !ctxList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator var2 = ctxList.iterator();

            while(var2.hasNext()) {
                ParserRuleContext myc = (ParserRuleContext)var2.next();
                sb.append(" ");
                appendNodeText(myc, sb);
            }

            return sb.substring(1);
        } else {
            return null;
        }
    }

    public static String getText(ParserRuleContext ctx) {
        if (ctx == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            appendNodeText(ctx, sb);
            String result = sb.toString();
            if (result.startsWith("'") && result.endsWith("'")) {
                result = result.substring(1, result.length() - 1);
            } else if (result.startsWith("\"") && result.endsWith("\"")) {
                result = result.substring(1, result.length() - 1);
            }

            return result;
        }
    }

    private static void appendNodeText(ParseTree node, StringBuilder sb) {
        if (node != null) {
            if (node.getChildCount() == 0) {
                sb.append(node.getText());
            } else {
                for(int i = 0; i < node.getChildCount(); ++i) {
                    if (i != 0) {
                        sb.append(" ");
                    }

                    ParseTree mychild = node.getChild(i);
                    appendNodeText(mychild, sb);
                }
            }

        }
    }

    public static String getText(Token value) {
        if (value == null) {
            return null;
        } else {
            String result = value.getText();
            if (result.startsWith("'") && result.endsWith("'")) {
                result = result.substring(1, result.length() - 1);
            } else if (result.startsWith("\"") && result.endsWith("\"")) {
                result = result.substring(1, result.length() - 1);
            }

            return result;
        }
    }

    public static String getTextForTokenList(List<Token> tokenList) {
        if (tokenList != null && !tokenList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator var2 = tokenList.iterator();

            while(var2.hasNext()) {
                Token myc = (Token)var2.next();
                sb.append(" ");
                sb.append(getText(myc));
            }

            return sb.substring(1);
        } else {
            return null;
        }
    }

    public static <T extends ParserRuleContext> List<String> getTextList(List<T> values) {
        if (values != null && !values.isEmpty()) {
            List<String> result = new ArrayList(values.size());
            Iterator var2 = values.iterator();

            while(var2.hasNext()) {
                ParserRuleContext val = (ParserRuleContext)var2.next();
                result.add(getText(val));
            }

            return result;
        } else {
            return null;
        }
    }

    public static List<String> getTextListToken(List<Token> values) {
        if (values != null && !values.isEmpty()) {
            List<String> result = new ArrayList(values.size());
            Iterator var2 = values.iterator();

            while(var2.hasNext()) {
                Token val = (Token)var2.next();
                result.add(getText(val));
            }

            return result;
        } else {
            return null;
        }
    }

    public static IP4AddressPO getIP4(Token value) {
        return value == null ? null : IP4AddressPO.create(value.getText());
    }

    public static TwoMemberObject<IP4AddressPO, Integer> getIP4WithLength(Token token, ParserRuleContext ctx) {
        if (token == null) {
            return null;
        } else {
            String ip4AndLength = getText(token);
            int myi = ip4AndLength.indexOf("/");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Unknown format for IP4 and length:" + ip4AndLength), (Throwable)null);
            } else {
                String ip4str = ip4AndLength.substring(0, myi);
                String lengthStr = ip4AndLength.substring(myi + 1);
                IP4AddressPO myadd = IP4AddressPO.create(ip4str);
                boolean var7 = true;

                int length;
                try {
                    length = Integer.parseInt(lengthStr);
                } catch (NumberFormatException var9) {
                    throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Unknown format for IP4 and length. Length is not an integer" + ip4AndLength), (Throwable)null);
                }
                return new TwoMemberObject<>(myadd, length);
            }
        }
    }

    public static TwoMemberObject<IP4AddressPO, IP4AddressPO> getIP4WithMask(Token token, ParserRuleContext ctx) {
        if (token == null) {
            return null;
        } else {
            String ip4AndMask = getText(token);
            int myi = ip4AndMask.indexOf("/");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Unknown format for IP4 and Netmask:" + ip4AndMask), (Throwable)null);
            } else {
                String ip4str = ip4AndMask.substring(0, myi);
                String maskStr = ip4AndMask.substring(myi + 1);
                IP4AddressPO myadd = IP4AddressPO.create(ip4str);
                IP4AddressPO mymask = IP4AddressPO.create(maskStr);
                return new TwoMemberObject(myadd, mymask);
            }
        }
    }

    public static TwoMemberObject<IP4AddressPO, IP4AddressPO> getIP4WithColon(Token token, ParserRuleContext ctx) {
        if (token == null) {
            return null;
        } else {
            String ip4Range = getText(token);
            int myi = ip4Range.indexOf(":");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Expecting format 'IP4:IP4', but found:" + ip4Range), (Throwable)null);
            } else {
                String ip4str = ip4Range.substring(0, myi);
                String endStr = ip4Range.substring(myi + 1);
                IP4AddressPO myadd = IP4AddressPO.create(ip4str);
                IP4AddressPO myend = IP4AddressPO.create(endStr);
                return new TwoMemberObject(myadd, myend);
            }
        }
    }

    public static TwoMemberObject<IP4AddressPO, IP4AddressPO> getIP4WithDash(String text, ParserRuleContext ctx) {
        if (text == null) {
            return null;
        } else {
            int myi = text.indexOf("-");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Expecting format 'IP4-IP4', but found:" + text), (Throwable)null);
            } else {
                String ip4str = text.substring(0, myi);
                String endStr = text.substring(myi + 1);
                IP4AddressPO myadd = IP4AddressPO.create(ip4str);
                IP4AddressPO myend = IP4AddressPO.create(endStr);
                return new TwoMemberObject(myadd, myend);
            }
        }
    }

    public static List<IP4AddressPO> getIP4WithComma(String ip4list) {
        if (ip4list == null) {
            return Collections.emptyList();
        } else {
            String[] mylist = ip4list.split(",");
            List<IP4AddressPO> result = new ArrayList();
            String[] var3 = mylist;
            int var4 = mylist.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String mystr = var3[var5];
                IP4AddressPO myadd = IP4AddressPO.create(mystr);
                result.add(myadd);
            }

            return result;
        }
    }

    public static TwoMemberObject<Integer, Integer> getIntWithColon(Token token, ParserRuleContext ctx) {
        if (token == null) {
            return null;
        } else {
            String intRange = getText(token);
            int myi = intRange.indexOf(":");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Expecting format 'INT:INT', but found:" + intRange), (Throwable)null);
            } else {
                String beginIntStr = intRange.substring(0, myi);
                String endIntStr = intRange.substring(myi + 1);
                Integer mystart = Integer.parseInt(beginIntStr);
                Integer myend = Integer.parseInt(endIntStr);
                return new TwoMemberObject(mystart, myend);
            }
        }
    }

    public static TwoMemberObject<Integer, Integer> getIntWithDash(String intRange, ParserRuleContext ctx) {
        if (intRange == null) {
            return null;
        } else {
            int myi = intRange.indexOf("-");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Expecting format 'INT-INT', but found:" + intRange), (Throwable)null);
            } else {
                String beginIntStr = intRange.substring(0, myi);
                String endIntStr = intRange.substring(myi + 1);
                Integer mystart = Integer.parseInt(beginIntStr);
                Integer myend = Integer.parseInt(endIntStr);
                return new TwoMemberObject(mystart, myend);
            }
        }
    }

    public static List<Integer> getIntWithComma(String intStr) {
        if (intStr == null) {
            return null;
        } else {
            String[] mylist = intStr.split(",");
            List<Integer> result = new ArrayList();
            String[] var3 = mylist;
            int var4 = mylist.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String myitem = var3[var5];
                int myval = Integer.parseInt(myitem);
                result.add(myval);
            }

            return result;
        }
    }

    public static List<IP4AddressPO> getIP4List(List<Token> values) {
        if (values != null && !values.isEmpty()) {
            List<IP4AddressPO> result = new ArrayList(values.size());
            Iterator var2 = values.iterator();

            while(var2.hasNext()) {
                Token value = (Token)var2.next();
                result.add(IP4AddressPO.create(value.getText()));
            }

            return result;
        } else {
            return null;
        }
    }

    public static IP6AddressPO getIP6(Token value) {
        return value == null ? null : IP6AddressPO.create(value.getText());
    }

    public static IP6AddressPO getIP6(ParserRuleContext value) {
        if (value == null) {
            return null;
        } else {
            String mystr = getText(value);
            return IP6AddressPO.create(mystr);
        }
    }

    public static TwoMemberObject<IP6AddressPO, Integer> getIP6WithLength(Token token, ParserRuleContext ctx) {
        if (token == null) {
            return null;
        } else {
            String ip6AndLength = getText(token);
            int myi = ip6AndLength.indexOf("/");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Unknown format for IP6 and length:" + ip6AndLength), (Throwable)null);
            } else {
                String ip6str = ip6AndLength.substring(0, myi);
                String lengthStr = ip6AndLength.substring(myi + 1);
                IP6AddressPO myadd = IP6AddressPO.create(ip6str);
                boolean var7 = true;

                int length;
                try {
                    length = Integer.parseInt(lengthStr);
                } catch (NumberFormatException var9) {
                    throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Unknown format for IP6 and length. Length is not an integer" + ip6AndLength), (Throwable)null);
                }

                return new TwoMemberObject(myadd, length);
            }
        }
    }

    public static TwoMemberObject<IP6AddressPO, IP6AddressPO> getIP6WithDash(String text, ParserRuleContext ctx) {
        if (text == null) {
            return null;
        } else {
            int myi = text.indexOf("-");
            if (myi == -1) {
                throw new ZeePluginRuntimeException(new ZeeParserError(ctx, "Unknown format for IP6 range:" + text), (Throwable)null);
            } else {
                String ip6start = text.substring(0, myi);
                String ip6end = text.substring(myi + 1);
                IP6AddressPO my1 = IP6AddressPO.create(ip6start);
                IP6AddressPO my2 = IP6AddressPO.create(ip6end);
                return new TwoMemberObject(my1, my2);
            }
        }
    }

    public static Integer getInt(Token value) {
        return value == null ? null : Integer.parseInt(value.getText());
    }

    public static List<Integer> getIntList(List<Token> values) {
        if (values != null && !values.isEmpty()) {
            List<Integer> result = new ArrayList(values.size());
            Iterator var2 = values.iterator();

            while(var2.hasNext()) {
                Token value = (Token)var2.next();
                result.add(getInt(value));
            }

            return result;
        } else {
            return null;
        }
    }

    public static ProtocolNameEnum getProtocolName(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            ProtocolNameEnum result = ProtocolNameEnum.lookup(getText(value));
            if (result == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown protocol name:" + getText(value)), (Throwable)null);
            } else {
                return result;
            }
        }
    }

    public static ProtocolNameEnum getProtocolName(Token value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            ProtocolNameEnum result = ProtocolNameEnum.lookup(getText(value));
            if (result == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown protocol name:" + getText(value)), (Throwable)null);
            } else {
                return result;
            }
        }
    }

    public static TwoMemberObject<ProtocolNameEnum, Integer> getProtocolNameOrNumber(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return new TwoMemberObject(null, null);
        } else {
            String txt = getText(value);
            Integer myint = null;

            try {
                myint = Integer.parseInt(txt);
            } catch (NumberFormatException var5) {
            }

            if (myint != null) {
                return new TwoMemberObject(null, myint);
            } else {
                ProtocolNameEnum myn = ProtocolNameEnum.lookup(txt);
                if (myn == null) {
                    throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown protocol name:" + txt), (Throwable)null);
                } else {
                    return new TwoMemberObject(myn,null);
                }
            }
        }
    }

    public static ServiceNameEnum getServiceName(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            ServiceNameEnum myname = ServiceNameEnum.lookup(getText(value));
            if (myname == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown service name:" + getText(value)), (Throwable)null);
            } else {
                return myname;
            }
        }
    }

    public static TwoMemberObject<ServiceNameEnum, Integer> getServiceNameOrNumber(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return new TwoMemberObject((Object)null, (Object)null);
        } else {
            String txt = getText(value);
            Integer myint = null;

            try {
                myint = Integer.parseInt(txt);
            } catch (NumberFormatException var5) {
            }

            if (myint != null) {
                return new TwoMemberObject((Object)null, myint);
            } else {
                ServiceNameEnum myn = ServiceNameEnum.lookup(txt);
                if (myn != null) {
                    return new TwoMemberObject(myn, (Object)null);
                } else {
                    throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown portName/number:" + txt), (Throwable)null);
                }
            }
        }
    }

    public static ICMPTypeEnum getICMPType(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            ICMPTypeEnum myt = ICMPTypeEnum.lookup(getText(value));
            if (myt == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown ICMP type:" + getText(value)), (Throwable)null);
            } else {
                return myt;
            }
        }
    }

    public static ICMPTypeCodeEnum getICMPTypeCode(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            ICMPTypeCodeEnum myt = ICMPTypeCodeEnum.lookup(getText(value));
            if (myt == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown ICMP type:" + getText(value)), (Throwable)null);
            } else {
                return myt;
            }
        }
    }

    public static TwoMemberObject<ICMPTypeEnum, Integer> getICMPTypeOrNumber(Token value, ParserRuleContext parent) {
        return value == null ? new TwoMemberObject(null, null) : getICMPTypeOrNumber(getText(value), parent);
    }

    public static TwoMemberObject<ICMPTypeEnum, Integer> getICMPTypeOrNumber(ParserRuleContext value, ParserRuleContext parent) {
        return value == null ? new TwoMemberObject(null, null) : getICMPTypeOrNumber(getText(value), parent);
    }

    public static TwoMemberObject<ICMPTypeEnum, Integer> getICMPTypeOrNumber(String txt, ParserRuleContext parent) {
        Integer myint = null;

        try {
            myint = Integer.parseInt(txt);
        } catch (NumberFormatException var4) {
        }

        if (myint != null) {
            return new TwoMemberObject(null, myint);
        } else {
            ICMPTypeEnum myt = ICMPTypeEnum.lookup(txt);
            if (myt == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown ICMP type:" + txt), (Throwable)null);
            } else {
                return new TwoMemberObject(myt, null);
            }
        }
    }

    public static ICMP6TypeEnum getICMP6Type(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            ICMP6TypeEnum myt = ICMP6TypeEnum.lookup(getText(value));
            if (myt == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown ICMP6 type:" + getText(value)), (Throwable)null);
            } else {
                return myt;
            }
        }
    }

    public static ICMP6TypeCodeEmun getICMP6TypeCode(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            ICMP6TypeCodeEmun myt = ICMP6TypeCodeEmun.lookup(getText(value));
            if (myt == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown ICMPv6 type:" + getText(value)), (Throwable)null);
            } else {
                return myt;
            }
        }
    }

    public static TwoMemberObject<ICMP6TypeEnum, Integer> getICMP6TypeOrNumber(Token value, ParserRuleContext parent) {
        return value == null ? new TwoMemberObject(null, null) : getICMP6TypeOrNumber(getText(value), parent);
    }

    public static TwoMemberObject<ICMP6TypeEnum, Integer> getICMP6TypeOrNumber(ParserRuleContext value, ParserRuleContext parent) {
        return value == null ? new TwoMemberObject(null, null) : getICMP6TypeOrNumber(getText(value), parent);
    }

    public static TwoMemberObject<ICMP6TypeEnum, Integer> getICMP6TypeOrNumber(String txt, ParserRuleContext parent) {
        Integer myint = null;

        try {
            myint = Integer.parseInt(txt);
        } catch (NumberFormatException var4) {
        }

        if (myint != null) {
            return new TwoMemberObject(null, myint);
        } else {
            ICMP6TypeEnum myt = ICMP6TypeEnum.lookup(txt);
            if (myt == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown ICMP6 type:" + txt), (Throwable)null);
            } else {
                return new TwoMemberObject(myt, null);
            }
        }
    }

    public static PortOperatorEnum getPortOperator(ParserRuleContext value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            PortOperatorEnum myp = PortOperatorEnum.lookup(getText(value));
            if (myp == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown port operator:" + getText(value)), (Throwable)null);
            } else {
                return myp;
            }
        }
    }

    public static PortOperatorEnum getPortOperator(Token value, ParserRuleContext parent) {
        if (value == null) {
            return null;
        } else {
            PortOperatorEnum myp = PortOperatorEnum.lookup(getText(value));
            if (myp == null) {
                throw new ZeePluginRuntimeException(new ZeeParserError(parent, "Unknown port operator:" + getText(value)), (Throwable)null);
            } else {
                return myp;
            }
        }
    }

    public static IP4AddressPO reverseBit(IP4AddressPO wildcard) {
        Long iosWildcard = wildcard.getAddress();
        Long regularWildcard = iosWildcard ^ 0xFFFFFFFFL;
        IP4AddressPO myresult = IP4AddressPO.create(regularWildcard);
        return myresult;
    }

    public static DevicePredefinedService loadServiceCSV(Class<?> clz, String name) throws Exception {
        DevicePredefinedService result = null;
        InputStream inStream = null;
        BufferedReader reader = null;

        try {
            inStream = clz.getResourceAsStream(name);
            reader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8));
            result = parseServiceCSV(reader);
        } finally {
            if (inStream != null) {
                inStream.close();
            }

            if (reader != null) {
                reader.close();
            }

        }

        return result;
    }

    private static DevicePredefinedService parseServiceCSV(BufferedReader reader) throws Exception {
        Map<String, FilterServiceGroupPO> serviceResult = new HashMap<>();
        Map<String, FilterServiceGroupPO> icmpResult = new HashMap<>();
        Map<String, FilterServiceGroupPO> icmp6Result = new HashMap<>();
        List<String[]> lines = parseCSV(reader);
        boolean isIcmp = false;
        boolean isIcmp6 = false;
        Iterator iterator = lines.iterator();
        while(true) {
            String name;
            String protocol;
            String srcPort;
            String dstPort;
            String port;
            do {
                do {
                    if (!iterator.hasNext()) {
                        if (icmpResult.isEmpty()) {
                            icmpResult = null;
                        }
                        if (icmp6Result.isEmpty()) {
                            icmp6Result = null;
                        }
                        if (serviceResult.isEmpty()) {
                            serviceResult = null;
                        }
                        return new DevicePredefinedService(icmpResult, icmp6Result, serviceResult);
                    }

                    String[] myline = (String[])iterator.next();
                    int len = myline.length;
                    name = myline[0];
                    protocol = len > 1 ? myline[1] : null;
                    srcPort = len > 2 ? myline[2] : null;
                    dstPort = len > 3 ? myline[3] : null;
                    port = len > 4 ? myline[4] : null;
                } while(name == null);
            } while(name.trim().isEmpty());

            name = name.trim();
            if (!name.equalsIgnoreCase("[ICMP]")) {
                if (!name.equalsIgnoreCase("[ICMP-END]")) {
                    if (!name.equalsIgnoreCase("[ICMP6]")) {
                        if (!name.equalsIgnoreCase("[ICMP6-END]")) {
                            Map<String, FilterServiceGroupPO> myresult = null;
                            if (isIcmp) {
                                myresult = icmpResult;
                            } else if (isIcmp6) {
                                myresult = icmp6Result;
                            } else {
                                myresult = serviceResult;
                            }

                            FilterServiceGroupPO myServiceGroup = myresult.get(name);
                            if (myServiceGroup == null) {
                                myServiceGroup = new FilterServiceGroupPO();
                                myServiceGroup.setName(name);
                                myresult.put(name, myServiceGroup);
                            }

                            FilterServicePO myservice = null;
                            if (protocol == null) {
                                throw new IllegalArgumentException("Protocol value couldn't be null or empty.");
                            }

                            Integer mynum = null;

                            try {
                                mynum = Integer.parseInt(protocol);
                            } catch (Exception var20) {
                            }

                            if (mynum != null) {
                                if (mynum == 6) {
                                    myservice = FilterServicePO.createTcpUdpService(ProtocolNameEnum.TCP);
                                } else if (mynum == 17) {
                                    myservice = FilterServicePO.createTcpUdpService(ProtocolNameEnum.UDP);
                                } else if (mynum == 1) {
                                    myservice = FilterServicePO.createIcmpService((String)null);
                                } else if (mynum == 58) {
                                    myservice = FilterServicePO.createIcmp6Service((String)null);
                                } else if (mynum == 2) {
                                    myservice = FilterServicePO.createIgmpService((Integer)null);
                                } else {
                                    myservice = FilterServicePO.createOtherService(null, mynum);
                                }
                            } else {
                                ProtocolNameEnum myproto = ProtocolNameEnum.lookup(protocol);
                                if (myproto == null) {
                                    throw new IllegalArgumentException("Unknown protocol name:" + protocol);
                                }

                                switch(myproto) {
                                    case TCP:
                                    case UDP:
                                    case TCP_UDP:
                                        myservice = FilterServicePO.createTcpUdpService(myproto);
                                        break;
                                    case ICMP:
                                        myservice = FilterServicePO.createIcmpService((String)null);
                                        break;
                                    case ICMP6:
                                        myservice = FilterServicePO.createIcmp6Service((String)null);
                                        break;
                                    case IGMP:
                                        myservice = FilterServicePO.createIgmpService((Integer)null);
                                        break;
                                    default:
                                        myservice = FilterServicePO.createOtherService(myproto, (Integer)null);
                                }
                            }

                            ProtocolNameEnum protoName = myservice.getProtocolName();
                            PortRangePO myr = getPortValue(srcPort);
                            if (myr != null) {
                                if (protoName != ProtocolNameEnum.ICMP && protoName != ProtocolNameEnum.ICMP6) {
                                    if (protoName == ProtocolNameEnum.IGMP) {
                                        myservice.setIgmpTypeNum(myr.getStart());
                                    } else if (myr.getStart() != myr.getEnd()) {
                                        myservice.setSrcPortValues(PortOperatorEnum.RANGE, new String[]{myr.getStart() + "", myr.getEnd() + ""});
                                    } else {
                                        myservice.setSrcPortValues(PortOperatorEnum.EQ, new String[]{myr.getStart() + ""});
                                    }
                                } else {
                                    myservice.setIcmpTypeNum(myr.getStart());
                                }
                            }

                            myr = getPortValue(dstPort);
                            if (myr != null) {
                                if (protoName != ProtocolNameEnum.ICMP && protoName != ProtocolNameEnum.ICMP6) {
                                    if (myr.getStart() != myr.getEnd()) {
                                        myservice.setDstPortValues(PortOperatorEnum.RANGE, new String[]{myr.getStart() + "", myr.getEnd() + ""});
                                    } else {
                                        myservice.setDstPortValues(PortOperatorEnum.EQ, new String[]{myr.getStart() + ""});
                                    }
                                } else {
                                    myservice.setIcmpCode(myr.getStart());
                                }
                            }

                            myr = getPortValue(port);
                            if (myr != null) {
                                if (protoName != ProtocolNameEnum.ICMP && protoName != ProtocolNameEnum.ICMP6) {
                                    if (protoName == ProtocolNameEnum.IGMP) {
                                        myservice.setIgmpTypeNum(myr.getStart());
                                    } else if (myr.getStart() != myr.getEnd()) {
                                        myservice.setPortValues(PortOperatorEnum.RANGE, new String[]{myr.getStart() + "", myr.getEnd() + ""});
                                    } else {
                                        myservice.setPortValues(PortOperatorEnum.EQ, new String[]{myr.getStart() + ""});
                                    }
                                } else {
                                    myservice.setIcmpTypeNum(myr.getStart());
                                }
                            }

                            myServiceGroup.addIncludeFilterService(myservice);
                        } else {
                            isIcmp6 = false;
                        }
                    } else {
                        isIcmp6 = true;
                    }
                } else {
                    isIcmp = false;
                }
            } else {
                isIcmp = true;
            }
        }
    }

    private static PortRangePO getPortValue(String portStr) {
        if (portStr != null && !portStr.trim().isEmpty()) {
            portStr = portStr.trim();
            if (portStr.equalsIgnoreCase("any")) {
                return null;
            } else {
                PortRangePO result = null;
                int myindex = portStr.indexOf(45);
                if (myindex == -1) {
                    int myv = Integer.parseInt(portStr);
                    result = PortRangePO.create(myv);
                } else {
                    String beginStr = portStr.substring(0, myindex);
                    String endStr = portStr.substring(myindex + 1);
                    int begin = Integer.parseInt(beginStr);
                    int end = Integer.parseInt(endStr);
                    result = PortRangePO.create(begin, end);
                }

                return result;
            }
        } else {
            return null;
        }
    }

    private static List<String[]> parseCSV(BufferedReader reader) throws Exception {
        List<String[]> result = new ArrayList<>();
        String line = null;
        String firstLine = null;

        while((line = reader.readLine()) != null) {
            if (firstLine == null) {
                firstLine = line;
            } else if (!line.startsWith("#") && !line.trim().isEmpty()) {
                String[] myobj = line.split(",", -1);
                result.add(myobj);
            }
        }

        return result;
    }

    public static Set<IP4AddressPO> getIp4OnInterface(DeviceInterfacePO devIf) {
        LinkedHashSet<IP4AddressPO> result = new LinkedHashSet();
        List<IPAddressInfoPO> myips = devIf.getIpAddresses();
        if (myips == null) {
            return result;
        } else {
            Iterator var3 = myips.iterator();

            while(var3.hasNext()) {
                IPAddressInfoPO myip = (IPAddressInfoPO)var3.next();
                IP4AddressPO my4 = myip.getIp4Address();
                if (my4 != null) {
                    result.add(my4);
                }
            }

            return result;
        }
    }

    public static Void visitChildren(RuleNode node, AbstractParseTreeVisitor<Void> visitor) {
        Void result = null;
        int n = node.getChildCount();

        for(int i = 0; i < n; ++i) {
            ParseTree c = node.getChild(i);

            Void childResult;
            try {
                childResult = (Void)c.accept(visitor);
            } catch (Exception var8) {
                Exception e = var8;
                if (!(var8 instanceof ZeePluginRuntimeException)) {
                    if (c instanceof ParserRuleContext) {
                        e = new ZeePluginRuntimeException(new ZeeParserError((ParserRuleContext)c, var8.getMessage()), var8);
                    } else {
                        e = new ZeePluginRuntimeException(new ZeeParserError((ParserRuleContext)node.getRuleContext(), var8.getMessage()), var8);
                    }
                }

                throw (ZeePluginRuntimeException)e;
            }

            result = childResult;
        }

        return result;
    }
}
