<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>触发器Job明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
			
			//input输入框-只读
			$("input").attr("readonly","readonly");
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/qrtz/qrtzJobDetails/">任务列表</a></li>
		<li class="active">
			<a href="${ctx}/qrtz/qrtzJobDetails/form?schedName=${qrtzJobDetails.schedName}&jobName=${qrtzJobDetails.jobName}&jobGroup=${qrtzJobDetails.jobGroup}">
				任务明细
				<shiro:hasPermission name="qrtz:qrtzJobDetails:edit">${not empty qrtzJobDetails.schedName?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="qrtz:qrtzJobDetails:edit">查看</shiro:lacksPermission>
			</a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qrtzJobDetails" action="${ctx}/qrtz/qrtzJobDetails/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">调度器名称：</label>
			<div class="controls">
				<form:input path="schedName" htmlEscape="false" maxlength="120" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
				<form:input path="jobName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务所属组：</label>
			<div class="controls">
				<form:input path="jobGroup" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="2" maxlength="250" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务所在类：</label>
			<div class="controls">
				<form:input path="jobClassName" htmlEscape="false" maxlength="250" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否持久化：</label>
			<div class="controls">
				<form:select path="isDurable" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否集群：</label>
			<div class="controls">
				<form:select path="isNonconcurrent" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否更新：</label>
			<div class="controls">
				<form:select path="isUpdateData" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否自动恢复：</label>
			<div class="controls">
				<form:select path="requestsRecovery" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="qrtz:qrtzJobDetails:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>