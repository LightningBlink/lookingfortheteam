package org.apache.jsp.WEB_002dINF.news;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class news_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<span ng-if=\"hasRole('ROLE_ADMIN')\">\r\n");
      out.write("    <a href=\"/addnew\">Add new</a>\r\n");
      out.write("</span>\r\n");
      out.write("\r\n");
      out.write("<div class=\"list-group\">\r\n");
      out.write("    <div class=\"list-group-item\" ng-repeat=\"new in news\">\r\n");
      out.write("        <p class=\"text-right\">\r\n");
      out.write("            <span ng-if=\"hasRole('ROLE_ADMIN')\">\r\n");
      out.write("                <a href=\"#\" ng-click=\"updateNew(new.id)\">Update</a>\r\n");
      out.write("               <a href=\"#\" ng-click=\"deleteNew(new.id)\">Remove</a>\r\n");
      out.write("            </span>\r\n");
      out.write("        </p>\r\n");
      out.write("\r\n");
      out.write("        <h3 class=\"list-group-item-heading page-header margin5\"><a href=\"/item/{{new.id}}\">{{new.header}}</a></h3>\r\n");
      out.write("\r\n");
      out.write("        <p class=\"list-group-item-text\" ng-bind-html=\"new.description\"></p>\r\n");
      out.write("\r\n");
      out.write("        <p  class=\"list-group-item-text text-right date-color\">{{new.date | date:'yyyy-MM-dd HH:mm'}} created by {{new.authorName}}</p>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"col-md-6 col-md-offset-3\">\r\n");
      out.write("    <pagination boundary-links=\"true\" ng-change=\"pageChanged()\" total-items=\"bigTotalItems\" ng-model=\"bigCurrentPage\"\r\n");
      out.write("                max-size=\"maxSize\" class=\"pagination-sm\" previous-text=\"&lsaquo;\" next-text=\"&rsaquo;\"\r\n");
      out.write("                first-text=\"&laquo;\" last-text=\"&raquo;\"></pagination>\r\n");
      out.write("</div>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
