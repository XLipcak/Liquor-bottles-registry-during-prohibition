<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="store.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean" var="actionBean"/>

        <p><f:message key="store.list.allbooks"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="store.name"/></th>
                <th><f:message key="store.address"/></th>
                <th></th>
                <th></th>
            </tr>
            <%--  vypis vsetkeho   --%>
                <c:forEach items="${actionBean.storeList}" var="store">
                <tr>
                    <td>${store.id}</td>
                    <td><c:out value="${store.name}"/></td>
                    <td><c:out value="${store.address}"/></td>
            
                    <%--   edit TODO
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.books.BooksActionBean" event="edit"><s:param name="book.id" value="${book.id}"/>edit</s:link>
                    </td>
                    
                    --%>
                    <%--   delete TODO
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.books.BooksActionBean">
                            <s:hidden name="book.id" value="${book.id}"/>
                            <s:submit name="delete"><f:message key="book.list.delete"/></s:submit>
                        </s:form>
                    </td> 
                    --%>
            <%--  end vypis --%>
                 </tr>
             </c:forEach>
            
        </table>
        
        <%--   add new TODO --%>
        <s:form beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean">
            <fieldset><legend><f:message key="store.list.newstore"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Vytvořit novy obchod</s:submit>
            </fieldset>
        </s:form>
        
    </s:layout-component>
</s:layout-render>
