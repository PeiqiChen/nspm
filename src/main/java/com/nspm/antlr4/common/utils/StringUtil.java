package com.nspm.antlr4.common.utils;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.DeviceRawConfigPO;
import com.nspm.antlr4.common.domain.obj.IntegerRangePO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class StringUtil {
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);


    public static final String SPECIAL_CHAR = "~!@#$%^&*()_+`-={}|[]\\:\";'<>?,./";


    public static boolean isValidStrCaseOne(CharSequence cs) {
        if ((cs == null) || (cs.length() == 0)) {
            return false;
        }

        int count = cs.length();

        for (int i = 0; i < count; i++) {
            char mych = cs.charAt(i);

            if (!Character.isLetterOrDigit(mych)) {

                if ("~!@#$%^&*()_+`-={}|[]\\:\";'<>?,./".indexOf(mych) == -1) {

                    return false;
                }
            }
        }
        return true;
    }

    public static String fatalMessage(String msg) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\n");
        sb.append("##########################   << FATAL >>  #############################\n");
        sb.append("###  \n");
        sb.append("###   ").append(msg).append("\n");
        sb.append("###  \n");
        sb.append("#######################################################################\n\n");

        return sb.toString();
    }

    public static String getStackTrace(Throwable t) {
        StringWriter mysw = new StringWriter();
        PrintWriter writer = new PrintWriter(mysw);

        t.printStackTrace(writer);

        String result = mysw.toString();
        writer.close();

        return result;
    }


    public static String removeLastLine(String data, boolean removeLastNewline) {
        if (data == null) {
            return null;
        }

        int beforeNewline = -1;

        int myi = data.lastIndexOf("\r\n");
        if (myi > 0) {
            beforeNewline = myi;
            myi += 2;
        }
        int myj = data.lastIndexOf("\n");
        if ((myj > 0) && (myj >= myi)) {
            beforeNewline = myj;
            myi = myj + 1;
        }
        myj = data.lastIndexOf("\r");
        if ((myj > 0) && (myj >= myi)) {
            beforeNewline = myj;
            myi = myj + 1;
        }

        String result = null;
        if (myi > 0) {
            if (removeLastNewline) {
                result = data.substring(0, beforeNewline);
            } else {
                result = data.substring(0, myi);
            }
        } else {
            result = "";
        }

        return result;
    }


    public static String getLastLine(String data) {
        if (data == null) {
            return null;
        }


        int myi = data.lastIndexOf("\r\n");
        if (myi > 0) {
            myi += 2;
        }
        int myj = data.lastIndexOf("\n");
        if ((myj > 0) && (myj >= myi)) {
            myi = myj + 1;
        }
        myj = data.lastIndexOf("\r");
        if ((myj > 0) && (myj >= myi)) {
            myi = myj + 1;
        }

        if (myi > 0) {
            return data.substring(myi);
        }

        return data;
    }


    public static String rightTrim(String data) {
        if (data == null) {
            return null;
        }

        int index = data.length() - 1;

        while ((index >= 0) && (data.charAt(index) <= ' ')) {
            index--;
        }

        if (index < 0) {
            return "";
        }
        return data.substring(0, index + 1);
    }

    public static ConcurrentHashMap<String, List<String>> convertRawConfigToLineMap(DeviceRawConfigPO deviceRawConfig) {
        ConcurrentHashMap<String, List<String>> result = new ConcurrentHashMap();

        if (deviceRawConfig == null) {
            return result;
        }

        LinkedHashMap<String, String> mymap = deviceRawConfig.getRawTexts();

        for (String name : mymap.keySet()) {
            String mytxt = (String) mymap.get(name);
            if (mytxt != null) {

                String[] myarr = mytxt.split("\n");

                List<String> mylist = Arrays.asList(myarr);

                result.put(name, mylist);
            }
        }
        return result;
    }


    public static String getConfigLines(Map<String, List<String>> linesMap, ConfigLinePO lineNumbers, boolean toNormalize, boolean toOneLine) {
        if ((linesMap == null) || (lineNumbers == null)) {
            return null;
        }


        String fileName = lineNumbers.getFileName();
        if (fileName == null) {
            fileName = "";
        }
        List<String> lineList = (List) linesMap.get(fileName);

        if (lineList == null) {
            return null;
        }

        int lineCount = lineList.size();

        List<IntegerRangePO> mylines = lineNumbers.getLines();

        StringBuilder sb = new StringBuilder(2000);

        for (IntegerRangePO myRange : mylines) {
            for (int i = myRange.getStart(); i <= myRange.getEnd(); i++) {
                if ((i > lineCount) || (i < 1)) {
                    logger.warn("Line number [{}-{}] is out of range [1-{}].", new Object[]{
                            Integer.valueOf(myRange.getStart()), Integer.valueOf(myRange.getEnd()), Integer.valueOf(lineCount)});
                    break;
                }
                String myline = (String) lineList.get(i - 1);
                if (myline != null) {

                    if (toNormalize) {
                        myline = myline.trim();
                    }

                    sb.append(myline);

                    if (toNormalize) {
                        sb.append('\n');
                    } else if (toOneLine) {
                        sb.append(' ');
                    } else {
                        sb.append('\n');
                    }
                }
            }
        }

        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }


    public static String getTextAtLine(DeviceRawConfigPO rawConfig, ConfigLinePO lineNumbers, boolean toNormalize, boolean toOneLine) {
        if ((rawConfig == null) || (lineNumbers == null)) {
            return null;
        }

        String fileName = lineNumbers.getFileName();
        String rawText = rawConfig.getRawText(fileName);

        return getTextAtLine(rawText, lineNumbers, toNormalize, toOneLine);
    }


    public static String getConfigLines(Map<String, List<String>> linesMap, ConfigLinePO lineNumbers, int limit) {
        if ((linesMap == null) || (lineNumbers == null)) {
            return null;
        }

        String mys = getConfigLines(linesMap, lineNumbers, true, false);

        if ((limit > 0) &&
                (mys.length() > limit)) {
            mys = mys.substring(0, limit);
            mys = mys + " ...... ";
        }

        return mys;
    }

    public static String getTextAtLine(DeviceRawConfigPO rawConfig, ConfigLinePO lineNumbers, int limit) {
        if ((rawConfig == null) || (lineNumbers == null)) {
            return null;
        }

        String fileName = lineNumbers.getFileName();
        String rawText = rawConfig.getRawText(fileName);

        String mys = getTextAtLine(rawText, lineNumbers, true, false);

        if ((limit > 0) &&
                (mys.length() > limit)) {
            mys = mys.substring(0, limit);
            mys = mys + " ...... ";
        }


        return mys;
    }


    private static String getTextAtLine(String rawText, ConfigLinePO lineNumbers, boolean toNormalize, boolean toOneLine) {
        if ((rawText == null) || (lineNumbers == null)) {
            return null;
        }


        List<IntegerRangePO> mylines = lineNumbers.getLines();

        List<Integer> intList = new ArrayList();
        for (IntegerRangePO myRange : mylines) {
            for (int i = myRange.getStart(); i <= myRange.getEnd(); i++) {
                intList.add(Integer.valueOf(i));
            }
        }

        StringBuilder sb = new StringBuilder();


        String[] mylist = rawText.split("\n");
        String line = null;

        for (Integer myint : intList) {
            if ((myint.intValue() >= 0) && (myint.intValue() < mylist.length)) {
                line = mylist[myint.intValue()];
                if (line != null) {

                    if (toNormalize) {
                        line = line.trim();
                    }
                    sb.append(line);


                    if (toNormalize) {
                        sb.append('\n');
                    } else if (toOneLine) {
                        sb.append(' ');
                    } else {
                        sb.append(System.lineSeparator());
                    }
                }
            }
        }

        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }


    public static boolean twoStringEquals(String one, String two) {
        if ((one == null) && (two == null)) {
            return true;
        }

        if (one != null) {
            return one.equals(two);
        }


        return false;
    }

    public static List<String> textToLines(String text) {
        if (text == null) {
            return Collections.emptyList();
        }


        String[] myarr = text.split("\n");

        return Arrays.asList(myarr);
    }

    public static List<String> splitTextByLineStart(String lineStartRegex, String text) {
        if (text == null) {
            return null;
        }

        Pattern myp = Pattern.compile("^" + lineStartRegex + ".*$", 8);

        String[] mylist = myp.split(text, -1);

        return Arrays.asList(mylist);
    }


    //追加对象到sb
    public static void appendObj(StringBuilder sb, Object obj) {

        if (obj == null) {
            return;
        }
        if (sb.length() != 0) {
            //多个对象，中间用空格隔开
            sb.append(" ");
        }

        String zoneList;
        if (obj instanceof Object[]) {
            zoneList = Arrays.asList(((Object[]) obj)).toString();
            if (zoneList.length() >= 2) {
                zoneList = zoneList.substring(1, zoneList.length() - 1);
                if (!zoneList.isEmpty()) {
                    sb.append(zoneList);
                }
            }
        } else if (obj instanceof int[]) {
            int[] zoneNumArr = (int[]) obj;
            if (zoneNumArr.length > 0) {

                for (int m = 0; m < zoneNumArr.length; m++) {
                    int num = zoneNumArr[m];
                    sb.append(num);
                    //多个域数字，从逗号隔开
                    sb.append(",");
                }

                //删除最后一个逗号
                sb.deleteCharAt(sb.length() - 1);
            }
        } else if (obj instanceof Collection) {
            zoneList = obj.toString();
            if (zoneList.length() >= 2) {
                zoneList = zoneList.substring(1, zoneList.length() - 1);
                if (!zoneList.isEmpty()) {
                    sb.append(zoneList);
                }
            }
        } else if (obj instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            sb.append(sdf.format(obj));
        } else {
            sb.append(obj.toString());
        }

    }


    //集合转换成str
    public static String getCollectionStr(Collection<String> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }

        String str = collection.toString();
        str = str.substring(1, str.length() - 1);
        return str.isEmpty() ? null : str;

    }

    //数组转换成str
    public static String getArrStr(String[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        String str = Arrays.asList(arr).toString();
        str = str.substring(1, str.length() - 1);
        return str.isEmpty() ? null : str;
    }
}


