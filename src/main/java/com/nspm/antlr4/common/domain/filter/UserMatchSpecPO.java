package com.nspm.antlr4.common.domain.filter;

import javax.validation.constraints.NotNull;

/**
 * @author luwei
 * @date 2019/9/10
 */
public class UserMatchSpecPO {

    @NotNull
    private UserMatchSpecType type;
    private String nameRef;

    public static UserMatchSpecPO createUserType(String userName) {
        UserMatchSpecPO result = new UserMatchSpecPO(UserMatchSpecType.USER);
        result.nameRef = userName;
        return result;
    }

    public static UserMatchSpecPO createUserGroupType(String userGroupName) {
        UserMatchSpecPO result = new UserMatchSpecPO(UserMatchSpecType.USER_GROUP);
        result.nameRef = userGroupName;
        return result;
    }

    public static UserMatchSpecPO createUserGroupObjectType(String userGroupObjectName) {
        UserMatchSpecPO result = new UserMatchSpecPO(UserMatchSpecType.USER_GROUP_OBJECT);
        result.nameRef = userGroupObjectName;
        return result;
    }

    public static UserMatchSpecPO createAnyType() {
        UserMatchSpecPO result = new UserMatchSpecPO(UserMatchSpecType.ANY);
        return result;
    }

    public static UserMatchSpecPO createNoneType() {
        UserMatchSpecPO result = new UserMatchSpecPO(UserMatchSpecType.NONE);
        return result;
    }

    public UserMatchSpecPO() {
    }

    public UserMatchSpecPO(UserMatchSpecType type) {
        this.type = type;
    }

    public UserMatchSpecType getType() {
        return this.type;
    }

    public void setType(UserMatchSpecType type) {
        this.type = type;
    }

    public String getNameRef() {
        return this.nameRef;
    }

    public void setNameRef(String name) {
        this.nameRef = name;
    }

    public static enum UserMatchSpecType {
        USER,
        USER_GROUP,
        USER_GROUP_OBJECT,
        ANY,
        NONE;

        private UserMatchSpecType() {
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
        UserMatchSpecPO other = (UserMatchSpecPO) obj;
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
