// Generated from /Users/chenpeiqi/courses/大三/大三下/实训/npsm/src/main/java/com/nspm/antlr4/ABT.g4 by ANTLR 4.8
package com.nspm.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ABTParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ABTVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ABTParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfig(ABTParser.ConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(ABTParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#commentLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentLine(ABTParser.CommentLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#blankLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlankLine(ABTParser.BlankLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#commandLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommandLine(ABTParser.CommandLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#knownCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownCommand(ABTParser.KnownCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#unknownCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownCommand(ABTParser.UnknownCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion(ABTParser.VersionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#hostname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHostname(ABTParser.HostnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#contextRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextRule(ABTParser.ContextRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextDescription}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextDescription(ABTParser.ContextDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextStart}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextStart(ABTParser.ContextStartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextLocation}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextLocation(ABTParser.ContextLocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextInterfaceSingle}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextInterfaceSingle(ABTParser.ContextInterfaceSingleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextInterfaceMultiple}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextInterfaceMultiple(ABTParser.ContextInterfaceMultipleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextThroughput}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextThroughput(ABTParser.ContextThroughputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextSession}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextSession(ABTParser.ContextSessionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextIgnore}
	 * labeled alternative in {@link ABTParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextIgnore(ABTParser.ContextIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#interfaceRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceRule(ABTParser.InterfaceRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceDescription}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDescription(ABTParser.InterfaceDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceMember}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMember(ABTParser.InterfaceMemberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceIP}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceIP(ABTParser.InterfaceIPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceNatServer}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceNatServer(ABTParser.InterfaceNatServerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceNatBound}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceNatBound(ABTParser.InterfaceNatBoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceIgnore}
	 * labeled alternative in {@link ABTParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceIgnore(ABTParser.InterfaceIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#interfaceRouteAggregationPriority}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceRouteAggregationPriority(ABTParser.InterfaceRouteAggregationPriorityContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#interfaceBridgeAggregation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBridgeAggregation(ABTParser.InterfaceBridgeAggregationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#routeAggregationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteAggregationRule(ABTParser.RouteAggregationRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routeAggregationVlan}
	 * labeled alternative in {@link ABTParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteAggregationVlan(ABTParser.RouteAggregationVlanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routeAggregationIgnore}
	 * labeled alternative in {@link ABTParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteAggregationIgnore(ABTParser.RouteAggregationIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#ethernetRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEthernetRule(ABTParser.EthernetRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetLinkModePort}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetLinkModePort(ABTParser.TethernetLinkModePortContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetLinkAgreegationPort}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetLinkAgreegationPort(ABTParser.TethernetLinkAgreegationPortContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetQos}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetQos(ABTParser.TethernetQosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetIgnore}
	 * labeled alternative in {@link ABTParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetIgnore(ABTParser.TethernetIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methernetVPN}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethernetVPN(ABTParser.MethernetVPNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methernetIPAdress}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethernetIPAdress(ABTParser.MethernetIPAdressContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methernetIgnore}
	 * labeled alternative in {@link ABTParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethernetIgnore(ABTParser.MethernetIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#securityZoneRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecurityZoneRule(ABTParser.SecurityZoneRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code securityZoneImport}
	 * labeled alternative in {@link ABTParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecurityZoneImport(ABTParser.SecurityZoneImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code securityZoneIgnore}
	 * labeled alternative in {@link ABTParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecurityZoneIgnore(ABTParser.SecurityZoneIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#objectPolicyRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicyRule(ABTParser.ObjectPolicyRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPolicySubRule}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicySubRule(ABTParser.ObjectPolicySubRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPolicyAccelerate}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicyAccelerate(ABTParser.ObjectPolicyAccelerateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPolicyIgnore}
	 * labeled alternative in {@link ABTParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicyIgnore(ABTParser.ObjectPolicyIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#zonePairRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairRule(ABTParser.ZonePairRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code zonePairPktFilter}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairPktFilter(ABTParser.ZonePairPktFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code zonePairObjPolicy}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairObjPolicy(ABTParser.ZonePairObjPolicyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code zonePairIgnore}
	 * labeled alternative in {@link ABTParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairIgnore(ABTParser.ZonePairIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#lineRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineRule(ABTParser.LineRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineUserRole}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineUserRole(ABTParser.LineUserRoleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineAuthMode}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineAuthMode(ABTParser.LineAuthModeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineIdle}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineIdle(ABTParser.LineIdleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineIgnore}
	 * labeled alternative in {@link ABTParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineIgnore(ABTParser.LineIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#routeRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteRule(ABTParser.RouteRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#snmpAgentRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSnmpAgentRule(ABTParser.SnmpAgentRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#redundancyRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyRule(ABTParser.RedundancyRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyPreemptDelay}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyPreemptDelay(ABTParser.RedundancyPreemptDelayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyInterface}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyInterface(ABTParser.RedundancyInterfaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyGroup}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyGroup(ABTParser.RedundancyGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyNode}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyNode(ABTParser.RedundancyNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyIgnore}
	 * labeled alternative in {@link ABTParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyIgnore(ABTParser.RedundancyIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyNodeBind}
	 * labeled alternative in {@link ABTParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyNodeBind(ABTParser.RedundancyNodeBindContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyNodeInterface}
	 * labeled alternative in {@link ABTParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyNodeInterface(ABTParser.RedundancyNodeInterfaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#aclRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclRule(ABTParser.AclRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aclDescription}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclDescription(ABTParser.AclDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aclSubRule}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclSubRule(ABTParser.AclSubRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aclIgnore}
	 * labeled alternative in {@link ABTParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclIgnore(ABTParser.AclIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(ABTParser.SourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#destination}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestination(ABTParser.DestinationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#roleRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleRule(ABTParser.RoleRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#roleRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleRuleSubTree(ABTParser.RoleRuleSubTreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#roleLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleLevel(ABTParser.RoleLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#userClassRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassRule(ABTParser.UserClassRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassPass}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassPass(ABTParser.UserClassPassContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassService}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassService(ABTParser.UserClassServiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassAuth}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassAuth(ABTParser.UserClassAuthContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassIgnore}
	 * labeled alternative in {@link ABTParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassIgnore(ABTParser.UserClassIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#sessionRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionRule(ABTParser.SessionRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#objectGroupRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupRule(ABTParser.ObjectGroupRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupHost}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupHost(ABTParser.ObjectGroupHostContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupNet}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupNet(ABTParser.ObjectGroupNetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupPortEq}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupPortEq(ABTParser.ObjectGroupPortEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupPortRange}
	 * labeled alternative in {@link ABTParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupPortRange(ABTParser.ObjectGroupPortRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs(ABTParser.WsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#eol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEol(ABTParser.EolContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWord(ABTParser.WordContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(ABTParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(ABTParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(ABTParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#protocal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocal(ABTParser.ProtocalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ABTParser#service}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitService(ABTParser.ServiceContext ctx);
}