<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>
<%@ taglib prefix="display" uri="/WEB-INF/tlds/displaytag.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<%
	String contextPath = request.getContextPath();
%>
<script type="text/javascript">
	var contextPath = "<%=contextPath%>";
</script>
<script src="<%=request.getContextPath()%>/common/lib/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath()%>/common/lib/jquery/jquery.ui.core.js"></script>
<script
	src="<%=request.getContextPath()%>/common/lib/jquery/jquery.ui.widget.js"></script>
<script
	src="<%=request.getContextPath()%>/common/lib/jquery/jquery.ui.datepicker.js"></script>
<script
	src="<%=request.getContextPath()%>/common/lib/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=request.getContextPath()%>/common/lib/jquery/jquery.form.js"></script>
<script src="<%=request.getContextPath()%>/common/lib/jquery/jquery.validate.js"></script>

