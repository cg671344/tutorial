
/**
 * firefox 风格的Tab控件，使用例子
 * <style type="text/css">
 *    .tabs{
 *    	width:800px;
 *    	height:300px;
 *    	padding:5px;
 *    	margin:5px;
 *   }
 * </style>
 * <div id="tabs">
 *     <ul>
 *         <li><a href="#one">One</a></li>
 *         <li><a href="#two">Two</a></li>
 *     </ul>
 *     <div id="one">One</div>
 *     <div id="two">Two</div>
 * </div>
 * <script type="text/javascript">
 * 		$(function(){
 * 			$("#tabs").tab();
 * 		});
 * </script>
 * 
 * 具体使用可以参考 jquery ui的官方文档 http://jqueryui.com/demos/tabs/
 */

(function($,undefined){
	
	//统一边框样式
	var borderCss="1px solid #AAAAAA";
	
	//继承jquery ui的tab控件
	$.widget("ui.tab",$.ui.tabs,{
		
		options:{
			//是否需要动态调整页面大小
			resize:true,
			
			//是否是简单模式
			simple:false,
			
			cache:true		
		},
		
		_create:function(){
			//调用父类的_create方法
			$.ui.tabs.prototype._create.apply(this,arguments);
			
			this.options.simple?this._createSimple():this._createComplex();
		},
				
		//简洁的tab页
		_createSimple:function(){
			var mainElement=this.element;
			mainElement.addClass("ui-simple-tabs").css('border',borderCss);
			mainElement.find(">ul,>ol").removeClass("ui-widget-header").css({paddingTop:'0'});
			mainElement.find("ul>li").removeClass("ui-state-default");
		},
		
		//复杂的tab页
		_createComplex:function(){
			//更新主元素
			this._updateMainElement();
			//产生新div用于填充
			this._genExtraDiv();
			this.options.resize&&$(window).resize($.proxy(function(){
				this.resizeWidget();
			},this));
		},
		
		//重新调整页面控件大小
		resizeWidget:function(){
			var simple=this.options.simple;
			if(!simple){
				this.element.find(".ui-extra-div").remove();
				this._genExtraDiv();
			}
		},
		
		_updateMainElement:function(){
			this.element.removeClass('ui-corner-all ui-widget-content')
				.css({
					borderBottom: borderCss,
					position:"relative"
				})
				.addClass("ui-corner-bl ui-corner-br");
		},
		
		//获得<ul>下所有<li>元素的实际宽度
		_getLiWidth:function(){
			var width=0,
				obj=this.element.find(">ul>li");
			this._updateLi();
			obj.each(function(){
				width+=$(this).outerWidth(true);
			});
			return width;
		},
		
		//重新设置ul元素下li元素的样式
		_updateLi:function(){
			var obj=this.element.find(">ul>li"),
				len=obj.length;
			obj.each(function(index){
				var self=$(this);
				self.removeClass("ui-corner-top").css({
					marginRight: 0,
					top:0,
					padding:0,
					borderBottom:borderCss
				});
				if(index==0){
					self.addClass("ui-corner-tl ui-corner-bl");
				}else if(index==len-1){
					self.addClass("ui-corner-tr ui-corner-br");
				}
			});
		},
		
		//获得ul元素的高度
		_getUlHeight:function(){
			 return this.element.find(">ul,>ol").outerHeight();
		},
		
		//获得ul的offsetTop
		_getUlOffsetTop:function(){
			return parseInt(this.element.find(">ul,o>l")[0].offsetTop,10);
		},
		
		//设置ul元素的宽度等信息
		_updateUl:function(width){
			this.element.find(">ul,>ol").removeClass("ui-corner-all ui-widget-header").css({
				width:width+4,
				margin:"0 auto",
				color:"#222222",
				fontWeight:"bold",
				borderBottomLeftRadius:"4px",
				borderBottomRightRadius:"4px",
				padding:"0px !important"
			});
		},
		
		//增加新的div来产生隔离效果
		_genExtraDiv:function(){
			var domNode=this.element,
				mainWidth=domNode.innerWidth(), //控件的宽度
				mainHeight=domNode.innerHeight(), //控件的高度
				offsetTop=domNode.find(">ul,>ol")[0].offsetTop, //ul元素距离父亲节点的距离
				lisWidth=this._getLiWidth(), //所有li元素的宽度总和
				remainWidth=mainWidth-lisWidth //控件宽度减去所有li元素的宽度
		
			//设置ul元素的宽度等信息
			this._updateUl(lisWidth);
			
			var top=this._getUlHeight()/2+this._getUlOffsetTop(),
				width=Math.ceil(remainWidth/2),
				height=mainHeight-top,
				cssProperties={
					position:"absolute",
					top:top,
					width:width,
					height:height,
					borderTop:borderCss,
					zIndex:-1
				};
			
			//左侧div
			var leftDiv=$("<div class='ui-extra-div'></div>").css(cssProperties)
										.css({borderLeft:borderCss,left:0})
										.addClass("ui-corner-tl ui-corner-bl");
			
			//右侧div
			var rightDiv=$("<div class='ui-extra-div'></div>").css(cssProperties)
										 .css({borderRight:borderCss,right:0})
										 .addClass("ui-corner-tr ui-corner-br");
			
			var obj=rightDiv.insertBefore(domNode.find(">ul,>ol"));
			leftDiv.insertBefore(obj);
		}
	});
})(jQuery);
