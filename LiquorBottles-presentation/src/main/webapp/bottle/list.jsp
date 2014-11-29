<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="bottle.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean" var="actionBean"/>

        <p><f:message key="bottle.list.allbottles"/></p>

        <table class="basic">
            <tr>
                <th class="info">id</th>
                <th class="info"><f:message key="bottle.store.name"/></th>
                <th class="info"><f:message key="bottle.bottleType.name"/></th>
                <th class="info"><f:message key="bottle.batchNumber"/></th>
                <th class="info"><f:message key="bottle.stamp"/></th>
                <th class="info"><f:message key="bottle.dateOfBirth"/></th>
                <th class="info"><f:message key="bottle.toxicity"/></th>
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

                    <%--   edit TODO --%>
                    <td>
                        <s:link beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean" event="edit"><s:param name="bottle.id" value="${bottle.id}"/>edit</s:link>
                        </td>


                    <%--   delete TODO  --%>
                    <td>
                        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean">
                            <s:hidden name="bottle.id" value="${bottle.id}"/>
                            <s:submit name="delete"><f:message key="bottle.list.delete"/></s:submit>
                        </s:form>
                    </td> 

                    <%--  end vypis --%>
                </tr>
            </c:forEach>

        </table>

        <%--   add new TODO --%>
        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean">
            <fieldset><legend><f:message key="bottle.list.newbottle"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="bottle.add.button"/></s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
