grammar H3C;


config
    : line *
      NEWLINE*
    ;

line
    : commentLine
    | blankLine
    | commandLine
    ;

commentLine
    : comment eol
    ;

blankLine
    : ws? eol
    ;

commandLine
    : knownCommand
    | unknownCommand
    ;

knownCommand
    : version
    | hostname
    | contextRule
    | interfaceRule
    | routeAggregationRule
    | ethernetRule
    | securityZoneRule
    | zonePairRule
    | lineRule
    | routeRule
    | snmpAgentRule
    | redundancyRule
    | aclRule
    | roleRule
    | userClassRule
    | sessionRule
    | objectGroupRule
    | RETRUN
    ;

unknownCommand
    : ws? word (ws description)? eol (ws description eol)*
    ;

version
    : ws 'version' ws word ws word ws word eol
    ;

hostname
    : ws 'sysname' ws word eol
    ;

// context
contextRule
    : 'context' ws INT ws 'id' ws INT eol (ws contextSubtree eol)*
    ;

contextSubtree
    : 'description' ws description (UNICODE* description UNICODE*)* #contextDescription
    | 'context start' #contextStart
    | 'location' ws 'blade-controller-team' ws INT #contextLocation
    | 'allocate' ws 'interface' ws INTERFACE ws 'share' #contextInterfaceSingle
    | 'allocate' ws 'interface' ws INTERFACE ws 'to' ws INTERFACE ws 'share' #contextInterfaceMultiple
    | 'capability' ws 'throughput' ws 'kbps' ws INT #contextThroughput
    | 'capability' ws 'session' ws 'maximum' ws INT #contextSession
    | description? #contextIgnore
    ;

// interface
interfaceRule
    : 'interface' ws INTERFACE eol (ws interfaceSubtree eol)*
    | interfaceBridgeAggregation eol
    ;

interfaceSubtree
    : 'description' ws description #interfaceDescription
    | 'member' ws interfaceRouteAggregationPriority #interfaceMember
    | 'ip' ws 'address' ws REGEX_IP ws REGEX_IP #interfaceIP
    | 'nat' ws 'server' ws 'global' ws REGEX_IP ws 'inside' ws REGEX_IP ws 'reversible' #interfaceNatServer
    | 'nat' ws 'outbound' ws INT #interfaceNatBound
    | description? #interfaceIgnore
    ;
interfaceRouteAggregationPriority
    : 'interface' ws ROUTEAGGREGATION ws 'priority' ws INT
    ;
interfaceBridgeAggregation
    : 'interface' ws 'Bridge-Aggregation' INT
    ;

// routeAggregation
routeAggregationRule
    : 'interface' ws ROUTEAGGREGATION eol (ws routeAggregationRuleSubtree eol)*
    ;
routeAggregationRuleSubtree
    : 'vlan-type' ws word ws word ws INT #routeAggregationVlan
    | description? #routeAggregationIgnore
    ;

// ethernet
ethernetRule
    : 'interface' ws TETHERNET eol (ws tethernetRuleSubtree eol)*
    | 'interface' ws METHERNET eol (ws methernetRuleSubtree eol)*
    ;
tethernetRuleSubtree
    : 'port' ws 'link-mode' ws 'route' #tethernetLinkModePort
    | 'port' ws 'link-aggregation' ws group #tethernetLinkAgreegationPort
    | 'qos' ws description #tethernetQos
    | description? #tethernetIgnore
    ;
methernetRuleSubtree
    : 'ip' ws 'binding' ws word ws word #methernetVPN
    | 'ip' ws 'address' ws REGEX_IP_RANGE #methernetIPAdress
    | description? #methernetIgnore
    ;

// securityZone
securityZoneRule
    : 'security-zone' ws 'name' ws word eol (ws securityZoneRuleSubtree eol)*
    ;
securityZoneRuleSubtree
    : 'import' ws 'interface' ws INTERFACE #securityZoneImport
    | description? #securityZoneIgnore
    ;

// object-policy
objectPolicyRule
    : 'object-policy' ws description eol (ws objectPolicyRuleSubtree eol)*
    ;
objectPolicyRuleSubtree
    : 'rule' ws description #objectPolicySubRule
    | 'accelerate' #objectPolicyAccelerate
    | description? #objectPolicyIgnore
    ;

// zonePair
zonePairRule
    : 'zone-pair' ws 'security' ws 'source' ws word ws 'destination' ws word eol (ws zonePairRuleSubtree eol)*
    ;
zonePairRuleSubtree
    : ' packet-filter' INT #zonePairPktFilter
    | objectPolicyRule #zonePairObjPolicy
    | description? #zonePairIgnore
    ;

// line
lineRule
    : 'line' ws ('class' ws word)|('con' | 'vty' ws word ws word) eol (ws lineRuleSubtree eol)*
    ;
lineRuleSubtree
    : 'user-role' ws word #lineUserRole
    | 'authentication-mode' ws word #lineAuthMode
    | 'idle-timeout' ws word ws word #lineIdle
    | description? #lineIgnore
    ;

// route
routeRule
    : ws 'ip' ws 'route-static' ws REGEX_IP_MASK ws REGEX_IP (description)? eol
    ;

// snmpAgent
snmpAgentRule
    : 'snmp-agent' ws description eol
    ;

// redundancy
redundancyRule
    : 'redundancy' ws 'group' ws INT eol (ws redundancyRuleSubtree eol)*
    ;
