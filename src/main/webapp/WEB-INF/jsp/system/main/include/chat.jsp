
<div class="chat" id="chat"></div>

<script>
	$("#chat").chat({
		userUrl : "main/queryContacts",
		server : "/ifreework/websocket1",
		subscribe : "/topic/greetings",
		send : '/app/greeting',
		fromUser : "admin",
		fromUserName : "你叫什么名i"
	});
	
	
</script>