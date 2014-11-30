<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="producer.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean" var="actionBean"/>

        <p><f:message key="producer.list.allproducers"/></p>

        <table class="basic">
            <tr>
                <th class="info">id</th>
                <th class="info"><f:message key="common.name"/></th>
                <th class="info"><f:message key="common.address"/></th>
                <th></th>
                <th></th>
            </tr>
            <%--  vypis vsetkeho   --%>
            <c:forEach items="${actionBean.producerList}" var="producer">
                <tr>
                    <td>${producer.id}</td>
                    <td><c:out value="${producer.name}"/> </td>
                    <td><c:out value="${producer.address}"/></td>
                    
                    <td>
                     <s:link beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean" event="edit">
                         <s:param name="producer.id" value="${producer.id}"/>
                         <f:message key="common.edit"/>
                     </s:link>
                    </td>
                    
                    
                    <%--   delete TODO  --%>
                    <td>
                        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean">
                            <s:hidden name="producer.id" value="${producer.id}"/>
                            <s:submit name="delete"><f:message key="common.delete"/></s:submit>
                        </s:form>
                    </td>
                    
                </tr>

            </c:forEach>

        </table>

                    <%--   add new TODO --%>
        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean">
            <fieldset><legend><f:message key="producer.list.newproducer"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="producer.add.button"/></s:submit>
            </fieldset>
        </s:form>

            
    </s:layout-component>
</s:layout-render>
