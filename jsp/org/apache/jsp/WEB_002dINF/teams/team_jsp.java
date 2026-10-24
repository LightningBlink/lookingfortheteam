package org.apache.jsp.WEB_002dINF.teams;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class team_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<form class=\"form-horizontal\">\r\n");
      out.write("    <div class=\"page-header\">\r\n");
      out.write("        <h1>Team Information</h1>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-6\">\r\n");
      out.write("            <div class=\"form-group \">\r\n");
      out.write("                <label class=\"col-sm-3 control-label\">Name </label>\r\n");
      out.write("\r\n");
      out.write("                <span class=\"col-sm-9 paragraph-text ng-binding ng-scope\"><img width=\"25\"\r\n");
      out.write("                                                                               ng-src=\"/images/flags/{{info.team.nation.toLowerCase()}}.png\"/> {{info.team.name}}</span>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"form-group \">\r\n");
      out.write("                <label class=\"col-sm-3 control-label\">About Team </label>\r\n");
      out.write("\r\n");
      out.write("                <span class=\"col-sm-9 paragraph-text ng-binding ng-scope\"> {{info.team.aboutTeam}}</span>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-6\">\r\n");
      out.write("            <div>\r\n");
      out.write("                <form id=\"do-chat\">\r\n");
      out.write("                    <h4 class=\"alert alert-success text-center\">Team Chat</h4>\r\n");
      out.write("                    <table id=\"response\" class=\"table table-bordered\">\r\n");
      out.write("                        <tr ng-repeat=\"message in messages\"><td class=\"received\">1\r\n");
      out.write("                            </td><td class=\"user label label-info\">2\r\n");
      out.write("                            </td><td class=\"message badge\">{{message}}\r\n");
      out.write("                            </td></tr>\r\n");
      out.write("                    </table>\r\n");
      out.write("                    <fieldset>\r\n");
      out.write("                        <div>\r\n");
      out.write("                            <input type=\"text\" ng-model=\"message\" style=\"width: 100%;margin-bottom: 20px; line-height: 20px\"  id=\"message\"/>\r\n");
      out.write("                            <input type=\"button\" ng-click=\"sendMessage(message)\" class=\"btn btn-large btn-block btn-info\"\r\n");
      out.write("                                   value=\"Send message\" />\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </fieldset>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"page-header\">\r\n");
      out.write("        <h2>Recaps</h2>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"panel panel-primary paragraph-text\" ng-repeat=\"game in gamesHeader\">\r\n");
      out.write("        <div class=\"panel-heading\">\r\n");
      out.write("            <span style=\"font-size: 30px;\"><img width=\"50\" ng-src=\"/images/logo/{{game}}.jpg\"/> {{game}}</span>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"panel-body\">\r\n");
      out.write("            <table class=\"table\">\r\n");
      out.write("                <thead>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td>#</td>\r\n");
      out.write("                    <td>Nickname</td>\r\n");
      out.write("                    <td>Post</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </thead>\r\n");
      out.write("                <tbody>\r\n");
      out.write("                <tr ng-repeat=\"teammate in games[game]\">\r\n");
      out.write("                    <td>\r\n");
      out.write("                        {{$index +1}}\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        {{teammate.username}}\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td>\r\n");
      out.write("                        {{teammate.post}}\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </tbody>\r\n");
      out.write("            </table>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</form>");
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
