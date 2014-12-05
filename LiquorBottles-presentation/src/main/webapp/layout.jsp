<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
  <title><f:message key="${titlekey}"/></title>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<s:layout-component name="header"/>
</head>
<body>
   
   <ul class="nav nav-pills" role="navigation">
       <li role="presentation"><s:link href="/index.jsp"><f:message key="navigation.index" /></s:link></li>
       <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.StoreActionBean"><f:message key="index.store.link"/></s:link></li> 
            <!-- producer -->
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.ProducerActionBean"><f:message key="index.producer.link"/></s:link></li>
            <!-- bottleType -->
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.BottleTypeActionBean"><f:message key="index.bottleType.link"/></s:link></li>
            <!-- bottle -->
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.BottleActionBean"><f:message key="index.bottle.link"/></s:link></li>
            <!-- police -->
            <li><s:link beanclass="muni.fi.pa165.liquorbottles.presentation.PoliceActionBean"><f:message key="index.police.link"/></s:link></li>

    </ul>
     <h1><f:message key="${titlekey}"/></h1>
   <div class="container">
       <s:messages/>
       <s:layout-component name="body"/>
    </div>
</body>
</html>
</s:layout-definition>