<%-- 
    Document   : form
    Created on : Nov 27, 2014, 9:52:32 PM
    Author     : Jakub Lipcak, Masaryk University
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table class="table">
    <tr>
        <th><s:label for="s1" name="common.name"/></th>
        <td><s:text class="form-control" id="s1" name="police.name"/></td>
    </tr>
    <tr>
        <th><s:label for="s2" name="common.address"/></th>
        <td><s:text class="form-control" id="s2" name="police.address"/></td>
    </tr>
    <tr>
        <th><s:label for="s3" name="common.username"/></th>
        <td><s:text class="form-control" id="s3" name="police.username"/></td>
    </tr>
    <tr>
        <th><s:label for="s4" name="common.password"/></th>
        <td><s:password class="form-control" id="s4" name="police.password"/></td>
    </tr>
</table>
