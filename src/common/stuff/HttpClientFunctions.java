/*
 * Copyright (c) 2014 Tomaz Dragar
 * See the file LICENSE.TXT for copying permission. 
 */
package common.stuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class HttpClientFunctions {

  public static String validateUrl(String url) {
    String url_link = "";
    String url1 = url;
    String url2 = "http://";
    if (url1.startsWith("http://")) {
      url_link = url1;
    } else {
      url_link = url2 + url1;
    }
    return url_link;
  }

  public static String getHtml(String url) throws MalformedURLException, IOException {
    String url_link = "";
    String url1 = url;
    String url2 = "http://";
    if (url1.startsWith("http://")) {
      url_link = url1;
    } else {
      url_link = url2 + url1;
    }
    String html = "";
    URL oracle = new URL(url_link);

    System.setProperty("java.net.useSystemProxies", "true");
    System.out.println("detecting proxies");
    List l = null;
    try {
      l = ProxySelector.getDefault().select(new URI(url_link));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    if (l != null) {
      for (Iterator iter = l.iterator(); iter.hasNext();) {
        java.net.Proxy proxy = (java.net.Proxy) iter.next();
        System.out.println("proxy hostname : " + proxy.type());

        InetSocketAddress addr = (InetSocketAddress) proxy.address();

        if (addr == null) {
          System.out.println("No System Proxy");
          String proxy_url = StaticVariables.config.getHttp_proxy_url();
          Integer proxy_port = StaticVariables.config.getProxy_port();
          if(proxy_url != "none" && proxy_port != -1){
            System.out.println("Define custom proxy");
            System.out.println("proxy hostname : " + proxy_url);
            System.setProperty("http.proxyHost", proxy_url);
            System.out.println("proxy port : " + proxy_port);
            System.setProperty("http.proxyPort", Integer.toString(proxy_port));
          }
        } else {
          System.out.println("proxy hostname : " + addr.getHostName());
          System.setProperty("http.proxyHost", addr.getHostName());
          System.out.println("proxy port : " + addr.getPort());
          System.setProperty("http.proxyPort", Integer.toString(addr.getPort()));
        }
      }
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      html = html + "\n" + inputLine;
    }
    in.close();

    return html;
  }
}