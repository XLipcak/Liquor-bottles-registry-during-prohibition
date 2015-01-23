<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<form class="form-inline" role="form">
    
        <tr>
            <td><s:label for="storeSelect" name="bottle.store.name" /></td>
            <td>
                <s:select class="btn btn-default dropdown-toggle" name="storeID" id="storeSelect">  
                    <s:option value="0"> / </s:option>
                    <c:forEach items="${actionBean.storeList}" var="store">
                        <s:option value="${store.id}">${store.name}</s:option>
                    </c:forEach>     
                </s:select>
            </td>
        </tr>
        
        <tr>
            <td><s:label for="bottleTypeSelect" name="bottle.bottleType.name" /></td>
            <td>
                <s:select class="btn btn-default dropdown-toggle" name="bottleTypeID" id="bottleTypeSelect">  
                    <s:option value="0"> / </s:option>
                    <c:forEach items="${actionBean.bottleTypeList}" var="bottleType">
                        <s:option value="${bottleType.id}">${bottleType.name}</s:option>
                    </c:forEach>     
                </s:select>
            </td>
        </tr>
        
        <tr>
            <td><s:label for="select" name="bottleType.common.name" /></td>
            <td>
                <s:select class="btn btn-default dropdown-toggle" name="producerID" id="select">
                    <s:option value="0"> / </s:option>
                    <c:forEach items="${actionBean.producerList}" var="producer">
                        <s:option value="${producer.id}">${producer.name}</s:option>
                    </c:forEach>     
                </s:select> 
            </td>
        </tr>
        
        <tr>
            <td><s:label for="f3" name="bottle.batchNumber"/></td>
            <td><s:text id="f3" class="form-control" name="batchNumber" value=""/></td>
        </tr>
        <tr>
            <td><s:label for="f4" name="bottle.stamp"/></td>
            <td><s:text id="f4" class="form-control" name="stamp" value=""/></td>
        </tr>
        <tr>
            <td><s:label for="f5" name="common.dateFrom"/></td>
            <td><s:text id="f5" class="form-control" name="dateFrom" value=""/></td>
        </tr>
        <tr>
            <td><s:label for="f6" name="common.dateTo"/></td>
            <td><s:text id="f6" class="form-control" name="dateTo" value=""/></td>
        </tr>
        <tr>
            <td><s:label for="toxicitySelect" name="bottle.toxicity"/></td>
            <td>
                <s:select class="btn btn-default dropdown-toggle" name="toxicitySelect" id="select" value="${actionBean.toxicitySelect}">  
                    <s:options-enumeration enum="muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO" />   
                </s:select>
            </td>
        </tr>
    
</form>