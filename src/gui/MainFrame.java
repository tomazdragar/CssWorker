/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */
package gui;

import common.stuff.Functions;
import common.stuff.HttpClientFunctions;
import common.stuff.StaticVariables;
import css.parser.CSSParser;
import css.parser.CssNode;
import html.parser.HtmlParser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

public class MainFrame extends javax.swing.JFrame implements ActionListener, KeyListener {

  private RSyntaxTextArea jTextArea_html;
  private RSyntaxTextArea jTextArea_css;
  private JFileChooser fc_set_css;
  private JFileChooser fc_set_html;

  /**
   * Creates new form MainFrame
   */
  public MainFrame() {
    initComponents();
    this.setTitle("CssWorker");
    try {
      UIManager.setLookAndFeel(new MetalLookAndFeel());
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    jTextArea_html = new RSyntaxTextArea(20, 60);
    jTextArea_html.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
    jTextArea_html.setCodeFoldingEnabled(true);
    jTextArea_html.setAntiAliasingEnabled(true);
    RTextScrollPane sp_html = new RTextScrollPane(jTextArea_html);
    sp_html.setFoldIndicatorEnabled(true);
    this.jpanel_html_textarea.add(sp_html);


    jTextArea_css = new RSyntaxTextArea(20, 60);
    jTextArea_css.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
    jTextArea_css.setCodeFoldingEnabled(true);
    jTextArea_css.setAntiAliasingEnabled(true);
    RTextScrollPane sp_css = new RTextScrollPane(jTextArea_css);
    sp_css.setFoldIndicatorEnabled(true);
    this.jpanel_css_textarea.add(sp_css);
    pack();


    this.fc_set_css = new JFileChooser();
    this.fc_set_html = new JFileChooser();

    this.button_generate.addActionListener(this);
    this.button_clear_css.addActionListener(this);
    this.button_clear_html.addActionListener(this);
    this.button_load_html.addActionListener(this);
    this.button_set_html.addActionListener(this);
    this.button_load_url.addActionListener(this);
    this.button_load_css.addActionListener(this);
    this.button_set_css.addActionListener(this);
    this.button_save_html.addActionListener(this);
    this.button_save_css.addActionListener(this);
    this.button_sort_selectos.addActionListener(this);
    this.button_format1.addActionListener(this);
    this.button_remove_empty.addActionListener(this);
    this.jcombo_format.addActionListener(this);
    this.tf_load_url.addKeyListener(this);
    this.jTextArea_css.setEditable(true);
    this.jTextArea_html.setEditable(true);
    this.jTextArea_css.setAutoscrolls(true);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jSplitPane1 = new javax.swing.JSplitPane();
    jPanel1 = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    button_sort_selectos = new javax.swing.JButton();
    button_save_css = new javax.swing.JButton();
    button_clear_css = new javax.swing.JButton();
    button_load_css = new javax.swing.JButton();
    tf_load_css = new javax.swing.JTextField();
    button_format1 = new javax.swing.JButton();
    button_remove_empty = new javax.swing.JButton();
    button_set_css = new javax.swing.JButton();
    jcombo_format = new javax.swing.JComboBox();
    jpanel_css_textarea = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jPanel4 = new javax.swing.JPanel();
    button_save_html = new javax.swing.JButton();
    button_load_html = new javax.swing.JButton();
    tf_load_html = new javax.swing.JTextField();
    button_generate = new javax.swing.JButton();
    button_clear_html = new javax.swing.JButton();
    button_set_html = new javax.swing.JButton();
    tf_load_url = new javax.swing.JTextField();
    button_load_url = new javax.swing.JButton();
    jpanel_html_textarea = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBounds(new java.awt.Rectangle(0, 0, 1000, 700));

    jSplitPane1.setDividerLocation(510);
    jSplitPane1.setPreferredSize(new java.awt.Dimension(1000, 484));

    jPanel3.setBackground(new java.awt.Color(184, 207, 229));
    jPanel3.setPreferredSize(new java.awt.Dimension(454, 50));

    button_sort_selectos.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_sort_selectos.setText("sort");
    button_sort_selectos.setName("selector1"); // NOI18N

    button_save_css.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_save_css.setText("save");

    button_clear_css.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_clear_css.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear.png"))); // NOI18N

    button_load_css.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_load_css.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
    button_load_css.setMaximumSize(new java.awt.Dimension(28, 28));
    button_load_css.setMinimumSize(new java.awt.Dimension(28, 28));

    button_format1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_format1.setText("format");
    button_format1.setToolTipText("");

    button_remove_empty.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_remove_empty.setText("remove empty");

    button_set_css.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_set_css.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/open.png"))); // NOI18N

    jcombo_format.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    jcombo_format.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "block", "inline" }));

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(button_set_css, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tf_load_css)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_load_css, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_save_css, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(button_format1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_sort_selectos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
            .addComponent(button_remove_empty, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_clear_css))
          .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(jcombo_format, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
        .addGap(18, 18, 18)
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(tf_load_css, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(button_set_css)
          .addComponent(button_load_css, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(button_save_css, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jcombo_format, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(button_sort_selectos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(button_remove_empty)
              .addComponent(button_format1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(13, 13, 13))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addComponent(button_clear_css)
            .addContainerGap())))
    );

    jpanel_css_textarea.setBackground(new java.awt.Color(255, 255, 255));
    jpanel_css_textarea.setLayout(new java.awt.BorderLayout());

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
      .addComponent(jpanel_css_textarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addComponent(jpanel_css_textarea, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jSplitPane1.setRightComponent(jPanel1);

    jPanel4.setBackground(new java.awt.Color(184, 207, 229));
    jPanel4.setPreferredSize(new java.awt.Dimension(449, 50));
    jPanel4.setRequestFocusEnabled(false);

    button_save_html.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_save_html.setText("save");

    button_load_html.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_load_html.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N

    tf_load_html.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
    tf_load_html.setToolTipText("");
    tf_load_html.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tf_load_htmlActionPerformed(evt);
      }
    });

    button_generate.setBackground(new java.awt.Color(204, 255, 204));
    button_generate.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_generate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/generate.png"))); // NOI18N
    button_generate.setText("generate css");
    button_generate.setToolTipText("");
    button_generate.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    button_generate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        button_generateActionPerformed(evt);
      }
    });

    button_clear_html.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_clear_html.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clear.png"))); // NOI18N

    button_set_html.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_set_html.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/open.png"))); // NOI18N
    button_set_html.setMaximumSize(new java.awt.Dimension(47, 21));
    button_set_html.setMinimumSize(new java.awt.Dimension(47, 21));

    tf_load_url.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

    button_load_url.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
    button_load_url.setText("load url");

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel4Layout.createSequentialGroup()
            .addComponent(button_generate)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(button_clear_html))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(button_load_url, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_load_url, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
              .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(button_set_html, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf_load_html)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_load_html, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(button_save_html, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(button_set_html, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(button_load_html, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(button_save_html, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(tf_load_html))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(button_load_url, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
          .addComponent(tf_load_url))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(button_clear_html)
          .addComponent(button_generate))
        .addContainerGap(13, Short.MAX_VALUE))
    );

    jpanel_html_textarea.setBackground(new java.awt.Color(255, 255, 255));
    jpanel_html_textarea.setLayout(new java.awt.BorderLayout());

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
      .addComponent(jpanel_html_textarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addComponent(jpanel_html_textarea, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jSplitPane1.setLeftComponent(jPanel2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void button_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_generateActionPerformed
    }//GEN-LAST:event_button_generateActionPerformed

   private void tf_load_htmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_load_htmlActionPerformed
     // TODO add your handling code here:
   }//GEN-LAST:event_tf_load_htmlActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton button_clear_css;
  private javax.swing.JButton button_clear_html;
  private javax.swing.JButton button_format1;
  private javax.swing.JButton button_generate;
  private javax.swing.JButton button_load_css;
  private javax.swing.JButton button_load_html;
  private javax.swing.JButton button_load_url;
  private javax.swing.JButton button_remove_empty;
  private javax.swing.JButton button_save_css;
  private javax.swing.JButton button_save_html;
  private javax.swing.JButton button_set_css;
  private javax.swing.JButton button_set_html;
  private javax.swing.JButton button_sort_selectos;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JSplitPane jSplitPane1;
  private javax.swing.JComboBox jcombo_format;
  private javax.swing.JPanel jpanel_css_textarea;
  private javax.swing.JPanel jpanel_html_textarea;
  private javax.swing.JTextField tf_load_css;
  private javax.swing.JTextField tf_load_html;
  private javax.swing.JTextField tf_load_url;
  // End of variables declaration//GEN-END:variables

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(button_generate)) {
      System.out.println("button_generate");
      // Reset all properties
      StaticVariables.css_paths = new LinkedList<String>();
      StaticVariables.css_css_string = "";
      StaticVariables.css_html_string = "";
      StaticVariables.css_merged_string = "";
      StaticVariables.css_css_nodes = new LinkedList<CssNode>();
      StaticVariables.css_html_nodes = new LinkedList<CssNode>();
      StaticVariables.css_merged_nodes = new LinkedList<CssNode>();
      StaticVariables.parsed_html = "";

      StaticVariables.css_css_string = Functions.get_css_nodes_2(StaticVariables.css_css_nodes);

      // Start to parse html
      Document doc = Jsoup.parse(this.jTextArea_html.getText());
      HtmlParser.setCssHtmlString((Node) doc, 0, "");

      // Parse css selectors generated from html and fill the list of objects
      //CSSParser.ParseCssHtml(StaticVariables.css_html_string);
      //StaticVariables.css_html_string = Functions.get_css_nodes_2(StaticVariables.css_html_nodes);

      // Merge both lists of selectors
      //StaticVariables.css_merged_nodes = Functions.getMergedCss(StaticVariables.css_html_nodes, StaticVariables.css_css_nodes);
      //StaticVariables.css_merged_string = Functions.get_css_nodes_2(StaticVariables.css_merged_nodes);

      // Prin the result
      this.jTextArea_css.setText(StaticVariables.css_html_string);
      this.jTextArea_css.repaint();
    } else if (e.getSource().equals(button_clear_css)) {
      System.out.println("button_clear_css");
      StaticVariables.css_paths = new LinkedList<String>();
      StaticVariables.css_html_string = "";
      this.jTextArea_css.setText("");
      this.jTextArea_css.repaint();
    } else if (e.getSource().equals(button_clear_html)) {
      System.out.println("button_clear_html");
      StaticVariables.css_paths = new LinkedList<String>();
      StaticVariables.css_html_string = "";
      this.jTextArea_html.setText("");
      this.jTextArea_html.repaint();
    } else if (e.getSource().equals(button_load_html)) {
      System.out.println("button_load_html");
      Document doc = Jsoup.parse(Functions.getFileText(this.tf_load_html.getText()));
      String html_text = "";
      html_text = doc.outerHtml();

      // Check if body tag is empty and return the notice
      if ("body".equals(doc.body().getAllElements().last().tagName())) {
        html_text = "File does not exists or there is no html tags in body.";
      }
      this.jTextArea_html.setText(html_text);
      this.jTextArea_html.repaint();
    } else if (e.getSource().equals(button_load_url)) {
      System.out.println("button_load_url");
      StaticVariables.html_url = HttpClientFunctions.validateUrl(this.tf_load_url.getText());
      String html_text;
      try {
        html_text = HttpClientFunctions.getHtml(StaticVariables.html_url);
        this.jTextArea_html.setText(html_text);
      } catch (MalformedURLException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
      }

      //StaticVariables.css_urls.clear();

      // Functions.findString(html_text, "url\\s*?\\(.*?\\.css.*?\\)");
      // Functions.findString(html_text, "href\\s*?=\\s*?\".*?\\.css\\s*?\"");
      String css_text = "";
      // for (String css_url : StaticVariables.css_urls) {
      //    css_text += HttpClientFunctions.getHtml(css_url);
      // }

      
      this.jTextArea_html.repaint();
      //this.jTextArea_css.setText(css_text);
      //this.jTextArea_css.repaint();
    } else if (e.getSource().equals(button_set_html)) {
      System.out.println("button_set_html");

      int returnVal = this.fc_set_html.showDialog(this, "Open");
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = this.fc_set_html.getSelectedFile();
        String file_path = file.getAbsolutePath();
        this.tf_load_html.setText(file_path);
        Document doc = Jsoup.parse(Functions.getFileText(file_path));
        String html_text = "";
        html_text = doc.outerHtml();

        // Check if body tag is empty and return the notice
        if ("body".equals(doc.body().getAllElements().last().tagName())) {
          html_text = "File does not exists or there is no html tags in body.";
        }
        this.jTextArea_html.setText(html_text);
        this.jTextArea_html.repaint();
      } else {
        System.out.println("Event cancelled by user.");
      }

      //Reset the file chooser for the next time it's shown.
      fc_set_html.setSelectedFile(null);
    } else if (e.getSource().equals(button_set_css)) {
      System.out.println("button_set_css");

      int returnVal = this.fc_set_css.showDialog(this, "Open");
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = this.fc_set_css.getSelectedFile();
        String file_path = file.getAbsolutePath();
        this.tf_load_css.setText(file_path);
        this.jTextArea_css.setText(Functions.getFileText(file_path));
        this.jTextArea_css.repaint();
      } else {
        System.out.println("Event cancelled by user.");
      }

      //Reset the file chooser for the next time it's shown.
      this.fc_set_css.setSelectedFile(null);
    } else if (e.getSource().equals(button_load_css)) {
      System.out.println("button_load_css");
      this.jTextArea_css.setText(Functions.getFileText(this.tf_load_css.getText()));
      this.jTextArea_css.repaint();
    } else if (e.getSource().equals(button_save_css)) {
      System.out.println("button_save_css");
      Functions.writeTextToFile(this.tf_load_css.getText(), this.jTextArea_css.getText());
    } else if (e.getSource().equals(button_save_html)) {
      System.out.println("button_save_html");
      Functions.writeTextToFile(this.tf_load_html.getText(), this.jTextArea_html.getText());
    } else if (e.getSource().equals(button_sort_selectos)) {
      System.out.println("button_sort_selectors");

      // Reset current variables
      StaticVariables.css_css_string = this.jTextArea_css.getText();
      StaticVariables.css_css_nodes = new LinkedList<CssNode>();

      // Parse current css on the panel
      LinkedList<CssNode> nodes = new LinkedList<CssNode>();
      nodes = CSSParser.getAllNodes(StaticVariables.css_css_string);
      nodes = CSSParser.setAllSelectors(nodes);
      nodes = CSSParser.setAllComments(nodes);
      nodes = CSSParser.setAllProperties(nodes);
      StaticVariables.css_css_nodes = nodes;
      Collections.sort(StaticVariables.css_css_nodes);
      StaticVariables.css_css_string = CSSParser.printAllNodesTree(StaticVariables.css_css_nodes);

      this.jTextArea_css.setText(StaticVariables.css_css_string);
      this.jTextArea_css.repaint();

    } else if (e.getSource().equals(button_format1)) {
      System.out.println("button_format1");

      // Reset current variables
      StaticVariables.css_css_string = this.jTextArea_css.getText();
      StaticVariables.css_css_nodes = new LinkedList<CssNode>();

      // Parse current css on the panel
      LinkedList<CssNode> nodes = new LinkedList<CssNode>();
      nodes = CSSParser.getAllNodes(StaticVariables.css_css_string);
      nodes = CSSParser.setAllSelectors(nodes);
      nodes = CSSParser.setAllComments(nodes);
      nodes = CSSParser.setAllProperties(nodes);
      StaticVariables.css_css_nodes = nodes;
      StaticVariables.css_css_string = CSSParser.printAllNodesTree(StaticVariables.css_css_nodes);

      this.jTextArea_css.setText(StaticVariables.css_css_string);
      this.jTextArea_css.repaint();
    } else if (e.getSource().equals(jcombo_format)) {
      System.out.println("jcombo_format");
      // Set css format
      StaticVariables.css_format = jcombo_format.getSelectedItem().toString();
      System.out.println(StaticVariables.css_format);
    } else if (e.getSource().equals(button_remove_empty)) {
      System.out.println("button_remove_empty");

      // Reset current variables
      StaticVariables.css_css_string = this.jTextArea_css.getText();
      StaticVariables.css_css_nodes = new LinkedList<CssNode>();

      LinkedList<CssNode> nodes = new LinkedList<CssNode>();
      nodes = CSSParser.getAllNodes(StaticVariables.css_css_string);
      nodes = CSSParser.setAllSelectors(nodes);
      nodes = CSSParser.setAllComments(nodes);
      nodes = CSSParser.setAllProperties(nodes);
      StaticVariables.css_css_nodes = nodes;
      Functions.removeEmptyNodes();
      StaticVariables.css_css_string = CSSParser.printAllNodesTree(StaticVariables.css_css_nodes);

      this.jTextArea_css.setText(StaticVariables.css_css_string);
      this.jTextArea_css.repaint();
    }
  }

  public JButton getjButton1() {
    return button_generate;
  }

  public JButton getjButton2() {
    return button_clear_css;
  }

  public JPanel getjPanel1() {
    return jPanel1;
  }

  public void setjPanel1(JPanel jPanel1) {
    this.jPanel1 = jPanel1;
  }

  public JPanel getjPanel2() {
    return jPanel2;
  }

  public void setjPanel2(JPanel jPanel2) {
    this.jPanel2 = jPanel2;
  }

  public JButton getButton_selector() {
    return button_sort_selectos;
  }

  public JPanel getjPanel3() {
    return jPanel3;
  }

  public void setjPanel3(JPanel jPanel3) {
    this.jPanel3 = jPanel3;
  }

  public JPanel getjPanel4() {
    return jPanel4;
  }

  public void setjPanel4(JPanel jPanel4) {
    this.jPanel4 = jPanel4;
  }

  public JSplitPane getjSplitPane1() {
    return jSplitPane1;
  }

  public void setjSplitPane1(JSplitPane jSplitPane1) {
    this.jSplitPane1 = jSplitPane1;
  }

  @Override
  public void keyTyped(KeyEvent ke) {
  }

  @Override
  public void keyPressed(KeyEvent ke) {
    if (ke.getSource().equals(tf_load_url)) {
      if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
        System.out.println("enter_load_url");
        StaticVariables.html_url = HttpClientFunctions.validateUrl(this.tf_load_url.getText());
        String html_text;
        try {
          html_text = HttpClientFunctions.getHtml(StaticVariables.html_url);
          this.jTextArea_html.setText(html_text);
        } catch (MalformedURLException ex) {
          Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        }

        //StaticVariables.css_urls.clear();
        //Functions.findString(html_text, "url\\s*?\\(.*?\\.css.*?\\)");
        //Functions.findString(html_text, "href\\s*?=\\s*?\".*?\\.css\\s*?\"");
        // String css_text = "";
        //for (String css_url : StaticVariables.css_urls) {
        //    css_text += HttpClientFunctions.getHtml(css_url);
        //}

        
        this.jTextArea_html.repaint();
        //this.jTextArea_css.setText(css_text);
        //this.jTextArea_css.repaint();
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent ke) {
  }
}
