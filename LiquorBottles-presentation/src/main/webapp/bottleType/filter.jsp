<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<form class="form-inline" role="form">

    <s:label for="select" name="bottleType.common.name" />
    <s:select class="btn btn-default dropdown-toggle" name="producerID" id="select"> 
        <s:option value="0"> / </s:option>
        <c:forEach items="${actionBean.producerList}" var="producer">
            <s:option value="${producer.id}">${producer.name}</s:option>
            </c:forEach>     
    </s:select> 
    <s:label for="s1" name="bottleType.name"/>
    <s:text class="form-control" id="s1" name="name"/>
    <s:label for="s2" name="bottleType.volume"/>
    <s:text class="form-control" id="s2" name="volume"/>
    <s:label for="s3" name="bottleType.alcType"/>
    <s:text class="form-control" id="s3" name="alcType"/>
    <s:label for="s4" name="bottleType.power"/>
    <s:text class="form-control" id="s4" name="power"/>
</form>