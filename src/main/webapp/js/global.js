/*
 * 该文件里放置全局js
 */
$(document).ready(function() {
	
	// 调整页脚位置
	function adjustFooterPosition() {
		if ($(window).height() == $(document).height()) {
	        $(".footer").addClass("navbar-fixed-bottom");
	    } else {
	        $(".footer").removeClass("navbar-fixed-bottom");
	    }
	}
	
	// 页面启动时，自动调整页脚位置
	adjustFooterPosition();
	
	// 页面窗口大小发生变动时，自动调整页脚位置
	$(window).resize(function() {
		adjustFooterPosition();
	});
	
	//禁止复制
//	$("body").bind("contextmenu", function() {return false;});
//	$("body").bind("selectstart",function(){return false;});
});