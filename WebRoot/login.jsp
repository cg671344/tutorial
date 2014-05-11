<!-- THE-NODE-OF-SESSION-TIMEOUT -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.text.*" %>
<html>
<html>
	<head>
		<title>Login Page</title>
		<%
			String path = request.getContextPath();
			Date date = new Date();
		%>
		<link rel="stylesheet" href="css/login.css"></link>
		<script type="text/javascript" charset="utf-8">
			function change(e, btnName, state){
				var btn = e;
				btn.src = btnName + state + ".png";
			}
			
			function styleChange(e,type){
				e.className=type;
			}
			
			if(top != self && top.location != self.location){
	            top.location = top.location;
			}	
		</script>
	</head>
	<body  class="background"> 
		<table height="100%" width="100%" border="0" >
			<colgroup>
				<col/>
				<col width="1000px"/>
				<col/>
			</colgroup>
			<tr>
				<td></td>
				<td style="background:url('css/images/backgroud.png') no-repeat center center;">
					<table>
						<colgroup>
							<col width="591px"/>
							<col />
							<col/>
							<col/>
							<col/>
						</colgroup>
						<tr height="78px">
							<td rowspan='18'></td>
							<td colspan='3'></td>
						</tr>
						<tr>
							<td colspan='3'>
								<!--<img src="css/images/logo.png" style="vertical-align:text-bottom"></img>-->
								<label style="vertical-align:text-bottom">&nbsp;Application Framework</label>
							</td>
						</tr>
						<tr height="47px">
							<td colspan='3'>
							<div class="${param.login_error == '1' ? 'error' : 'errorDiv'}">Login failed:
						
							</div>
							</td>
						</tr>
						<form name='f' action="<%=path%>/j_spring_security_check"
							method='POST'>
						<tr>
							<td style="font-size: 12px;">
								Username:
							</td>

							<td>
								<input style="height: 26px;" type='text' name='j_username'
									value='' onmouseover="styleChange(this,'outer');"
									onmouseout="styleChange(this,'inner');" class="inner"
									tabindex="1" />
							</td>

							<td rowspan="3">
								<input type="image" src="css/images/login_static.png"
									onmouseover="change(this,'css/images/','login_touch');"
									onmouseout="change(this,'css/images/','login_static'); "
									tabindex="3"></input>
							</td>
						</tr>
						<tr height="10px">
							<td colspan='2'></td>
						</tr>
						<tr>
							<td style="font-size: 12px;">
								Password:
							</td>

							<td>
								<input style="height: 26px;" type='password' name='j_password'
									onmouseover="styleChange(this,'outer');"
									onmouseout="styleChange(this,'inner');" class="inner"
									tabindex="1" />
							</td>
						</tr>
						</form>
						<tr height="64px">
							<td colspan='3'></td>
						</tr>
						<tr>
							<td id="resolution" colspan='4' style="color:#00cafc;font-size:12px;font-family:Helvetica;"></td>
						</tr>
						<tr height="80px">
							<td colspan='3'></td>
						</tr>
						<tr height="30px">
						   <!--  
							<td colspan='3' style="font-weight:bold;">
								About Aclome Management Center:
							</td>
							-->
						</tr>
						
						<tr height="160px">
						   <!-- 
							<td colspan='3' style="font-size:12px;word-break:keep-all" valign="top">
							Aclome Management Center is the one-stop cloud data center management portal.
								It provides valuable functionalities to administrator, such as multi-dimensional 
								resource management, automatic resource detection, risk alert and policy based data
								center self-management. Supporting wide range of resources, including physical device, 
								virtual infrastructure, cloud environment resource, middleware and appliance et al.
							

							</td>
							 -->
						</tr>
						
					</table>
				</td>
				<td></td>
			</tr>
		</table>
	</body>
	<script type="text/javascript" charset="utf-8">
		if(localeStr=="zh_CN"){
			document.getElementById('resolution').innerHTML="最低分辨率1280*800，全屏时体验更佳";
		}else{
			document.getElementById('resolution').innerHTML="Minimum resolution 1280*800, full screen is preferred";
		}
	</script>
</html>