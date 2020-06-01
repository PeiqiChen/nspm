package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/10
 */
public class MatchGroupPO {

    private String name;
    private String description;
    private String[] tags;
    @JsonView({DiffIgnoreFields.class})
    private ConfigLinePO lineNumbers;
    @NotNull
    private MatchType matchType;
    @Valid
    private MatchClausePO[] matchClauses;
    @Valid
    private MatchGroupPO[] nestedMatchGroup;

    private MatchGroupPO() {}

    public MatchGroupPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, MatchType matchType, List<MatchClausePO> matchClausesList, List<MatchGroupPO> nestedMatchGroupList)
    {
        this.name = name;
        this.description = description;
        this.tags = (tagsList == null ? null : (String[])tagsList.toArray(new String[tagsList.size()]));
        this.lineNumbers = lineNumbers;
        this.matchType = matchType;
        this.matchClauses = (matchClausesList == null ? null : (MatchClausePO[])matchClausesList.toArray(new MatchClausePO[matchClausesList.size()]));
        this.nestedMatchGroup = (nestedMatchGroupList == null ? null : (MatchGroupPO[])nestedMatchGroupList.toArray(new MatchGroupPO[nestedMatchGroupList.size()]));
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getTags() {
        return this.tags == null ? null : Collections.unmodifiableList(Arrays.asList(this.tags));
    }

    public ConfigLinePO getLineNumbers() {
        return this.lineNumbers;
    }

    public MatchType getMatchType() {
        return this.matchType;
    }

    public List<MatchClausePO> getMatchClauses() {
        return this.matchClauses == null ? null : Collections.unmodifiableList(Arrays.asList(this.matchClauses));
    }

    public List<MatchGroupPO> getNestedMatchGroup() {
        return this.nestedMatchGroup == null ? null : Collections.unmodifiableList(Arrays.asList(this.nestedMatchGroup));
    }

    public static enum MatchType
    {
        ALL,
        ANY,
        NOT;

        private MatchType() {}
    }
    
}
