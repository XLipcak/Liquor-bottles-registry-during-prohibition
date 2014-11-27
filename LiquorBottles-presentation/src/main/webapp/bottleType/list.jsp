<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="bottleType.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean" var="actionBean"/>

        <p><f:message key="bottleType.list.allbottleTypes"/></p>

        <%-- -private long id;
    private String name;
    private String alcType;
    private int power;
    private int volume;
    private ProducerDTO producer; --%>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="bottleType.name"/></th>
                <th><f:message key="bottleType.alcType"/></th>
                <th><f:message key="bottleType.power"/></th>
                <th><f:message key="bottleType.volume"/></th>
                <th><f:message key="bottleType.producer.name"/></th>
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

                    <%--   edit TODO --%>
                    <td>
                        <s:link beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean" event="edit"><s:param name="bottleType.id" value="${bottleType.id}"/>edit</s:link>
                        </td>


                    <%--   delete TODO  --%>
                    <td>
                        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean">
                            <s:hidden name="bottleType.id" value="${bottleType.id}"/>
                            <s:submit name="delete"><f:message key="bottleType.list.delete"/></s:submit>
                        </s:form>
                    </td> 

                    <%--  end vypis --%>
                </tr>
            </c:forEach>

        </table>

        <%--   add new TODO --%>
        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean">
            <fieldset><legend><f:message key="bottleType.list.newbottleType"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="bottleType.add.button"/></s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
