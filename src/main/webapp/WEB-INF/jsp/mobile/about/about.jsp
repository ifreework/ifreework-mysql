<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="initial-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"/>
  <title>之麻开门</title>
  <link rel="stylesheet" href="${cssPath }/about/animate.min.css" />
  <link rel="stylesheet" type="text/css" href="${cssPath }/about/swiper.min.css"/>
  <link rel="stylesheet" href="${cssPath }/about/main.css" />
  
  <script src="${jsPath }/about/jquery.js"></script>
  <script type="text/javascript" src="${jsPath }/about/swiper.min.js" ></script>
  <script type="text/javascript" src="${jsPath }/about/swiper.animate1.0.2.min.js" ></script>
</head>
<body>
<div class="wrap">
  <div class="swiper-container">
    <div class="swiper-wrapper">
    
      <div class="swiper-slide">
        <div class="page_01">
          <div class="page_01_01"><img src="${imagePath}/about/1/bg_01.jpg" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0s"></div>
          <div class="page_01_01" style="-webkit-animation:op 4s linear 0s infinite;"><img src="${imagePath}/about/1/dianzhui.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_01_02"><img src="${imagePath}/about/1/bg_02.jpg" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0s"></div>
          <div class="page_01_03"><img src="${imagePath}/about/1/bt.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0s"></div>
          <div class="page_01_06 ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"></div>
          <div class="page_01_07 ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.6s"></div>
          <div class="page_01_08 ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.7s"></div>
          <div class="page_01_04" style="-webkit-animation:qw 8s linear 0s infinite;"><img src="${imagePath}/about/1/quan_wai.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.4s"></div>
          <div class="page_01_05" style="-webkit-animation:qn 10s linear 0s infinite;"><img src="${imagePath}/about/1/quan_nei.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_01_09 ani" style="top:68.5%;" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"><img src="${imagePath}/about/1/line_01.png"></div>
          <div class="page_01_09 ani" style="top:69.5%;" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"><img src="${imagePath}/about/1/line_02.png"></div>
          <div class="page_01_09 ani" style="top:60%;" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.9s"><img src="${imagePath}/about/1/time.png"></div>
          <div class="page_01_10 ani" swiper-animate-effect="rotateInDownRight" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"><img src="${imagePath}/about/1/xian.png"></div>
          <div class="page_01_11 ani" swiper-animate-effect="rotateInUpLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"><img src="${imagePath}/about/1/xian.png"></div>
          <div class="go"><img src="${imagePath}/about/go.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="1s"></div>
        </div>
      </div>
      
      <div class="swiper-slide">
        <div class="page_02">
          <div class="page_02_01" style="top:0;"><img src="${imagePath}/about/gao_up.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_01" style="bottom:0;"><img src="${imagePath}/about/gao_down.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_02"><img src="${imagePath}/about/2/bt.png" class="ani" swiper-animate-effect="rotateIn" swiper-animate-duration="0.5s" swiper-animate-delay="0s"></div>
          <div class="page_02_03 ani" style="top:44%;" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3s"><img src="${imagePath}/about/2/bt_01.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>        
          <div class="page_02_03 ani" style="top:52%;" swiper-animate-effect="bounceOutLeft" swiper-animate-duration="0.5s" swiper-animate-delay="3.2s"><img src="${imagePath}/about/2/txt_01.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.4s"></div>
          <div class="page_02_03 ani" style="top:60%;" swiper-animate-effect="bounceOutRight" swiper-animate-duration="0.5s" swiper-animate-delay="3.4s"><img src="${imagePath}/about/2/txt_02.png" class="ani" swiper-animate-effect="bounceInRight" swiper-animate-duration="0.5s" swiper-animate-delay="0.6s"></div>
          <div class="page_02_03 ani" style="top:68%;" swiper-animate-effect="bounceOutLeft" swiper-animate-duration="0.5s" swiper-animate-delay="3.6s"><img src="${imagePath}/about/2/txt_03.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"></div>
          <div class="page_02_03" style="top:44%;"><img src="${imagePath}/about/2/bt_02.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="3.8s"></div>
          <div class="page_02_04"><img src="${imagePath}/about/2/png_01.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="4s"></div>
          <div class="page_02_04"><img src="${imagePath}/about/2/png_02.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="4.2s"></div>
          <div class="page_02_04"><img src="${imagePath}/about/2/png_03.png" class="ani" swiper-animate-effect="bounceInRight" swiper-animate-duration="0.5s" swiper-animate-delay="4.4s"></div>
          <div class="page_02_04"><img src="${imagePath}/about/2/png_04.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="4.6s"></div>
          <div class="page_02_04"><img src="${imagePath}/about/2/png_05.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="4.8s"></div>
          <div class="page_02_04"><img src="${imagePath}/about/2/png_06.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="5s"></div>
          <div class="go"><img src="${imagePath}/about/go.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="5s"></div>
        </div>
      </div>
      
      <div class="swiper-slide">
        <div class="page_02">
          <div class="page_02_01" style="top:0;"><img src="${imagePath}/about/gao_up.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_01" style="bottom:0;"><img src="${imagePath}/about/gao_down.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_02"><img src="${imagePath}/about/3/bt.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0s"></div>
          <div class="page_03_01 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3.6s"><img src="${imagePath}/about/3/quan_01.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_03_01 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3.4s"><img src="${imagePath}/about/3/quan_02.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"></div>
          <div class="page_03_01 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3.2s"><img src="${imagePath}/about/3/quan_03.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"></div>
          <div class="page_03_01 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3s"><img src="${imagePath}/about/3/quan_04.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.1s"></div>
          <div class="page_03_02 ani" swiper-animate-effect="bounceOutLeft" swiper-animate-duration="0.5s" swiper-animate-delay="3.2s"><img src="${imagePath}/about/3/txt_01.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="1.1s"></div>
          <div class="page_03_02 ani" swiper-animate-effect="bounceOutRight" swiper-animate-duration="0.5s" swiper-animate-delay="3.4s"><img src="${imagePath}/about/3/txt_02.png" class="ani" swiper-animate-effect="bounceInRight" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"></div>
          <div class="page_03_02 ani" swiper-animate-effect="bounceOutLeft" swiper-animate-duration="0.5s" swiper-animate-delay="3.6s"><img src="${imagePath}/about/3/txt_03.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"></div>
          <div class="page_03_03"><img src="${imagePath}/about/3/ren.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="4s"></div>
          <div class="page_03_04"><img src="${imagePath}/about/3/zan.png" class="ani" swiper-animate-effect="rotateInUpLeft" swiper-animate-duration="0.3s" swiper-animate-delay="4.3s"></div>
          <div class="go"><img src="${imagePath}/about/go.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="4.4s"></div>
        </div>
      </div>
      
      <div class="swiper-slide">
        <div class="page_02">
          <div class="page_02_01" style="top:0;"><img src="${imagePath}/about/gao_up.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_01" style="bottom:0;"><img src="${imagePath}/about/gao_down.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_02"><img src="${imagePath}/about/4/bt.png" class="ani" swiper-animate-effect="rotateIn" swiper-animate-duration="0.5s" swiper-animate-delay="0s"></div>
          <div class="page_04_01 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3.6s"><img src="${imagePath}/about/4/bt_01.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_04_02 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="4.2s"><div class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.4s"><img src="${imagePath}/about/4/quan.png" style="-webkit-animation:qw 12s linear 0s infinite;"></div></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="4s"><img src="${imagePath}/about/4/png_00.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="4s"><img src="${imagePath}/about/4/png_01.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="1s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="4s"><img src="${imagePath}/about/4/png_02.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="1.2s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3.8s"><img src="${imagePath}/about/4/dian_01.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3.8s"><img src="${imagePath}/about/4/dian_02.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.6s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3.8s"><img src="${imagePath}/about/4/dian_03.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.8s"></div>
          <div class="page_04_01 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="7.2s"><img src="${imagePath}/about/4/bt_02.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="4.4s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="7.4s"><img src="${imagePath}/about/4/txt_01.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="4.6s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="7.5s"><img src="${imagePath}/about/4/txt_02.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="4.7s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="7.6s"><img src="${imagePath}/about/4/txt_03.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="4.8s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="7.8s"><img src="${imagePath}/about/4/txt_04.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="5s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="7.8s"><img src="${imagePath}/about/4/txt_05.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="5.1s"></div>
          <div class="page_04_03 ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="7.8s"><img src="${imagePath}/about/4/txt_06.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="5.2s"></div>
          <div class="page_04_01"><img src="${imagePath}/about/4/bt_03.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.5s" swiper-animate-delay="8s"></div>
          <div class="page_04_03"><img src="${imagePath}/about/4/PPT.png" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="8.2s"></div>
          <div class="page_04_03"><img src="${imagePath}/about/4/KD.png" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="8.4s"></div>
          <div class="page_04_04" style="-webkit-animation:qw 12s linear 0s infinite;"><img src="${imagePath}/about/4/quan_01.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="8.6s"></div>
          <div class="page_04_04 ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="8.6s"><img src="${imagePath}/about/4/jian.png" style="-webkit-animation:py 4s linear 0s infinite;"></div>
          <div class="page_04_05" style="-webkit-animation:down 2.5s linear 0s infinite;"><img src="${imagePath}/about/4/down_01.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="8.8s"></div>
          <div class="page_04_05" style="-webkit-animation:down 3s linear 0s infinite;"><img src="${imagePath}/about/4/down_02.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="8.9s"></div>
          <div class="page_04_05" style="-webkit-animation:down 3.5s linear 0s infinite;"><img src="${imagePath}/about/4/down_03.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="9s"></div>
          <div class="page_04_05" style="-webkit-animation:down 2s linear 0s infinite;"><img src="${imagePath}/about/4/down_04.png" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="9.1s"></div>
          <div class="go"><img src="${imagePath}/about/go.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="9.1s"></div>
        </div>
      </div>
      
      <div class="swiper-slide">
      	<div class="page_02">
          <div class="page_02_01" style="top:0;"><img src="${imagePath}/about/gao_up.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_01" style="bottom:0;"><img src="${imagePath}/about/gao_down.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_02"><img src="${imagePath}/about/5/bt.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_07_11" style="left:18%;top:55%;-webkit-animation:rotate 10s linear 0s infinite;"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.4s"></div>
          <div class="page_07_11" style="left:45.3%;top:57%;-webkit-animation:rotate 10s linear 0s infinite;"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.6s"></div>
          <div class="page_07_11" style="left:31%;top:63%;-webkit-animation:qw 10s linear 0s infinite;"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/5/png_01.png" class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="1s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/5/png_02.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="1.2s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/5/png_03.png" class="ani" swiper-animate-effect="bounceInRight" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"></div>
          <div class="go"><img src="${imagePath}/about/go.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="1.6s"></div>
        </div>
      </div>
      
      <div class="swiper-slide">
      	<div class="page_02">
          <div class="page_02_01" style="top:0;"><img src="${imagePath}/about/gao_up.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_01" style="bottom:0;"><img src="${imagePath}/about/gao_down.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_02"><img src="${imagePath}/about/9/bt.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_07_11" style="left:18%;top:55%;-webkit-animation:rotate 10s linear 0s infinite;"><div class="ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3s"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.4s"></div></div>
          <div class="page_07_11" style="left:45.3%;top:57%;-webkit-animation:rotate 10s linear 0s infinite;"><div class="ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3s"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.6s"></div></div>
          <div class="page_07_11" style="left:31%;top:63%;-webkit-animation:qw 10s linear 0s infinite;"><div class="ani" swiper-animate-effect="bounceOut" swiper-animate-duration="0.5s" swiper-animate-delay="3s"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"></div></div>
          <div class="page_05_01 ani" swiper-animate-effect="bounceOutUp" swiper-animate-duration="0.5s" swiper-animate-delay="3.6s"><img src="${imagePath}/about/9/png_01.png" class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="1s"></div>
          <div class="page_05_01 ani" swiper-animate-effect="bounceOutLeft" swiper-animate-duration="0.5s" swiper-animate-delay="3.4s"><img src="${imagePath}/about/9/png_02.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="1.2s"></div>
          <div class="page_05_01 ani" swiper-animate-effect="bounceOutRight" swiper-animate-duration="0.5s" swiper-animate-delay="3.2s"><img src="${imagePath}/about/9/png_03.png" class="ani" swiper-animate-effect="bounceInRight" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/9/png_05.png" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="4.2s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/9/png_04.png" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="4s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/9/png_06.png" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="4.4s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/9/png_07.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="4.6s"></div>
          <div class="go"><img src="${imagePath}/about/go.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="4.7s"></div>
        </div>
      </div>
      
      <div class="swiper-slide">
      	<div class="page_02">
          <div class="page_02_01" style="top:0;"><img src="${imagePath}/about/gao_up.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_01" style="bottom:0;"><img src="${imagePath}/about/gao_down.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_02"><img src="${imagePath}/about/10/bt.png" class="ani" swiper-animate-effect="rotateIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_10_01 ani" swiper-animate-effect="aaaa" swiper-animate-duration="0.5s" swiper-animate-delay="1s"><img src="${imagePath}/about/10/png_01.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.4s"></div>
          <div class="page_10_02"><img src="${imagePath}/about/10/png_02.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"></div>
          <div class="page_10_02"><img src="${imagePath}/about/10/png_03.png" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="1.6s"></div>
          <div class="page_10_02"><img src="${imagePath}/about/10/png_04.png" class="ani" swiper-animate-effect="bounceInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="1.8s"></div>
          <div class="page_10_02"><img src="${imagePath}/about/10/png_05.png" class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="2s"></div>
          <div class="page_10_02"><img src="${imagePath}/about/10/png_06.png" class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="2.2s"></div>
          <div class="page_10_02"><img src="${imagePath}/about/10/png_07.png" class="ani" swiper-animate-effect="bounceInRight" swiper-animate-duration="0.5s" swiper-animate-delay="2.4s"></div>
          <div class="go"><img src="${imagePath}/about/go.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.5s" swiper-animate-delay="2.5s"></div>
        </div>
      </div>
      
      <div class="swiper-slide">
      	<div class="page_02">
          <div class="page_02_01" style="top:0;"><img src="${imagePath}/about/gao_up.png" class="ani" swiper-animate-effect="fadeInDown" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_01" style="bottom:0;"><img src="${imagePath}/about/gao_down.png" class="ani" swiper-animate-effect="fadeInUp" swiper-animate-duration="0.3s" swiper-animate-delay="0s"></div>
          <div class="page_02_02"><img src="${imagePath}/about/11/bt.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.2s"></div>
          <div class="page_07_11" style="left:18%;top:55%;-webkit-animation:rotate 10s linear 0s infinite;"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.4s"></div>
          <div class="page_07_11" style="left:45.3%;top:57%;-webkit-animation:rotate 10s linear 0s infinite;"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.6s"></div>
          <div class="page_07_11" style="left:31%;top:63%;-webkit-animation:qw 10s linear 0s infinite;"><img src="${imagePath}/about/7/quan.png" class="ani" swiper-animate-effect="bounceIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"></div>
          <div class="page_05_01"><img src="${imagePath}/about/11/png_01.png" class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="1s"></div>
        </div>
      </div>
      
    </div>
   </div>
</div>
</body>
<script type="text/javascript">
  var mySwiper = new Swiper('.swiper-container', {
    direction: 'vertical',
    onSlideChangeEnd: function(swiper) {
    swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
    },
    onInit: function(swiper) { //Swiper2.x的初始化是onFirstInit
    swiperAnimateCache(swiper); //隐藏动画元素 
    setTimeout(function(){
    swiperAnimate(swiper); //初始化完成开始动画
    },100);
    }
  });
</script>
</html>
