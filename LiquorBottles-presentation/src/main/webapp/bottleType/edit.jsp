<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="bottleType.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean" var="actionBean"/>

        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean">
            <s:hidden name="bottleType.id"/>
            <fieldset><legend><f:message key="common.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit class="btn btn-primary" name="save"><f:message key="common.save"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
