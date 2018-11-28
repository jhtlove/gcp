package hissock.hjxt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.StreamSourceFrameChannel;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;

public class hisWebSocketConnectionCallback implements WebSocketConnectionCallback {
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	//若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	public static List<HisSocketBean> clientList=new ArrayList<HisSocketBean>();
	
	private static int count = 0;
	/**
	 * 用户连接时进行注册
	 */	
	public void onConnect(WebSocketHttpExchange exchange,WebSocketChannel channel) {
		channel.getReceiveSetter().set(new AbstractReceiveListener() {
					@Override
					protected void onFullTextMessage(WebSocketChannel channel,BufferedTextMessage message){
						System.out.println("客户端发送的消息："+message.getData());
						WebSockets.sendText("helooooo",channel, null);
						System.out.println("onFullTextMessage:"+channel.getLocalAddress().toString());
					}
					@Override
					protected void onClose(WebSocketChannel webSocketChannel, StreamSourceFrameChannel channel) {
						clientList.remove(webSocketChannel);
						System.out.println("onClose:"+webSocketChannel.getLocalAddress().toString());
					}
					@Override
				    protected void onPong(WebSocketChannel webSocketChannel, StreamSourceFrameChannel channel) throws IOException {
						System.out.println("onPong:"+webSocketChannel.getLocalAddress().toString());
				    }
				});
		channel.resumeReceives();
		String surl = channel.getUrl();
		System.out.println(surl);// ws://192.168.3.20:4080/Tjhjsocket?tvid=TJ1001
		String[] urlArray = surl.split("\\u003F");//?
		if(urlArray.length<2){
			System.out.println("websocket URL 不正确！");
			return ;
		}
		System.out.println(urlArray[1]);
		if(urlArray[1]==null || urlArray[1].equals("")){
			System.out.println("未传入参数！");
			return ;
		}
		String[] paramArray = urlArray[1].split("=");//"tvid=TJ0001"
		if(paramArray.length<2){
			System.out.println("传入参数不正确！");
			return ;
		}
		String tvid = paramArray[1];
		System.out.println(tvid);
		HisSocketBean webSocketBean = new HisSocketBean();
		webSocketBean.setWebSocket(channel);
		webSocketBean.setTvId(tvid);
		clientList.add(webSocketBean);
//		if(count==0){
//			HisCallInit.init();
//			count++;
//		}
	}
}
