<%-- 
    Document   : list
    Created on : Nov 27, 2014, 9:52:39 PM
    Author     : Jakub Lipcak, Masaryk University
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="police.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean" var="actionBean"/>

        <p><f:message key="police.list.allPolice"/></p>

        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean">
            <fieldset><legend><f:message key="filter.list"/></legend>
                <%@include file="filter.jsp"%>
                <s:submit name="filter"><f:message key="filter.submit"/></s:submit>
                </fieldset>
        </s:form>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="police.name"/></th>
                <th><f:message key="police.address"/></th>
                <th></th>
                <th></th>
            </tr>
            <%--  vypis vsetkeho   --%>
            <c:forEach items="${actionBean.policeList}" var="police">
                <tr>
                    <td>${police.id}</td>
                    <td><c:out value="${police.name}"/></td>
                    <td><c:out value="${police.address}"/></td>


                    <td>
                        <s:link beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean" event="edit"><s:param name="police.id" value="${police.id}"/>edit</s:link>
                        </td>

                    <%--   delete  --%>
                    <td>
                        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean">
                            <s:hidden name="police.id" value="${police.id}"/>
                            <s:submit name="delete"><f:message key="police.list.delete"/></s:submit>
                        </s:form>
                    </td> 

                    <%--  end vypis --%>
                </tr>
            </c:forEach>

        </table>

        <%--   add new TODO --%>
        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean">
            <fieldset><legend><f:message key="police.list.newpolice"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Create Police</s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
