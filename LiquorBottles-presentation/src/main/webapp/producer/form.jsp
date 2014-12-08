<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>

<form class="form-horizontal" role="form">
    <div class="form-group">
        <s:label class="col-sm-2 control-label" for="s1" name="common.name"/>

        <s:text class="form-control" id="s1" name="producer.name"/>

    </div>
    <div class="form-group">
        <s:label for="s2" class="col-sm-2 control-label" name="common.address"/>

        <s:text id="s2" class="form-control" name="producer.address"/>

    </div>
    <div class="form-group">
        <s:label for="s3" class="col-sm-2 control-label" name="common.username"/>

        <s:text id="s3" class="form-control" name="producer.username"/>
    </div>

    <div class="form-group">
        <s:label for="s4" class="col-sm-2 control-label" name="common.password"/>

        <s:text id="s4" class="form-control" name="producer.password"/>

    </div>
</form>

