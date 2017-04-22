<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>触发器记录管理</title>
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
			//允许修改时间表达式
			$("#cronExpression").removeAttr("readonly");
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/qrtz/qrtzTriggers/">触发器列表</a></li>
		<li class="active">
			<a href="${ctx}/qrtz/qrtzTriggers/form?schedName=${qrtzTriggers.schedName}&triggerName=${qrtzTriggers.triggerName}&triggerGroup=${qrtzTriggers.triggerGroup}">
				<shiro:hasPermission name="qrtz:qrtzTriggers:edit">${not empty qrtzTriggers.schedName?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="qrtz:qrtzTriggers:edit">查看</shiro:lacksPermission>
				触发器
			</a>
		</li>
		<li><a href="${ctx}/qrtz/qrtzJobDetails/">任务属性列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qrtzTriggers" action="${ctx}/qrtz/qrtzTriggers/save" method="post" class="form-horizontal">
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
			<label class="control-label">触发器名称：</label>
			<div class="controls">
				<form:input path="triggerName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">触发器所属组：</label>
			<div class="controls">
				<form:input path="triggerGroup" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
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
			<label class="control-label">下次触发时间：</label>
			<div class="controls">
				<input name="nextFireDate" type="text" maxlength="13" class="input-medium Wdate "
					value="<fmt:formatDate value="${qrtzTriggers.nextFireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" disabled="true" "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次触发时间：</label>
			<div class="controls">
				<input name="prevFireDate" type="text" maxlength="13" class="input-medium Wdate "
					value="<fmt:formatDate value="${qrtzTriggers.prevFireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" disabled="true" "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优先级：</label>
			<div class="controls">
				<form:input path="priority" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">触发器状态：</label>
			<div class="controls">
				<form:input path="triggerState" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">触发器类型：</label>
			<div class="controls">
				<form:input path="triggerType" htmlEscape="false" maxlength="8" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间表达式：</label>
			<div class="controls">
				<form:input path="cronExpression" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startDate" type="text" maxlength="13" class="input-medium Wdate "
					value="<fmt:formatDate value="${qrtzTriggers.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" disabled="true" "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">停止时间：</label>
			<div class="controls">
				<input name="endDate" type="text" maxlength="13" class="input-medium Wdate "
					value="<fmt:formatDate value="${qrtzTriggers.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" disabled="true" "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程名称：</label>
			<div class="controls">
				<form:input path="calendarName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">异常数：</label>
			<div class="controls">
				<form:input path="misfireInstr" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="qrtz:qrtzTriggers:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>