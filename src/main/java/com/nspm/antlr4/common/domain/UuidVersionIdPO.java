package com.nspm.antlr4.common.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "uuid、版本集合")
@Data
public class UuidVersionIdPO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private long version;

    public UuidVersionIdPO(String uuid, long version) {
        this.uuid = uuid;
        this.version = version;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UuidVersionIdPO other = (UuidVersionIdPO)obj;
        if (this.uuid == null) {
            if (other.uuid != null) {
                return false;
            }
        } else if (!this.uuid.equals(other.uuid)) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        return true;
    }

}
