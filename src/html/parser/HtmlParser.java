/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */

package html.parser;

import common.stuff.Functions;
import common.stuff.StaticVariables;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Node;

public class HtmlParser {

  public static void setCssHtmlString(Node nod, int level, String parent_css) {
    List<Node> node_list = nod.childNodes();

    for (int i = 0; i < node_list.size(); i++) {
      // Create temporary node, on which operations will be performed
      Node node_tmp = node_list.get(i);
      String current_css = "";
      String current_id_css = "";
      String current_class_css = "";
      LinkedList<String> current_classes = new LinkedList<String>();

      // Print only children without other elements
      if (!node_tmp.nodeName().equals("script") && !node_tmp.nodeName().equals("link") && !node_tmp.nodeName().equals("#doctype") && !node_tmp.nodeName().equals("#text")  && !node_tmp.nodeName().equals("#comment") && !node_tmp.nodeName().equals("br") && !node_tmp.nodeName().equals("meta") && !node_tmp.nodeName().equals("title") && !node_tmp.nodeName().equals("style")) {

        // If current tag is not head, html or body, then add css selector without class or id
        if (!node_tmp.nodeName().equals("head") && !node_tmp.nodeName().equals("html") && !node_tmp.nodeName().equals("body")) {
         
          // Set the element as the parent from which recursion will continue
          current_id_css = parent_css + " " + node_tmp.nodeName();
          current_css = current_id_css;//node_tmp.nodeName() + "#" + Functions.cleanClasses(node_tmp.attr("id").trim());

          // Output of the node
          // Check if it has allready been added to your collection
          if (!Functions.inList((current_class_css + current_id_css).trim())) {
            // If not, then add it into css_paths
            StaticVariables.css_paths.add((current_id_css).trim());
            // System.out.println((current_id_css).trim() + " {}");
            StaticVariables.css_html_string = StaticVariables.css_html_string + (current_id_css).trim() + " { }" + "\n";
          }
        }

        // If css class is defined
        if (!node_tmp.attr("class").equals("")) {
          // Fill in all the classes
          current_classes = Functions.splitWordsToList(Functions.cleanClasses(node_tmp.attr("class")));

          // Set the first class as the parent
          // This is important because recursion will continue from this class
          current_class_css = parent_css + " " + node_tmp.nodeName() + "." + current_classes.getFirst();
          current_css = current_class_css;

          // Check if this class has allready been written
          for (String current_class : current_classes) {
            if (!Functions.inList((parent_css + " " + node_tmp.nodeName() + "." + current_class).trim())) {
              // If not, then add it into collection
              StaticVariables.css_paths.add((parent_css + " " + node_tmp.nodeName() + "." + current_class).trim());
              // System.out.println((parent_css + " " + node_tmp.nodeName() + "." + current_class).trim() + " {}");
              StaticVariables.css_html_string = StaticVariables.css_html_string + (parent_css + " " + node_tmp.nodeName() + "." + current_class).trim() + " { }" + "\n";
            }
          }
        }

        // If there is id defined, then recursion will continue from id
        if (!node_tmp.attr("id").equals("")) {
          // Set the id as the parent
          // This is important because recursion will continue from this id
          current_id_css = parent_css + " " + node_tmp.nodeName() + "#" + Functions.cleanClasses(node_tmp.attr("id"));
          // Clear previous path to this element because id is unique and it is not needed any more
          current_css = current_id_css;//node_tmp.nodeName() + "#" + Functions.cleanClasses(node_tmp.attr("id").trim());

          // Output of the node
          // Check if it has allready been added to your collection
          if (!Functions.inList((current_class_css + current_id_css).trim())) {
            // If not, then add it into css_paths
            StaticVariables.css_paths.add((current_id_css).trim());
            // System.out.println((current_id_css).trim() + " {}");
            StaticVariables.css_html_string = StaticVariables.css_html_string + (current_id_css).trim() + " { }" + "\n";
          }
        }
        // Continue with recursion
        setCssHtmlString(node_tmp, level, current_css);
      }
    }
  }
}
