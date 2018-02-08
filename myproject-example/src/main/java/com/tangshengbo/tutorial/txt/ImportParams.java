package com.tangshengbo.tutorial.txt;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class ImportParams {

    private int headRows = 0;

    private String encoding = "UTF-8";

    private String separator;

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
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

    public ImportParams(int headRows, String separator, String encoding) {
        this.headRows = headRows;
        this.separator = separator;
        this.encoding = encoding;
    }

    public ImportParams(int headRows, String separator) {
        this.headRows = headRows;
        this.separator = separator;
    }

    public ImportParams(String separator) {
        this.separator = separator;
    }
}
