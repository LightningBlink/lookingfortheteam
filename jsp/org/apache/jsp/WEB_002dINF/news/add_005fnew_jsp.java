package org.apache.jsp.WEB_002dINF.news;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_005fnew_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("    CKEDITOR.replace('editor1', {\r\n");
      out.write("        filebrowserBrowseUrl: '/js/Filemanager-master/index.jsp'\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("    <form action=\"/admin/savenew\" method=\"POST\">\r\n");
      out.write("        <input name=\"id\" type=\"hidden\" value=\"{{new.id}}\">\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <label for=\"header\">Header</label>\r\n");
      out.write("            <input type=\"text\" class=\"form-control\" name=\"header\" id=\"header\" value=\"{{new.header}}\" placeholder=\"Enter header\">\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <label for=\"editor1\">Description</label>\r\n");
      out.write("            <textarea name=\"editor1\" class=\"form-control\" id=\"editor1\" rows=\"10\" cols=\"80\">\r\n");
      out.write("                {{new.description}}\r\n");
      out.write("            </textarea>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <button class=\"btn btn-default\" type=\"submit\">Publish</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </form>\r\n");
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
