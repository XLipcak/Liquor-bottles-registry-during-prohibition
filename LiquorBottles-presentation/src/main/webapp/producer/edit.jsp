<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="producer.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean" var="actionBean"/>

        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean">
            <s:hidden name="producer.id"/>
            <fieldset>
                <legend><f:message key="common.edit"/></legend>
                <s:errors/> 
                <table class="table">
                    <%@include file="form.jsp"%>
                    <tr>
                        <td></td>
                        <td><s:submit name="save" class="btn btn-primary"><f:message key="common.save"/></s:submit></td>
                    </tr>
                </table>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>