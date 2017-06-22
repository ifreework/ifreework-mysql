<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<!-- Navbar -->
    <div class="navbar">
    	<div class="navbar-inner">
    	 	<!-- Navbar Barnd -->
			<div class="navbar-header pull-left">
				<span class="navbar-brand">
					<small>
                        <img alt="" src="${imagePath}/system/Logo_48px.png">
                    </small>
                    <span class="logo-text">
                    	iFreeWork权限管理平台
                    </span>
                </span>
			</div>
            <!-- /Navbar Barnd -->
            
    		<div class="sidebar-collapse" id="sidebar-collapse">
				<i class="collapse-icon fa fa-bars"></i>
            </div>
            
    		<!-- Account Area and Settings --->
            <div class="navbar-header pull-right">
            	<div class="navbar-account">
            		<ul class="account-area">
            			
            			<li>
            				 <a class=" dropdown-toggle" data-toggle="dropdown" title="Help" href="javascript:void(0)">
                             	<i class="icon fa fa-warning"></i>
                             </a>
                             
                             <!--Notification Dropdown-->
                             <ul class="pull-right dropdown-menu dropdown-arrow dropdown-notifications">
                          	 	<li>
                          	 		<a href="javascript:void(0)">
										<div class="clearfix">
											<div class="notification-icon">
                                                <i class="fa fa-phone bg-themeprimary white"></i>
                                            </div>
											<div class="notification-body">
												<span class="title">下班打卡</span>
                                                <span class="description">01:00 pm</span>
											</div>
											<div class="notification-extra">
                                                    <i class="fa fa-clock-o themeprimary"></i>
                                                    <span class="description">office</span>
                                            </div>
										</div>
                          	 		</a>
                                </li>
                                <li>
                          	 		<a href="javascript:void(0)">
										<div class="clearfix">
											<div class="notification-icon">
                                                 <i class="fa fa-check bg-darkorange white"></i>
                                            </div>
											<div class="notification-body">
												<span class="title">参加公司内部会议</span>
                                                <span class="description">01:00 pm - 03:00 pm</span>
											</div>
											<div class="notification-extra">
                                                   <i class="fa fa-clock-o darkorange"></i>
                                            </div>
										</div>
                          	 		</a>
                                </li>
                                <li class="dropdown-footer ">
                                	<span>今天，2017年04月07日</span>
                                    <span class="pull-right">
                                    	10°c
                                        <i class="wi wi-cloudy"></i>
                                    </span>
                                </li>
                             </ul>
                             <!--/Notification Dropdown-->
            			</li>
            			<li>
            				<a class="wave in dropdown-toggle" data-toggle="dropdown" title="Help" href="javascript:void(0)">
                            	<i class="icon fa fa-envelope"></i>
                                <span class="badge">3</span>
                           	</a>
                           	
            				<!--Messages Dropdown-->
                            <ul class="pull-right dropdown-menu dropdown-arrow dropdown-messages">
                           		<li>
                                	<a href="javascript:void(0)">
                                    	<img src="${ imagePath }/avatars/divyia.jpg" class="message-avatar" alt="Divyia Austin">
                                        <div class="message">
                                        	<span class="message-sender">
                                            	张学友
                                            </span>
                                            <span class="message-time">
                                                2分钟前
                                            </span>
                                            <span class="message-subject">
                                              	有时间来听我的演唱会
                                            </span>
                                            <span class="message-body">
                                              	下个星期我将会在济南举办演唱会，有时间的话欢迎来参加
                                            </span>
                                        </div>
                                    </a>
								</li>
                            	<li>
                                	<a href="javascript:void(0)">
                                    	<img src="${ imagePath }/avatars/bing.png" class="message-avatar" alt="Divyia Austin">
                                        <div class="message">
                                        	<span class="message-sender">
                                            	Bing.com
                                            </span>
                                            <span class="message-time">
                                             	昨天
                                            </span>
                                            <span class="message-subject">
                                            	Bing重大升级
                                            </span>
                                            <span class="message-body">
                                                Bing公司对搜索引擎进行重大升级，包涵许多新的功能
                                            </span>
                                        </div>
                                    </a>
								</li>
            				</ul>
            				<!--/Messages Dropdown-->
            			</li>
            			<li>
            				<a class="dropdown-toggle" data-toggle="dropdown" title="Tasks" href="javascript:void(0)">
                            	<i class="icon fa fa-tasks"></i>
                                <span class="badge">4</span>
                            </a>
                            
                            <!--Tasks Dropdown-->
                            <ul class="pull-right dropdown-menu dropdown-tasks dropdown-arrow ">
                            	<li class="dropdown-header bordered-darkorange">
                                	<i class="fa fa-tasks"></i>
                                        4个未完成进度
                                </li>
                                <li>
                                	<a href="javascript:void(0)">
                                    	<div class="clearfix">
                                            <span class="pull-left">帐号申请进度</span>
                                            <span class="pull-right">65%</span>
                                        </div>

                                        <div class="progress progress-xs">
                                            <div style="width:65%" class="progress-bar"></div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <div class="clearfix">
                                            <span class="pull-left">文件下载进度</span>
                                            <span class="pull-right">35%</span>
                                        </div>
                                        <div class="progress progress-xs">
                                            <div style="width:35%" class="progress-bar progress-bar-success"></div>
                                        </div>
                                    </a>
                                </li>
                                <li class="dropdown-footer">
                                	<a href="javascript:void(0)">
                                      	查看所有进度
                                    </a>
                                    <button class="btn btn-xs btn-default shiny darkorange icon-only pull-right"><i class="fa fa-check"></i></button>
                                </li>
                            </ul>
                            <!--/Tasks Dropdown-->
            			</li>
            			<li id="user-manager">
            				<a class="login-area dropdown-toggle" data-toggle="dropdown">
                                <div class="avatar" title="View your public profile">
                                    <img src="main/userImgDownload">
                                </div>
                                <section>
                                    <h2><span class="profile"><span>${user.personName }</span></span></h2>
                                </section>
                            </a>
                            
                            <ul class="pull-right dropdown-menu dropdown-tasks dropdown-arrow dropdown-login">
                            	<li><a href="javascript:void(0)" data-id="user-img"><b class="fa fa-user"></b><span>编辑头像</span></a></li>
								<li><a href="javascript:void(0)" data-id="user-edit" data-username="${user.username }"><b class="fa fa-cog"></b><span>用户设置</span></a></li>
								<li><a href="javascript:void(0)" data-id="password-reset" data-username="${user.username }"><b class="fa fa-cog"></b><span>密码重置</span></a></li>
								<li><a href="javascript:void(0)"><b class="fa fa-info-circle"></b><span>&nbsp;&nbsp;帮&nbsp;&nbsp;&nbsp;&nbsp;助&nbsp;&nbsp;</span></a></li>
								<li><a href="javascript:void(0)" data-id="logout"><b
										class="glyphicon glyphicon-log-out red"></b><span class="red">&nbsp;&nbsp;退&nbsp;&nbsp;&nbsp;&nbsp;出</span></a></li>
                            </ul>
            			</li>
            		</ul>
            	</div>
    		</div>
    	</div>
    </div>
    <!-- /Navbar -->