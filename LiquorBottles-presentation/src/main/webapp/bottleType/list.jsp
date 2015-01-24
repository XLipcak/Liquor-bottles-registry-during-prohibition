<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="bottleType.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean" var="actionBean"/>
        
        <!--    Filter    -->
        <s:form class="form-inline" beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean">
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

        <h2><f:message key="bottleType.list.allbottleTypes"/></h2>
        <table class="table table-hover">
            <tr>
                <th >id</th>
                <th ><f:message key="bottleType.name"/></th>
                <th ><f:message key="bottleType.alcType"/></th>
                <th ><f:message key="bottleType.power"/></th>
                <th ><f:message key="bottleType.volume"/></th>
                <th ><f:message key="bottleType.common.name"/></th>
                <th></th>
                <th></th>
            </tr>
            <%--  vypis vsetkeho   --%>
            <c:forEach items="${actionBean.bottleTypeList}" var="bottleType">
                <tr>
                    <td>${bottleType.id}</td>
                    <td><c:out value="${bottleType.name}"/></td>
                    <td><c:out value="${bottleType.alcType}"/></td>
                    <td><c:out value="${bottleType.power}"/></td>
                    <td><c:out value="${bottleType.volume}"/></td>
                    <td><c:out value="${bottleType.producer.name}"/></td>

                    <%--   edit  --%>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_PRODUCER')">
                    <td>
                        <s:link class="btn btn-success" beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean" event="edit">
                            <s:param name="bottleType.id" value="${bottleType.id}"/>
                            <f:message key="common.edit"/>
                        </s:link>
                    </td>
                    </sec:authorize>
                
                    <%--   delete   --%>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean">
                            <s:hidden name="bottleType.id" value="${bottleType.id}"/>
                            <s:submit class="btn btn-danger" name="delete"><f:message key="common.delete"/></s:submit>
                        </s:form>
                    </td> 
                    </sec:authorize>

                    <%--  end vypis --%>
                </tr>
            </c:forEach>
        </table>
                      
        <%--   add new  --%>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_PRODUCER')">
            <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean">
                <fieldset>
                    <legend><f:message key="bottleType.list.newbottleType"/></legend>
                    <s:errors/>
                    <table class="table">
                        <%@include file="form.jsp"%>
                        <tr>
                            <td></td>
                            <td><s:submit class="btn btn-primary" name="add"><f:message key="bottleType.add.button"/></s:submit></td>
                        </tr>
                    </table>
                </fieldset>
            </s:form>
        </sec:authorize>
        
    </s:layout-component>
</s:layout-render>
