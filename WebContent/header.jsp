  <link rel="stylesheet" type="text/css" href="css/top.css">
  <%@page import="user.UserDAO" %>
<%

String user_id1 = (String)session.getAttribute("id");
UserDAO userDAO1=new UserDAO();
String user_name1=userDAO1.getUser(user_id1).getUser_name();%>
<div class="top_container">
 			<a href="main.jsp">
                <img style="float:left;margin-left:30px;" src="images/Pen_Logo.png">
            </a>
            <a href="#">
                <img src="images/Icon_Self.png">
            </a>
            
            <span id="user_name" style="position:relative;top:-15px;left:-5px;"><%=user_name1%></span>
            
            <a href="#">
                <img src="images/Icon_Heart.png">
            </a>
          
            <button type="button" onclick="location.href='logout.jsp';" id="logout_btn"><img src="images/Icon_LogOut.png" width="30" height="30"></button>
           
</div>

