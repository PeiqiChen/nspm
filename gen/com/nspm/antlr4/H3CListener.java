// Generated from /Users/chenpeiqi/courses/大三/大三下/实训/npsm/src/main/java/com/nspm/antlr4/H3C.g4 by ANTLR 4.8
package com.nspm.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link H3CParser}.
 */
public interface H3CListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link H3CParser#config}.
	 * @param ctx the parse tree
	 */
	void enterConfig(H3CParser.ConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#config}.
	 * @param ctx the parse tree
	 */
	void exitConfig(H3CParser.ConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(H3CParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(H3CParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#commentLine}.
	 * @param ctx the parse tree
	 */
	void enterCommentLine(H3CParser.CommentLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#commentLine}.
	 * @param ctx the parse tree
	 */
	void exitCommentLine(H3CParser.CommentLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#blankLine}.
	 * @param ctx the parse tree
	 */
	void enterBlankLine(H3CParser.BlankLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#blankLine}.
	 * @param ctx the parse tree
	 */
	void exitBlankLine(H3CParser.BlankLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#commandLine}.
	 * @param ctx the parse tree
	 */
	void enterCommandLine(H3CParser.CommandLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#commandLine}.
	 * @param ctx the parse tree
	 */
	void exitCommandLine(H3CParser.CommandLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#knownCommand}.
	 * @param ctx the parse tree
	 */
	void enterKnownCommand(H3CParser.KnownCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#knownCommand}.
	 * @param ctx the parse tree
	 */
	void exitKnownCommand(H3CParser.KnownCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#unknownCommand}.
	 * @param ctx the parse tree
	 */
	void enterUnknownCommand(H3CParser.UnknownCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#unknownCommand}.
	 * @param ctx the parse tree
	 */
	void exitUnknownCommand(H3CParser.UnknownCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#version}.
	 * @param ctx the parse tree
	 */
	void enterVersion(H3CParser.VersionContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#version}.
	 * @param ctx the parse tree
	 */
	void exitVersion(H3CParser.VersionContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#hostname}.
	 * @param ctx the parse tree
	 */
	void enterHostname(H3CParser.HostnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#hostname}.
	 * @param ctx the parse tree
	 */
	void exitHostname(H3CParser.HostnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#contextRule}.
	 * @param ctx the parse tree
	 */
	void enterContextRule(H3CParser.ContextRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#contextRule}.
	 * @param ctx the parse tree
	 */
	void exitContextRule(H3CParser.ContextRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextDescription}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextDescription(H3CParser.ContextDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextDescription}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextDescription(H3CParser.ContextDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextStart}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextStart(H3CParser.ContextStartContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextStart}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextStart(H3CParser.ContextStartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextLocation}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextLocation(H3CParser.ContextLocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextLocation}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextLocation(H3CParser.ContextLocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextInterfaceSingle}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextInterfaceSingle(H3CParser.ContextInterfaceSingleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextInterfaceSingle}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextInterfaceSingle(H3CParser.ContextInterfaceSingleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextInterfaceMultiple}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextInterfaceMultiple(H3CParser.ContextInterfaceMultipleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextInterfaceMultiple}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextInterfaceMultiple(H3CParser.ContextInterfaceMultipleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextThroughput}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextThroughput(H3CParser.ContextThroughputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextThroughput}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextThroughput(H3CParser.ContextThroughputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextSession}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextSession(H3CParser.ContextSessionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextSession}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextSession(H3CParser.ContextSessionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code contextIgnore}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void enterContextIgnore(H3CParser.ContextIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code contextIgnore}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 */
	void exitContextIgnore(H3CParser.ContextIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#interfaceRule}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceRule(H3CParser.InterfaceRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#interfaceRule}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceRule(H3CParser.InterfaceRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceDescription}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDescription(H3CParser.InterfaceDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceDescription}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDescription(H3CParser.InterfaceDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceMember}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMember(H3CParser.InterfaceMemberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceMember}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMember(H3CParser.InterfaceMemberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceIP}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceIP(H3CParser.InterfaceIPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceIP}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceIP(H3CParser.InterfaceIPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceNatServer}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceNatServer(H3CParser.InterfaceNatServerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceNatServer}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceNatServer(H3CParser.InterfaceNatServerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceNatBound}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceNatBound(H3CParser.InterfaceNatBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceNatBound}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceNatBound(H3CParser.InterfaceNatBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code interfaceIgnore}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceIgnore(H3CParser.InterfaceIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code interfaceIgnore}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceIgnore(H3CParser.InterfaceIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#interfaceRouteAggregationPriority}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceRouteAggregationPriority(H3CParser.InterfaceRouteAggregationPriorityContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#interfaceRouteAggregationPriority}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceRouteAggregationPriority(H3CParser.InterfaceRouteAggregationPriorityContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#interfaceBridgeAggregation}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBridgeAggregation(H3CParser.InterfaceBridgeAggregationContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#interfaceBridgeAggregation}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBridgeAggregation(H3CParser.InterfaceBridgeAggregationContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#routeAggregationRule}.
	 * @param ctx the parse tree
	 */
	void enterRouteAggregationRule(H3CParser.RouteAggregationRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#routeAggregationRule}.
	 * @param ctx the parse tree
	 */
	void exitRouteAggregationRule(H3CParser.RouteAggregationRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routeAggregationVlan}
	 * labeled alternative in {@link H3CParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRouteAggregationVlan(H3CParser.RouteAggregationVlanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routeAggregationVlan}
	 * labeled alternative in {@link H3CParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRouteAggregationVlan(H3CParser.RouteAggregationVlanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routeAggregationIgnore}
	 * labeled alternative in {@link H3CParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRouteAggregationIgnore(H3CParser.RouteAggregationIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routeAggregationIgnore}
	 * labeled alternative in {@link H3CParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRouteAggregationIgnore(H3CParser.RouteAggregationIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#ethernetRule}.
	 * @param ctx the parse tree
	 */
	void enterEthernetRule(H3CParser.EthernetRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#ethernetRule}.
	 * @param ctx the parse tree
	 */
	void exitEthernetRule(H3CParser.EthernetRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetLinkModePort}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetLinkModePort(H3CParser.TethernetLinkModePortContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetLinkModePort}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetLinkModePort(H3CParser.TethernetLinkModePortContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetLinkAgreegationPort}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetLinkAgreegationPort(H3CParser.TethernetLinkAgreegationPortContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetLinkAgreegationPort}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetLinkAgreegationPort(H3CParser.TethernetLinkAgreegationPortContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetQos}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetQos(H3CParser.TethernetQosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetQos}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetQos(H3CParser.TethernetQosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tethernetIgnore}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterTethernetIgnore(H3CParser.TethernetIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tethernetIgnore}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitTethernetIgnore(H3CParser.TethernetIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methernetVPN}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterMethernetVPN(H3CParser.MethernetVPNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methernetVPN}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitMethernetVPN(H3CParser.MethernetVPNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methernetIPAdress}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterMethernetIPAdress(H3CParser.MethernetIPAdressContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methernetIPAdress}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitMethernetIPAdress(H3CParser.MethernetIPAdressContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methernetIgnore}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterMethernetIgnore(H3CParser.MethernetIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methernetIgnore}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitMethernetIgnore(H3CParser.MethernetIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#securityZoneRule}.
	 * @param ctx the parse tree
	 */
	void enterSecurityZoneRule(H3CParser.SecurityZoneRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#securityZoneRule}.
	 * @param ctx the parse tree
	 */
	void exitSecurityZoneRule(H3CParser.SecurityZoneRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code securityZoneImport}
	 * labeled alternative in {@link H3CParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterSecurityZoneImport(H3CParser.SecurityZoneImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code securityZoneImport}
	 * labeled alternative in {@link H3CParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitSecurityZoneImport(H3CParser.SecurityZoneImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code securityZoneIgnore}
	 * labeled alternative in {@link H3CParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterSecurityZoneIgnore(H3CParser.SecurityZoneIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code securityZoneIgnore}
	 * labeled alternative in {@link H3CParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitSecurityZoneIgnore(H3CParser.SecurityZoneIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#objectPolicyRule}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicyRule(H3CParser.ObjectPolicyRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#objectPolicyRule}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicyRule(H3CParser.ObjectPolicyRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectPolicySubRule}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicySubRule(H3CParser.ObjectPolicySubRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectPolicySubRule}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicySubRule(H3CParser.ObjectPolicySubRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectPolicyAccelerate}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicyAccelerate(H3CParser.ObjectPolicyAccelerateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectPolicyAccelerate}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicyAccelerate(H3CParser.ObjectPolicyAccelerateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectPolicyIgnore}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterObjectPolicyIgnore(H3CParser.ObjectPolicyIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectPolicyIgnore}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitObjectPolicyIgnore(H3CParser.ObjectPolicyIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#zonePairRule}.
	 * @param ctx the parse tree
	 */
	void enterZonePairRule(H3CParser.ZonePairRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#zonePairRule}.
	 * @param ctx the parse tree
	 */
	void exitZonePairRule(H3CParser.ZonePairRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code zonePairPktFilter}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterZonePairPktFilter(H3CParser.ZonePairPktFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code zonePairPktFilter}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitZonePairPktFilter(H3CParser.ZonePairPktFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code zonePairObjPolicy}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterZonePairObjPolicy(H3CParser.ZonePairObjPolicyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code zonePairObjPolicy}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitZonePairObjPolicy(H3CParser.ZonePairObjPolicyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code zonePairIgnore}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterZonePairIgnore(H3CParser.ZonePairIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code zonePairIgnore}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitZonePairIgnore(H3CParser.ZonePairIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#lineRule}.
	 * @param ctx the parse tree
	 */
	void enterLineRule(H3CParser.LineRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#lineRule}.
	 * @param ctx the parse tree
	 */
	void exitLineRule(H3CParser.LineRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineUserRole}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineUserRole(H3CParser.LineUserRoleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineUserRole}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineUserRole(H3CParser.LineUserRoleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineAuthMode}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineAuthMode(H3CParser.LineAuthModeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineAuthMode}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineAuthMode(H3CParser.LineAuthModeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineIdle}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineIdle(H3CParser.LineIdleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineIdle}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineIdle(H3CParser.LineIdleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lineIgnore}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterLineIgnore(H3CParser.LineIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lineIgnore}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitLineIgnore(H3CParser.LineIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#routeRule}.
	 * @param ctx the parse tree
	 */
	void enterRouteRule(H3CParser.RouteRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#routeRule}.
	 * @param ctx the parse tree
	 */
	void exitRouteRule(H3CParser.RouteRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#snmpAgentRule}.
	 * @param ctx the parse tree
	 */
	void enterSnmpAgentRule(H3CParser.SnmpAgentRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#snmpAgentRule}.
	 * @param ctx the parse tree
	 */
	void exitSnmpAgentRule(H3CParser.SnmpAgentRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#redundancyRule}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyRule(H3CParser.RedundancyRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#redundancyRule}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyRule(H3CParser.RedundancyRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyPreemptDelay}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyPreemptDelay(H3CParser.RedundancyPreemptDelayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyPreemptDelay}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyPreemptDelay(H3CParser.RedundancyPreemptDelayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyInterface}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyInterface(H3CParser.RedundancyInterfaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyInterface}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyInterface(H3CParser.RedundancyInterfaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyGroup}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyGroup(H3CParser.RedundancyGroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyGroup}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyGroup(H3CParser.RedundancyGroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyNode}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyNode(H3CParser.RedundancyNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyNode}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyNode(H3CParser.RedundancyNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyIgnore}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyIgnore(H3CParser.RedundancyIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyIgnore}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyIgnore(H3CParser.RedundancyIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyNodeBind}
	 * labeled alternative in {@link H3CParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyNodeBind(H3CParser.RedundancyNodeBindContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyNodeBind}
	 * labeled alternative in {@link H3CParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyNodeBind(H3CParser.RedundancyNodeBindContext ctx);
	/**
	 * Enter a parse tree produced by the {@code redundancyNodeInterface}
	 * labeled alternative in {@link H3CParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterRedundancyNodeInterface(H3CParser.RedundancyNodeInterfaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code redundancyNodeInterface}
	 * labeled alternative in {@link H3CParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitRedundancyNodeInterface(H3CParser.RedundancyNodeInterfaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#aclRule}.
	 * @param ctx the parse tree
	 */
	void enterAclRule(H3CParser.AclRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#aclRule}.
	 * @param ctx the parse tree
	 */
	void exitAclRule(H3CParser.AclRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aclDescription}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterAclDescription(H3CParser.AclDescriptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aclDescription}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitAclDescription(H3CParser.AclDescriptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aclSubRule}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterAclSubRule(H3CParser.AclSubRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aclSubRule}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitAclSubRule(H3CParser.AclSubRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aclIgnore}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void enterAclIgnore(H3CParser.AclIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aclIgnore}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 */
	void exitAclIgnore(H3CParser.AclIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(H3CParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(H3CParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#destination}.
	 * @param ctx the parse tree
	 */
	void enterDestination(H3CParser.DestinationContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#destination}.
	 * @param ctx the parse tree
	 */
	void exitDestination(H3CParser.DestinationContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#roleRule}.
	 * @param ctx the parse tree
	 */
	void enterRoleRule(H3CParser.RoleRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#roleRule}.
	 * @param ctx the parse tree
	 */
	void exitRoleRule(H3CParser.RoleRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#roleRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterRoleRuleSubTree(H3CParser.RoleRuleSubTreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#roleRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitRoleRuleSubTree(H3CParser.RoleRuleSubTreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#roleLevel}.
	 * @param ctx the parse tree
	 */
	void enterRoleLevel(H3CParser.RoleLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#roleLevel}.
	 * @param ctx the parse tree
	 */
	void exitRoleLevel(H3CParser.RoleLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#userClassRule}.
	 * @param ctx the parse tree
	 */
	void enterUserClassRule(H3CParser.UserClassRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#userClassRule}.
	 * @param ctx the parse tree
	 */
	void exitUserClassRule(H3CParser.UserClassRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassPass}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassPass(H3CParser.UserClassPassContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassPass}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassPass(H3CParser.UserClassPassContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassService}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassService(H3CParser.UserClassServiceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassService}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassService(H3CParser.UserClassServiceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassAuth}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassAuth(H3CParser.UserClassAuthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassAuth}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassAuth(H3CParser.UserClassAuthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userClassIgnore}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void enterUserClassIgnore(H3CParser.UserClassIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userClassIgnore}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 */
	void exitUserClassIgnore(H3CParser.UserClassIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#sessionRule}.
	 * @param ctx the parse tree
	 */
	void enterSessionRule(H3CParser.SessionRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#sessionRule}.
	 * @param ctx the parse tree
	 */
	void exitSessionRule(H3CParser.SessionRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#objectGroupRule}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupRule(H3CParser.ObjectGroupRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#objectGroupRule}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupRule(H3CParser.ObjectGroupRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupHost}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupHost(H3CParser.ObjectGroupHostContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupHost}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupHost(H3CParser.ObjectGroupHostContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupNet}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupNet(H3CParser.ObjectGroupNetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupNet}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupNet(H3CParser.ObjectGroupNetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupPortEq}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupPortEq(H3CParser.ObjectGroupPortEqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupPortEq}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupPortEq(H3CParser.ObjectGroupPortEqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectGroupPortRange}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void enterObjectGroupPortRange(H3CParser.ObjectGroupPortRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectGroupPortRange}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 */
	void exitObjectGroupPortRange(H3CParser.ObjectGroupPortRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#ws}.
	 * @param ctx the parse tree
	 */
	void enterWs(H3CParser.WsContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#ws}.
	 * @param ctx the parse tree
	 */
	void exitWs(H3CParser.WsContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#eol}.
	 * @param ctx the parse tree
	 */
	void enterEol(H3CParser.EolContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#eol}.
	 * @param ctx the parse tree
	 */
	void exitEol(H3CParser.EolContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(H3CParser.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(H3CParser.WordContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(H3CParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(H3CParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(H3CParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(H3CParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(H3CParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(H3CParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#protocal}.
	 * @param ctx the parse tree
	 */
	void enterProtocal(H3CParser.ProtocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#protocal}.
	 * @param ctx the parse tree
	 */
	void exitProtocal(H3CParser.ProtocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link H3CParser#service}.
	 * @param ctx the parse tree
	 */
	void enterService(H3CParser.ServiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link H3CParser#service}.
	 * @param ctx the parse tree
	 */
	void exitService(H3CParser.ServiceContext ctx);
}