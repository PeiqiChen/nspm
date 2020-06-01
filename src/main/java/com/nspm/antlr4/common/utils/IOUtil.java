package com.nspm.antlr4.common.utils;

import com.nspm.antlr4.exceptions.ZeeRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author luwei
 * @date 2019/9/17
 */
public class IOUtil {


    private static Logger logger = LoggerFactory.getLogger(IOUtil.class);
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String getFileNameWithTimestamp(String originalFilename) {
        StringBuilder newFilename = new StringBuilder();
        for (char c : originalFilename.toCharArray()) {
            if ((c == '.') || (c == '-') || (c == '_') || (Character.isJavaIdentifierPart(c))) {
                newFilename.append(c);
            } else {
                newFilename.append("_");
            }
        }
        String newFilenameStr = newFilename.toString();
        int i = newFilenameStr.lastIndexOf('.');
        String base = null;
        String ext = "";
        if (i != -1) {
            base = newFilenameStr.substring(0, i);
            ext = newFilenameStr.substring(i);
        } else {
            base = newFilenameStr;
        }
        if (base.length() > 200) {
            base = base.substring(0, 200);
        }
        String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss.SSS").format(new Date());

        int myInt = RANDOM.nextInt(1000);

        String randomStr = String.format("%03d", new Object[]{Integer.valueOf(myInt)});

        String result = base + "-" + timestamp + "-" + randomStr + ext;

        return result;
    }

