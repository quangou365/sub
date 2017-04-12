<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="defaul1t"/>
	<style type="text/css">
		#button-special span:first-child{padding: .2em 25px !important;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		//上级菜单
		function preLevel(){
			var id=$('#id').val();
			//alert("上一级："+id);
			if(id==0||id==1){
				return false;
			}
			$.ajax({ url: "${ctx}/craw/pCategory/plist4json", data: "id="+id, success: function(data){
				//alert(data[0].id);
				$('#categorys').empty();
		        for(var i=0;i<data.length;i++){
		        	$('#categorys').append("<button onclick='getCate(\""+data[i].id+"\",\""+data[i].name+"\")'>"+data[i].name+"</button>&nbsp;");
		        }
		    }});
			$.ajax({ url: "${ctx}/craw/pCategory/getByid", data: "id="+id, success: function(data){
				$('#id').val(data.parent);
				$('#cateName').html(data.name);
		    }});
		}
		function getCate(id,name){
			//alert(id);
			$('#cateName').html(name);
			$('#id').val(id);
			//alert(id+"||"+parent);
			//加载分类
			$.ajax({ url: "${ctx}/craw/pCategory/list4json", async:true,data: "id="+id, success: function(data){
				$('#categorys').empty();
				//alert(id+"|"+data.length+"-"+data[0].id);
		        for(var i=0;i<data.length;i++){
		        	//$('#categorys').append("<a  href='#'  onclick='getCate("+data[i].id+",\""+data[i].name+"\")'>"+data[i].name+"</a>&nbsp;");
		        	$('#categorys').append("<button  onclick='getCate(\""+data[i].id+"\",\""+data[i].name+"\")'>&nbsp;"+data[i].name+"&nbsp;</button> &nbsp;");
		        }
		    }});
		}
		getCate("0","");
		
		function save(){
			//alert(2);
			//验证验证码
			$.ajax({ url: "${ctx}/sys/vc/cc", async:true,data: "vc="+$('#vCode').val()+"&mb="+$('#mobile').val(), success: function(d){
				//alert(d);
				if(d==1){
					$('#inputForm').submit();
				}else{
					alert("输入的验证码错误");
				}
		    }});
			
		}
		
//验证码
$(function () {
     var util = {
         wait: 90,
         hsTime: function (that) {                	
             _this = $(this);
             if (_this.wait == 0) {
                 $('#hsbtn').removeAttr("disabled").val('重发短信验证码');
                 _this.wait = 90;
             } else {
                 var _this = this;
                 //alert( $(that).val());
                 //$(that).val('在' + _this.wait + '秒后点此重发');
                 $(that).attr("disabled", true).val('在' + _this.wait + '秒后点此重发').button("refresh");;
                 _this.wait--;
                 setTimeout(function () {
                     _this.hsTime(that);
                 }, 1000);
             }
         }
     };

     $("#hsbtn").click(function(){
    	 var mb=$('#mobile').val();
    	 if(mb==''){
    		 alert('请输入手机号');
    		 return false;
    	 }
         util.hsTime('#hsbtn');
         //调用后台程序产生验证码
         $.ajax({ url: "${ctx}/sys/vc/gc", data: "mb="+mb});
     });
})
		
		
	</script>
</head>
<body>

<div data-role="page" id="pageone">

	<div data-role="content">
	
	
	<div data-role="controlgroup" data-type="horizontal" >
			<label data-icon="search">所选分类：</label><label id="cateName" >&nbsp;&nbsp;&nbsp;&nbsp;</label>
			
			</div>
			<div data-role="controlgroup" data-type="horizontal" id="categorys">
			    <a id="button-special" href="#" data-role="button">按钮 1</a>
			    <a id="button-special" href="#" data-role="button">按钮 2</a>
			    <a id="button-special1" href="#" data-mini data-role="button">按钮 3</a>
			</div>
			<div data-role="controlgroup" data-type="horizontal">
		    	<a id="button-special" href="#" data-role="button" onclick="preLevel()" >上一级</a>
		    </div>
	
	
    <form id="inputForm" modelAttribute="catgoryMobile" action="${ctx}/craw/catgoryMobile/save" method="post" class="form-horizontal">
		<input id="id" name="id" type="hidden" value=""/>		
			
		 <div data-role="fieldcontain" >
			<label for="mobile1" > 手 机 号 ：</label>
			<input type="text" id="mobile" name="mobile">
			<input type="button" id="hsbtn" name="hsbtn" value="获取验证码"/><br>
			<label for="mobile1" > 验 证 码：</label>
			<input type="text" id="vCode" name="vCode"><br>
			<label for="priceMin1">价格下限：</label>
			<input type="text" id="priceMin" name="priceMin"><br>
			<label for="priceMax1">价格上限：</label>
			<input type="text" id="priceMax" name="priceMax">
		</div>
		
		<div data-role="controlgroup" data-type="horizontal">
	    	<a id="button-special" href="#" data-role="button" onclick="save()" >提交</a>
	    </div>
	</form>
	</div>
</div>	
	
	
	
	
	
</body>
</html>