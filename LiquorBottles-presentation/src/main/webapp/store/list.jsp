<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="store.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean" var="actionBean"/>

        

        <s:form  class="form-inline" beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean">
                <fieldset><legend><f:message key="filter.list"/></legend>
                    <%@include file="filter.jsp"%>
                    <s:submit class="btn btn-default" name="filter"><f:message key="filter.submit"/></s:submit>
                </fieldset>
        </s:form>
        
        <p><f:message key="store.list.allstores"/></p>
        <table class="table table-hover">
            <tr> 
                <th >id</th>
                <th ><f:message key="common.name"/></th>
                <th ><f:message key="common.address"/></th>
                <th></th>
                <th></th>
            </tr>
            <%--  vypis vsetkeho   --%>
                <c:forEach items="${actionBean.storeList}" var="store">
                <tr>
                    <td>${store.id}</td>
                    <td><c:out value="${store.name}"/></td>
                    <td><c:out value="${store.address}"/></td>
            
                    <%--   edit  --%>
                    <td>
                     <s:link class="btn btn-success" beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean" event="edit">
                         <s:param name="store.id" value="${store.id}"/>
                         <f:message key="common.edit"/>
                     </s:link>
                    </td>
                    
                    
                    <%--   delete   --%>
                    <td>
                        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean">
                            <s:hidden name="store.id" value="${store.id}"/>
                            <s:submit name="delete" class="btn btn-danger"><f:message key="common.delete"/></s:submit>
                        </s:form>
                    </td> 
                    
            <%--  end vypis --%>
                 </tr>
             </c:forEach>
            
        </table>
        
        <%--   add new  --%>
        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean">
            <fieldset><legend><f:message key="store.list.newstore"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add" class="btn btn-default"><f:message key="store.button.add"/></s:submit>
            </fieldset>
        </s:form>
        
    </s:layout-component>
</s:layout-render>