    public static String getZeeFileName(String pluginId, String primaryId, String opUuid, String miscText, boolean isRouting) {
        if (ObjectUtil.isNullOrEmpty(pluginId)) {
            throw new IllegalArgumentException("pluginId is required.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(pluginId.trim());
        sb.append("_");
        if (!ObjectUtil.isNullOrEmpty(primaryId)) {
            sb.append(primaryId.trim());
        }
        sb.append("_");
        if (!ObjectUtil.isNullOrEmpty(opUuid)) {
            sb.append(opUuid);
        }
        sb.append("_");
        if (!ObjectUtil.isNullOrEmpty(miscText)) {
            sb.append(".");
            sb.append(opUuid);
        }
        if (isRouting) {
            sb.append(".rt.zee");
        } else {
            sb.append(".zee");
        }
        return sb.toString();
    }

    public static String toValidPathName(String name) {
        if (name == null) {
            return null;
        }
        StringBuilder newFilename = new StringBuilder();
        for (char c : name.toCharArray()) {
            if ((c == '.') || (c == '-') || (c == '_') || (Character.isJavaIdentifierPart(c))) {
                newFilename.append(c);
            } else {
                newFilename.append("_");
            }
        }
        return newFilename.toString();
    }

    public static byte[] encodeUtf8(CharSequence str) {
        if (str == null) {
            return null;
        }
        ByteBuffer bytes = StandardCharsets.UTF_8.encode(CharBuffer.wrap(str));
        byte[] result = new byte[bytes.remaining()];
        bytes.get(result, 0, result.length);
        return result;
    }

    public static String getFileContent(String file, int maxLimit)
            throws Exception {
        return getFileContent(file, maxLimit, StandardCharsets.UTF_8);
    }

    public static String getFileContent(String file, int maxLimit, Charset charset)
            throws Exception {
        BufferedReader reader = null;
        try {
            InputStreamReader myisr = new InputStreamReader(new FileInputStream(file), charset);
            reader = new BufferedReader(myisr);
            String text = getContent(reader, maxLimit);
            return text;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void saveStringToFile(String data, File file)
            throws Exception {
        //将内容写进file
        if(file == null){
            throw new ZeeRuntimeException("file not exists");
        }

        if(!file.exists()){
            file.createNewFile();
        }

        com.google.common.io.Files.write(data.getBytes(), file);

    }

    public static void copyResource(String resName, Path targetPath)
            throws Exception {
        InputStreamReader myreader = null;
        try {
            myreader = new InputStreamReader(IOUtil.class.getResourceAsStream(resName), StandardCharsets.UTF_8);

            String result = getContent(myreader, 0);
            Files.write(targetPath, Collections.singleton(result), StandardCharsets.UTF_8, new OpenOption[0]);
            return;
        } finally {
            if (myreader != null) {
                try {
                    myreader.close();
                } catch (IOException localIOException1) {
                }
            }
        }
    }

    public static String getContent(Reader reader, int maxLimit)
            throws Exception {
        BufferedReader myReader = new BufferedReader(reader);

        StringBuilder sb = new StringBuilder();

        char[] charBuffer = new char['?'];

        int charRead = 0;
        while ((charRead = myReader.read(charBuffer, 0, charBuffer.length)) > 0) {
            sb.append(charBuffer, 0, charRead);
            if ((maxLimit > 0) && (sb.length() > maxLimit)) {
                throw new ZeeRuntimeException("Failed to read all content. Max size limit exceeded:" + maxLimit);
            }
        }
        return sb.toString();
    }

    public static <T extends Serializable> List<byte[]> serializeAndZip(T object, int maxByteSize)
            throws IOException {
        if (object == null) {
            return null;
        }
        byte[] mybytes = serializeAndZip(object);

        return ObjectUtil.splitArray(mybytes, maxByteSize);
    }

    public static <T extends Serializable> byte[] serializeAndZip(T object)
            throws IOException {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream baos = null;
        GZIPOutputStream gzos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            gzos = new GZIPOutputStream(baos);
            oos = new ObjectOutputStream(gzos);

            oos.writeObject(object);
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (gzos != null) {
                gzos.close();
            }
            if (baos != null) {
                baos.close();
            }
        }
        byte[] bytes = null;
        if (baos != null) {
            bytes = baos.toByteArray();
        }
        return bytes;
    }

    public static <T extends Serializable> T unzipAndDeserialize(List<byte[]> bytesList)
            throws IOException, ClassNotFoundException {
        if ((bytesList == null) || (bytesList.isEmpty())) {
            return null;
        }
        byte[] mybytes = ObjectUtil.combineArray(bytesList);
        return unzipAndDeserialize(mybytes);
    }

    public static <T extends Serializable> T unzipAndDeserialize(byte[] bytes)
            throws IOException, ClassNotFoundException {
        if ((bytes == null) || (bytes.length == 0)) {
            return null;
        }
        ByteArrayInputStream bais = null;
        GZIPInputStream gzis = null;
        ObjectInputStream ois = null;

        T result = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            gzis = new GZIPInputStream(bais);
            ois = new ObjectInputStream(gzis);
            result = (T) ois.readObject();
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (gzis != null) {
                gzis.close();
            }
            if (bais != null) {
                bais.close();
            }
        }
        return result;
    }

    public static Map<String, List<String>> loadRegexFile(InputStream inputStream)
            throws Exception {
        return loadRegexFile(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    public static Map<String, List<String>> loadRegexFile(Reader reader)
            throws Exception {
        BufferedReader br = new BufferedReader(reader);

        Map<String, List<String>> result = new LinkedHashMap();

        String myline = null;
        while ((myline = br.readLine()) != null) {
            myline = myline.trim();
            if ((!myline.isEmpty()) &&

                    (!myline.startsWith("#"))) {
                int myi = myline.indexOf('=');
                if (myi <= 0) {
                    logger.warn("Format should be xxx=xxx, not: {}", myline);
                } else {
                    String key = myline.substring(0, myi).trim();
                    if ((key.contains(" ")) || (key.contains("\t"))) {
                        logger.warn("No space allowed in the property name: {}", key);
                    } else {
                        String val = myline.substring(myi + 1).trim();
                        if (val.isEmpty()) {
                            logger.warn("Empty value for property name {}. Entry ignored.", key);
                        } else {
                            List<String> oldVal = (List) result.get(key);
                            if (oldVal == null) {
                                oldVal = new ArrayList(4);
                                result.put(key, oldVal);
                            }
                            oldVal.add(val);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> loadRegexFile(String filePath, Class<?> clz) {
        InputStream mystr = null;
        Map<String, List<String>> result = null;
        try {
            mystr = clz.getResourceAsStream(filePath);
            result = loadRegexFile(mystr);
            return result;
        } catch (Exception e) {
            throw new ZeeRuntimeException("Failed to load regex file:" + filePath, e);
        } finally {
            if (mystr != null) {
                try {
                    mystr.close();
                } catch (IOException localIOException1) {
                }
            }
        }
    }
}
