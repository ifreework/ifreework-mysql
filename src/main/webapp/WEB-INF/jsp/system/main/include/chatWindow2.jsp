<div class="chat_window">
	<div class="chat_window_top">
		<div class="user_img">
			<img src="main/userImgDownload">
		</div>
		<span class="user_name">爱新觉罗</span> <i class="fa fa-minus"></i> <i
			class="fa fa-times"></i>
	</div>
	
	<div class="chat_window_body">
		<ul class="nav">
			<li>
				<img src="http://api.randomuser.me/portraits/thumb/men/10.jpg" style="width: 40px;height:40px;position: relative;left:15px">
				<div class="message-popover left" style="position: relative;left: 25px;">
					<p>Sed posuere consectetur est at lobortis. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum.</p>
				</div>
			</li>
			<li>
				<img src="http://api.randomuser.me/portraits/thumb/men/10.jpg" style="width: 40px;height:40px;position: relative;left:590px">
				<div class="message-popover right">
					<p>Sed posuere 随着角色的持续成长，依赖于召唤体的输出会遇到瓶颈</p>
				</div>
			</li>
		</ul>
		
	</div>
	
	<div class="chat_window_footer">
		<div class="widget-body">
			<div class="btn-toolbar wysiwyg-toolbar" data-role="editor-toolbar"
				data-target="#editor">
				<div class="btn-group">
					<a class="btn btn-default dropdown-toggle" data-toggle="dropdown"
						title="Font"><i class="fa fa-font"></i><b class="caret"></b></a>
					<ul class="dropdown-menu"></ul>
				</div>
				<div class="btn-group">
					<a class="btn btn-default dropdown-toggle" data-toggle="dropdown"
						title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b
						class="caret"></b></a>
					<ul class="dropdown-menu dropdown-default">
						<li><a data-edit="fontSize 5"><font size="5">Huge</font></a></li>
						<li><a data-edit="fontSize 3"><font size="3">Normal</font></a></li>
						<li><a data-edit="fontSize 1"><font size="1">Small</font></a></li>
					</ul>
					<a class="btn btn-default" data-edit="bold"
						title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a> <a
						class="btn btn-default" data-edit="italic"
						title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a> <a
						class="btn btn-default" data-edit="strikethrough"
						title="Strikethrough"><i class="fa fa-strikethrough"></i></a> <a
						class="btn btn-default" data-edit="underline"
						title="Underline (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
					<a class="btn btn-default"
						title="Insert picture (or just drag & drop)" ><i
						class="fa fa-picture-o"></i></a> <input type="file"
						data-role="magic-overlay" data-target="#pictureBtn"
						data-edit="insertImage" />
				</div>
				<input type="text" data-edit="inserttext" class="wysiwyg-voiceBtn"
					id="voiceBtn" x-webkit-speech="">
			</div>
			<div class="wysiwyg-editor" id="editor"></div>
		</div>
		<div class="btn-toolbar wysiwyg-button">
			<a class="btn btn-default pull-right submit">发送</a>
			<a class="btn btn-default pull-right cannel">取消</a>
		</div>

	</div>
</div>
<script src="resources/js/bootstrap/editors/wysiwyg/jquery.hotkeys.js"></script>
<script src="resources/js/bootstrap/editors/wysiwyg/prettify.js"></script>
<script src="resources/js/bootstrap/editors/wysiwyg/bootstrap-wysiwyg.js"></script>
	
<script>
	$(".chat_window").css({
		top : ($("body").height() - 550) / 2,
		left : ($("body").width() - 650) / 2
	});
	
	var a1 = $("body").chatwindow({id:1});
    $(function () {
        function initToolbarBootstrapBindings() {
            var fonts = ['Serif', 'Sans', 'Arial', '宋体'],
                  fontTarget = $('[title=Font]').siblings('.dropdown-menu');
            $.each(fonts, function (idx, fontName) {
                fontTarget.append($('<li><a data-edit="fontName ' + fontName + '" style="font-family:\'' + fontName + '\'">' + fontName + '</a></li>'));
            });
            $('a[title]').tooltip({ container: 'body' });
            
            $('.dropdown-menu input').click(function () { return false; })
                .change(function () { $(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle'); })
            .keydown('esc', function () { this.value = ''; $(this).change(); });

            $('[data-role=magic-overlay]').each(function () {
                var overlay = $(this), target = $(overlay.data('target'));
                overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
            });
            if ("onwebkitspeechchange" in document.createElement("input")) {
                var editorOffset = $('#editor').offset();
                $('#voiceBtn').css('position', 'absolute').offset({ top: editorOffset.top, left: editorOffset.left + $('#editor').innerWidth() - 35 });
            } else {
                $('#voiceBtn').hide();
            }
        };
        
        initToolbarBootstrapBindings();
        
        $('.wysiwyg-editor').wysiwyg();
        
        window.prettyPrint && prettyPrint();
    });
</script>