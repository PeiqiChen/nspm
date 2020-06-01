package com.nspm.antlr4.common.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.util.Date;

/**
 * @author luwei
 * @date 2019/10/25
 */
public class VersionBaseEntity extends BaseEntity {


    @JsonIgnore
    @CreatedDate
    protected Date createdTime;
    @JsonIgnore
    @LastModifiedDate
    protected Date lastModifiedTime;
    @JsonIgnore
    @Version
    protected Long version;

    @JsonView({DiffIgnoreFields.class})
    @JsonProperty
    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    @JsonView({DiffIgnoreFields.class})
    @JsonProperty
    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    @JsonView({DiffIgnoreFields.class})
    @JsonProperty
    public Long getVersion()
    {
        return this.version;
    }

    @JsonProperty
    public void setVersion(Long version) {
        this.version = version;
    }

    public void mergeVersionFields(VersionBaseEntity dbObj)
    {
        this.createdTime = dbObj.createdTime;
    }
}
