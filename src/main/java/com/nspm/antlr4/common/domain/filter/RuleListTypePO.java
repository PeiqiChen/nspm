package com.nspm.antlr4.common.domain.filter;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * 策略集类型对象
 * @author luwei
 * @date 2019/9/11
 */
public class RuleListTypePO {
    /**
     * 是否是系统自定义的
     */
    private boolean isSystem;
    /**
     * 名字
     */
    private String name;

    private EnumSet<RuleListOption> options;

    public RuleListTypePO(boolean isSystem, String name, EnumSet<RuleListOption> options) {
        this.isSystem = isSystem;
        this.name = name;
        if (options == null) {
            this.options = EnumSet.noneOf(RuleListOption.class);
        } else {
            this.options = options;
        }
    }

    public RuleListTypePO(String name, EnumSet<RuleListOption> options) {
        this(false, name, options);
    }

    public RuleListTypePO(String name, RuleListOption... options) {
        this.isSystem = false;
        this.name = name;
        if ((options != null) && (options.length > 0)) {
            this.options = EnumSet.copyOf(Arrays.asList(options));
        } else {
            this.options = EnumSet.noneOf(RuleListOption.class);
        }
    }

    public boolean isSystem() {
        return this.isSystem;
    }

    public String getName() {
        return this.name;
    }

    public EnumSet<RuleListOption> getOptions() {
        return this.options;
    }

    public static enum RuleListOption {
        PERMIT_ALL_AT_THE_END_OF_COMBINED_RULE_LIST
    }

}
