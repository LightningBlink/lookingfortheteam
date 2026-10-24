package org.apache.jsp.WEB_002dINF.profile;

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

      out.write("<form name=\"formProfile\" novalidate>\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-9 form-horizontal\">\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"name\" class=\"col-sm-3 control-label\">Name</label>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"col-sm-9\">\r\n");
      out.write("                    <input ng-if=\"user.name == userInfo.username\"  minlength=\"2\" maxlength=\"20\" type=\"text\" class=\"form-control\" ng-model=\"userInfo.name\" name=\"name\" id=\"name\" placeholder=\"Name\">\r\n");
      out.write("                    <span ng-if=\"user.name != userInfo.username\" class=\"paragraph-text\">{{userInfo.name}}</span>\r\n");
      out.write("\r\n");
      out.write("                    <div ng-show=\"formProfile.$submitted\">\r\n");
      out.write("                        <div class=\"error-input\" ng-show=\"formProfile.name.$error.minlength ||\r\n");
      out.write("                             formProfile.name.$error.maxlength\">The name should be in the range 2 - 20\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"surname\" class=\"col-sm-3 control-label\">Surname</label>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"col-sm-9\">\r\n");
      out.write("                    <input type=\"text\" ng-if=\"user.name == userInfo.username\"   minlength=\"2\" maxlength=\"20\" class=\"form-control\" ng-model=\"userInfo.surname\" name=\"surname\" id=\"surname\" placeholder=\"Surname\">\r\n");
      out.write("                    <span ng-if=\"user.name != userInfo.username\" class=\"paragraph-text\">{{userInfo.surname}}</span>\r\n");
      out.write("\r\n");
      out.write("                    <div ng-show=\"formProfile.$submitted\">\r\n");
      out.write("                        <div class=\"error-input\" ng-show=\"formProfile.surname.$error.minlength ||\r\n");
      out.write("                             formProfile.name.$error.maxlength\">The surname should be in the range 2 - 20\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"country\" class=\"col-sm-3 control-label\">Country</label>\r\n");
      out.write("\r\n");
      out.write("                <div ng-if=\"user.name == userInfo.username\" nationpicker id=\"country\"  class=\"col-sm-9\" ng-model=\"userInfo.country\"  data-flags=\"true\" data-filter=\"true\"></div>\r\n");
      out.write("                <span ng-if=\"user.name != userInfo.username\"><img  class=\"country-flag\" ng-src=\"/images/flags/{{userInfo.country.toLowerCase()}}.png\"/><span class=\"paragraph-text\">{{countryName}}</span></span>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"city\" class=\"col-sm-3 control-label\">City</label>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"col-sm-9\">\r\n");
      out.write("                    <input ng-if=\"user.name == userInfo.username\" type=\"text\"   minlength=\"2\" maxlength=\"30\"  class=\"form-control\" ng-model=\"userInfo.city\" name=\"city\" id=\"city\" placeholder=\"City\">\r\n");
      out.write("                    <span ng-if=\"user.name != userInfo.username\" class=\"paragraph-text\">{{userInfo.city}}</span>\r\n");
      out.write("\r\n");
      out.write("                    <div ng-show=\"formProfile.$submitted\">\r\n");
      out.write("                        <div class=\"error-input\" ng-show=\"formProfile.city.$error.minlength ||\r\n");
      out.write("                             formProfile.name.$error.maxlength\">The city should be in the range 2 - 30\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <label for=\"aboutMe\" class=\"col-sm-3 control-label\">About Me</label>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"col-sm-9\">\r\n");
      out.write("                    <textarea ng-if=\"user.name == userInfo.username\"  maxlength=\"1000\" style=\"resize: none;height: 120px;\" class=\"form-control\"  ng-model=\"userInfo.aboutMe\" name=\"aboutMe\" id=\"aboutMe\"\r\n");
      out.write("                              placeholder=\"About Me\"></textarea>\r\n");
      out.write("                    <span ng-if=\"user.name != userInfo.username\" class=\"paragraph-text\">{{userInfo.aboutMe}}</span>\r\n");
      out.write("\r\n");
      out.write("                    <div ng-show=\"formProfile.$submitted\">\r\n");
      out.write("                        <div class=\"error-input\" ng-show=\"formProfile.aboutMe.$error.minlength ||\r\n");
      out.write("                             formProfile.aboutMe.$error.maxlength\">Your information should be not more than 2000 characters\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-md-3 form-horizontal\">\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <div style=\"padding: 10px;\">\r\n");
      out.write("                    <div class=\"text-center\"><kbd>Avatar Picture</kbd></div>\r\n");
      out.write("                    <div class=\"thumbnail\" ng-show=\"userInfo.hasAvatar && obj.flow.files.length == 0\">\r\n");
      out.write("                        <img src=\"/user/avatarimage?name={{userInfo.username}}\"/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"thumbnail\" ng-hide=\"userInfo.hasAvatar\">\r\n");
      out.write("                        <img src=\"/images/avatar.png\"/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"thumbnail\" ng-show=\"obj.flow.files.length\">\r\n");
      out.write("                        <img flow-img=\"obj.flow.files[0]\"/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div ng-if=\"user.name == userInfo.username\" class=\"text-center\">\r\n");
      out.write("                        <span class=\"btn btn-info\" ng-hide=\"userInfo.hasAvatar\" flow-btn\r\n");
      out.write("                              flow-attrs=\"{accept:'image/*'}\">Select image</span>\r\n");
      out.write("                        <span class=\"btn btn-info\" ng-show=\"userInfo.hasAvatar\" flow-btn\r\n");
      out.write("                              flow-attrs=\"{accept:'image/*'}\">Change</span>\r\n");
      out.write("                        <a href=\"#\" class=\"btn btn-danger\" ng-show=\"userInfo.hasAvatar\"\r\n");
      out.write("                           ng-click=\"removeImage()\">\r\n");
      out.write("                            Remove\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <div ng-if=\"user.name == userInfo.username\" class=\"checkbox text-center\">\r\n");
      out.write("                    <label>\r\n");
      out.write("                        <input type=\"checkbox\" ng-model=\"userInfo.male\">\r\n");
      out.write("                        Male\r\n");
      out.write("                    </label>\r\n");
      out.write("                </div>\r\n");
      out.write("                <span style=\"margin: 40px;\" ng-if=\"user.name != userInfo.username\">\r\n");
      out.write("                    <label class=\"control-label\">Sex : </label>\r\n");
      out.write("                    <span  ng-if=\"userInfo.male\" class=\"paragraph-text\">woman</span>\r\n");
      out.write("                    <span  ng-if=\"!userInfo.male\" class=\"paragraph-text\">man</span>\r\n");
      out.write("                </span>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div>\r\n");
      out.write("        <div  class=\"form-inline\" ng-hide=\"allGames.length == 0 || user.name != userInfo.username\">\r\n");
      out.write("            <div class=\"form-group\">\r\n");
      out.write("                <button type=\"button\" ng-click=\"addGame(selectGame)\" class=\"btn btn-primary\">Add Game</button>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"form-group\" >\r\n");
      out.write("                <select ng-model=\"gameSelect\" class=\"form-control\" ng-options=\"gameInfo.name for gameInfo in allGames\">\r\n");
      out.write("                    <option value=\"\">Choose The Game</option>\r\n");
      out.write("                </select>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"margin-top20\">\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">\r\n");
      out.write("\r\n");
      out.write("                <div class=\"panel panel-default\" ng-repeat=\"userGame in userInfo.userGames\">\r\n");
      out.write("                    <div class=\"panel-heading\" role=\"tab\" id=\"heading{{$index}}\">\r\n");
      out.write("                        <h4 class=\"panel-title\">\r\n");
      out.write("                            <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#\"\r\n");
      out.write("                               aria-expanded=\"false\" aria-controls=\"collapse{{userGame.pk.game.name}}\">\r\n");
      out.write("                                {{userGame.pk.game.name}}\r\n");
      out.write("                                <span ng-if=\"user.name == userInfo.username\"  ng-click=\"removeGame(userGame)\" class=\"glyphicon glyphicon-remove block-right\" aria-hidden=\"true\"></span>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </h4>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div id=\"collapse{{userGame.pk.game.name}}\" class=\"panel-collapse collapse in\" role=\"tabpanel\"\r\n");
      out.write("                         aria-labelledby=\"heading{{userGame.pk.game.name}}\">\r\n");
      out.write("                        <div class=\"panel-body\">\r\n");
      out.write("                            <label class=\"col-sm-4 control-label\">{{userGame.pk.game.nickname}}</label>\r\n");
      out.write("\r\n");
      out.write("                            <div class=\"col-sm-4\">\r\n");
      out.write("                                <input style=\"margin-top: -5px; \" ng-if=\"user.name == userInfo.username\" type=\"text\" name=\"nickname{{$index}}\" required=\"\" class=\"form-control\" ng-model=\"userGame.account\"\r\n");
      out.write("                                       placeholder=\"{{userGame.pk.game.nickname}}\">\r\n");
      out.write("                                <span ng-if=\"user.name != userInfo.username\">{{userGame.account}}</span>\r\n");
      out.write("                            </div>\r\n");
      out.write("\r\n");
      out.write("                                <div class=\"col-sm-4\" ng-show=\"formProfile.$submitted\">\r\n");
      out.write("                                    <span class=\"error-input\" ng-show=\"formProfile.nickname{{$index}}.$error.required\">\r\n");
      out.write("                                        Nickname is required!\r\n");
      out.write("                                    </span>\r\n");
      out.write("                                </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div ng-if=\"user.name == userInfo.username\" class=\"form-group\">\r\n");
      out.write("            <button  type=\"submit\" class=\"btn btn-primary\" ng-click=\"saveUser(formProfile.$valid)\">Save</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
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
