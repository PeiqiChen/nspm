package com.nspm.antlr4.common.domain.filter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author luwei
 * @date 2019/9/10
 */
public class AppMatchSpecPO {

    @NotNull
    private AppMatchSpecType type;
    @NotEmpty
    private String nameRef;

    public static AppMatchSpecPO createAppType(String appName) {
        AppMatchSpecPO result = new AppMatchSpecPO(AppMatchSpecType.APP);
        result.nameRef = appName;
        return result;
    }

    public static AppMatchSpecPO createAppGroupType(String appGroupName) {
        AppMatchSpecPO result = new AppMatchSpecPO(AppMatchSpecType.APP_GROUP);
        result.nameRef = appGroupName;
        return result;
    }

    public static AppMatchSpecPO createAppGroupObjectType(String appGroupObjectName) {
        AppMatchSpecPO result = new AppMatchSpecPO(AppMatchSpecType.APP_GROUP_OBJECT);
        result.nameRef = appGroupObjectName;
        return result;
    }

    public static AppMatchSpecPO createAnyType() {
        AppMatchSpecPO result = new AppMatchSpecPO(AppMatchSpecType.ANY);
        return result;
    }

    public AppMatchSpecPO() {
    }

    public AppMatchSpecPO(AppMatchSpecType type) {
        this.type = type;
    }

    public AppMatchSpecType getType() {
        return this.type;
    }

    public void setType(AppMatchSpecType type) {
        this.type = type;
    }

    public String getNameRef() {
        return this.nameRef;
    }

    public void setNameRef(String name) {
        this.nameRef = name;
    }

    public static enum AppMatchSpecType {
        APP,
        APP_GROUP,
        APP_GROUP_OBJECT,
        ANY;

        private AppMatchSpecType() {
        }
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.nameRef == null ? 0 : this.nameRef.hashCode());
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
        AppMatchSpecPO other = (AppMatchSpecPO) obj;
        if (this.nameRef == null) {
            if (other.nameRef != null) {
                return false;
            }
        } else if (!this.nameRef.equals(other.nameRef)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
}
