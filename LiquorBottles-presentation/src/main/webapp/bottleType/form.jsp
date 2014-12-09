<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:errors/>
<table class="table">
    <tr>
        <th>
            <s:label for="select" name="bottleType.common.name" />
        </th>
        <td>
            <s:select class="btn btn-default dropdown-toggle" name="producerID" id="select">  
                <c:forEach items="${actionBean.producerList}" var="producer">
                    <s:option value="${producer.id}">${producer.name}</s:option>
                </c:forEach>     
            </s:select>  
        </td>
    </tr>
    <tr>
        <th><s:label for="s1" name="bottleType.name"/></th>
        <td><s:text class="form-control" id="s1" name="bottleType.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="bottleType.volume"/></th>
        <td><s:text class="form-control" id="s2" name="bottleType.volume"/></td>
    </tr>
    <tr>
        <th><s:label for="s3" name="bottleType.alcType"/></th>
        <td><s:text class="form-control" id="s3" name="bottleType.alcType"/></td>
    </tr>
    <tr>
        <th><s:label for="s4" name="bottleType.power"/></th>
        <td><s:text class="form-control" id="s4" name="bottleType.power"/></td>
    </tr>

</table>