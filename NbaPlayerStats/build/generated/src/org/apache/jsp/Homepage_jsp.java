package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">       \n");
      out.write("        <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/css/style.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Wordpress API Showcase</h1>\n");
      out.write("        <div class=\"getPostsDiv\">\n");
      out.write("            <h2>Get Posts</h2>\n");
      out.write("            <form method=\"get\" action=\"WordpressController\">\n");
      out.write("                <input type=\"button\" value=\"getPosts\" id=\"getPosts\">\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"showPosts\">\n");
      out.write("            <p id=\"allPosts\"></p>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"getUsersDiv\">\n");
      out.write("            <h2>Get Users</h2>\n");
      out.write("            <form method=\"get\" action=\"WordpressController\">\n");
      out.write("                <input type=\"button\" value=\"getUsers\" id=\"getUsers\">\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"showUsers\">\n");
      out.write("            <p id=\"allUsers\"></p>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"createPostDiv\">\n");
      out.write("            <h2>Create Post</h2>\n");
      out.write("            <button class=\"createPostBtn\" value=\"show\">Show</button>\n");
      out.write("            <button class=\"createPostBtn\" value=\"hide\">Hide</button>\n");
      out.write("            <form id=\"createPlayerForm\" method=\"post\" action=\"WordpressController\">\n");
      out.write("                <label for=\"playerName\">Player name:\n");
      out.write("                    <br>\n");
      out.write("                    <input type=\"text\" name=\"playerName\" id=\"playerName\">\n");
      out.write("                    <br>\n");
      out.write("                </label>\n");
      out.write("                <label for=\"playerDescription\">Player description:\n");
      out.write("                    <br>\n");
      out.write("                    <input type=\"text\" name=\"playerDescription\" id=\"playerDescription\">\n");
      out.write("                    <br>\n");
      out.write("                </label>\n");
      out.write("                <input type=\"button\" value=\"createPost\" id=\"createPost\">\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"createUserDiv\">\n");
      out.write("            <h2>Create User</h2>\n");
      out.write("            <button class=\"createUserBtn\" value=\"show\">Show</button>\n");
      out.write("            <button class=\"createUserBtn\" value=\"hide\">Hide</button>\n");
      out.write("            <form id=\"createUserForm\" method=\"post\" action=\"WordpressController\">\n");
      out.write("                <label for=\"username\"> Username:\n");
      out.write("                    <br>\n");
      out.write("                    <input type=\"text\" name=\"username\" id=\"username\">\n");
      out.write("                    <br>\n");
      out.write("                </label>\n");
      out.write("                <label for=\"password\"> Password:\n");
      out.write("                    <br>\n");
      out.write("                    <input type=\"text\" name=\"password\" id=\"password\">\n");
      out.write("                    <br>\n");
      out.write("                </label>\n");
      out.write("                <label for=\"email\">User email:\n");
      out.write("                    <br>\n");
      out.write("                    <input type=\"text\" name=\"email\" id=\"email\">\n");
      out.write("                    <br>\n");
      out.write("                </label>\n");
      out.write("                <input type=\"button\" value=\"createUser\" id=\"createUser\">\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function(){\n");
      out.write("              // Get all posts  \n");
      out.write("              $('#getPosts').click(function(){\n");
      out.write("                  const menu = $('#getPosts').val();\n");
      out.write("                  $.ajax({\n");
      out.write("                      type:'GET',\n");
      out.write("                      data:{menu: menu},\n");
      out.write("                      url:'WordpressController',\n");
      out.write("                      success: function(result){\n");
      out.write("                          const resObj = JSON.parse(result);\n");
      out.write("                          //console.log(resObj);\n");
      out.write("                          $('.showPosts').show();\n");
      out.write("                          resObj.forEach(function(player){\n");
      out.write("                              let content = '<h2>' + player.title + '</h2>' + player.content;\n");
      out.write("                              \n");
      out.write("                              $('#allPosts').append(content);\n");
      out.write("                          });\n");
      out.write("                      }\n");
      out.write("                  });\n");
      out.write("              });\n");
      out.write("              // Get all users\n");
      out.write("              $('#getUsers').click(function(){\n");
      out.write("                  const menu = $('#getUsers').val();\n");
      out.write("                  $.ajax({\n");
      out.write("                      type:'GET',\n");
      out.write("                      data:{menu: menu},\n");
      out.write("                      url:'WordpressController',\n");
      out.write("                      success: function(result){\n");
      out.write("                          const resObj = JSON.parse(result);\n");
      out.write("                          //console.log(resObj);\n");
      out.write("                          $('.showUsers').show();\n");
      out.write("                          resObj.forEach(function(user){\n");
      out.write("                              let content = '<p>User id: ' + user.id + '</p>' + '<p>Username: ' + user.name + '</p>'\n");
      out.write("                                    + '<p>User description: ' + user.content + '</p>';\n");
      out.write("                              \n");
      out.write("                              $('#allUsers').append(content);\n");
      out.write("                          });\n");
      out.write("                      }\n");
      out.write("                  });\n");
      out.write("              });\n");
      out.write("              // Create post\n");
      out.write("              $('#createPost').click(function(){\n");
      out.write("                  const menu = $('#createPost').val();\n");
      out.write("                  const name = $('#playerName').val();\n");
      out.write("                  const description = $('#playerDescription').val();\n");
      out.write("                  $.ajax({\n");
      out.write("                      type:'POST',\n");
      out.write("                      data:{\n");
      out.write("                          menu: menu,\n");
      out.write("                          name: name,\n");
      out.write("                          description: description\n");
      out.write("                      },\n");
      out.write("                      url:'WordpressController',\n");
      out.write("                      success: function(result){\n");
      out.write("                          console.log(result);\n");
      out.write("                          // message indicating success/failure of POST request\n");
      out.write("                          alert(result);\n");
      out.write("                      }\n");
      out.write("                  });\n");
      out.write("              });\n");
      out.write("              \n");
      out.write("              // Create user\n");
      out.write("              $('#createUser').click(function(){\n");
      out.write("                  const menu = $('#createUser').val();\n");
      out.write("                  const name = $('#username').val();\n");
      out.write("                  const password = $('#password').val();\n");
      out.write("                  const email = $('#email').val();\n");
      out.write("                  $.ajax({\n");
      out.write("                      type:'POST',\n");
      out.write("                      data:{\n");
      out.write("                          menu: menu,\n");
      out.write("                          username: name,\n");
      out.write("                          password: password,\n");
      out.write("                          email: email\n");
      out.write("                      },\n");
      out.write("                      url:'WordpressController',\n");
      out.write("                      success: function(result){\n");
      out.write("                          const resObj = JSON.parse(result);\n");
      out.write("                          // message indicating success/failure of POST request\n");
      out.write("                          alert(resObj.message);\n");
      out.write("                      }\n");
      out.write("                  });\n");
      out.write("              });\n");
      out.write("              // show/hide forms\n");
      out.write("              $(document).on(\"click\", function(event){\n");
      out.write("                  console.log(event.target.className)\n");
      out.write("                  const btnClass = event.target.className;\n");
      out.write("                  switch(btnClass){\n");
      out.write("                      \n");
      out.write("                      case 'createPostBtn':\n");
      out.write("                          if(event.target.value === 'show'){\n");
      out.write("                              $('#createPlayerForm').show();\n");
      out.write("                          } else {\n");
      out.write("                              $('#createPlayerForm').hide();\n");
      out.write("                          }\n");
      out.write("                          break;\n");
      out.write("                      case 'createUserBtn':\n");
      out.write("                          if(event.target.value === 'show'){\n");
      out.write("                              $('#createUserForm').show();\n");
      out.write("                          } else {\n");
      out.write("                              $('#createUserForm').hide();\n");
      out.write("                          }\n");
      out.write("                          break;\n");
      out.write("                  }\n");
      out.write("              })\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
