<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table>
    
    <tr>
        <th>
            <s:label for="storeSelect" name="bottle.store.name" />
        </th>
        <td>
            <s:select class="btn btn-default dropdown-toggle" name="storeID" id="storeSelect">  
                <c:forEach items="${actionBean.storeList}" var="store">
                    <s:option value="${store.id}">${store.name}</s:option>
                </c:forEach>     
            </s:select>  
        </td>
    </tr>
    
    <tr>
        <th>
            <s:label for="bottleTypeSelect" name="bottle.bottleType.name" />
        </th>
        <td>
            <s:select class="btn btn-default dropdown-toggle" name="bottleTypeID" id="bottleTypeSelect">  
                <c:forEach items="${actionBean.bottleTypeList}" var="bottleType">
                    <s:option value="${bottleType.id}">${bottleType.name}</s:option>
                </c:forEach>     
            </s:select>  
        </td>
    </tr>
    
    <tr>
        <th><s:label for="batchNumberInput" name="bottle.batchNumber"/></th>
        <td><s:text class="form-control" id="batchNumberInput" name="bottle.batchNumber"/></td>
    </tr>
    
    <tr>
        <th><s:label for="stampInput" name="bottle.stamp"/></th>
        <td><s:text class="form-control" id="stampInput" name="bottle.stamp"/></td>
    </tr>
    
    <tr>
        <th><s:label for="dateInput" name="bottle.dateOfBirth"/></th>
        <td><s:text class="form-control" id="dateInput" name="bottle.dateOfBirth" formatType="date"/></td>
    </tr>
    
    <tr>
        <th><s:label for="toxicitySelect" name="bottle.toxicity"/></th>
        <td>
            <s:select class="btn btn-default dropdown-toggle" name="toxicitySelect" id="select" value="${actionBean.toxicitySelect}">  
                <s:options-enumeration enum="muni.fi.pa165.liquorbottles.service.dto.ToxicityDTO" />   
        </s:select>
        </td>
    </tr>

</table>