package org.apache.jsp.WEB_002dINF.teams;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createteam_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<form name=\"formTeam\" novalidate>\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-9 form-horizontal\">\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"name\" class=\"col-sm-4 control-label\">NAME OF TEAM</label>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"col-sm-8\">\r\n");
      out.write("                    <input  required=\"\" minlength=\"2\" maxlength=\"20\"  type=\"text\"\r\n");
      out.write("                           class=\"form-control\" ng-model=\"team.name\"  name=\"name\" id=\"name\" placeholder=\"Name\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                    <div ng-show=\"formTeam.$submitted\">\r\n");
      out.write("                        <div class=\"error-input\" ng-show=\"formTeam.name.$error.required\">Tell us your\r\n");
      out.write("                            team name!\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"error-input\" ng-show=\"formTeam.name.$error.minlength ||\r\n");
      out.write("                             formTeam.name.$error.maxlength\">The name should be in the range 2 - 20\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"aboutTeam\" class=\"col-sm-4 control-label\">ABOUT TEAM</label>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"col-sm-8\">\r\n");
      out.write("                        <textarea   style=\"resize: none; height: 120px;\"  maxlength=\"2000\" required=\"\"\r\n");
      out.write("                                class=\"form-control\" ng-model=\"team.aboutTeam\" name=\"aboutTeam\" id=\"aboutTeam\" placeholder=\"ABOUT TEAM\"></textarea>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                        <div ng-show=\"formTeam.$submitted\">\r\n");
      out.write("                            <div class=\"error-input\" ng-show=\"formTeam.aboutTeam.$error.required\">Please write information about your team!\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"error-input\" ng-show=\"formTeam.aboutTeam.$error.minlength ||\r\n");
      out.write("                             formTeam.aboutTeam.$error.maxlength\">Your information should be not more than 2000 characters</div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"country\" class=\"col-sm-4 control-label\">TEAM NATION</label>\r\n");
      out.write("                <div class=\"col-sm-8\" nationpicker id=\"country\" ng-model=\"team.nation\"   data-flags=\"true\" data-filter=\"true\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"text-center\">\r\n");
      out.write("        <button  type=\"submit\" class=\"btn btn-primary\" ng-click=\"saveTeam(formTeam.$invalid)\">Save</button>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<script src=\"/js/form-helpres/dist/js/bootstrap-formhelpers.js\"></script>");
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
