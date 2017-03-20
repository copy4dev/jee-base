<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>触发器Job明细管理</title>
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
		<li class="active"><a href="${ctx}/qrtz/qrtzJobDetails/">任务列表</a></li>
		<%--<shiro:hasPermission name="qrtz:qrtzJobDetails:edit"><li><a href="${ctx}/qrtz/qrtzJobDetails/form">触发器Job明细添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="qrtzJobDetails" action="${ctx}/qrtz/qrtzJobDetails/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>调度器名称：</label>
				<form:input path="schedName" htmlEscape="false" maxlength="120" class="input-medium"/>
			</li>
			<li><label>任务名称：</label>
				<form:input path="jobName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>任务所在类：</label>
				<form:input path="jobClassName" htmlEscape="false" maxlength="250" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>调度器名称</th>
				<th>任务名称</th>
				<th>任务所在类</th>
				<th>是否持久化</th>
				<th>是否集群</th>
				<th>是否更新</th>
				<th>是否自动恢复</th>
				<th>描述</th>
				<%--<shiro:hasPermission name="qrtz:qrtzJobDetails:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qrtzJobDetails">
			<tr>
				<td><a href="${ctx}/qrtz/qrtzJobDetails/form?schedName=${qrtzJobDetails.schedName}&jobName=${qrtzJobDetails.jobName}&jobGroup=${qrtzJobDetails.jobGroup}">
					${qrtzJobDetails.schedName}
				</a></td>
				<td>
					${qrtzJobDetails.jobName}
				</td>
				<td>
					${qrtzJobDetails.jobClassName}
				</td>
				<td>
					${fns:getDictLabel(qrtzJobDetails.isDurable, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(qrtzJobDetails.isNonconcurrent, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(qrtzJobDetails.isUpdateData, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(qrtzJobDetails.requestsRecovery, 'yes_no', '')}
				</td>
				<td>
					${qrtzJobDetails.description}
				</td>
				<%--<shiro:hasPermission name="qrtz:qrtzJobDetails:edit"><td>
    				<a href="${ctx}/qrtz/qrtzJobDetails/form?id=${qrtzJobDetails.id}">修改</a>
					<a href="${ctx}/qrtz/qrtzJobDetails/delete?id=${qrtzJobDetails.id}" onclick="return confirmx('确认要删除该触发器Job明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>