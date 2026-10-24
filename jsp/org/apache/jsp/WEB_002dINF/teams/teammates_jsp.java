package org.apache.jsp.WEB_002dINF.teams;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class teammates_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<div>\r\n");
      out.write("    <p class=\"header-game text-center bg-info\">{{game}}</p>\r\n");
      out.write("</div>\r\n");
      out.write("<div>\r\n");
      out.write("<table class=\"table text-center\">\r\n");
      out.write("    <thead>\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td>#</td>\r\n");
      out.write("        <td>Player</td>\r\n");
      out.write("        <td style=\"width: 30%;\">Actions</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </thead>\r\n");
      out.write("    <tbody>\r\n");
      out.write("    <tr ng-repeat=\"player in players\">\r\n");
      out.write("        <td>\r\n");
      out.write("            {{(10 * (bigCurrentPage - 1)) + $index+1}}\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("            {{player.username}}\r\n");
      out.write("        </td>\r\n");
      out.write("        <td>\r\n");
      out.write("            <a  ng-click=\"invite(player.username)\" class=\"btn btn-primary\">Invite</a>\r\n");
      out.write("            <a  href=\"/profile/{{player.username}}\" class=\"btn btn-primary\">Show</a>\r\n");
      out.write("        </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </tbody>\r\n");
      out.write("</table>\r\n");
      out.write("<div class=\"col-md-6 col-md-offset-3\">\r\n");
      out.write("    <pagination boundary-links=\"true\" ng-change=\"pageChanged()\" total-items=\"bigTotalItems\" ng-model=\"bigCurrentPage\"\r\n");
      out.write("                max-size=\"maxSize\" class=\"pagination-sm\" previous-text=\"&lsaquo;\" next-text=\"&rsaquo;\"\r\n");
      out.write("                first-text=\"&laquo;\" last-text=\"&raquo;\"></pagination>\r\n");
      out.write("</div>\r\n");
      out.write("</div>");
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
