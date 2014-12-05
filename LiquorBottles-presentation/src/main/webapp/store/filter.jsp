<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<form class="form-inline" role="form">
    <s:label for="f1" name="common.name"/>
    <s:text name="name" class="form-control" id="f1"/>
    <s:label for="f2" name="common.address"/>
    <s:text id="f2" class="form-control" name="address" value=""/>
</form>

<%--<table class="filter">
    <tr>
        <th><s:label for="f1" name="common.name"/></th>
        <td><s:text id="f1" name="name" value=""/></td>

        <th><s:label for="f2" name="common.address"/></th>
        <td><s:text id="f2" name="address" value=""/></td>
    </tr>
</table>--%>