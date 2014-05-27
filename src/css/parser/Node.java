/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */

package css.parser;

import java.util.LinkedList;

public class Node {
  String start_string;
  LinkedList<String> properties;
  String block_def;
  String bottom_block;

  public Node() {
    this.start_string = "";
    this.properties = new LinkedList<String>();
    this.block_def = "";
    this.bottom_block = "";
  }

  public Node(String start_string, LinkedList<String> properties, String block_def, String bottom_block) {
    this.start_string = start_string;
    this.properties = properties;
    this.block_def = block_def;
    this.bottom_block = bottom_block;
  }

  public String getStart_string() {
    return start_string;
  }

  public void setStart_string(String start_string) {
    this.start_string = start_string;
  }

  public LinkedList<String> getProperties() {
    return properties;
  }

  public void setProperties(LinkedList<String> properties) {
    this.properties = properties;
  }

  public String getBlock_def() {
    return block_def;
  }

  public void setBlock_def(String block_def) {
    this.block_def = block_def;
  }

  public String getBottom_block() {
    return bottom_block;
  }

  public void setBottom_block(String bottom_block) {
    this.bottom_block = bottom_block;
  }

}
