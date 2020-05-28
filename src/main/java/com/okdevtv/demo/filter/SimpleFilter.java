package com.okdevtv.demo.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class SimpleFilter implements Filter {
  @Override
  public void destroy() {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    Enumeration<String> headerNames = req.getHeaderNames();
    Iterator<String> asIterator = headerNames.asIterator();
    String headers = "";
    while (asIterator.hasNext()) {
      headers += "," + asIterator.next();
    }

    // -Djava.net.preferIPv4Stack=true
    System.out.println("Remote Method: " + req.getMethod() + ": " + headers);
    filterchain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig filterconfig) throws ServletException {}
}
