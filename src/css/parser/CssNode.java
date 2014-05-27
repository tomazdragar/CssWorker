/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */

package css.parser;

import java.util.LinkedList;

public class CssNode implements Comparable<CssNode> {

    private String start_string = "";
    private int start = 0;
    private int end = 0;
    private String selector = "";
    private LinkedList<String> comments = new LinkedList<String>();
    private LinkedList<CssProperty> properties = new LinkedList<CssProperty>();
    private LinkedList<String> properties1 = new LinkedList<String>();
    private String tree_properties;

    public CssNode(String start_string, int start, int end, String selector, LinkedList<CssProperty> properties) {
        this.selector = selector;
        this.properties = properties;
        this.start = start;
        this.end = end;
        this.start_string = start_string;
    }

    public void setProperties(LinkedList<CssProperty> properties) {
        this.properties = properties;
    }

    public String getStart_string() {
        return start_string;
    }

    public void setStart_string(String start_string) {
        this.start_string = start_string;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public LinkedList<CssProperty> getProperties() {
        return properties;
    }

    public String getSelector() {
        return selector;
    }

    public LinkedList<String> getComments() {
        return comments;
    }

    public void setComments(LinkedList<String> comments) {
        this.comments = comments;
    }

    public LinkedList<String> getProperties1() {
        return properties1;
    }

    public void setProperties1(LinkedList<String> properties1) {
        this.properties1 = properties1;
    }

    public String getTree_properties() {
        return tree_properties;
    }

    public void setTree_properties(String tree_properties) {
        this.tree_properties = tree_properties;
    }

    
    
    @Override
    public int compareTo(CssNode o) {
        int lastCmp = this.selector.compareTo(o.selector);
        return lastCmp;
    }
}
