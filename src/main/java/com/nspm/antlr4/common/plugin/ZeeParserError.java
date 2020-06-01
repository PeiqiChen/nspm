package com.nspm.antlr4.common.plugin;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * @author zc
 * @date 2019/09/18
 */
public class ZeeParserError extends ZeeError {
    private ZeeParserError() {
    }

    public ZeeParserError(ParserRuleContext ctx, String systemMessage) {
        super(ZeeErrorName.PLUGIN_DEVICE_PARSER_ERROR, new Object[]{VisitorHelper.getConfigLine(ctx), VisitorHelper.getText(ctx)}, systemMessage);
    }

    public ZeeParserError(ConfigLinePO lineNumber, String systemMessage) {
        super(ZeeErrorName.PLUGIN_DEVICE_PARSER_ERROR, new Object[]{lineNumber, null}, systemMessage);
    }
}
