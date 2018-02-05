package com.tangshengbo.tutorial.txt;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class ImportParams {

    private int headRows = 0;

    private String encoding = "UTF-8";

    private String separatorChar;

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
    }

    public ImportParams(int headRows, String separatorChar) {
        this.headRows = headRows;
        this.separatorChar = separatorChar;
    }

    public ImportParams(String separatorChar) {
        this.separatorChar = separatorChar;
    }
}
