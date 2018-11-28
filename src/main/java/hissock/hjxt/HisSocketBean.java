package hissock.hjxt;

import io.undertow.websockets.core.WebSocketChannel;

public class HisSocketBean {
	private WebSocketChannel webSocket;
	private String tvId;
	public WebSocketChannel getWebSocket() {
		return webSocket;
	}
	public void setWebSocket(WebSocketChannel webSocket) {
		this.webSocket = webSocket;
	}
	public String getTvId() {
		return tvId;
	}
	public void setTvId(String tvId) {
		this.tvId = tvId;
	}
	
}
