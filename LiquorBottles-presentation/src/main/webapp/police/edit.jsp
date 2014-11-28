<%-- 
    Document   : edit
    Created on : Nov 27, 2014, 9:21:55 PM
    Author     : Jakub Lipcak, Masaryk University
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="police.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean" var="actionBean"/>

        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean">
            <s:hidden name="police.id"/>
            <fieldset><legend><f:message key="police.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="save"><f:message key="police.edit.save"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
