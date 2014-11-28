<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    
    <tr>
        <th>
            <s:label for="select" name="storeID">store</s:label>
        </th>
        <td>
            <s:select name="storeID" id="select">  
                <c:forEach items="${actionBean.storeList}" var="store">
                    <s:option value="${store.id}">${store.name}</s:option>
                </c:forEach>     
            </s:select>  
        </td>
    </tr>
    
    <tr>
        <th>
            <s:label for="select" name="bottleTypeID">bottle type</s:label>
        </th>
        <td>
            <s:select name="bottleTypeID" id="select">  
                <c:forEach items="${actionBean.bottleTypeList}" var="bottleType">
                    <s:option value="${bottleType.id}">${bottleType.name}</s:option>
                </c:forEach>     
            </s:select>  
        </td>
    </tr>
    
    <tr>
        <th><s:label for="batchNumberInput" name="bottle.batchNumber"/></th>
        <td><s:text id="batchNumberInput" name="bottle.batchNumber"/></td>
    </tr>
    
    <tr>
        <th><s:label for="stampInput" name="bottle.stamp"/></th>
        <td><s:text id="stampInput" name="bottle.stamp"/></td>
    </tr>
    
    <tr>
        <th><s:label for="dateInput" name="bottle.dateOfBirth"/></th>
        <td><s:text id="dateInput" name="bottle.dateOfBirth" formatType="date"/></td>
    </tr>
    
    <tr>
        <th><s:label for="toxicityInput" name="bottle.toxicity"/></th>
        <td><s:checkbox id="toxicityInput" name="bottle.toxicity"/></td>
    </tr>

</table>