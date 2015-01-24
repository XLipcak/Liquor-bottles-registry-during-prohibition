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



        <s:form class="form-inline" beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean">
            <fieldset class="filter">
                <legend><f:message key="filter.list"/></legend>
                <table class="table">
                    <%@include file="filter.jsp"%>
                    <tr>
                        <td></td>
                        <td><s:submit class="btn btn-default" name="filter"><f:message key="filter.submit"/></s:submit></td>
                    </tr>
                </table>
            </fieldset>
        </s:form>
        
        <h2><f:message key="police.list.allPolice"/></h2>
        <table class="table table-hover" >
            <tr>
                <th >id</th>
                <th ><f:message key="common.name"/></th>
                <th ><f:message key="common.address"/></th>
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
                        <s:link class="btn btn-success" beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean" event="edit">
                            <s:param name="police.id" value="${police.id}"/>
                            <f:message key="common.edit"/>
                        </s:link>
                    </td>

                    <%--   delete  --%>
                    <td>
                        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean">
                            <s:hidden name="police.id" value="${police.id}"/>
                            <s:submit class="btn btn-danger" name="delete"><f:message key="common.delete"/></s:submit>
                        </s:form>
                    </td> 

                    <%--  end vypis --%>
                </tr>
            </c:forEach>

        </table>

        <%--   add new  --%>
        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean">
            <fieldset>
                <legend><f:message key="police.list.newpolice"/></legend>
                <s:errors/>
                <table class="table">
                    <%@include file="form.jsp"%>
                    <tr>
                        <td></td>
                        <td><s:submit class="btn btn-primary" name="add"><f:message key="police.add.button"/></s:submit></td>
                    </tr>
                </table>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
