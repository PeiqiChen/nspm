package com.nspm.antlr4.common.deviceInterface;

import com.nspm.antlr4.common.enums.IPTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
@Data
public class AdhocLinkPO implements Serializable {

    @NotNull
    private IPTypeEnum ipType;
    private String name;
    private String description;
    @NotEmpty
    private String matchingText;

    public AdhocLinkPO() {
    }

    public AdhocLinkPO(IPTypeEnum ipType, String name, String description, String matchingText) {
        this.ipType = ipType;
        this.name = name;
        this.description = description;
        this.matchingText = matchingText;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AdhocLinkPO other = (AdhocLinkPO) obj;
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        if (this.ipType != other.ipType) {
            return false;
        }
        if (this.matchingText == null) {
            if (other.matchingText != null) {
                return false;
            }
        } else if (!this.matchingText.equals(other.matchingText)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }


}
