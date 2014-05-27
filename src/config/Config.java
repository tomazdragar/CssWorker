/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */
package config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {

  private String http_proxy_url;
  private Integer proxy_port;

  public Config(String http_proxy_url, int proxy_port) {
    this.http_proxy_url = http_proxy_url;
    this.proxy_port = proxy_port;
  }

  public Config() {
    
    
    this.http_proxy_url = null;
    this.proxy_port = null;
    try {
      this.http_proxy_url = getProxyUrl();
    } catch (IOException ex) {
      Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
      this.http_proxy_url = null;
    }
    try {
      this.proxy_port = getProxyPort();
    } catch (IOException ex) {
      Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
      this.proxy_port = null;
    }
    System.out.println("Proxy url = " + this.http_proxy_url);
    System.out.println("Proxy port = " + this.proxy_port);

  }

  public static String getProxyUrl() throws IOException {
    String proxy_url = null;
    Properties mainProperties = new Properties();
    mainProperties.load(Config.class.getResourceAsStream("proxy.properties"));
    proxy_url = mainProperties.getProperty("proxy.url");

    return proxy_url;
  }

  public static Integer getProxyPort() throws IOException {
    String proxy_port = null;
    Properties mainProperties = new Properties();
    mainProperties.load(Config.class.getResourceAsStream("proxy.properties"));
    proxy_port = mainProperties.getProperty("proxy.port");
    Integer i = new Integer(proxy_port);
    return i;
  }

  public String getHttp_proxy_url() {
    return http_proxy_url;
  }

  public void setHttp_proxy_url(String http_proxy_url) {
    this.http_proxy_url = http_proxy_url;
  }

  public Integer getProxy_port() {
    return proxy_port;
  }

  public void setProxy_port(Integer proxy_port) {
    this.proxy_port = proxy_port;
  }
}
