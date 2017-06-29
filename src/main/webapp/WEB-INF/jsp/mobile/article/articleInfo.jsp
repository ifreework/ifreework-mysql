<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>${user.company }的公司介绍</title>
<link href="${cssPath}/mobile/main.css" rel="stylesheet" type="text/css">
<link href="${cssPath}/mobile/style.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/mobile/shake.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="${cssPath}/animate.min.css" rel="stylesheet" type="text/css">
<link href="${cssPath}/mobile/idangerous.swiper.css" rel="stylesheet"
	type="text/css">

<script type="text/javascript" src="${ jsPath }/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${jsPath }/mobile/wo.js"></script>
</head>
<body>
<div class="warpe">
    <div class="head head2">
        <a href="#" class="return" style="color: #5f5e5e;"><i class="fa fa-chevron-left"></i></a>
        <a href="#" id="sc_bth" class="search" style="color: #5f5e5e;"><i class="fa fa-home"></i> </a>
    </div>
    <div class="main detail">
        <div class="finance">
            <ul>
                <li class="animated fadeIn clear_border">
                    <a>
                        <p>【必看】这些钱可以省了！总理力督，国务院开出减税降费清单</p>
                        <p><span class="puff_left info_user">张三</span><span class="puff_left">08-05 23:58</span><span class="puff_right edit_mine">修改成我的</span> </p>
                        <div class="fin_txt">
                            <p><span>李克强总理在今年的政府工作报告中提出，2016年将安排财政赤字2.18万亿元，比去年增加5600亿元，赤字率提高到3%，主要用于减税降费，进一步减轻企业负担。关于减税降费，今年以来，国务院常务会议至少进行了5次部署，各部门也出台多项政策。下面就是国务院客户端为你整理的2016年减税降费清单，赶紧看看哪些与你有关：</span></p>
                            <p><img src="${imagePath }/system/11.jpg"></p>
                            <p><span>截至目前，“营改增”攻坚战已在全国稳步有序铺展开来。房地产、建筑和生活服务业企业均已完成营改增后的首次报税，新增季度性报税的金融业和小规模纳税人也从7月1日起迎来首次申报期。预计营改增今年将为企业降低成本5000亿以上。</span></p>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="main small_card">
			<div class="small_card_img">
					<a href="#"><img src="${imagePath }/mobile/pic8.jpg"
							alt="" /></a>
			</div>
			
			<div class="small_card_info">
				<div class="user_img">
					<img alt="用户未上传头像" src="${user.imgPath }">
				</div>
				<div class="user_info">
					<span class="username">${user.personName }</span>
					<span><label><i class="fa fa-envelope"></i></label> ${user.email }</span>
				</div>
				<div class="work_room">
					<span><i class="fa fa-home"></i></span>
					<span>工作室</span>
				</div>
				<div class="card_option">
					<a href="#"><i class="fa fa-phone"></i>一键拨号</a>
					<a href="#"><i class="fa fa-comments-o"></i>添加好友</a>
					<a href="#">我的工作室</a>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="recommend">
				<div class="title">
					相关阅读：
				</div>
				<div class="content">
					<ul>
						<li>
							<a href="#">北大博士精心推荐24字公开课程</a>
						</li>
						<li>
							<a href="#">北大博士精心推荐24字公开课程</a>
						</li>
						<li>
							<a href="#">北大博士精心推荐24字公开课程</a>
						</li>
						<li>
							<a href="#">北大博士精心推荐24字公开课程</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="main">
	        <div class="pictitle">
	            <span class="picbg"></span>
	            <span>热销产品推荐</span>
	            <a href="#" class="puff_right">更多 ></a>
	        </div>
	        <div class="bannerside">
	            <a href="#"><img src="${imagePath }/mobile/pic1.jpg"></a>
	        </div>
	        <div class="bannerside">
	            <a href="#"><img src="${imagePath }/mobile/pic2.jpg"></a>
	        </div>
	        <div class="bannerside">
	            <a href="#"><img src="${imagePath }/mobile/pic3.jpg"></a>
	        </div>
    	</div>
		
		<div class="footer">
			<div class="id_bth">
				<a href="#">免责声明</a> <a href="#">关于我们</a> <a href="#">申请工作室</a>
			</div>
		</div>
</div>

</body>
</html>