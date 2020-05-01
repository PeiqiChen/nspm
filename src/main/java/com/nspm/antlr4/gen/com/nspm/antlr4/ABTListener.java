// Generated from /Users/chenpeiqi/courses/大三/大三下/实训/npsm/src/main/java/com/nspm/antlr4/ABT.g4 by ANTLR 4.8
package com.nspm.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ABTParser}.
 */
public interface ABTListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ABTParser#config}.
	 * @param ctx the parse tree
	 */
	void enterConfig(ABTParser.ConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#config}.
	 * @param ctx the parse tree
	 */
	void exitConfig(ABTParser.ConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ABTParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ABTParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#commentLine}.
	 * @param ctx the parse tree
	 */
	void enterCommentLine(ABTParser.CommentLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#commentLine}.
	 * @param ctx the parse tree
	 */
	void exitCommentLine(ABTParser.CommentLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#blankLine}.
	 * @param ctx the parse tree
	 */
	void enterBlankLine(ABTParser.BlankLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#blankLine}.
	 * @param ctx the parse tree
	 */
	void exitBlankLine(ABTParser.BlankLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#commandLine}.
	 * @param ctx the parse tree
	 */
	void enterCommandLine(ABTParser.CommandLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#commandLine}.
	 * @param ctx the parse tree
	 */
	void exitCommandLine(ABTParser.CommandLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#knownCommand}.
	 * @param ctx the parse tree
	 */
	void enterKnownCommand(ABTParser.KnownCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#knownCommand}.
	 * @param ctx the parse tree
	 */
	void exitKnownCommand(ABTParser.KnownCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#unknownCommand}.
	 * @param ctx the parse tree
	 */
	void enterUnknownCommand(ABTParser.UnknownCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#unknownCommand}.
	 * @param ctx the parse tree
	 */
	void exitUnknownCommand(ABTParser.UnknownCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#version}.
	 * @param ctx the parse tree
	 */
	void enterVersion(ABTParser.VersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#version}.
	 * @param ctx the parse tree
	 */
	void exitVersion(ABTParser.VersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#hostname}.
	 * @param ctx the parse tree
	 */
	void enterHostname(ABTParser.HostnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#hostname}.
	 * @param ctx the parse tree
	 */
	void exitHostname(ABTParser.HostnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#contextRule}.
	 * @param ctx the parse tree
	 */
	void enterContextRule(ABTParser.ContextRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#contextRule}.
	 * @param ctx the parse tree
	 */
	void exitContextRule(ABTParser.ContextRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextDescription}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextDescription(ABTParser.ContextDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextDescription}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextDescription(ABTParser.ContextDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextStart}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextStart(ABTParser.ContextStartContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextStart}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextStart(ABTParser.ContextStartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextLocation}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextLocation(ABTParser.ContextLocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextLocation}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextLocation(ABTParser.ContextLocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextInterfaceSingle}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextInterfaceSingle(ABTParser.ContextInterfaceSingleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextInterfaceSingle}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextInterfaceSingle(ABTParser.ContextInterfaceSingleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextInterfaceMultiple}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextInterfaceMultiple(ABTParser.ContextInterfaceMultipleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextInterfaceMultiple}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextInterfaceMultiple(ABTParser.ContextInterfaceMultipleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextThroughput}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextThroughput(ABTParser.ContextThroughputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextThroughput}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextThroughput(ABTParser.ContextThroughputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextSession}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextSession(ABTParser.ContextSessionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextSession}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextSession(ABTParser.ContextSessionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextIgnore}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextIgnore(ABTParser.ContextIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextIgnore}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextIgnore(ABTParser.ContextIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#interfaceRule}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceRule(ABTParser.InterfaceRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#interfaceRule}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceRule(ABTParser.InterfaceRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceDescription}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDescription(ABTParser.InterfaceDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceDescription}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDescription(ABTParser.InterfaceDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceMember}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMember(ABTParser.InterfaceMemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceMember}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMember(ABTParser.InterfaceMemberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceIP}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceIP(ABTParser.InterfaceIPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceIP}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceIP(ABTParser.InterfaceIPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceNatServer}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceNatServer(ABTParser.InterfaceNatServerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceNatServer}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceNatServer(ABTParser.InterfaceNatServerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceNatBound}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceNatBound(ABTParser.InterfaceNatBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceNatBound}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceNatBound(ABTParser.InterfaceNatBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceIgnore}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceIgnore(ABTParser.InterfaceIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceIgnore}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceIgnore(ABTParser.InterfaceIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#interfaceRouteAggregationPriority}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceRouteAggregationPriority(ABTParser.InterfaceRouteAggregationPriorityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#interfaceRouteAggregationPriority}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceRouteAggregationPriority(ABTParser.InterfaceRouteAggregationPriorityContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#interfaceBridgeAggregation}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBridgeAggregation(ABTParser.InterfaceBridgeAggregationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#interfaceBridgeAggregation}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBridgeAggregation(ABTParser.InterfaceBridgeAggregationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#routeAggregationRule}.
	 * @param ctx the parse tree
	 */
	void enterRouteAggregationRule(ABTParser.RouteAggregationRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#routeAggregationRule}.
	 * @param ctx the parse tree
	 */
	void exitRouteAggregationRule(ABTParser.RouteAggregationRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routeAggregationVlan}
	 * labeled alternative in {@link ABTParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRouteAggregationVlan(ABTParser.RouteAggregationVlanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routeAggregationVlan}
	 * labeled alternative in {@link ABTParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRouteAggregationVlan(ABTParser.RouteAggregationVlanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routeAggregationIgnore}
	 * labeled alternative in {@link ABTParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRouteAggregationIgnore(ABTParser.RouteAggregationIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routeAggregationIgnore}
	 * labeled alternative in {@link ABTParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRouteAggregationIgnore(ABTParser.RouteAggregationIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#ethernetRule}.
	 * @param ctx the parse tree
	 */
	void enterEthernetRule(ABTParser.EthernetRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#ethernetRule}.
	 * @param ctx the parse tree
	 */
	void exitEthernetRule(ABTParser.EthernetRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetLinkModePort}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetLinkModePort(ABTParser.TethernetLinkModePortContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetLinkModePort}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetLinkModePort(ABTParser.TethernetLinkModePortContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetLinkAgreegationPort}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetLinkAgreegationPort(ABTParser.TethernetLinkAgreegationPortContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetLinkAgreegationPort}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetLinkAgreegationPort(ABTParser.TethernetLinkAgreegationPortContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetQos}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetQos(ABTParser.TethernetQosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetQos}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetQos(ABTParser.TethernetQosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetIgnore}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetIgnore(ABTParser.TethernetIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetIgnore}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetIgnore(ABTParser.TethernetIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methernetVPN}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterMethernetVPN(ABTParser.MethernetVPNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methernetVPN}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitMethernetVPN(ABTParser.MethernetVPNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methernetIPAdress}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterMethernetIPAdress(ABTParser.MethernetIPAdressContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methernetIPAdress}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitMethernetIPAdress(ABTParser.MethernetIPAdressContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methernetIgnore}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterMethernetIgnore(ABTParser.MethernetIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methernetIgnore}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitMethernetIgnore(ABTParser.MethernetIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#securityZoneRule}.
	 * @param ctx the parse tree
	 */
	void enterSecurityZoneRule(ABTParser.SecurityZoneRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#securityZoneRule}.
	 * @param ctx the parse tree
	 */
	void exitSecurityZoneRule(ABTParser.SecurityZoneRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code securityZoneImport}
	 * labeled alternative in {@link ABTParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterSecurityZoneImport(ABTParser.SecurityZoneImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code securityZoneImport}
	 * labeled alternative in {@link ABTParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitSecurityZoneImport(ABTParser.SecurityZoneImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code securityZoneIgnore}
	 * labeled alternative in {@link ABTParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterSecurityZoneIgnore(ABTParser.SecurityZoneIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code securityZoneIgnore}
	 * labeled alternative in {@link ABTParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitSecurityZoneIgnore(ABTParser.SecurityZoneIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#objectPolicyRule}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicyRule(ABTParser.ObjectPolicyRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#objectPolicyRule}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicyRule(ABTParser.ObjectPolicyRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectPolicySubRule}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicySubRule(ABTParser.ObjectPolicySubRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectPolicySubRule}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicySubRule(ABTParser.ObjectPolicySubRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectPolicyAccelerate}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicyAccelerate(ABTParser.ObjectPolicyAccelerateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectPolicyAccelerate}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicyAccelerate(ABTParser.ObjectPolicyAccelerateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectPolicyIgnore}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicyIgnore(ABTParser.ObjectPolicyIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectPolicyIgnore}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicyIgnore(ABTParser.ObjectPolicyIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#zonePairRule}.
	 * @param ctx the parse tree
	 */
	void enterZonePairRule(ABTParser.ZonePairRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#zonePairRule}.
	 * @param ctx the parse tree
	 */
	void exitZonePairRule(ABTParser.ZonePairRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code zonePairPktFilter}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterZonePairPktFilter(ABTParser.ZonePairPktFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code zonePairPktFilter}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitZonePairPktFilter(ABTParser.ZonePairPktFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code zonePairObjPolicy}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterZonePairObjPolicy(ABTParser.ZonePairObjPolicyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code zonePairObjPolicy}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitZonePairObjPolicy(ABTParser.ZonePairObjPolicyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code zonePairIgnore}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterZonePairIgnore(ABTParser.ZonePairIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code zonePairIgnore}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitZonePairIgnore(ABTParser.ZonePairIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#lineRule}.
	 * @param ctx the parse tree
	 */
	void enterLineRule(ABTParser.LineRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#lineRule}.
	 * @param ctx the parse tree
	 */
	void exitLineRule(ABTParser.LineRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineUserRole}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineUserRole(ABTParser.LineUserRoleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineUserRole}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineUserRole(ABTParser.LineUserRoleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineAuthMode}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineAuthMode(ABTParser.LineAuthModeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineAuthMode}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineAuthMode(ABTParser.LineAuthModeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineIdle}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineIdle(ABTParser.LineIdleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineIdle}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineIdle(ABTParser.LineIdleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineIgnore}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineIgnore(ABTParser.LineIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineIgnore}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineIgnore(ABTParser.LineIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#routeRule}.
	 * @param ctx the parse tree
	 */
	void enterRouteRule(ABTParser.RouteRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#routeRule}.
	 * @param ctx the parse tree
	 */
	void exitRouteRule(ABTParser.RouteRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#snmpAgentRule}.
	 * @param ctx the parse tree
	 */
	void enterSnmpAgentRule(ABTParser.SnmpAgentRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#snmpAgentRule}.
	 * @param ctx the parse tree
	 */
	void exitSnmpAgentRule(ABTParser.SnmpAgentRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#redundancyRule}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyRule(ABTParser.RedundancyRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#redundancyRule}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyRule(ABTParser.RedundancyRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyPreemptDelay}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyPreemptDelay(ABTParser.RedundancyPreemptDelayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyPreemptDelay}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyPreemptDelay(ABTParser.RedundancyPreemptDelayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyInterface}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyInterface(ABTParser.RedundancyInterfaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyInterface}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyInterface(ABTParser.RedundancyInterfaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyGroup}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyGroup(ABTParser.RedundancyGroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyGroup}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyGroup(ABTParser.RedundancyGroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyNode}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyNode(ABTParser.RedundancyNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyNode}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyNode(ABTParser.RedundancyNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyIgnore}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyIgnore(ABTParser.RedundancyIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyIgnore}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyIgnore(ABTParser.RedundancyIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyNodeBind}
	 * labeled alternative in {@link ABTParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyNodeBind(ABTParser.RedundancyNodeBindContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyNodeBind}
	 * labeled alternative in {@link ABTParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyNodeBind(ABTParser.RedundancyNodeBindContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyNodeInterface}
	 * labeled alternative in {@link ABTParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyNodeInterface(ABTParser.RedundancyNodeInterfaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyNodeInterface}
	 * labeled alternative in {@link ABTParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyNodeInterface(ABTParser.RedundancyNodeInterfaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#aclRule}.
	 * @param ctx the parse tree
	 */
	void enterAclRule(ABTParser.AclRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#aclRule}.
	 * @param ctx the parse tree
	 */
	void exitAclRule(ABTParser.AclRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aclDescription}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterAclDescription(ABTParser.AclDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aclDescription}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitAclDescription(ABTParser.AclDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aclSubRule}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterAclSubRule(ABTParser.AclSubRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aclSubRule}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitAclSubRule(ABTParser.AclSubRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aclIgnore}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterAclIgnore(ABTParser.AclIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aclIgnore}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitAclIgnore(ABTParser.AclIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(ABTParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(ABTParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#destination}.
	 * @param ctx the parse tree
	 */
	void enterDestination(ABTParser.DestinationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#destination}.
	 * @param ctx the parse tree
	 */
	void exitDestination(ABTParser.DestinationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#roleRule}.
	 * @param ctx the parse tree
	 */
	void enterRoleRule(ABTParser.RoleRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#roleRule}.
	 * @param ctx the parse tree
	 */
	void exitRoleRule(ABTParser.RoleRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#roleRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterRoleRuleSubTree(ABTParser.RoleRuleSubTreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#roleRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitRoleRuleSubTree(ABTParser.RoleRuleSubTreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#roleLevel}.
	 * @param ctx the parse tree
	 */
	void enterRoleLevel(ABTParser.RoleLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#roleLevel}.
	 * @param ctx the parse tree
	 */
	void exitRoleLevel(ABTParser.RoleLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#userClassRule}.
	 * @param ctx the parse tree
	 */
	void enterUserClassRule(ABTParser.UserClassRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#userClassRule}.
	 * @param ctx the parse tree
	 */
	void exitUserClassRule(ABTParser.UserClassRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassPass}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassPass(ABTParser.UserClassPassContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassPass}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassPass(ABTParser.UserClassPassContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassService}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassService(ABTParser.UserClassServiceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassService}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassService(ABTParser.UserClassServiceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassAuth}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassAuth(ABTParser.UserClassAuthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassAuth}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassAuth(ABTParser.UserClassAuthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassIgnore}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassIgnore(ABTParser.UserClassIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassIgnore}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassIgnore(ABTParser.UserClassIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#sessionRule}.
	 * @param ctx the parse tree
	 */
	void enterSessionRule(ABTParser.SessionRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#sessionRule}.
	 * @param ctx the parse tree
	 */
	void exitSessionRule(ABTParser.SessionRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#objectGroupRule}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupRule(ABTParser.ObjectGroupRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#objectGroupRule}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupRule(ABTParser.ObjectGroupRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupHost}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupHost(ABTParser.ObjectGroupHostContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupHost}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupHost(ABTParser.ObjectGroupHostContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupNet}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupNet(ABTParser.ObjectGroupNetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupNet}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupNet(ABTParser.ObjectGroupNetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupPortEq}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupPortEq(ABTParser.ObjectGroupPortEqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupPortEq}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupPortEq(ABTParser.ObjectGroupPortEqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupPortRange}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupPortRange(ABTParser.ObjectGroupPortRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupPortRange}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupPortRange(ABTParser.ObjectGroupPortRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#ws}.
	 * @param ctx the parse tree
	 */
	void enterWs(ABTParser.WsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#ws}.
	 * @param ctx the parse tree
	 */
	void exitWs(ABTParser.WsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#eol}.
	 * @param ctx the parse tree
	 */
	void enterEol(ABTParser.EolContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#eol}.
	 * @param ctx the parse tree
	 */
	void exitEol(ABTParser.EolContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(ABTParser.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(ABTParser.WordContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(ABTParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(ABTParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(ABTParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(ABTParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(ABTParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(ABTParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#protocal}.
	 * @param ctx the parse tree
	 */
	void enterProtocal(ABTParser.ProtocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#protocal}.
	 * @param ctx the parse tree
	 */
	void exitProtocal(ABTParser.ProtocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABTParser#service}.
	 * @param ctx the parse tree
	 */
	void enterService(ABTParser.ServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABTParser#service}.
	 * @param ctx the parse tree
	 */
	void exitService(ABTParser.ServiceContext ctx);
}