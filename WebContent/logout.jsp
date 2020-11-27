<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
if(session!=null) session.invalidate();
response.sendRedirect("index.jsp");
%>