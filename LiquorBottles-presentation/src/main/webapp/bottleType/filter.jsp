<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<form class="form-inline" role="form">

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
        <td><s:label for="s1" name="bottleType.name"/></td>
        <td><s:text class="form-control" id="s1" name="name"/></td>
    </tr>
    <tr>
        <td><s:label for="s2" name="bottleType.volume"/></td>
        <td><s:text class="form-control" id="s2" name="volume"/></td>
    </tr>
    <tr>
        <td><s:label for="s3" name="bottleType.alcType"/></td>
        <td><s:text class="form-control" id="s3" name="alcType"/></td>
    </tr>
    <tr>
        <td><s:label for="s4" name="bottleType.power"/></td>
        <td><s:text class="form-control" id="s4" name="power"/></td>
    </tr>
</form>