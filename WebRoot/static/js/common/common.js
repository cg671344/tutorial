Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"H+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

var common={};
var loadingMask = null;
/**
 * @param： {boolean|null} isShow 
 * 		当等于true|null为显示进度条，否则为关闭
 * 	{Dom} dom
 * 	需要显示loading的Dom节点，为空时在document显示
 */
common.showLoading = function(isShow, dom){
	if(null == loadingMask){
		var html = ["<div class='loading-alpha'></div>"];
		html.push("<div class='loading-p'>");
		html.push("<div class='loading-content'>");
		html.push("<div class='loading-text'>");
		html.push("");
		html.push("</div>");
		html.push("<div class='loading-img'></div>");
		html.push("<div class='loading-cancel'></div>");
		html.push("</div>");
		html.push("</div>");
		loadingMask = document.createElement("div");
		loadingMask.className = "loading";
		loadingMask.innerHTML = html.join("");
		dom = dom ? dom : document.body;
		dom && dom.appendChild(loadingMask);
	}
	loadingMask.style.display = isShow==false ? "none" : "block";
};

function deleteChecked(url, params){
	var ids = [];
	var inputs = document.getElementsByTagName('input');
	for(var i=0; i<inputs.length; i++){
		if(inputs[i].type=='checkbox' && inputs[i].checked){
			ids.push(inputs[i].value);
		}
	}
	ids = ids.join(',');
	if(ids==''){
		alert('请选择要删除的记录!');
	}else{
	    if(window.confirm('确认要删除吗？')){
	    	//FIXME:CONTEXTPATH
			$.ajax({ 
         		type: "post", 
                url: contextPath + "/deleteRecords.do", 
                dataType: "json", 
                async:false,
                data:{ids:ids},
                success: function (data) {
                	alert("删除成功！");
                	window.location.href = url;
                }, 
               	error: function (XMLHttpRequest, textStatus, errorThrown) {
               		alert("删除失败！");
               	} 
             });
			return;
		 }
		else {
			return false;
		}
	}
}


