<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>���ݷ���ϵͳ</title>
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
	font: 12px/20px "΢���ź�", "����", Arial, sans-serif, Verdana, Tahoma;
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
	height:60px;background:#B3DFDA;
}
.cs-north-bg {
	width: 100%;
	height: 100%;
	background: url("<%=path%>/static/css/themes/gray/images/header_bg.png") repeat-x;
}
.cs-north-logo {
	height: 40px;
	padding: 15px 0px 0px 5px;
	color:#fff;font-size:22px;font-weight:bold;text-decoration:none
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
		$('#tabs').tabs('select', title);//ѡ�в�ˢ��
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
	/*˫���ر�TABѡ�*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*Ϊѡ����Ҽ�*/
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
//���Ҽ��˵��¼�
function tabCloseEven() {
	//ˢ��
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
	//�رյ�ǰ
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//ȫ���ر�
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//�رճ���ǰ֮���TAB
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
	//�رյ�ǰ�Ҳ��TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('ϵͳ��ʾ','���û����~~','error');
			alert('���û����~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//�رյ�ǰ����TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('��ͷ�ˣ�ǰ��û����~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//�˳�
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
				<a href="javascript:;" title="�ر�" onclick="cancelLoginDiv()"
					class="close">��</a>
				<h3>
					�������û���������
				</h3>
			</div>
			<div class="theme-popbod dform" style="position:relative;z-index:999">
				<form class="theme-signin" name="loginform" action="<%=path%>/j_spring_security_check" method="post">
					<ol>
						<li>
						<div id="loginErrorDiv" class="loginErrorHidden"> 
						�û������������
						</div>
						<li>
							<strong>�û�����</strong>
							<input class="ipt" type="text" name="j_username" value="" size="20" />
						</li>
						<li>
							<strong>���룺</strong>
							<input class="ipt" type="password" name="j_password" value="" size="20" />
						</li>
						<li>
							<input class="btn btn-primary" type="submit" name="submit"
								value=" �� ¼ " />
						</li>
					</ol>
				</form>
			</div>
		</div>
		<div region="north" border="true" class="cs-north">
		<div class="cs-north-bg">
			<div class="cs-north-logo" style="float:left">
				���Ӻϸ����Զ�Ԥ��ϵͳ
			</div>
			<div style="float:right;padding-top:25px;padding-right:30px">
					<authz:authorize access="isAuthenticated()">
						��¼�ˣ�
						<authz:authentication property="principal.username" />
						<a class="btn btn-primary btn-large" href="<%=path%>/j_spring_security_logout">�˳�</a>
					</authz:authorize>
					<authz:authorize access="!isAuthenticated()">
						<a class="btn btn-primary btn-large theme-login" href="javascript:;">��¼</a>
					</authz:authorize>
			</div>
		</div>
	</div>
	<div region="west" border="true" split="true" title="����" class="cs-west">
		<div class="easyui-accordion" fit="true" border="false">
				<div title="һ�κϸ�������ͼ">
					<p><a href="javascript:void(0);" src="<%=path%>/weekAnalystic.do" class="cs-navi-tab">��һ�κϸ�������ͼ</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/monthAnalystic.do" class="cs-navi-tab">�¶�һ�κϸ�������ͼ</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/getCumulativedRate.do" class="cs-navi-tab">���һ���ۼƺϸ���</a></p>
				</div>
				<div title="���޺ϸ�������ͼ">
					<p><a href="javascript:void(0);" src="<%=path%>/weekAnalysticSecondRT.do" class="cs-navi-tab">�ܷ��޺ϸ�������ͼ</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/monthAnalysticSecondRT.do" class="cs-navi-tab">�¶ȷ��޺ϸ�������ͼ</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/getCumulativedRateSecondRT.do" class="cs-navi-tab">��ȷ����ۼƺϸ���</a></p>
				</div>
				<div title="���������ݷ���">
					<p><a href="javascript:void(0);" src="<%=path%>/zoneClassAnalystic.do" class="cs-navi-tab">������һ�κϸ���</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/zoneClassAnalysticSecondRT.do" class="cs-navi-tab">���������޺ϸ���</a></p>
				</div>
				<div title="ϸ���������ݷ���">
					<p><a href="javascript:void(0);" src="<%=path%>/zoneAnalystic.do" class="cs-navi-tab">ϸ������һ�κϸ���</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/zoneAnalysticSecondRT.do" class="cs-navi-tab">ϸ�������޺ϸ���</a></p>
				</div>
				<div title="�������ݲ�ѯ">
					<p><a href="javascript:void(0);" src="<%=path%>/hangongAnalystic.do" class="cs-navi-tab">����һ�κϸ����ݲ�ѯ</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/hangongAnalysticSecondRT.do" class="cs-navi-tab">�������޺ϸ����ݲ�ѯ</a></p>
					<p><a href="javascript:void(0);" src="<%=path%>/hangongChartNoQuery.do" class="cs-navi-tab">����ͼ���뺸�ںŲ�ѯ</a></p>
				</div>
				<authz:authorize access="hasRole('supervisor')">
					<div title="�������ݹ���">
						<p><a href="javascript:void(0);" src="<%=path%>/listRecords.do" class="cs-navi-tab">Դ���ݲ�ѯ</a></p>
						<p><a href="javascript:void(0);" src="<%=path%>/file/uploadingFile.do" class="cs-navi-tab">���������ļ��ϴ�</a></p>
						<p><a href="javascript:void(0);" src="<%=path%>/file/uploadingHangongFile.do" class="cs-navi-tab">������Ϣ�ļ��ϴ�</a></p>
						<p><a href="javascript:void(0);" src="<%=path%>/setting/alertLimitSetting.do" class="cs-navi-tab">�����趨</a></p>
					</div>
					<div title="�����޸�">
						<p>
							<a href="javascript:void(0);"
								src="<%=path%>/userInforMaintain.do" class="cs-navi-tab">�����޸�</a>
						</p>
					</div>
				</authz:authorize>
			</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="��ҳ">
				<div class="cs-home-remark" style="height:100%">
					<iframe scrolling="auto" frameborder="0"  src="<%=path%>/weekAnalystic.do" style="width:100%;height:100%;"></iframe>
					<h1></h1> <br>
				</div>
				</div>
        </div>
	</div>
	<div region="south" border="false" class="cs-south">��ʾ���Ƽ�ʹ��Chrome���������ͷֱ���1280*800���밲װflash���</div>
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">ˢ��</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">�ر�</div>
		<div id="mm-tabcloseother">�ر�����</div>
		<div id="mm-tabcloseall">�ر�ȫ��</div>
	</div>
</body>
</html>