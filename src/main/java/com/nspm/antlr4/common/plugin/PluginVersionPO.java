package com.nspm.antlr4.common.plugin;

//import com.nspm.antlr4.common.domain.bundle.BundleVersionPO;
//import com.nspm.antlr4.common.enums.PluginTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "插件版本信息")
@Data
public class PluginVersionPO implements Serializable {

//    private BundleVersionPO bundleVersion;
//    private PluginTypeEnum pluginType;
//    private String pluginId;
//
//    @Override
//    public boolean equals(Object obj)
//    {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        PluginVersionPO other = (PluginVersionPO)obj;
//        if (this.bundleVersion == null) {
//            if (other.bundleVersion != null) {
//                return false;
//            }
//        } else if (!this.bundleVersion.equals(other.bundleVersion)) {
//            return false;
//        }
//        if (this.pluginId == null) {
//            if (other.pluginId != null) {
//                return false;
//            }
//        } else if (!this.pluginId.equals(other.pluginId)) {
//            return false;
//        }
//        if (this.pluginType != other.pluginType) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
}
