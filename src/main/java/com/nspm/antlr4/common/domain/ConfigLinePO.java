package com.nspm.antlr4.common.domain;

import com.nspm.antlr4.common.domain.obj.IntegerRangePO;
import com.nspm.antlr4.common.domain.obj.IntegerRangePO;
import com.nspm.antlr4.exceptions.ZeeRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nspm.antlr4.exceptions.ZeeRuntimeException;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel("配置行")
@Data
public class ConfigLinePO implements Serializable, Comparable<ConfigLinePO> {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private String fileName = null;
    @JsonIgnore
    private int[] lineNumbers;


    public ConfigLinePO() {
    }

    public ConfigLinePO(int lineNumber) {
        populateLineNumbers(lineNumber);
    }

    public ConfigLinePO(int lineNumberLow, int lineNumberHigh) {
        if (lineNumberLow > lineNumberHigh) {
            throw new IllegalArgumentException("LineNumberHigh:" + lineNumberHigh + " should be greater or equal to lineNumberLow:" + lineNumberLow);
        }

        if (lineNumberLow == lineNumberHigh) {
            populateLineNumbers(lineNumberLow);
        } else {
            populateLineNumbers(lineNumberLow, lineNumberHigh);
        }
    }

    public ConfigLinePO(String fileName, int lineNumber) {
        this.fileName = fileName;
        populateLineNumbers(lineNumber);
    }

    public ConfigLinePO(String fileName, int lineNumberLow, int lineNumberHigh) {
        if (lineNumberLow > lineNumberHigh) {
            throw new IllegalArgumentException("LineNumberHigh:" + lineNumberHigh + " should be greater or equal to lineNumberLow:" + lineNumberLow);
        }

        this.fileName = fileName;
        if (lineNumberLow == lineNumberHigh) {
            populateLineNumbers(lineNumberLow);
        } else {
            populateLineNumbers(lineNumberLow, lineNumberHigh);
        }
    }

    private void populateLineNumbers(int lineNumber) {
        this.lineNumbers = new int[1];
        this.lineNumbers[0] = lineNumber;
    }

    private void populateLineNumbers(int low, int high) {
        this.lineNumbers = new int[2];
        this.lineNumbers[0] = low;
        this.lineNumbers[1] = high;
    }

    public ConfigLinePO(List<IntegerRangePO> lineRanges) {
        this(null, lineRanges);
    }

