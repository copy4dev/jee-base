<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/usrtaglib.jsp"%>
<html>
<head>
	<title>触发器记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qrtz/qrtzTriggers/">触发器列表</a></li>
		<%--<shiro:hasPermission name="qrtz:qrtzTriggers:edit"><li><a href="${ctx}/qrtz/qrtzTriggers/form">触发器记录添加</a></li></shiro:hasPermission>--%>
		<li><a href="${ctx}/qrtz/qrtzJobDetails/">任务属性列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="qrtzTriggers" action="${ctx}/qrtz/qrtzTriggers/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%--<li><label>调度器名称：</label>
				<form:input path="schedName" htmlEscape="false" maxlength="120" class="input-medium"/>
			</li>--%>
			<li><label>触发器名称：</label>
				<form:input path="triggerName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>任务名称：</label>
				<form:input path="jobName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>触发器名称</th>
				<th>任务名称</th>
				<th>上次触发时间</th>
				<th>下次触发时间</th>
				<th>优先级</th>
				<th>触发器状态</th>
				<th>触发器类型</th>
				<th>时间表达式</th>
				<th>描述</th>
 				<shiro:hasPermission name="qrtz:qrtzTriggers:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qrtzTriggers">
			<tr>
				<td><a href="${ctx}/qrtz/qrtzTriggers/form?schedName=${qrtzTriggers.schedName}&triggerName=${qrtzTriggers.triggerName}&triggerGroup=${qrtzTriggers.triggerGroup}">
					${qrtzTriggers.triggerName}
				</a></td>
				<td><a href="${ctx}/qrtz/qrtzTriggers/form?schedName=${qrtzTriggers.schedName}&triggerName=${qrtzTriggers.triggerName}&triggerGroup=${qrtzTriggers.triggerGroup}">
					${qrtzTriggers.jobName}
				</a></td>
				<td>
					<fmt:formatDate value="${qrtzTriggers.prevFireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${qrtzTriggers.nextFireDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${qrtzTriggers.priority}
				</td>
				<td>
					${qrtzTriggers.triggerState}
				</td>
				<td>
					${qrtzTriggers.triggerType}
				</td>
				<td>
					${qrtzTriggers.cronExpression}
				</td>
				<td>
					${qrtzTriggers.description}
				</td>
				<%--<shiro:hasPermission name="qrtz:qrtzTriggers:edit"><td>
    				<a href="${ctx}/qrtz/qrtzTriggers/form?id=${qrtzTriggers.id}">修改</a>
					<a href="${ctx}/qrtz/qrtzTriggers/delete?id=${qrtzTriggers.id}" onclick="return confirmx('确认要删除该触发器记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
				<shiro:hasPermission name="qrtz:qrtzTriggers:edit"><td>
					<a href="${ctx}/qrtz/qrtzTriggers/option?jobName=${qrtzTriggers.jobName}&jobGroup=${qrtzTriggers.jobGroup}&triggerState=${qrtzTriggers.triggerState}" onclick="return confirmx('确认要更改触发器状态吗？', this.href)">
						${qrtzPaused == qrtzTriggers.triggerState ? '恢复' : '挂起'}
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>