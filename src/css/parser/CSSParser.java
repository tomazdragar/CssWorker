/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */

package css.parser;

import com.gaurav.tree.LinkedTree;
import com.gaurav.tree.NodeNotFoundException;
import common.stuff.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSSParser {

    public static String removeComments(String source) {
        String regex = "\\/\\*.*?\\*/";
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Matcher m = p.matcher(source);

        return m.replaceAll("");
    }

    public static LinkedList<String> getComments(String source) {
        LinkedList<String> result = new LinkedList<String>();
        String regex = "\\/\\*.*?\\*/";
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Matcher m = p.matcher(source);
        while (m.find()) {
            result.add(m.group());
        }
        return result;
    }

    public static String printComments(LinkedList<String> comments) {
        String result = "";
        for (Iterator<String> it = comments.iterator(); it.hasNext();) {
            result = result + it.next() + "\n";
        }
        return result;
    }

    public static Hashtable<Integer, Integer> getCommentsIndexes(String source) {
        Hashtable<Integer, Integer> indexes = new Hashtable<Integer, Integer>();
        String regex = "\\/\\*.*?\\*/";
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        // Get a matcher objet
        Matcher m = p.matcher(source);
        while (m.find()) {
            indexes.put(m.start(), m.end());
        }
        return indexes;
    }

    public static String getFirstRule(String source) {
        Hashtable<Integer, Integer> comments_indexes = new Hashtable<Integer, Integer>();
        comments_indexes = getCommentsIndexes(source);
        StringBuilder sb = new StringBuilder(source);
        StringBuilder result = new StringBuilder("");
        int start = 0;
        int end = 0;
        for (int i = 0; i < sb.length(); i++) {
            // Check if the current i is on the string interval where comment is
            Enumeration<Integer> enumKey = comments_indexes.keys();
            boolean is_comment = false;
            while (enumKey.hasMoreElements()) {
                Integer key = enumKey.nextElement();
                Integer val = comments_indexes.get(key);
                if (i >= key.intValue() && i <= val.intValue()) {
                    is_comment = true;
                }
            }

            if (!is_comment) {
                // If string contains another "{substring}" block
                if (sb.charAt(i) == '{') {
                    start++;
                }
                if (sb.charAt(i) == '}' && end < start) {
                    end++;
                }
            }

            result.append(sb.charAt(i));
            if (start == end && start != 0 && end != 0) {
                break;
            }
        }
        return result.toString();
    }

    public static CssNode getRuleFrom(int idx, String source, Hashtable<Integer, Integer> comments_indexes) {
        StringBuilder sb = new StringBuilder(source);
        StringBuilder result = new StringBuilder("");
        int start = 0;
        int end = 0;
        int start_idx = idx;
        int end_idx = idx;

        for (int i = idx; i < sb.length(); i++) { 
            Enumeration<Integer> enumKey = comments_indexes.keys();
            boolean is_comment = false;
            while (enumKey.hasMoreElements()) {
                Integer key = enumKey.nextElement();
                Integer val = comments_indexes.get(key);
                if (i >= key.intValue() && i <= val.intValue()) {
                    is_comment = true;
                }
            }

            if (!is_comment) {
                if (sb.charAt(i) == '{') {
                    start++;
                }
                if (sb.charAt(i) == '}' && end < start) {
                    end++;
                }
            }

            result.append(sb.charAt(i));
            end_idx = i;
            if (start == end && start != 0 && end != 0) {
                break;
            }
        }

        CssNode node = new CssNode(result.toString(), start_idx, end_idx, "", null);
        return node;
    }

    public static String cutFirstRule(String source) {
        String first = getFirstRule(source);
        String result = source.substring(first.length());
        return result;
    }

    public static String getSelector(String source) {
        StringBuilder s = new StringBuilder(removeComments(source));
        StringBuilder result = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                break;
            }
            result.append(s.charAt(i));
        }

        String selector = deleteInvalidEndRule(result.toString());
        selector = deleteSpaces(selector);
        selector = selector.trim();
        return selector;
    }

    public static String deleteSpaces(String source) {
        String result = source.trim().replaceAll(" +", " ");
        return result;
    }

    public static String replaceWhitespaces(String source) {
        source = source.replaceAll("\\s", " ");
        return source;
    }

    public static String deleteWhitespaces(String source) {
        source = source.replaceAll("\\s", "");
        return source;
    }

    public static String deleteInvalidEndRule(String source) {
        String result = source.replaceAll("}", " ");
        return result;
    }

    public static LinkedList<CssNode> getAllNodes(String source) {
        Hashtable<Integer, Integer> comments_indexes = new Hashtable<Integer, Integer>();
        comments_indexes = getCommentsIndexes(source);

        LinkedList<CssNode> nodes = new LinkedList<CssNode>();
        int length = source.length();
        int current_idx = 0;
        while (current_idx < length - 1) {
            CssNode tmp_node = new CssNode("", 0, 0, "", null);
            tmp_node = getRuleFrom(current_idx, source, comments_indexes);
            current_idx = tmp_node.getEnd() + 1;
            if (!tmp_node.getStart_string().trim().isEmpty()) {
                nodes.add(tmp_node);
            }
        }
        return nodes;
    }

    public static String printAllNodes(LinkedList<CssNode> nodes) {
        String result = "";
        String comments = "";
        for (int i = 0; i < nodes.size(); i++) {
            CssNode cssNode = nodes.get(i);
            if (!printComments(cssNode.getComments()).isEmpty()) {
                comments = printComments(cssNode.getComments());
            } else {
                comments = "";
            }
            result = result + comments + cssNode.getSelector() + " " + printProperties(cssNode.getProperties1()) + "\n";
        }
        return result;
    }

    public static String printAllNodesTree(LinkedList<CssNode> nodes) {
        String result = "";
        String comments = "";
        if (StaticVariables.css_format == "block") {
            for (int i = 0; i < nodes.size(); i++) {
                CssNode cssNode = nodes.get(i);
                if (!printComments(cssNode.getComments()).isEmpty()) {
                    comments = printComments(cssNode.getComments());
                } else {
                    comments = "";
                }
                result = result + comments + cssNode.getSelector() + " {\n" + cssNode.getTree_properties() + "}\n\n";
            }
        } else {
            for (int i = 0; i < nodes.size(); i++) {
                CssNode cssNode = nodes.get(i);
                if (!printComments(cssNode.getComments()).isEmpty()) {
                    comments = "\n" + printComments(cssNode.getComments());
                } else {
                    comments = "";
                }
                result = result + comments + cssNode.getSelector() + " {" + cssNode.getTree_properties() + "}\n";
            }
        }
        return result.trim();
    }

    public static String printAllNodesInline(LinkedList<CssNode> nodes) {
        String result = "";
        for (int i = 0; i < nodes.size(); i++) {
            CssNode cssNode = nodes.get(i);
            String comments = "";
            if (!cssNode.getComments().isEmpty()) {
                comments = printComments(cssNode.getComments()) + "\n";
            }
            result = result + comments + cssNode.getSelector() + " " + printPropertiesInline(cssNode.getProperties1()) + "\n";
        }
        return result;
    }

    public static String printAllSelectors(LinkedList<CssNode> nodes) {
        String result = "";
        for (int i = 0; i < nodes.size(); i++) {
            CssNode cssNode = nodes.get(i);
            result = result + cssNode.getSelector() + "\n";
        }
        return result;
    }

    public static String printProperties(LinkedList<String> properties) {
        String result = "{\n";
        for (int i = 0; i < properties.size(); i++) {
            String property = properties.get(i);
            result = result + "\t" + property + "\n";
        }
        return result + "}";
    }

    public static String printPropertiesInline(LinkedList<String> properties) {
        String result = "{";
        for (int i = 0; i < properties.size(); i++) {
            String property = properties.get(i);
            result = result + " " + property;
        }
        return result + " }";
    }

    public static LinkedList<CssNode> setAllSelectors(LinkedList<CssNode> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            CssNode cssNode = nodes.get(i);
            String source = cssNode.getStart_string();
            String selector = getSelector(source);
            cssNode.setSelector(selector);
        }
        return nodes;
    }

    public static LinkedList<CssNode> setAllComments(LinkedList<CssNode> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            CssNode cssNode = nodes.get(i);
            String source = cssNode.getStart_string();
            LinkedList<String> comments = new LinkedList<String>();
            comments = getComments(source);
            cssNode.setComments(comments);
        }
        return nodes;
    }

    // Expected text { properties }
    public static LinkedList<String> getProperties(String source) {
        source = removeComments(source);
        LinkedList<String> properties = new LinkedList<String>();
        String regex = ".*?;";
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        //  get a matcher objet
        Matcher m = p.matcher(source);
        while (m.find()) {
            properties.add(deleteSpaces(replaceWhitespaces(m.group())).trim());
        }

        return properties;
    }

    // Expected text { properties }
    public static void getPropertiesTree(String source, Node parent) throws NodeNotFoundException {
        // Divide string to smaller blocks of text (properties, block)(properties, block)(properties, block)
        LinkedList<CssNode> inner_blocks = new LinkedList<CssNode>();
        inner_blocks = getAllNodes(deleteSpaces(replaceWhitespaces(removeComments(source).trim())));
        for (int i = 0; i < inner_blocks.size(); i++) {
            
            Node node = new Node();
            node.setStart_string(source);

            // Set start string
            CssNode cssNode = inner_blocks.get(i);
            String start_string = cssNode.getStart_string();
            // Divided string to upper and lower block
            String top_properties = getSelector(start_string);
            String bottom_block = "";
            if ((top_properties.length() + 1) < start_string.length()) {
                bottom_block = start_string.substring(top_properties.length());
            }

            // Search block_def
            StringBuilder block_def = new StringBuilder("");
            for (int j = top_properties.length() - 1; j >= 0; j--) {
                if (top_properties.charAt(j) != ';') {
                    block_def.insert(0, top_properties.charAt(j));
                } else {
                    break;
                }
            }

            // Set upper properties and lower block to node
            LinkedList<String> properties = getProperties(top_properties);
            node.setProperties(properties);
            node.setBlock_def(block_def.toString());
            node.setBottom_block(bottom_block);

            // Add current node into tree
            StaticVariables.properties_tree.add(parent, node);

            // If lower block has properties then continue with recursion
            if (bottom_block.length() > 3) {
                // Extract all content which is inside the block and then call function getPropertiesTree() again
                String regex = "\\{.*\\}";
                Pattern p = Pattern.compile(regex, Pattern.DOTALL);
                //  Get a matcher objet
                Matcher m = p.matcher(bottom_block);
                while (m.find()) {
                    String block = m.group();
                    String inner_block = block.substring(1, block.length() - 1);
                    getPropertiesTree(inner_block, node);
                }
            }
        }
    }

    public static LinkedList<CssNode> setAllProperties(LinkedList<CssNode> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            String tree_properties = "";
            CssNode cssNode = nodes.get(i);
            LinkedList<String> properties = new LinkedList<String>();
            String source = cssNode.getStart_string();
            source = removeComments(source);

            String regex = "\\{.*\\}";
            Pattern p = Pattern.compile(regex, Pattern.DOTALL);
            //  Get a matcher objet
            Matcher m = p.matcher(source);
            while (m.find()) {
                String block = m.group();
                String inner_block = block.substring(1, block.length() - 1);
                
                // Create starting tree structure and root node in it
                StaticVariables.properties_tree = new LinkedTree<Node>();
                Node node = new Node();
                StaticVariables.properties_tree.add(node);
                try {
                    getPropertiesTree(inner_block, node);
                    tree_properties = Functions.printTree(StaticVariables.properties_tree, node, 0);
                } catch (NodeNotFoundException ex) {
                    Logger.getLogger(CSSParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            cssNode.setTree_properties(tree_properties);
            cssNode.setProperties1(properties);
        }
        return nodes;
    }
}
