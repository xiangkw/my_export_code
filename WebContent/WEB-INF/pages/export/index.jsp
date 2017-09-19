<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% String basePath = request.getContextPath();  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<%=basePath%>/static/favicon.png?v=1" type="image/x-icon">
<title>My export code tools</title>

<script src="/static/jquery-1.11.2.min.js"></script>
<style type="text/css">
body {
	text-align: center;
}

table {
	margin-right: auto;
	margin-left: auto;
	font-size: 12px;
	border-left: 1px solid #999;
	border-bottom: 1px solid #999;
}

table td, table th {
	border-right: 1px solid #999;
	border-top: 1px solid #999;
}

table th {
	padding: 15px;
	font-size: 14px;
}

table td {
	padding: 5px 20px;
}

table input[type=checkbox] {
	cursor: pointer;
	width: 16px;
	height: 16px;
}

.button {
	width: 100px;
	height: 28px;
	line-height: 28px;
	cursor: pointer;
}
</style>

<script type="text/javascript">
	$(function() {
		
		$(".selectAll").click(function() {
			if ($(this).is(":checked")) {
				$(".selectAll").prop("checked", true);
				$("input[name='tableName']").prop("checked", true);
			} else {
				$(".selectAll").prop("checked", false);
				$("input[name='tableName']").prop("checked", false);
			}
		});

		$("input[name='tableName']").click(function() {
					if ($("input[name='tableName']").size() == $("input[name='tableName']:checked").size()) {
						$(".selectAll").prop("checked", true);
					} else {
						$(".selectAll").prop("checked", false);
					}
				});
		
		$("select[name='dbConfigs']").change(function(){
			var dbId = $(this).val();
			window.location.href = "<%=basePath%>/export.htm?dbId="+dbId ;
		});
	});
	
	function exportCode(){
		if($("input[name='tableName']:checked").size() == 0){
			alert("请先选择需要导出的表");
			return false;
		}
		
		
		var tableNames = "";
		$("input[name='tableName']:checked").each(function(){
			if(tableNames.length > 0 ){
				tableNames += ",";
			}
			tableNames += $(this).val();
		});
		
		$("#tableNames").val(tableNames);
		$("#dbId").val($("select[name='dbConfigs']").val());
		$("#basePakeage").val($("#myBasePakeage").val());
		$("#template").val($("#myTemplate").val());
		
		$("#exportForm").submit();
	}
</script>
</head>
<body>
	<div style="float: left;width: 40%;">
		<h3>请选择要导出的对应的库</h3>
		<hr>
		<select name="dbConfigs" style="width: 95%;padding: 10px;">
			<c:forEach items="${dbList}" var="item">
				<option value="${item.dbId}" <c:if test="${item.dbId == dbConfig.dbId}">selected="selected" </c:if> >${item.desc}</option>
			</c:forEach>
		</select>
		<table style="width: 95%;margin-top: 20px;">
			<thead>
				<th>配置名称</th>
				<th>配置内容</th>
			</thead>
			<tr>
				<td align="left">URL</td>
				<td align="left" style="word-break:break-all; ">${dbConfig.url}</td>
			</tr>
			<tr>
				<td align="left">用户名</td>
				<td align="left">${dbConfig.username}</td>
			</tr>
			<tr>
				<td align="left">包路径</td>
				<td align="left">
					<input style="padding: 5px;width: 200px;" type="text" name="basePakeage" id="myBasePakeage" value="${dbConfig.basePakeage}">&nbsp;&nbsp;可填写 
				</td>
			</tr>
			<tr>
				<td align="left">模板选择</td>
				<td align="left">
					<select id="myTemplate" name="template" style="padding: 5px;width: 212px;">
						<option value="template">模板一</option>
						<option value="template2">模板二</option>
					</select>
					&nbsp;&nbsp;可选择
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="button" value="导出代码"
					class="button" onclick="exportCode();" /></td>
			</tr>
		</table>
	</div>
	<div style="float: left;width: 60%;">
		<h3>导出表对应的基础代码</h3>
		<hr>
		<table style="width: 95%;margin-bottom: 20px;">
			<thead>
				<th colspan="3">当前选择库：${dbConfig.desc}</th>
			</thead>
			<thead>
				<th style="width: 60px;"><input type="checkbox" class="selectAll" />全选</th>
				<th>表名</th>
				<th>表说明（表的注释）</th>
			</thead>
			
			<c:forEach items="${tables}" var="item">
				<tr>
					<td align="center"><input type="checkbox" name="tableName"
						value="${item.TABLE_NAME}" /></td>
					<td align="left">${item.TABLE_NAME}</td>
					<td align="left">${item.TABLE_COMMENT}</td>
				</tr>
			</c:forEach>
			<tr>
				<td><input type="checkbox" class="selectAll" />全选</td>
				<td colspan="2">&nbsp;</td>
			</tr>
		</table>
	</div>
	
	<div style="display: none;">
		<form action="<%=basePath%>/doExport.htm" method="post" id="exportForm">
			<input type="hidden" name="tableNames" id="tableNames" />
			<input type="hidden" name="basePakeage" id="basePakeage" />
			<input type="hidden" name="dbId" id="dbId" />
			<input type="hidden" name="template" id="template" />
		</form>
	</div>
</body>
</html>