package com.nspm.antlr4.common.domain.vpn;

import com.nspm.antlr4.common.enums.VPNTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
@Data
public class VPNRefPO implements Serializable {
    private VPNTypeEnum type;

    private String name;


    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
        return result;
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
        VPNRefPO other = (VPNRefPO) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.type);
        builder.append(':');
        builder.append(this.name);

        return builder.toString();
    }


}
