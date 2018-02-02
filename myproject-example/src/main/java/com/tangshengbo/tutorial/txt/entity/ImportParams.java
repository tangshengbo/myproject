package com.tangshengbo.tutorial.txt.entity;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class ImportParams {

    /**
     * 表头行数,默认1
     */
    private int headRows = 0;

    private String encoding = "UTF-8";

    private String separatorChar = "|";

    private int startIndex;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getSeparatorChar() {
        return separatorChar;
    }

    public void setSeparatorChar(String separatorChar) {
        this.separatorChar = separatorChar;
    }

    public int getHeadRows() {
        return headRows;
    }

    public void setHeadRows(int headRows) {
        this.headRows = headRows;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public ImportParams(int headRows, String separatorChar, String encoding) {
        this.headRows = headRows;
        this.separatorChar = separatorChar;
        this.encoding = encoding;
        this.startIndex = headRows + 1;
    }
}
