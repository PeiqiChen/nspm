package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.routing.NextHopPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/10
 */
@ApiModel("策略其余动作的匹配模型")
public class ActionClausePO implements Serializable {

    @ApiModelProperty("多余的操作字符串")
    private List<String> miscActions;
    @ApiModelProperty("下一跳集")
    private NextHopPO[] nextHops;
    @ApiModelProperty("是否跳过其他策略")
    private Boolean skipOtherFilterList;

    public ActionClausePO() {
    }

    public ActionClausePO(NextHopPO[] nextHops) {
        this.nextHops = nextHops;
    }

    public ActionClausePO(Boolean skipOtherFilter) {
        this.skipOtherFilterList = skipOtherFilter;
    }

    public NextHopPO[] getNextHops() {
        return this.nextHops;
    }

    public void setNextHops(NextHopPO[] nextHops) {
        this.nextHops = nextHops;
    }

    public void addNextHop(NextHopPO nh) {
        if (nh == null) {
            return;
        }
        if (this.nextHops == null) {
            this.nextHops = new NextHopPO[]{nh};
        } else {
            NextHopPO[] newArr = new NextHopPO[this.nextHops.length + 1];
            System.arraycopy(this.nextHops, 0, newArr, 0, this.nextHops.length);

            newArr[(newArr.length - 1)] = nh;
            this.nextHops = newArr;
        }
    }

    public List<String> getMiscActions() {
        return this.miscActions;
    }

    public void setMiscActions(List<String> miscActions) {
        this.miscActions = miscActions;
    }

    public void addMiscAction(String miscAction) {
        if (miscAction == null) {
            return;
        }
        if (this.miscActions == null) {
            this.miscActions = new ArrayList();
        }

        HashSet<String> myset = new HashSet(this.miscActions);
        if (myset.contains(miscAction)) {
            return;
        }

        this.miscActions.add(miscAction);
    }

    public boolean isSkipOtherFilterList() {
        if (this.skipOtherFilterList == null) {
            return false;
        }
        return this.skipOtherFilterList.booleanValue();
    }

    public Boolean getSkipOtherFilterList() {
        return this.skipOtherFilterList;
    }

    public void setSkipOtherFilterList(Boolean skipOtherFilter) {
        this.skipOtherFilterList = skipOtherFilter;
    }
}
