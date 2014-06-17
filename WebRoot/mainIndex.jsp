<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>数据分析系统</title>
<%
	String path = request.getContextPath();
	String error = request.getParameter("error");
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/login/css/lanrenzhijia.css" media="all"/>
<script type="text/javascript" src="<%=path%>/common/lib/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/common/lib/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/login.js"></script>

<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
a:link {
 text-decoration: none;
}
a:visited {
 text-decoration: none;
}
a:hover {
 text-decoration: underline;
}
a:active {
 text-decoration: none;
}
.cs-north {
	/**background:#B3DFDA;**/
	height:95px;
	background:#219CEF;
}
.cs-north-bg {
	width: 100%;
	height: 100%;
	/**background: url("<%=path%>/static/css/themes/images/header_bg.png") repeat-x;**/
	/**background: url("<%=path%>/css/images/hongyanhe.gif");**/
}
.cs-north-logo {
	background:url('<%=path%>/static/css/themes/gray/images/hongyanhe.png') no-repeat right top;
	height: 90px;
	width:100%;
	color:white;font-weight:bold;text-decoration:none
}
.cs-west {
	width:200px;padding:0px;border-left:1px solid #99BBE8;
}
.cs-south {
	height:25px;background:url('<%=path%>/static/css/themes/gray/images/panel_title.gif') repeat-x;padding:0px;text-align:center;
}
.cs-navi-tab {
	padding: 5px;
}
.cs-tab-menu {
	width:120px;
}
.cs-home-remark {
	padding: 10px;
}
</style>
<script type="text/javascript">
var error = "<%=error%>";
function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}
		
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}		
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

$(function() {
	tabCloseEven();
	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});
});
</script>
</head>
<body class="easyui-layout">
		<div id="loginDiv" class="theme-popover">
			<div class="theme-poptit">
				<a href="javascript:;" title="关闭" onclick="cancelLoginDiv()"
					class="close">×</a>
				<h3>
					请输入用户名和密码
				</h3>
			</div>
			<div class="theme-popbod dform" style="position:relative;z-index:999">
				<form class="theme-signin" name="loginform" action="<%=path%>/j_spring_security_check" method="post">
					<ol>
						<li>
						<div id="loginErrorDiv" class="loginErrorHidden"> 
						用户名或密码错误
						</div>
						<li>
							<strong>用户名：</strong>
							<input class="ipt" type="text" name="j_username" value="" size="20" />
						</li>
						<li>
							<strong>密码：</strong>
							<input class="ipt" type="password" name="j_password" value="" size="20" />
						</li>
						<li>
							<input class="btn btn-primary" type="submit" name="submit"
								value=" 登 录 " />
						</li>
					</ol>
				</form>
			</div>
		</div>
		<div region="north" border="true" class="cs-north">
		<div class="cs-north-bg">
			<div class="cs-north-logo">
				<div style="float:left; padding:15px 0px 0px 5px">
					<div style="font-size:18px;padding:15px 0px 0px 5px">焊接合格率自动预警系统</div>
					<div style="font-size:14px;padding:15px 0px 0px 100px">一次把事情做好</div>
				</div>
				<div style="float:right;font-size:14px;padding:40px 5px 0px 5px">
					<authz:authorize access="isAuthenticated()">
						登录人：
						<authz:authentication property="principal.username" />
						<a class="btn btn-primary btn-large" href="<%=path%>/j_spring_security_logout">退出</a>
					</authz:authorize>
					<authz:authorize access="!isAuthenticated()">
						<a class="btn btn-primary btn-large theme-login" href="javascript:;">管理员登录</a>
					</authz:authorize>
				</div>
			</div>
		</div>
	</div>
	<div region="west" border="true" split="true" title="导航" class="cs-west">
		<div class="easyui-accordion" fit="true" border="false">
				<div title="一次合格率趋势图">
					<p><a href="javascript:void(0);" src="<%=path%>/weekAnalystic.do" class="cs-navi-tab">周一次合格率趋势图</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/monthAnalystic.do" class="cs-navi-tab">月度一次合格率趋势图</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/getCumulativedRate.do" class="cs-navi-tab">年度一次累计合格率</a></p>
				</div>
				<div title="返修合格率趋势图">
					<p><a href="javascript:void(0);" src="<%=path%>/weekAnalysticSecondRT.do" class="cs-navi-tab">周返修合格率趋势图</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/monthAnalysticSecondRT.do" class="cs-navi-tab">月度返修合格率趋势图</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/getCumulativedRateSecondRT.do" class="cs-navi-tab">年度返修累计合格率</a></p>
				</div>
				<div title="责任区数据分析">
					<p><a href="javascript:void(0);" src="<%=path%>/zoneClassAnalystic.do" class="cs-navi-tab">责任区一次合格率</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/zoneClassAnalysticSecondRT.do" class="cs-navi-tab">责任区返修合格率</a></p>
				</div>
				<div title="细化区域数据分析">
					<p><a href="javascript:void(0);" src="<%=path%>/zoneAnalystic.do" class="cs-navi-tab">细化区域一次合格率</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/zoneAnalysticSecondRT.do" class="cs-navi-tab">细化区域返修合格率</a></p>
				</div>
				<div title="焊接数据查询">
					<p><a href="javascript:void(0);" src="<%=path%>/hangongAnalystic.do" class="cs-navi-tab">焊工一次合格数据查询</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/hangongAnalysticSecondRT.do" class="cs-navi-tab">焊工返修合格数据查询</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/hangongChartNoQuery.do" class="cs-navi-tab">等轴图号与焊口号查询</a></p>
				</div>
				<authz:authorize access="hasRole('supervisor')">
					<div title="焊接数据管理">
						<p><a href="javascript:void(0);" src="<%=path%>/listRecords.do" class="cs-navi-tab">源数据时间段查询</a></p>
						<p><a href="javascript:void(0);" src="<%=path%>/hangongChartNoQueryForAdmin.do" class="cs-navi-tab">源数据等轴图号查询</a></p>
						<p><a href="javascript:void(0);" src="<%=path%>/file/uploadingFile.do" class="cs-navi-tab">焊接数据文件上传</a></p>
						<p><a href="javascript:void(0);" src="<%=path%>/file/uploadingHangongFile.do" class="cs-navi-tab">焊工信息文件上传</a></p>
						<p><a href="javascript:void(0);" src="<%=path%>/setting/alertLimitSetting.do" class="cs-navi-tab">参数设定</a></p>
					</div>
					<div title="密码修改">
						<p>
							<a href="javascript:void(0);"
								src="<%=path%>/userInforMaintain.do" class="cs-navi-tab">密码修改</a>
						</p>
					</div>
				</authz:authorize>
			</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="主页">
				<div class="cs-home-remark" style="height:100%">
					<iframe scrolling="auto" frameborder="0"  src="<%=path%>/weekAnalystic.do" style="width:100%;height:100%;"></iframe>
					<h1></h1> <br>
				</div>
				</div>
        </div>
	</div>
	<div region="south" border="false" class="cs-south">提示：推荐使用Chrome浏览器，最低分辨率1280*800，请安装flash插件</div>
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
</body>
</html>