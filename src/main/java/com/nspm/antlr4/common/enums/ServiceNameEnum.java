package com.nspm.antlr4.common.enums;

import com.nspm.antlr4.exceptions.ZeeRuntimeException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author luwei
 * @date 2019/9/5
 */
public enum ServiceNameEnum {

    /**
     * 通用的TCP/UDP协议类型的服务
     */
    AOL(ProtocolNameEnum.TCP, 5190),
    BGP(ProtocolNameEnum.TCP, 179),
    BIFF(ProtocolNameEnum.UDP, 512),
    BOOTPC(ProtocolNameEnum.UDP, 68),
    BOOTPS(ProtocolNameEnum.UDP, 67),
    CHARGEN(ProtocolNameEnum.TCP, 19),
    CIFS(ProtocolNameEnum.TCP_UDP, 3020),
    ICA(ProtocolNameEnum.TCP, 1494, "citrix-ica"),
    SHELL(ProtocolNameEnum.TCP, 514, "cmd", "rsh"),
    CTIQBE(ProtocolNameEnum.TCP, 2748, "fjippol-polsvr"),
    DAYTIME(ProtocolNameEnum.TCP, 13),
    DISCARD(ProtocolNameEnum.TCP_UDP, 9),
    DN6_NLM_AUD(ProtocolNameEnum.UDP, 195),
    DNSIX(ProtocolNameEnum.UDP, 90),
    DOMAIN(ProtocolNameEnum.TCP_UDP, 53),
    DRIP(ProtocolNameEnum.TCP, 3949),
    ECHO(ProtocolNameEnum.TCP_UDP, 7),
    EXEC(ProtocolNameEnum.TCP, 512),
    FINGER(ProtocolNameEnum.TCP, 79),
    FTP(ProtocolNameEnum.TCP, 21),
    FTP_DATA(ProtocolNameEnum.TCP, 20),
    GOPHER(ProtocolNameEnum.TCP, 70),
    GTP(ProtocolNameEnum.UDP, 3386),
    GTPV1(ProtocolNameEnum.UDP, 2123),
    GTPU(ProtocolNameEnum.UDP, 2152),
    H323HOSTCALL(ProtocolNameEnum.TCP, 1720),
    H323(ProtocolNameEnum.UDP, 1719),
    HOSTNAME(ProtocolNameEnum.TCP, 101),
    HTTP(ProtocolNameEnum.TCP, 80, "www"),
    HTTPS(ProtocolNameEnum.TCP, 443),
    HWCC(ProtocolNameEnum.UDP, 10000),
    IDENT(ProtocolNameEnum.TCP, 113),
    ILS(ProtocolNameEnum.TCP, 1002),
    IMAP(ProtocolNameEnum.TCP, 143, "imap4"),
    IPSEC_NAT_T(ProtocolNameEnum.UDP, 4500, "non500-isakmp"),
    IRC(ProtocolNameEnum.TCP, 194),
    ISAKMP(ProtocolNameEnum.UDP, 500),
    KERBEROS_IV(ProtocolNameEnum.TCP_UDP, 750, "kerberos"),
    KLOGIN(ProtocolNameEnum.TCP, 543),
    KSHELL(ProtocolNameEnum.TCP, 544),
    L2TP(ProtocolNameEnum.UDP, 1701),
    LDAP(ProtocolNameEnum.TCP, 389),
    LDAPS(ProtocolNameEnum.TCP, 636),
    LOGIN(ProtocolNameEnum.TCP, 513),
    LOTUSNOTE(ProtocolNameEnum.TCP, 1352, "lotusnotes"),
    PRINTER(ProtocolNameEnum.TCP, 515, "lpd"),
    MGCP(ProtocolNameEnum.UDP, 2727),
    MMS(ProtocolNameEnum.UDP, 1755),
    MOBILEIP_AGENT(ProtocolNameEnum.UDP, 434, "mobile-ip"),
    MOBILEIP_MN(ProtocolNameEnum.UDP, 435),
    MSN(ProtocolNameEnum.TCP, 1863),
    MSN_AUDIO(ProtocolNameEnum.UDP, 7001),
    MSN_STUN(ProtocolNameEnum.UDP, 3478),
    NAMESERVER(ProtocolNameEnum.UDP, 42, "name"),
    NETBIOS_DGM(ProtocolNameEnum.UDP, 138),
    NETBIOS_NS(ProtocolNameEnum.UDP, 137),
    NETBIOS_SSN(ProtocolNameEnum.TCP, 139, "netbios-ss"),
    NFS(ProtocolNameEnum.TCP_UDP, 2049),
    NNTP(ProtocolNameEnum.TCP, 119),
    NTP(ProtocolNameEnum.UDP, 123),
    PCANYWHEREDATA(ProtocolNameEnum.TCP, 5631, "pcanywhere-data"),
    PCANYWHERESTAT(ProtocolNameEnum.UDP, 5632, "pcanywhere-status"),
    PIM_RP_DISC(ProtocolNameEnum.TCP_UDP, 496, "pim-auto-rp"),
    POP2(ProtocolNameEnum.TCP, 109),
    POP3(ProtocolNameEnum.TCP, 110),
    PPTP(ProtocolNameEnum.TCP, 1723),
    QQ(ProtocolNameEnum.UDP, 8000),
    RADIUS(ProtocolNameEnum.UDP, 1645),
    RADIUS_ACCT(ProtocolNameEnum.UDP, 1646),
    RAS(ProtocolNameEnum.UDP, 1719),
    RIP(ProtocolNameEnum.UDP, 520, "router"),
    RPC(ProtocolNameEnum.TCP, 135),
    RTSP(ProtocolNameEnum.TCP_UDP, 554),
    SECUREID_UDP(ProtocolNameEnum.UDP, 5510),
    SIP(ProtocolNameEnum.TCP_UDP, 5060),
    SMTP(ProtocolNameEnum.TCP, 25),
    SNMP(ProtocolNameEnum.UDP, 161),
    SNMPTRAP(ProtocolNameEnum.UDP, 162),
    SQLNET(ProtocolNameEnum.TCP, 1521),
    SSH(ProtocolNameEnum.TCP, 22),
    SUNRPC(ProtocolNameEnum.TCP_UDP, 111),
    SYSLOG(ProtocolNameEnum.UDP, 514),
    TACACS(ProtocolNameEnum.TCP_UDP, 49),
    TACACS_DS(ProtocolNameEnum.UDP, 65),
    TALK(ProtocolNameEnum.TCP_UDP, 517),
    TELNET(ProtocolNameEnum.TCP, 23),
    TFTP(ProtocolNameEnum.UDP, 69),
    TIME(ProtocolNameEnum.UDP, 37),
    UUCP(ProtocolNameEnum.TCP, 540),
    WHO(ProtocolNameEnum.UDP, 513),
    WHOIS(ProtocolNameEnum.TCP, 43, "nicname"),
    XDMCP(ProtocolNameEnum.UDP, 177);

