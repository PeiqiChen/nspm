package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.enums.ConfigTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "子设备版本信息")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubDeviceVersionIdPO implements Serializable {

    private ConfigTypeEnum configType;
    private String deviceUuid;
    private long version;

    @Override
    public String toString()
    {
        String mytype = null;
        if (this.configType == null) {
            mytype = "master";
        } else {
            mytype = this.configType.name();
        }

        return "[" + mytype + ":" + this.deviceUuid + "," + this.version + "]";
    }

    @Override
    public int hashCode()
    {
        int prime = 31;
        int result = 1;

        result = 31 * result + (this.configType == null ? 0 : this.configType.hashCode());

        result = 31 * result + (this.deviceUuid == null ? 0 : this.deviceUuid.hashCode());
        result = 31 * result + (int)(this.version ^ this.version >>> 32);
        return result;
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
        SubDeviceVersionIdPO other = (SubDeviceVersionIdPO)obj;
        if (this.configType != other.configType) {
            return false;
        }
        if (this.deviceUuid == null) {
            if (other.deviceUuid != null) {
                return false;
            }
        } else if (!this.deviceUuid.equals(other.deviceUuid)) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        return true;
    }
}


