package org.apache.jsp.WEB_002dINF.teams;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<table class=\"table table-striped\" ng-show=\"games.length > 0\">\r\n");
      out.write("    <thead>\r\n");
      out.write("    <tr>\r\n");
      out.write("        <th class=\"games-header-th\">GAMES</th>\r\n");
      out.write("        <th class=\"games-header-th\"></th>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </thead>\r\n");
      out.write("    <tbody>\r\n");
      out.write("    <tr ng-repeat=\"game in games | orderBy:'name'\">\r\n");
      out.write("        <td class=\"games-header\"><img width=\"100\" ng-src=\"/images/logo/{{game.name}}.jpg\"/> {{game.name}}</td>\r\n");
      out.write("        <td class=\"text-center games-header\">\r\n");
      out.write("            <a ng-href=\"looking/team/{{game.name}}\" class=\"btn  btn-default\">FIND A TEAM</a>\r\n");
      out.write("            <a ng-href=\"looking{{game.hasTeam.link}}\" class=\"btn  btn-default\">{{game.hasTeam.message}}</a>\r\n");
      out.write("        </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </tbody>\r\n");
      out.write("</table>\r\n");
      out.write("<div ng-hide=\"games.length\" class=\"games-header\">\r\n");
      out.write("    You have no added games in your profile. Please add games if you want use this service!\r\n");
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