    public ConfigLinePO(String fileName, List<IntegerRangePO> lineRanges) {
        if (lineRanges == null) {
            throw new IllegalArgumentException("lineRanges can not be null");
        }

        this.fileName = fileName;
        this.lineNumbers = new int[lineRanges.size() * 2];
        int i = 0;
        for (IntegerRangePO myrange : lineRanges) {
            this.lineNumbers[(i++)] = myrange.getStart();
            this.lineNumbers[(i++)] = myrange.getEnd();
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public List<IntegerRangePO> getLines() {
        if (this.lineNumbers == null) {
            return null;
        }

        if (this.lineNumbers.length == 1) {
            return Collections.singletonList(new IntegerRangePO(this.lineNumbers[0]));
        }

        List<IntegerRangePO> result = new ArrayList(this.lineNumbers.length / 2);

        int i = 0;
        while (i < this.lineNumbers.length) {
            IntegerRangePO myRange = new IntegerRangePO(this.lineNumbers[(i++)], this.lineNumbers[(i++)]);
            result.add(myRange);
        }
        return result;
    }

    public void addLineRange(int low, int high) {
        if (hasRange(new IntegerRangePO(low, high))) {
            return;
        }

        if (this.lineNumbers == null) {
            this.lineNumbers = new int[]{low, high};
            return;
        }

        if (this.lineNumbers.length == 1) {
            this.lineNumbers = new int[]{this.lineNumbers[0], this.lineNumbers[0]};
        }

        /*     */
        int last = this.lineNumbers[(this.lineNumbers.length - 1)];
        if (last == low - 1) {
            this.lineNumbers[(this.lineNumbers.length - 1)] = high;
        } else {
            int[] newLines = new int[this.lineNumbers.length + 2];
            System.arraycopy(this.lineNumbers, 0, newLines, 0, this.lineNumbers.length);
            newLines[this.lineNumbers.length] = low;
            newLines[(this.lineNumbers.length + 1)] = high;
            this.lineNumbers = newLines;
        }
    }

    /*     */
    /*     */
    public void addLine(int lineNumber) {
        if (hasRange(new IntegerRangePO(lineNumber))) {
            return;
        }

        if (this.lineNumbers == null) {
            this.lineNumbers = new int[]{lineNumber};
            return;
        }

        if (this.lineNumbers.length == 1) {
            this.lineNumbers = new int[]{this.lineNumbers[0], this.lineNumbers[0]};
        }

        /*     */
        int last = this.lineNumbers[(this.lineNumbers.length - 1)];
        if (last == lineNumber - 1) {
            this.lineNumbers[(this.lineNumbers.length - 1)] = lineNumber;
        } else {
            int[] newLines = new int[this.lineNumbers.length + 2];
            System.arraycopy(this.lineNumbers, 0, newLines, 0, this.lineNumbers.length);
            newLines[this.lineNumbers.length] = lineNumber;
            newLines[(this.lineNumbers.length + 1)] = lineNumber;
            this.lineNumbers = newLines;
        }
    }

    public ConfigLinePO combine(ConfigLinePO other) {
        List<IntegerRangePO> mylist = new ArrayList();

        mylist.addAll(getLines());
        mylist.addAll(other.getLines());

        mylist = sortAndCompressIntRanges(mylist);

        ConfigLinePO result = new ConfigLinePO(this.fileName, mylist);

        return result;
    }

    private List<IntegerRangePO> sortAndCompressIntRanges(List<IntegerRangePO> ranges) {
        Collections.sort(ranges);

        List<IntegerRangePO> result = new ArrayList(ranges.size());

        int index = 0;
        IntegerRangePO firstRange = (IntegerRangePO) ranges.get(0);
        int currentStart = firstRange.getStart();
        int currentStartIndex = 0;
        int currentEnd = firstRange.getEnd();
        int currentEndIndex = 0;
        index++;

        while (index < ranges.size()) {
            IntegerRangePO myRange = (IntegerRangePO) ranges.get(index);
            if (myRange.getStart() <= currentEnd) {
                if (myRange.getEnd() <= currentEnd) {
                    index++;
                } else {
                    currentEnd = myRange.getEnd();
                    currentEndIndex = index;
                    index++;
                }
            } else if (myRange.getStart() == currentEnd + 1) {
                currentEnd = myRange.getEnd();
                currentEndIndex = index;
                index++;
            } else {
                if (currentStartIndex == currentEndIndex) {
                    result.add(ranges.get(currentStartIndex));
                } else {
                    result.add(new IntegerRangePO(currentStart, currentEnd));
                }
                currentStart = myRange.getStart();
                currentStartIndex = index;
                currentEnd = myRange.getEnd();
                currentEndIndex = index;
                index++;
            }
        }

        if (currentStartIndex == currentEndIndex) {
            result.add(ranges.get(currentStartIndex));
        } else {
            result.add(new IntegerRangePO(currentStart, currentEnd));
        }

        return result;
    }

    public boolean hasRange(IntegerRangePO range) {
        if (range == null) {
            return false;
        }

        List<IntegerRangePO> mylist = getLines();
        if (mylist == null) {
            return false;
        }

        Set<IntegerRangePO> myset = new HashSet(mylist);
        if (myset.contains(range)) {
            return true;
        }

        return false;
    }

    @JsonValue
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.fileName != null) {
            sb.append(this.fileName);
            sb.append(" ");
        }
        if (getLines() == null) {
            sb.append("[]");
        } else {
            sb.append("[");
            for (IntegerRangePO myRange : getLines()) {
                int start = myRange.getStart();
                int end = myRange.getEnd();
                if (start == end) {
                    sb.append(start);
                } else {
                    sb.append(start);
                    sb.append("-");
                    sb.append(end);
                }
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = 31 * result + (this.fileName == null ? 0 : this.fileName.hashCode());
        result = 31 * result + Arrays.hashCode(this.lineNumbers);
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
        ConfigLinePO other = (ConfigLinePO) obj;
        if (this.fileName == null) {
            if (other.fileName != null) {
                return false;
            }
        } else if (!this.fileName.equals(other.fileName)) {
            return false;
        }
        if (!Arrays.equals(this.lineNumbers, other.lineNumbers)) {
            return false;
        }
        return true;
    }


    @JsonCreator
    public static ConfigLinePO parseConfigLine(String value) {
        if ((value == null) || (value.trim().isEmpty())) {
            return null;
        }

        String msg = "Not a valid format of config line numbers. Expected format is: 'filename [xx-xx, xx, ... ]', where filename is optional, xx is number. xx-xx is the range. When it is only one number, it is abbreviated as xx.";

        int myi = value.indexOf('[');
        if ((myi == -1) || (!value.endsWith("]"))) {
            throw new ZeeRuntimeException(msg);
        }

        String filename = null;
        if (myi != 0) {
            filename = value.substring(0, myi).trim();
        }
        String linenumbers = value.substring(myi + 1, value.length() - 1);
        String[] myranges = linenumbers.split(",");

        List<IntegerRangePO> mylist = new ArrayList();
        for (String myr : myranges) {
            myr = myr.trim();
            String[] myints = myr.split("-");
            int start = Integer.parseInt(myints[0]);
            if (myints.length == 2) {
                int end = Integer.parseInt(myints[1]);
                mylist.add(new IntegerRangePO(start, end));
            } else {
                mylist.add(new IntegerRangePO(start));
            }
        }

        return new ConfigLinePO(filename, mylist);
    }

    @Override
    public int compareTo(ConfigLinePO that) {
        if (that == null) {
            return 1;
        }

        if ((this.fileName != null) && (that.fileName != null)) {
            return this.fileName.compareTo(that.fileName);
        }

        if ((this.fileName == null) && (that.fileName != null)) {
            return -1;
        }

        if ((this.fileName != null) && (that.fileName == null)) {
            return 1;
        }

        Integer thisInt = null;
        Integer thatInt = null;
        if ((this.lineNumbers != null) && (this.lineNumbers.length > 0)) {
            thisInt = Integer.valueOf(this.lineNumbers[0]);
        }
        if ((that.lineNumbers != null) && (that.lineNumbers.length > 0)) {
            thatInt = Integer.valueOf(that.lineNumbers[0]);
        }

        if ((thisInt != null) && (thatInt != null)) {
            return thisInt.compareTo(thatInt);
        }
        if ((thisInt != null) && (thatInt == null)) {
            return 1;
        }
        if ((thisInt == null) && (thatInt != null)) {
            return -1;
        }
        return 0;
    }
}
