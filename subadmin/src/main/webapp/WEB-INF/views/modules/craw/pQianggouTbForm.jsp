<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>淘宝请购管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/craw/pQianggouTb/list">淘宝抢购商品列表</a></li>
		<li class="active"><a href="${ctx}/craw/pQianggouTb/form?id=${pQianggouTb.id}">淘宝抢购商品<shiro:hasPermission name="craw:pQianggouTb:edit">${not empty pQianggouTb.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="craw:pQianggouTb:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pQianggouTb" action="${ctx}/craw/pQianggouTb/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">name：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">pcurl：</label>
			<div class="controls">
				<form:input path="pcurl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片链接：</label>
			<div class="controls">
				<form:input path="picurl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品连接：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">starttime：</label>
			<div class="controls">
				<form:input path="starttime" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">endtime：</label>
			<div class="controls">
				<form:input path="endtime" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">原价：</label>
			<div class="controls">
				<input id="price" name="price" type="text" maxlength="255" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际价格：</label>
			<div class="controls">
				<input type="text" id="saleprice" name="saleprice" maxlength="255" class="required "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">selfsellingpoint：</label>
			<div class="controls">
				<form:input path="selfsellingpoint" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">category：</label>
			<div class="controls">
				<form:input path="category" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<!--shiro:hasPermission name="craw:pQianggouTb:edit"-->
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<!--/shiro:hasPermission-->
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>