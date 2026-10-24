package org.apache.jsp.js.Filemanager_002dmaster.connectors.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.den.nartex.*;
import com.den.json.JSONObject;
import java.io.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

public final class filemanager_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 
/**
 *	Check if user is authorized
 *
 *	@return boolean true is access granted, false if no access
 */
	public boolean auth() {
		// You can insert your own code over here to check if the user is authorized.
		return true;
	}





  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/js/Filemanager-master/connectors/jsp/auth.jsp");
  }

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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');

/*
 *	connector filemanager.jsp
 *
 *	@license	MIT License
 *	@author		Dick Toussaint <d.tricky@gmail.com>
 *	@copyright	Authors
 */

	FileManager fm = new FileManager(request.getServletContext(), request);

	JSONObject responseData = null;

	String mode = "";
    boolean putTextarea = false;
	if(!fm.getAuth()) {
		fm.error(fm.lang("AUTHORIZATION_REQUIRED"));
	}
	else {
		if(request.getMethod().equals("GET")) {
			if(request.getParameter("mode") != null && request.getParameter("mode") != "") {
				mode = request.getParameter("mode");
				if (mode.equals("getinfo")){
					if(fm.setGetVar("path", request.getParameter("path"))) {
						responseData = fm.getInfo();
					}
				}
				else if (mode.equals("getfolder")){
					if(fm.setGetVar("path", request.getParameter("path"))) {
						responseData = fm.getFolder();
					}
				}
				else if (mode.equals("rename")){
					if(fm.setGetVar("old", request.getParameter("old")) &&
							fm.setGetVar("new", request.getParameter("new"))) {
						responseData = fm.rename();
					}
				}
				else if (mode.equals("delete")){
					if(fm.setGetVar("path", request.getParameter("path"))) {
						responseData = fm.delete();
					}
				}
				else if (mode.equals("addfolder")){
					if(fm.setGetVar("path", request.getParameter("path")) &&
							fm.setGetVar("name", request.getParameter("name"))) {
						responseData = fm.addFolder();
					}
				}
				else if (mode.equals("download")){
					if(fm.setGetVar("path", request.getParameter("path"))) {
						fm.download(response);
					}
				}
				else if (mode.equals("preview")){
					if(fm.setGetVar("path", request.getParameter("path"))) {
						fm.preview(response);
					}
				}
				else {
					fm.error(fm.lang("MODE_ERROR"));
				}
			}
		}
		else if(request.getMethod().equals("POST")){
			mode = "upload";
			responseData = fm.add();
			putTextarea = true;
		}
	}
	if (responseData == null){
		responseData = fm.getError();
	}
	if (responseData != null){
	  	PrintWriter pw = response.getWriter();
	  	String responseStr = responseData.toString();
	  	if (putTextarea)
	  		responseStr = "<textarea>" + responseStr + "</textarea>";
	  	//fm.log("c:\\logfilej.txt", "mode:" + mode + ",wrapper:" + responseStr);
	  	pw.print(responseStr);
	  	pw.close();
	}
  
      out.write('\r');
      out.write('\n');
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
