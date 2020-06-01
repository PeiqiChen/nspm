package com.nspm.antlr4.common.domain;

import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.common.interfaces.RawDataHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luwei
 * @date 2019/9/11
 */
@Document(collection = "DEVICE_RAW_CONFIG")
@JsonPropertyOrder({"id"})
@Data
public class DeviceRawConfigPO extends COWAuditBaseEntity implements RawDataHolder<LinkedHashMap<String, String>> {
    public static final int CONFIG_MAX_SIZE = 104857600;

    @Transient
    private LinkedHashMap<String, String> rawTexts;
    @JsonIgnore
    private byte[] rawData;
    @JsonIgnore
    private String[] rawDataIds;
    private String origFileName;

    private String deviceUuid;

    public DeviceRawConfigPO() {
    }

    public DeviceRawConfigPO(String rawText) {
        this.rawTexts = new LinkedHashMap();
        this.rawTexts.put("", rawText);
    }

    public String getOrigFileName() {
        return this.origFileName;
    }

    public void setOrigFileName(String origFileName) {
        this.origFileName = origFileName;
    }

    public String getFirstRawText() {
        if (this.rawTexts == null) {
            return null;
        }

        String firstKey = (String) this.rawTexts.keySet().iterator().next();
        return (String) this.rawTexts.get(firstKey);
    }

    public void setRawText(String rawText) {
        if (this.rawTexts == null) {
            this.rawTexts = new LinkedHashMap();
        }

        this.rawTexts.put("", rawText);
    }

    public LinkedHashMap<String, String> getRawTexts() {
        return this.rawTexts;
    }

    public void setRawTexts(LinkedHashMap<String, String> rawTexts) {
        this.rawTexts = rawTexts;
    }

    public String getRawText(String fileName) {
        if (this.rawTexts == null) {
            return null;
        }

        if (fileName == null) {
            fileName = "";
        }

        return (String) this.rawTexts.get(fileName);
    }

    public void addRawText(String fileName, String text) {
        if (this.rawTexts == null) {
            this.rawTexts = new LinkedHashMap();
        }
        this.rawTexts.put(fileName, text);
    }

    @Override
    public LinkedHashMap<String, String> getOriginData() {
        return this.rawTexts;
    }

    @Override
    public void setOriginData(LinkedHashMap<String, String> data) {
        this.rawTexts = data;
    }

    @Override
    public byte[] getRawData() {
        return this.rawData;
    }

    @Override
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    @Override
    public String[] getRawDataIds() {
        return this.rawDataIds;
    }

    @Override
    public void setRawDataIds(String[] rawDataIds) {
        this.rawDataIds = rawDataIds;
    }

    @Override
    public Integer getRawDataSplitThreshold() {
        return null;
    }

    @Override
    public String getCollectionName() {
        return "DEVICE_RAW_CONFIG";
    }

    public boolean isRawTextSame(DeviceRawConfigPO that) {
        if (that == null) {
            return false;
        }

        if (this.rawTexts == null) {
            if (that.rawTexts != null) {
                return false;
            }
        } else if (!this.rawTexts.equals(that.rawTexts)) {
            return false;
        }

        return true;
    }

    public String getRawTextAsOne() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> mye : this.rawTexts.entrySet()) {
            String key = (String) mye.getKey();
            String val = (String) mye.getValue();

            if (key == null) {
                sb.append(val);
            } else {
                sb.append("<<" + key + ">>");
                sb.append("\n");
                sb.append(val);
            }
        }

        return sb.toString();
    }


}