redundancyRuleSubtree
    : 'preempt-delay' INT #redundancyPreemptDelay
    | 'member' ws 'interface' ws INTERFACE #redundancyInterface
    | 'member' ws group #redundancyGroup
    | 'node' ws INT eol (ws redundancyNodeRuleSubtree eol)* #redundancyNode
    | description? #redundancyIgnore
    ;

redundancyNodeRuleSubtree
    : 'bind' description #redundancyNodeBind
    | 'track' ws INT ws 'interface' ws (ROUTEAGGREGATION|BLADE) #redundancyNodeInterface
    ;

// acl
aclRule
    : 'acl' ws type=('basic'|'advanced') ws INT eol (ws aclRuleSubtree eol)*
    ;
aclRuleSubtree
    : 'description' word #aclDescription
    | 'rule' ws INT ws 'permit' (ws protocal)? (ws source)? (ws destination)? #aclSubRule
    | description? #aclIgnore
    ;
source: 'source' ws REGEX_IP_MASK;
destination: 'destination' ws REGEX_IP_MASK;

// role
roleRule
    : 'role' ws 'name' ws roleLevel eol (ws roleRuleSubTree eol)*
    ;
roleRuleSubTree
    : 'description' description
    ;
roleLevel
    : 'level-' INT
    | 'network-admin'
    | 'network-operator'
    ;

// user class manage
userClassRule
    : 'local-user' ws word ws 'class' ws 'manage' eol (ws userClassRuleSubTree eol)*
    ;
userClassRuleSubTree
    : 'password' ws 'hash' ws word #userClassPass
    | 'service-type' (ws service) #userClassService
    | 'authorization-attribute' ws 'user-role' ws roleLevel #userClassAuth
    | description? #userClassIgnore
    ;

// session
sessionRule
    : ws 'session' ws description
    ;

// object-group
objectGroupRule
    : 'object-group' ws 'ip' ws 'address' ws REGEX_IP_ADDRESS eol (ws objectGroupSubTree eol)*
    | 'object-group' ws 'port' ws PORT eol (ws objectGroupSubTree eol)*
    ;
objectGroupSubTree
    : INT ws 'network' ws 'host' ws 'address' ws REGEX_IP #objectGroupHost
    | INT ws 'network' ws ('subnet'| 'range') ws REGEX_IP_RANGE #objectGroupNet
    | INT ws 'port' ws 'eq' ws INT #objectGroupPortEq
    | INT ws 'port' ws 'range' ws INT ws INT #objectGroupPortRange
    ;



ws // 若干空格
    : WS
    ;

eol // 若干换行 或若干空格
    : WS? EOL
    ;

word // 开头不换行 不空格 的一整行
    : ~(EOL|WS)+
    ;

description
    : ~EOL+ // 开头不换行的一整行
    ;

comment // #作开头
    : COMMENT
    | '#'
    ;

group
    : 'group' ws INT
    ;

protocal
    : 'ip'
    | 'tcp'
    | 'udp'
    | 'icmp'
    ;


service
    : 'ssh'
    | 'telnet'
    | 'http'
    | 'https'
    ;
RETRUN: 'return';



INTERFACE: 'Reth'+INT;
ROUTEAGGREGATION
    : 'Route-Aggregation'+INT+'.'+INT
    | 'Route-Aggregation'+INT
    ;
TETHERNET: 'Ten-GigabitEthernet'+INT+'/'+INT+'/'+INT+'/'+INT;
METHERNET: 'M-GigabitEthernet'+INT+'/'+INT+'/'+INT+'/'+INT;
BLADE: 'Blade'+INT+'/'+INT+'/'+INT+'/'+INT;

REGEX_IP6:  [0-9a-fA-F:]+ ':' ( HEX_CODE? HEX_CODE? HEX_CODE? HEX_CODE? | REGEX_IP );
REGEX_IP_MASK: REGEX_IP ' ' MASK; // 172.17.34.192 26 172
REGEX_IP_RANGE: REGEX_IP ' ' REGEX_IP;
REGEX_IP_ADDRESS
    : INT '-' INT
    | 'vlan' INT '-dcn-' INT
    ;
REGEX_IP: RANGE255 '.' RANGE255 '.' RANGE255 '.' RANGE255; // 172.17.254.254 , 255.255.255.0
PORT: 'vlan' INT '-port-' INT;

fragment HEX_CODE: [0-9a-fA-F];
fragment MASK
    : '3'[0-2]
    |[1-2][0-9]
    |[1-9]
    ;
fragment RANGE255
    : [0-9]
    | [1-9][0-9]
    | '1'[0-9][0-9]
    | '2'[0-4][0-9]
    | '25'[0-5]
    ;

INT: [0-9]+;
COMMENT: '!' ~[\r\n]*;
EOL: [\r\n]+; // 换行
WS: [ \t]+; // 回车

NEWLINE	: '\r' '\n'?
        | '\n'
        ;

CJK
    : [\u2E80-\u2EFF] //CJK Radicals Supplement
    | [\u3000-\u303F] //CJK Symbols and Punctuation
    | [\u3400-\u4DBF] //CJK Unified Ideographs Extension A
    | [\u4E00-\u9FBF] //CJK Unified Ideographs
    ;


Basic_Latin
    : [\u0020-\u002F]
    | [\u003A-\u0040]
    | [\u005B-\u0060]
    | [\u007B-\u007E]
    | [1-9a-zA-Z]+
    ;
UNICODE
    : [\uff09]
    | [\uff08]
    | [\u2014]
    ;
fragment LETTER  : 'a'..'z' | 'A'..'Z' ;
fragment NUMBER  : '0'..'9' ;

