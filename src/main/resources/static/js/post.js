var regEx_mobile = /(^(13|14|15|16|17|18|19)\d{9}$)/;
var regEx_yzm = /^\d{6}$/;
var p = /^(13[0-9]|15[012356789]|18[0-9]|14[57]|17[0678])\d{8}$/;
var speed = 1000;
var wait = 60;


$(function() {
			$("#btn_getcode").click(function() {

				layer.open({
					content: '手机号填写错误,请重新填写！',
					time: 1.2
				});


			});
