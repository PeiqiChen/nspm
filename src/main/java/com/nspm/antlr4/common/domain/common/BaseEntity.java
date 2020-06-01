package com.nspm.antlr4.common.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.Id;

/**
 * @author luwei
 * @date 2019/10/25
 */
public class BaseEntity {

    @Id
    @JsonIgnore
    protected String id;

    @JsonView({DiffIgnoreFields.class})
    @JsonProperty
    public String getId() {
        return this.id;
    }

    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
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
        BaseEntity other = (BaseEntity) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }


}
