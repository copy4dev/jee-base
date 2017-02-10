<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模块日志管理</title>
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
		<li class="active"><a href="${ctx}/log/modLog/">模块日志列表</a></li>
		<shiro:hasPermission name="log:modLog:edit"><li><a href="${ctx}/log/modLog/form">模块日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="modLog" action="${ctx}/log/modLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日志类型：</label>
				<form:select path="logType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('mod_log_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>生成时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${modLog.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${modLog.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日志类型</th>
				<th>模块类型</th>
				<th>实体名</th>
				<th>业务方法</th>
				<th>摘要</th>
				<shiro:hasPermission name="log:modLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="modLog">
			<tr>
				<td><a href="${ctx}/log/modLog/form?id=${modLog.id}">
					${fns:getDictLabel(modLog.logType, 'mod_log_type', '')}
				</a></td>
				<td>
					${modLog.moduleType}
				</td>
				<td>
					${modLog.entityName}
				</td>
				<td>
					${modLog.bisMethod}
				</td>
				<td>
					${modLog.notes}
				</td>
				<shiro:hasPermission name="log:modLog:edit"><td>
    				<a href="${ctx}/log/modLog/form?id=${modLog.id}">修改</a>
					<a href="${ctx}/log/modLog/delete?id=${modLog.id}" onclick="return confirmx('确认要删除该模块日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>