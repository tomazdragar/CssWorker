/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */

package css.parser;

public class CssProperty implements Comparable<CssProperty> {

    private String key = "";
    private String value = "";
    private String priority = "";

    public CssProperty(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public CssProperty(String key, String value, String priority) {
        this.key = key;
        this.value = value;
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(CssProperty n) {
        int lastCmp = this.key.compareTo(n.key);
        return lastCmp;
    }
}
