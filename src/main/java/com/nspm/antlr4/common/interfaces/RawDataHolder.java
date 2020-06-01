package com.nspm.antlr4.common.interfaces;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/9
 */
public interface RawDataHolder<T extends Serializable> {

    public abstract T getOriginData();

    public abstract void setOriginData(T paramT);

    public abstract byte[] getRawData();

    public abstract void setRawData(byte[] paramArrayOfByte);

    public abstract String[] getRawDataIds();

    public abstract void setRawDataIds(String[] paramArrayOfString);

    public abstract Integer getRawDataSplitThreshold();

    public abstract String getCollectionName();
}
