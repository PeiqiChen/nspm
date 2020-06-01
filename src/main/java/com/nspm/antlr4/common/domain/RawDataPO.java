package com.nspm.antlr4.common.domain;

import com.nspm.antlr4.common.domain.common.BaseEntity;
import com.nspm.antlr4.common.utils.ObjectUtil;
import com.nspm.antlr4.exceptions.ZeeRuntimeException;
import com.nspm.antlr4.common.utils.IOUtil;
import com.nspm.antlr4.common.utils.ObjectUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/17
 */
public class RawDataPO extends BaseEntity {

    private static final int MAX_SIZE = 12000000;

    protected byte[] data;

    protected RawDataPO() {
    }

    public RawDataPO(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }

    protected void setData(byte[] data) {
        this.data = data;
    }

    public static <T extends Serializable> List<RawDataPO> serializeAndZip(T object) {
        return serializeAndZip(object, 12000000);
    }

    public static <T extends Serializable> List<RawDataPO> serializeAndZip(T object, int sizeLimit) {
        try {
            if (object == null) {
                return null;
            }

            List<byte[]> bytesList = IOUtil.serializeAndZip(object, sizeLimit);

            List<RawDataPO> result = new ArrayList(bytesList.size());
            for (byte[] myobj : bytesList) {
                result.add(new RawDataPO(myobj));
            }

            return result;
        } catch (IOException e) {
            throw new ZeeRuntimeException(e);
        }
    }

    public static <T extends Serializable, S extends RawDataPO> List<S> serializeAndZip(T object, Class<S> clz) {
        try {
            if (object == null) {
                return null;
            }

            List<byte[]> bytesList = IOUtil.serializeAndZip(object, 12000000);

            List<S> result = new ArrayList(bytesList.size());
            for (byte[] myobj : bytesList) {
                S val = (S) ObjectUtil.newInstance(clz);
                val.setData(myobj);

                result.add(val);
            }

            return result;
        } catch (Exception e) {
            throw new ZeeRuntimeException(e);
        }
    }

    public static <T extends Serializable> T unzipAndDeserialize(List<? extends RawDataPO> rawList) {
        try {
            if ((rawList == null) || (rawList.isEmpty())) {
                return null;
            }

            List<byte[]> bytesList = new ArrayList(rawList.size());
            for (RawDataPO myobj : rawList) {
                bytesList.add(myobj.getData());
            }

            return IOUtil.unzipAndDeserialize(bytesList);
        } catch (ClassNotFoundException | IOException e) {
            throw new ZeeRuntimeException(e);
        }
    }
}
