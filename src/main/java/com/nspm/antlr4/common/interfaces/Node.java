package com.nspm.antlr4.common.interfaces;

/**
 * @author luwei
 * @date 2019/9/9
 */
public interface Node {


    String getObjectId();

    String getNodeId();

    NodeType getNodeType();


    public static enum NodeType {
        DEVICE,
        SUBNET;

        private NodeType() {
        }
    }

}
