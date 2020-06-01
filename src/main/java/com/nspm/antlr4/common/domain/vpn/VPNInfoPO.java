package com.nspm.antlr4.common.domain.vpn;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.enums.VPNTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
@Data
public class VPNInfoPO implements Serializable {
    private VPNTypeEnum type;
    private String name;
    private ConfigLinePO lineNumber;
    private String description;


}
