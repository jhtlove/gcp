package hissock.hjxt;

import static io.undertow.Handlers.path;
import static io.undertow.Handlers.resource;
import static io.undertow.Handlers.websocket;

import ihis.pubfun.Pfun;
import io.undertow.Undertow;
import io.undertow.Undertow.Builder;
import io.undertow.server.handlers.resource.ClassPathResourceManager;

public class hisService {
	private Undertow server=null;
	
	public hisService() {
		//ws://192.168.3.20:4080/gcpws/Tjhjsocket
	}
	public void funInit() {
		Builder builder = Undertow.builder();
		Pfun pfun=new Pfun();
		String ssocketip;
		int iport;
		ssocketip=pfun.funGetHisProperties("HjxtSocketIp");
		iport=Integer.valueOf(pfun.funGetHisProperties("HjxtSocketPort"));	
		System.out.println("websocket  ip:"+ssocketip+"  ; port:"+iport);
		builder.addHttpListener(iport,ssocketip);
		builder.setHandler(path().addPrefixPath("/Tjhjsocket",websocket(new hisWebSocketConnectionCallback())).addPrefixPath("/", resource(new ClassPathResourceManager(hisService.class.getClassLoader(),hisService.class.getPackage())).addWelcomeFiles("index.html")));
		server=builder.build();
        server.start();
	}
	public void funClose() {
		server.stop();
	}
}
