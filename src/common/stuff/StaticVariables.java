/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */

package common.stuff;

import com.gaurav.tree.LinkedTree;
import config.Config;
import css.parser.CssNode;
import css.parser.Node;
import gui.MainFrame;
import java.util.LinkedList;

public class StaticVariables {

  public static Config config = new Config();
  public static MainFrame main_frame = new MainFrame();
  
  public static LinkedList<String> css_paths = new LinkedList<String>();
  public static LinkedList<String> css_urls = new LinkedList<String>();
  
  public static String css_css_string = "";
  public static String css_html_string = "";
  public static String css_merged_string = "";
  
  public static LinkedTree<Node> properties_tree = new LinkedTree<Node>();
  public static LinkedList<CssNode> css_css_nodes = new LinkedList<CssNode>();
  public static LinkedList<CssNode> css_html_nodes = new LinkedList<CssNode>();
  public static LinkedList<CssNode> css_merged_nodes = new LinkedList<CssNode>();
  
  public static String parsed_html = "";
  public static String html_url = "";
  public static String css_format = "block";
}
