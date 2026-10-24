package org.apache.jsp.WEB_002dINF;

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
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("<html lang=\"en\" ng-app=\"myApp\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"description\" content=\"\">\r\n");
      out.write("    <meta name=\"author\" content=\"Rozhko Denis\">\r\n");
      out.write("\r\n");
      out.write("    <title>Looking for team</title>\r\n");
      out.write("\r\n");
      out.write("    <!-- Bootstrap -->\r\n");
      out.write("    <link href=\"/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"/css/main.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n");
      out.write("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\r\n");
      out.write("    <!--[if lt IE 9]>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\r\n");
      out.write("    <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <![endif]-->\r\n");
      out.write("\r\n");
      out.write("    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\r\n");
      out.write("    <script src=\"/js/jquery-1.11.1.js\"></script>\r\n");
      out.write("    <script src=\"/js/angular.js\"></script>\r\n");
      out.write("    <script src=\"/js/angular-route.min.js\"></script>\r\n");
      out.write("    <script src=\"/js/angular-resource.min.js\"></script>\r\n");
      out.write("    <script src=\"/js/angular-cookies.min.js\"></script>\r\n");
      out.write("    <script src=\"/js/bootstrap.min.js\"></script>\r\n");
      out.write("    <script src=\"/js/ui-bootstrap-tpls-0.11.2.min.js\"></script>\r\n");
      out.write("    <script src=\"/js/service.js\"></script>\r\n");
      out.write("    <script src=\"/js/module.js\"></script>\r\n");
      out.write("    <script src=\"/js/route.js\"></script>\r\n");
      out.write("    <script src=\"/js/controller.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <base href=\"/\" />\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"full\" ng-controller=\"MenuController\">\r\n");
      out.write("\r\n");
      out.write("<nav class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"collapse navbar-collapse\">\r\n");
      out.write("            <ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("                <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#registration\">Registration</a></li>\r\n");
      out.write("                <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#login\">Login</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</nav>\r\n");
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("\r\n");
      out.write("    <div class=\"page-header\">\r\n");
      out.write("        <h1 class=\"text-center\"><img src=\"http://x-lines.ru/letters/i/cyrillicbasic/0433/0e1016/40/1/jtzs645jp3u1y3uxqeo8e3mbpw.png\"></h1>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div>\r\n");
      out.write("        <nav class=\"navbar navbar-default\" role=\"navigation\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"collapse navbar-collapse\">\r\n");
      out.write("                    <ul class=\"nav navbar-nav\">\r\n");
      out.write("                        <li class=\"{{array_active[0]}}\" ng-click=\"activeFunc(0)\"><a href=\"news\">NEWS</a></li>\r\n");
      out.write("                        <li class=\"{{array_active[1]}}\" ng-click=\"activeFunc(1)\"><a href=\"lookingteam\">SEARCH TEAM</a></li>\r\n");
      out.write("                        <li class=\"{{array_active[2]}}\" ng-click=\"activeFunc(2)\"><a href=\"lookingplayer\">SEARCH PLAYER</a></li>\r\n");
      out.write("                        <li class=\"{{array_active[3]}}\" ng-click=\"activeFunc(3)\"><a href=\"mygames\">MY GAMES</a></li>\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"col-md-8\" class=\"row\" ng-view></div>\r\n");
      out.write("    <div class=\"col-md-4\">YLA LT ALTLA L </div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<div class=\"modal fade\" id=\"login\" ng-controller=\"LoginController\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"LoginModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("    <div class=\"modal-dialog\">\r\n");
      out.write("        <div class=\"modal-content\">\r\n");
      out.write("            <div class=\"modal-header\">\r\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\r\n");
      out.write("                <h4 class=\"modal-title\" id=\"LoginModalLabel\">Login</h4>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-body\">\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"form-group\">\r\n");
      out.write("                        <label for=\"username\">Username</label>\r\n");
      out.write("                        <input type=\"text\" ng-model=\"username\" name=\"username\" class=\"form-control\" id=\"username\" placeholder=\"Enter username\">\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"form-group\">\r\n");
      out.write("                        <label for=\"password\">Password</label>\r\n");
      out.write("                        <input type=\"password\" ng-model=\"password\" class=\"form-control\" id=\"password\" name=\"password\" placeholder=\"Password\">\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <button type=\"button\" ng-click=\"login()\" class=\"btn btn-default\" >Log in</button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-footer\">\r\n");
      out.write("                ----\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"modal fade\" id=\"registration\" ng-controller=\"RegistrationController\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"RegistrationModalLabel\" aria-hidden=\"true\">\r\n");
      out.write("    <div class=\"modal-dialog\">\r\n");
      out.write("        <div class=\"modal-content\">\r\n");
      out.write("            <div class=\"modal-header\">\r\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\r\n");
      out.write("                <h4 class=\"modal-title\" id=\"RegistrationController\">Registration</h4>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-body\">\r\n");
      out.write("\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"r_username\">Username</label>\r\n");
      out.write("                    <input type=\"text\" ng-model=\"r_username\" name=\"r_username\" id=\"r_username\" class=\"form-control\"  placeholder=\"Enter username\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"r_email\">E-mail</label>\r\n");
      out.write("                    <input type=\"text\" ng-model=\"r_email\" name=\"r_email\" id=\"r_email\" class=\"form-control\"  placeholder=\"Enter e-mail\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"r_password\">Password</label>\r\n");
      out.write("                    <input type=\"text\" ng-model=\"r_password\" id=\"r_password\" class=\"form-control\"  name=\"r_password\" placeholder=\"Password\">\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <button type=\"button\" ng-click=\"registration()\" class=\"btn btn-default\" >Registration</button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"modal-footer\">\r\n");
      out.write("                ----\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("</html>");
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