    private ProtocolNameEnum protocol;
    private int port;
    private String[] alias;

    private static Map<String, ServiceNameEnum> nameMap;
    static {
        nameMap = new HashMap();
        Set<String> mykeys = new HashSet();
        for (ServiceNameEnum myname : values()) {
            addMapEntry(myname.name(), myname, mykeys);
            String[] myalias = myname.getAlias();
            if (myalias != null) {
                for (String myval : myalias) {
                    addMapEntry(myval, myname, mykeys);
                }
            }
        }
    }

    ServiceNameEnum(ProtocolNameEnum protocol, int port, String... alias) {
        this.protocol = protocol;
        this.port = port;
        this.alias = alias;
    }

    private static void addMapEntry(String key, ServiceNameEnum serviceName, Set<String> existingKeys) {
        key = key.toLowerCase();
        key = key.replace('_', '-');
        if (existingKeys.contains(key)) {
            throw new ZeeRuntimeException("Duplicate name/alias with ServiceName: " + key);
        }
        existingKeys.add(key);
        nameMap.put(key, serviceName);
    }

    public static ServiceNameEnum lookup(String txt) {
        txt = txt.toLowerCase();
        txt = txt.replace('_', '-');
        return nameMap.get(txt);
    }

    public static Map<String, ServiceNameEnum> getNameMap() {
        return nameMap;
    }

    public static boolean isValidNameOrNum(String txt) {
        if (lookup(txt) != null) {
            return true;
        }
        Integer myint = null;
        try {
            myint = Integer.valueOf(Integer.parseInt(txt));
        } catch (NumberFormatException localNumberFormatException) {
        }
        return myint != null;
    }

    public ProtocolNameEnum getProtocol() {
        return this.protocol;
    }

    public int getPort() {
        return this.port;
    }

    public String[] getAlias() {
        return this.alias;
    }

}
