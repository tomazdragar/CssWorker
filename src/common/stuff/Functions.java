/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */

package common.stuff;

import com.gaurav.tree.LinkedTree;
import com.gaurav.tree.NodeNotFoundException;
import css.parser.CssNode;
import css.parser.CssProperty;
import css.parser.Node;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {

  public static LinkedList<CssNode> getMergedCss(LinkedList<CssNode> css_html_nodes, LinkedList<CssNode> css_css_nodes) {
    // Create new list
    // Initial values are set to current css_css_nodes
    LinkedList<CssNode> css_merged_nodes = css_css_nodes;
    
    // Insert objects from css_html_nodes into newly created list
    // Adding only those objects which doesn't exist in css_css_nodes list
    boolean in_list;
    for (CssNode cssHtmlNode : css_html_nodes) {
      in_list = false;
      for (CssNode cssCssNode : css_css_nodes) {
        // If doesn't exist
        if (cssHtmlNode.getSelector().equals(cssCssNode.getSelector())) {
          in_list = true;
        }
      }
      // Add node if it is not in list
      if (in_list == false) {
        css_merged_nodes.add(cssHtmlNode);
      }
    }
    return css_merged_nodes;
  }

  public static String cleanClasses(String text) {
    LinkedList<String> string_list = splitWordsToList(text.trim());
    String clean_text = "";
    for (String string : string_list) {
      clean_text = clean_text + string + " ";
    }
    return clean_text;
  }

  public static LinkedList<String> splitWordsToList(String text) {
    LinkedList<String> string_list = new LinkedList<String>();
    String text_tmp = text.trim();
    String[] text_arr = text_tmp.split("\\s+");
    string_list.addAll(Arrays.asList(text_arr));
    return string_list;
  }

  
  public static boolean inList(String text) {
    Boolean inList = false;
    for (String string : StaticVariables.css_paths) {
      if (string.equals(text)) {
        inList = true;
      }
    }
    return inList;
  }

  public static void print_css_nodes(LinkedList<CssNode> css_nodes) {
    for (CssNode cssNode : css_nodes) {
      System.out.println(cssNode.getSelector() + " {");
      for (CssProperty property : cssNode.getProperties()) {
        if (property.getPriority().equals("")) {
          System.out.println("    " + property.getKey() + ": " + property.getValue() + ";");
        } else {
          System.out.println("    " + property.getKey() + ": " + property.getValue() + " !" + property.getPriority() + ";");
        }
      }
      System.out.println("}");
      System.out.println();
      System.out.println();
    }
  }

  public static String get_css_nodes_1(LinkedList<CssNode> css_nodes) {
    String css_nodes_string = "";
    for (CssNode cssNode : css_nodes) {
      css_nodes_string += cssNode.getSelector() + " {" + "\n";
      for (CssProperty property : cssNode.getProperties()) {
        if (property.getPriority().equals("")) {
          css_nodes_string += "    " + property.getKey() + ": " + property.getValue() + ";" + "\n";
        } else {
          css_nodes_string += "    " + property.getKey() + ": " + property.getValue() + " !" + property.getPriority() + ";" + "\n";
        }
      }
      css_nodes_string += "}" + "\n";
      css_nodes_string += "\n";
    }
    return css_nodes_string;
  }

  public static String get_css_nodes_2(LinkedList<CssNode> css_nodes) {
    String css_nodes_string = "";
    for (CssNode cssNode : css_nodes) {
      css_nodes_string += cssNode.getSelector() + " {";
      for (CssProperty property : cssNode.getProperties()) {
        if (property.getPriority().equals("")) {
          css_nodes_string += " " + property.getKey() + ": " + property.getValue() + ";";
        } else {
          css_nodes_string += " " + property.getKey() + ": " + property.getValue() + " !" + property.getPriority() + ";";
        }
      }
      css_nodes_string += " }";
      css_nodes_string += "\n";
    }
    return css_nodes_string;
  }

  public static String getFileText(String filePath) {
    String fileText = "";
    File file = new File(filePath);
    StringBuilder contents = new StringBuilder();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String text = null;
      // Repeat until all lines are read
      while ((text = reader.readLine()) != null) {
        contents.append(text).append(System.getProperty("line.separator"));
      }
    } catch (FileNotFoundException e) {
      System.out.println("No such file or directory");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // Show file contents here
    fileText = contents.toString();
    return fileText;
  }

  public static void writeTextToFile(String file_path, String file_text) {
    Writer writer = null;
    try {
      String text = file_text;
      File file = new File(file_path);
      writer = new BufferedWriter(new FileWriter(file));
      writer.write(text);
    } catch (FileNotFoundException e) {
      System.out.println("File not found writeTextToFile() 1");
    } catch (IOException e) {
      System.out.println("File not found writeTextToFile() 1");
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        System.out.println("File not found writeTextToFile() 1");
      }
    }
  }

  public static LinkedList<CssNode> sortAttributes(LinkedList<CssNode> css_nodes) {
    for (CssNode cssNode : css_nodes) {
      Collections.sort(cssNode.getProperties());
    }
    return css_nodes;
  }

  public static void addCustomProperty() {
    for (CssNode cssNode : StaticVariables.css_css_nodes) {
      if (cssNode.getProperties().isEmpty()) {
        cssNode.getProperties().add(new CssProperty("background-color", "transparent"));
      }
    }
  }

  public static void removeCustomProperty() {
    for (CssNode cssNode : StaticVariables.css_css_nodes) {
      CssProperty property_to_delete = null;
      for (CssProperty cssProperty : cssNode.getProperties()) {
        if (cssProperty.getKey().equals("background-color") && cssProperty.getValue().equals("transparent")) {
          property_to_delete = cssProperty;
        }
      }
      if (property_to_delete != null) {
        cssNode.getProperties().remove(property_to_delete);
      }
    }
  }

  public static void removeEmptyNodes() {
    LinkedList<CssNode> list_nodes_to_delete = new LinkedList<CssNode>();
    for (CssNode cssNode : StaticVariables.css_css_nodes) {
      if (cssNode.getTree_properties().isEmpty()) {
        list_nodes_to_delete.add(cssNode);
      }
    }
    for (CssNode cssNode : list_nodes_to_delete) {
      StaticVariables.css_css_nodes.remove(cssNode);
    }
  }

  public static void findString(String text, String patern) {
    Pattern pattern = Pattern.compile(patern);
    Matcher matcher = pattern.matcher(text);
    String matched_string = "";
    String css_url = "";
    while (matcher.find()) {
      System.out.print("Start index: " + matcher.start());
      System.out.print(" End index: " + matcher.end() + " ");
      matched_string = matcher.group();
      css_url = matched_string.replaceFirst("url", "").replaceFirst("href", "").replaceFirst("=", "").replaceFirst("\\(", "").replaceFirst("\\)", "").replaceAll("\"", "").trim();
      String css_result_url = css_url;
      if (css_url.startsWith("/")) {
        css_result_url = StaticVariables.html_url + css_url;
      }
      System.out.println(css_result_url);
      StaticVariables.css_urls.add(css_result_url);
    }
  }

  public static String printTree(LinkedTree<Node> tree, Node node, int tab) throws NodeNotFoundException {
    String result = "";
    String string_tab = "";

    if (StaticVariables.css_format == "block") {
      for (int i = 0; i <= tab; i++) {
        string_tab += "  ";
      }
      // Iterate over node children
      for (Iterator it = tree.children(node).iterator(); it.hasNext();) {
        // For every child print properties and block_def
        Node current = (Node) it.next();
        LinkedList<String> properties = current.getProperties();
        String block_def = current.getBlock_def();
        
        // Print properties
        for (int i = 0; i < properties.size(); i++) {
          String property = properties.get(i);
          result = result + string_tab + property + "\n";
        }
        
        // Print selector for the next block
        if (!block_def.isEmpty()) {
          String newline_string = "";
          if (current.getBottom_block().trim().isEmpty()) {
            newline_string = "\n";
          }
          result = result + string_tab + block_def.trim() + newline_string;
        }

        // If current child has children, then repeat the procedure
        if (!tree.children(current).isEmpty()) {
          result = result + " {\n";
          result = result + printTree(tree, current, tab + 1);
          result = result + string_tab + "}\n";
        }
      }
    } else {
      // Iterate over node children
      for (Iterator it = tree.children(node).iterator(); it.hasNext();) {
        // For every child print properties and block_def
        Node current = (Node) it.next();
        LinkedList<String> properties = current.getProperties();
        String block_def = current.getBlock_def();
        
        // Print properties
        for (int i = 0; i < properties.size(); i++) {
          String property = properties.get(i);
          result = result + property;
        }

        // Print selector for the next block
        if (!block_def.isEmpty()) {
          result = result + block_def.trim();
        }

        // If current child has children, then repeat the procedure
        if (!tree.children(current).isEmpty()) {
          result = result + " {";
          result = result + printTree(tree, current, tab + 1);
          result = result + "} ";
        }
      }
    }
    return result;
  }
}
