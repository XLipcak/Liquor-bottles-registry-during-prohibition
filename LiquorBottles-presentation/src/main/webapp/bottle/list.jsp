<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="bottle.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean" var="actionBean"/>

        <div>
            <!--    Statistics image    -->
            <img src="images/chart.jpg" alt="statistics" />
        </div>

            <!--    Filter    -->
        <s:form class="form-inline" beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean"> 
            <s:errors/>
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

        <h2><f:message key="bottle.list.allbottles"/></h2>
        <table class="table table-hover">
            <tr>
                <th >id</th>
                <th ><f:message key="bottle.common.name"/></th>
                <th ><f:message key="bottle.bottleType.name"/></th>
                <th ><f:message key="bottle.batchNumber"/></th>
                <th ><f:message key="bottle.stamp"/></th>
                <th ><f:message key="bottle.dateOfBirth"/></th>
                <th ><f:message key="bottle.toxicity"/></th>
                <th></th>
                <th></th>
            </tr>
            <%--  vypis vsetkeho   --%>
            <c:forEach items="${actionBean.bottleList}" var="bottle">
                <tr>
                    <td>${bottle.id}</td>
                    <td><c:out value="${bottle.store.name}"/></td>
                    <td><c:out value="${bottle.bottleType.name}"/></td>
                    <td><c:out value="${bottle.batchNumber}"/></td>
                    <td><c:out value="${bottle.stamp}"/></td>
                    <td><c:out value="${bottle.dateOfBirth}"/></td>
                    <td><c:out value="${bottle.toxicity}"/></td>

                    <%--   edit  --%>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_POLICE', 'ROLE_STORE')">
                        <td>
                            <s:link class="btn btn-success" beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean" event="edit">
                                <s:param name="bottle.id" value="${bottle.id}"/>
                                <f:message key="common.edit"/>
                            </s:link>
                        </td>
                    </sec:authorize>

                    <%--   delete  --%>   
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean">
                                <s:hidden name="bottle.id" value="${bottle.id}"/>
                                <s:submit name="delete" class="btn btn-danger"><f:message key="common.delete"/></s:submit>
                            </s:form>
                        </td> 
                    </sec:authorize>
                    <%--  end vypis --%>
                </tr>
            </c:forEach>

        </table>

        <%--   add  --%>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_STORE')">
            <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean">
                <fieldset>
                    <legend><f:message key="bottle.list.newbottle"/></legend>
                    <s:errors/>
                    <table class="table">
                        <%@include file="form.jsp"%>
                        <tr>
                            <td></td>
                            <td><s:submit name="add" class="btn btn-primary"><f:message key="bottle.add.button"/></s:submit></td>
                        </tr>
                    </table>
                </fieldset>
            </s:form>
        </sec:authorize>
    </s:layout-component>
</s:layout-render>
