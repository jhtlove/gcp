package hissock.hjxt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class hisWebSocket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static hisService hisSocket;
    public void init() throws ServletException {
    	System.out.println("HjxtWebSocket start.");
    	hisSocket=new hisService();
    	hisSocket.funInit();
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException,IOException{
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
    public void destroy() {
    	if(!(hisSocket==null)) {
    		System.out.println("HjxtWebSocket close.");
    		hisSocket.funClose();
    	}
    }
}