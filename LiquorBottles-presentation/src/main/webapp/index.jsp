<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        
        <ul>
            <%-- store --%>
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean"><f:message key="index.store.link"/></s:link></li> 
            <!-- producer -->
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean"><f:message key="index.producer.link"/></s:link></li>
            <!-- bottleType -->
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean"><f:message key="index.bottleType.link"/></s:link></li>
            <!-- bottle -->
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean"><f:message key="index.bottle.link"/></s:link></li>
        </ul>
        
        
    </s:layout-component>
</s:layout-render>
