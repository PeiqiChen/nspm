// Generated from /Users/chenpeiqi/courses/大三/大三下/实训/npsm/src/main/java/com/nspm/antlr4/H3C.g4 by ANTLR 4.8
package com.nspm.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link H3CParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface H3CVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link H3CParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfig(H3CParser.ConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(H3CParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#commentLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentLine(H3CParser.CommentLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#blankLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlankLine(H3CParser.BlankLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#commandLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommandLine(H3CParser.CommandLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#knownCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownCommand(H3CParser.KnownCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#unknownCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownCommand(H3CParser.UnknownCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersion(H3CParser.VersionContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#hostname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHostname(H3CParser.HostnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#contextRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextRule(H3CParser.ContextRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextDescription}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextDescription(H3CParser.ContextDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextStart}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextStart(H3CParser.ContextStartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextLocation}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextLocation(H3CParser.ContextLocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextInterfaceSingle}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextInterfaceSingle(H3CParser.ContextInterfaceSingleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextInterfaceMultiple}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextInterfaceMultiple(H3CParser.ContextInterfaceMultipleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextThroughput}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextThroughput(H3CParser.ContextThroughputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextSession}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextSession(H3CParser.ContextSessionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code contextIgnore}
	 * labeled alternative in {@link H3CParser#contextSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextIgnore(H3CParser.ContextIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#interfaceRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceRule(H3CParser.InterfaceRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceDescription}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDescription(H3CParser.InterfaceDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceMember}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMember(H3CParser.InterfaceMemberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceIP}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceIP(H3CParser.InterfaceIPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceNatServer}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceNatServer(H3CParser.InterfaceNatServerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceNatBound}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceNatBound(H3CParser.InterfaceNatBoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code interfaceIgnore}
	 * labeled alternative in {@link H3CParser#interfaceSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceIgnore(H3CParser.InterfaceIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#interfaceRouteAggregationPriority}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceRouteAggregationPriority(H3CParser.InterfaceRouteAggregationPriorityContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#interfaceBridgeAggregation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBridgeAggregation(H3CParser.InterfaceBridgeAggregationContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#routeAggregationRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteAggregationRule(H3CParser.RouteAggregationRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routeAggregationVlan}
	 * labeled alternative in {@link H3CParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteAggregationVlan(H3CParser.RouteAggregationVlanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routeAggregationIgnore}
	 * labeled alternative in {@link H3CParser#routeAggregationRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteAggregationIgnore(H3CParser.RouteAggregationIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#ethernetRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEthernetRule(H3CParser.EthernetRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetLinkModePort}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetLinkModePort(H3CParser.TethernetLinkModePortContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetLinkAgreegationPort}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetLinkAgreegationPort(H3CParser.TethernetLinkAgreegationPortContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetQos}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetQos(H3CParser.TethernetQosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tethernetIgnore}
	 * labeled alternative in {@link H3CParser#tethernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTethernetIgnore(H3CParser.TethernetIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methernetVPN}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethernetVPN(H3CParser.MethernetVPNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methernetIPAdress}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethernetIPAdress(H3CParser.MethernetIPAdressContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methernetIgnore}
	 * labeled alternative in {@link H3CParser#methernetRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethernetIgnore(H3CParser.MethernetIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#securityZoneRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecurityZoneRule(H3CParser.SecurityZoneRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code securityZoneImport}
	 * labeled alternative in {@link H3CParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecurityZoneImport(H3CParser.SecurityZoneImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code securityZoneIgnore}
	 * labeled alternative in {@link H3CParser#securityZoneRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecurityZoneIgnore(H3CParser.SecurityZoneIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#objectPolicyRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicyRule(H3CParser.ObjectPolicyRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPolicySubRule}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicySubRule(H3CParser.ObjectPolicySubRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPolicyAccelerate}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicyAccelerate(H3CParser.ObjectPolicyAccelerateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectPolicyIgnore}
	 * labeled alternative in {@link H3CParser#objectPolicyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectPolicyIgnore(H3CParser.ObjectPolicyIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#zonePairRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairRule(H3CParser.ZonePairRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code zonePairPktFilter}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairPktFilter(H3CParser.ZonePairPktFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code zonePairObjPolicy}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairObjPolicy(H3CParser.ZonePairObjPolicyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code zonePairIgnore}
	 * labeled alternative in {@link H3CParser#zonePairRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZonePairIgnore(H3CParser.ZonePairIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#lineRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineRule(H3CParser.LineRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineUserRole}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineUserRole(H3CParser.LineUserRoleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineAuthMode}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineAuthMode(H3CParser.LineAuthModeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineIdle}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineIdle(H3CParser.LineIdleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lineIgnore}
	 * labeled alternative in {@link H3CParser#lineRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineIgnore(H3CParser.LineIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#routeRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteRule(H3CParser.RouteRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#snmpAgentRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSnmpAgentRule(H3CParser.SnmpAgentRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#redundancyRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyRule(H3CParser.RedundancyRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyPreemptDelay}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyPreemptDelay(H3CParser.RedundancyPreemptDelayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyInterface}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyInterface(H3CParser.RedundancyInterfaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyGroup}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyGroup(H3CParser.RedundancyGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyNode}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyNode(H3CParser.RedundancyNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyIgnore}
	 * labeled alternative in {@link H3CParser#redundancyRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyIgnore(H3CParser.RedundancyIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyNodeBind}
	 * labeled alternative in {@link H3CParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyNodeBind(H3CParser.RedundancyNodeBindContext ctx);
	/**
	 * Visit a parse tree produced by the {@code redundancyNodeInterface}
	 * labeled alternative in {@link H3CParser#redundancyNodeRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRedundancyNodeInterface(H3CParser.RedundancyNodeInterfaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#aclRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclRule(H3CParser.AclRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aclDescription}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclDescription(H3CParser.AclDescriptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aclSubRule}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclSubRule(H3CParser.AclSubRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aclIgnore}
	 * labeled alternative in {@link H3CParser#aclRuleSubtree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAclIgnore(H3CParser.AclIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(H3CParser.SourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#destination}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestination(H3CParser.DestinationContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#roleRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleRule(H3CParser.RoleRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#roleRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleRuleSubTree(H3CParser.RoleRuleSubTreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#roleLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleLevel(H3CParser.RoleLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#userClassRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassRule(H3CParser.UserClassRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassPass}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassPass(H3CParser.UserClassPassContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassService}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassService(H3CParser.UserClassServiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassAuth}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassAuth(H3CParser.UserClassAuthContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userClassIgnore}
	 * labeled alternative in {@link H3CParser#userClassRuleSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserClassIgnore(H3CParser.UserClassIgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#sessionRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSessionRule(H3CParser.SessionRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#objectGroupRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupRule(H3CParser.ObjectGroupRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupHost}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupHost(H3CParser.ObjectGroupHostContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupNet}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupNet(H3CParser.ObjectGroupNetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupPortEq}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupPortEq(H3CParser.ObjectGroupPortEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectGroupPortRange}
	 * labeled alternative in {@link H3CParser#objectGroupSubTree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectGroupPortRange(H3CParser.ObjectGroupPortRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs(H3CParser.WsContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#eol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEol(H3CParser.EolContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWord(H3CParser.WordContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(H3CParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(H3CParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(H3CParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#protocal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocal(H3CParser.ProtocalContext ctx);
	/**
	 * Visit a parse tree produced by {@link H3CParser#service}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitService(H3CParser.ServiceContext ctx);
}