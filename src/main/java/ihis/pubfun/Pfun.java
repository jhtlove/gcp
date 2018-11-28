package ihis.pubfun;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import ihis.bean.JsonBean;


public class Pfun {
	@SuppressWarnings("unchecked")
	public Map<String,String> funMapCopy(Map<String,String> vmap){
		Map<String,String> rtnMap=new HashMap<String,String>();
		rtnMap=(Map<String,String>)((HashMap<String,String>)vmap).clone();
		return rtnMap;
	}
	public String funRight(String smls,int len){
		String vsrtn;
		vsrtn=smls.substring(smls.length() - len,smls.length());
		return vsrtn;
	}
	public String funMd(String arg,String valid){
		String sscmd;
		sscmd=arg+valid;
		MessageDigest md;
		byte b[];
		StringBuffer buf = new StringBuffer("");
		int i;
		try { 
			md = MessageDigest.getInstance("MD5"); 
			md.update(sscmd.getBytes()); 
			b = md.digest();			 
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if(i<0) i+= 256; 
				if(i<16)
				buf.append("0");
				buf.append(Integer.toHexString(i)); 
			}
			md=null;	
			b=null;
			return buf.toString().substring(8,24);
		} catch (NoSuchAlgorithmException e) { 
			return "";
		}finally{
			sscmd=null;
			md=null;
			b=null;
			buf=null;
		}
	}
	public String Autoadd(String arg){
		String out="",temp="",rtn="",st;
		boolean b=false;
		arg=arg.trim();
		int i=1,len;
		try{
			if(arg.equals("") || arg==null){
				return "";
			}
			len=arg.length();
			do{
				temp=arg.substring(len - i,len - i+1);
				i++;
				if (isnumber(temp)){
					out=temp+out;
				}
				else
				{
					b=true;
					break;
				}
			}while(i<len+1);
			i--;
			if(out.equals("")){
				return arg;
			}
			int outlen=out.length();
			st=String.valueOf((Long.parseLong(out)+1));
			st=st.trim();
			out="0000000000000"+st;
			out=out.substring(out.length() - outlen,out.length());
			if (out.length()>len)
				rtn=out;
			else 
			{
				if(b)
					rtn=arg.substring(0,len - i+1)+out;
				else
					rtn=arg.substring(0,len - i)+out;
			}
			return rtn;
		}finally{
			out=null;
			temp=null;
			rtn=null;
			st=null;
		}
	}
	public void SqlLog(boolean bfile,String serr){
		if(bfile){
			FileWriter fw=null;
			String sjtbpath="/home/lxg/sqllog.log";
			try {				
				if(sjtbpath.equals("")){
					return;
				}
				fw=new FileWriter(sjtbpath,true);
				fw.write("〖"+this.getToday("yyyy/MM/dd HH:mm:ss")+"〗"+serr+"\r\n");
				fw.close();				
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				fw=null;
				sjtbpath=null;
			}
		}else{
			System.out.println(this.getToday("yyyy/MM/dd HH:mm:ss")+":"+serr);
		}
	}	
	public void WriteLog(boolean bfile,String serr){
		if(bfile){
			WriteLogA(serr);
		}else{
			System.out.println(this.getToday("yyyy/MM/dd HH:mm:ss")+":"+serr);
		}
	}
	public void WriteLogA(String serr){
		String smm;
		String sjtbpath;
		String ls_os;
		FileWriter fw;
		try {
			smm=this.getToday("yyyyMMdd");
			sjtbpath="/home/lxg/"+smm+".log";
			boolean bos=false;
			ls_os=System.getProperty("os.name");
			ls_os=ls_os.toLowerCase();
			if(ls_os.equals("linux")){
				bos=true;
			}
			if(bos){
				sjtbpath="/home/lxg/"+smm+".log";
			}else{
				sjtbpath="c:/jboss71/"+smm+".log";
			}
			if(sjtbpath.equals("")){
				return;
			}
			fw=new FileWriter(sjtbpath,true);
			System.out.println(serr);
			fw.write(this.getToday("yyyy/MM/dd HH:mm:ss")+":"+serr+"\r\n");
			fw.close();
			fw=null;
		}catch(IOException e){
			
		}finally{
			fw=null;
			sjtbpath=null;
			ls_os=null;
		}
	}
	public void SjtbLog(String swid,String serr){
		String sjtbpath;
		String ls_os;
		FileWriter fw;
		try {			
			sjtbpath="/home/lxg/"+swid+".log";
			boolean bos=false;
			ls_os=System.getProperty("os.name");
			ls_os=ls_os.toLowerCase();
			if(ls_os.equals("linux")){
				bos=true;
			}
			if(bos){
				sjtbpath="/home/lxg/"+swid+".log";
			}else{
				sjtbpath="c:/jboss71/"+swid+".log";
			}
			if(sjtbpath.equals("")){
				return;
			}
			fw=new FileWriter(sjtbpath,true);
			fw.write(this.getToday("yyyy/MM/dd HH:mm:ss")+":"+serr+"\r\n");
			fw.close();
			fw=null;
		}catch(IOException e){
			
		}finally{
			fw=null;
			sjtbpath=null;
			ls_os=null;
		}
	}
	/*
	 * 判断传入字符串是否是数字型数据
	 */
	private boolean isnumber(String arg){
		String temp;
		if (arg.trim()==""||arg==null) return false;
		for(int i=1;i<=arg.length();i++){
			temp=arg.substring(arg.length() - i,arg.length() - i+1);
			if(temp.equals("0")||temp.equals("1")||temp.equals("2")||temp.equals("3")||temp.equals("4")||temp.equals("5")||temp.equals("6")||temp.equals("7")||temp.equals("8")||temp.equals("9"))
			{
				
			}else
			{
				return false;
			}
		}
		return true;
	}
	public String getToday(String format){
		String srtn,gs;
		gs=format;
		SimpleDateFormat dateFormat=new SimpleDateFormat(gs);
		srtn=dateFormat.format(new Date());
		dateFormat=null;
		return srtn;
	}
	public String loadFileToString(String filePath) throws IOException {   
		 BufferedReader br = null;   
		 String ret = null;
		 File file = new File(filePath);   
		 StringBuffer sb ;
		 String line = null;
		 try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));   
			 sb = new StringBuffer((int)file.length());   
		  	 while( (line = br.readLine() ) != null ) {   
		  		 sb.append(line);   
		  	}
		  	ret = sb.toString();   
		  	return ret;
		 } finally {   
			 if(br!=null) {try{br.close();} catch(Exception e){} }   
			 sb=null;
			 br=null;
			 file=null;
			 ret=null;
			 line=null;
		 }
	}
	public String getid(String usrid){
		//长度为24位
		StringBuffer rtnid=new StringBuffer();
		String rq;
		String ran,suseid;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmssSS");
		try{
			//随机码
			ran="0000"+String.valueOf((int)(Math.random()*1000));
			ran=ran.substring(ran.length() - 3);
			//用户ID
			suseid="0000"+usrid;
			suseid=suseid.substring(suseid.length() - 4);
			//日期		
			rq=dateFormat.format(new Date());
			//返回值为日期(17)+用户ID（4位）+随机码(3位)
			rtnid=new StringBuffer(rq);
			rtnid.append(suseid);
			rtnid.append(ran);
			dateFormat=null;
			return rtnid.toString();
		}finally{
			rtnid=null;
			rq=null;
			ran=null;
			suseid=null;
			dateFormat=null;
		}
	}
	public String getswid(String usrid,String ssrq){
		//长度为24位
		StringBuffer rtnid=new StringBuffer();
		String rq;
		String ran,suseid;
		try{
			//随机码
			ran="0000"+String.valueOf((int)(Math.random()*1000));
			ran=ran.substring(ran.length() - 3);
			//用户ID
			suseid="0000"+usrid;
			suseid=suseid.substring(suseid.length() - 4);
			//日期 2011/04/27 16:34:26:903
			rq=ssrq+"00";
			rq=rq.substring(0,17);
			//返回值为日期(17)+用户ID（4位）+随机码(3位)
			rtnid=new StringBuffer(rq);
			rtnid.append(suseid);
			rtnid.append(ran);
			return rtnid.toString();
		}finally{
			rtnid=null;
			rq=null;
			ran=null;
			suseid=null;
		}
	}
    public String removeBlank(String str){
    	StringBuilder sb = new StringBuilder();
    	char c =' ';
    	char ch;
    	try{
        	for(int i = 0 ; i < str.length() ; i++){
        		ch = str.charAt(i);
        		if(ch != c){
        			sb.append(ch);
        		}
        	}
        	return sb.toString();
    	}finally{
    		sb=null;
    	}
    }
    //相加
    public double dsum(double d1,double d2){ 
        BigDecimal bd1,bd2;
        try{
            bd1 = new BigDecimal(Double.toString(d1)); 
            bd2 = new BigDecimal(Double.toString(d2)); 
            return bd1.add(bd2).doubleValue(); 
        }finally{
        	bd1=null;
        	bd2=null;
        }
    }
    //相减
    public double dsub(double d1,double d2){ 
    	BigDecimal bd1,bd2;
    	try{
            bd1 = new BigDecimal(Double.toString(d1)); 
            bd2 = new BigDecimal(Double.toString(d2)); 
            return bd1.subtract(bd2).doubleValue(); 
        }finally{
        	bd1=null;
        	bd2=null;
        }
    }
    //乘法
    public double dmul(double d1,double d2){ 
    	BigDecimal bd1,bd2;
    	try{
	        bd1 = new BigDecimal(Double.toString(d1)); 
	        bd2 = new BigDecimal(Double.toString(d2)); 
	        return bd1.multiply(bd2).doubleValue(); 
        }finally{
        	bd1=null;
        	bd2=null;
        }
    }
    //除法
    public double ddiv(double d1,double d2,int scale){ 
    	BigDecimal bd1,bd2;
    	try{
	        bd1 = new BigDecimal(Double.toString(d1)); 
	        bd2 = new BigDecimal(Double.toString(d2)); 
	        return bd1.divide(bd2,scale,BigDecimal.ROUND_HALF_UP).doubleValue(); 
        }finally{
        	bd1=null;
        	bd2=null;
        }
    }
    //数据进行取精度
    public double dround(Double value,int scale){
		Double flag=null;
		String text=value.toString(); 		
		BigDecimal bd;
		try{
			bd=new BigDecimal(text).setScale(scale,BigDecimal.ROUND_HALF_UP); 
			flag=bd.doubleValue();
			return flag;
		}finally{
			bd=null;
			flag=null;
			text=null;
		}
    }  
	public String listToXmlString(String sencoding,List<Map<String,Object>> result){
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding(sencoding); 		//字符集
		Element rows = doc.addElement("rows");
		String skey;
		String svalue;
		Set<String> key;
		Element row,titleElement;
		Iterator<String> itera;
		Map<String,Object> map;
		try{
			for(int i=0;i<result.size();i++){
				map=result.get(i);
				key=map.keySet();
				row=rows.addElement("row");
				itera=key.iterator();
				while(itera.hasNext()){
					skey=itera.next();
					if (map.get(skey)==null)
					{
						svalue="";
					}else{
						svalue=String.valueOf(map.get(skey));
						if(svalue.equals("null")) svalue="";
						svalue=svalue.trim();
					}
					titleElement = row.addElement(skey);
					titleElement.setText(svalue);
				}
			}
			return doc.asXML();
		}finally{
			doc=null;
			rows=null;
			skey=null;
			svalue=null;
			key=null;
			row=null;
			titleElement=null;
			itera=null;
			map=null;
		}
	}
	public String listToXmlStringA(String sencoding,List<Map<String,String>> result){
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding(sencoding); 		//字符集
		Element rows = doc.addElement("rows");
		String skey;
		String svalue;
		Map<String,String> map;
		Set<String> key;
		Element row,titleElement;
		Iterator<String> itera;
		try{
			for(int i=0;i<result.size();i++){
				map=result.get(i);
				key=map.keySet();
				row=rows.addElement("row");
				itera=key.iterator();
				while(itera.hasNext()){
					skey=itera.next();
					if (map.get(skey)==null)
					{
						svalue="";
					}else{
						svalue=String.valueOf(map.get(skey));
						if(svalue.equals("null")) svalue="";
						svalue=svalue.trim();
					}
					titleElement = row.addElement(skey);
					titleElement.setText(svalue);
				}
			}
			return doc.asXML();
		}finally{
			doc=null;
			rows=null;
			skey=null;
			svalue=null;
			map=null;
			key=null;
			row=null;
			titleElement=null;
			itera=null;
		}
	}
	public StringBuilder listToTxt(List<Map<String,Object>> vlist){
		StringBuilder str=new StringBuilder("");
		String skey,svalue;
		Map<String,Object> cmap;
		Set<String> mapset;
		Object objs;
		Iterator<String> itera;
		try{
			for(int i=0;i<vlist.size();i++){
				cmap=vlist.get(i);
				mapset=cmap.keySet();
				itera=mapset.iterator();
				while(itera.hasNext()){
					skey=itera.next();
					objs=cmap.get(skey);
					if(objs==null){
						svalue="";
					}else{
						svalue=String.valueOf(objs);
					}
					if(svalue.indexOf("\n")>=0||svalue.indexOf("\t")>=0){
						if(svalue.indexOf("\"")>=0){
							
						}else{
							svalue="\""+svalue+"\"";
						}
					}
					str.append(svalue);
					if(itera.hasNext()){
						str.append("\t");
					}
				}
				if(i==(vlist.size() - 1)){				
				}else{
					str.append("\r\n");
				}
			}
			return str;
		}finally{
			str=null;
			skey=null;
			svalue=null;
			cmap=null;
			mapset=null;
			objs=null;
			itera=null;	
		}
	}	
	public String funRtnToXmlString(String sencoding,RtnStr arg){
		String serror;
		serror=arg.getErrortext();
		if(serror==null){
			serror="";
		}
		serror=serror.replaceAll("<","&lt;");
		serror=serror.replaceAll(">","&gt;");
		StringBuffer sb;
		try{
			sb=new StringBuffer("<?xml version=\"1.0\" encoding=\""+sencoding+"\" standalone=\"no\"?><rtns><rtn>");
			sb.append("<state>"+arg.getState()+"</state>");
			sb.append("<errortext>"+serror+"</errortext>");
			sb.append("<a>"+arg.getA()+"</a>");
			sb.append("<b>"+arg.getB()+"</b>");
			sb.append("<c>"+arg.getC()+"</c>");
			sb.append("<d>"+arg.getD()+"</d>");
			sb.append("<e>"+arg.getE()+"</e>");
			sb.append("<f>"+arg.getF()+"</f>");
			sb.append("<g>"+arg.getG()+"</g>");
			sb.append("<h>"+arg.getH()+"</h>");
			sb.append("<i>"+arg.getI()+"</i>");
			sb.append("</rtn></rtns>");
			return sb.toString();			
		}finally{
			serror=null;
			sb=null;
		}
	}
	public void funFileWriter(String spath,String txt,boolean bpd) throws IOException{
		File file=new File(spath);
		FileWriter writer=null;
		try{
			writer = new FileWriter(file,bpd);
			writer.write(txt);
			writer.flush();
			writer.close();			
		}finally{
			if(writer==null){
			}else{
				writer.close();	
			}			
			writer=null;
			file=null;
		}
	}
	public String funGetIp(boolean bipm){
		InetAddress iadd;
		try {
			iadd = InetAddress.getLocalHost();		
			String sip,shname;
			sip=iadd.getHostAddress();
			shname=iadd.getHostName();
			if(bipm){
				return sip;			
			}else{
				return shname;	
			}
		} catch (UnknownHostException e) {
			System.out.println("得到本机IP时出错:"+e.getMessage());
			return "";
		}		
	}
	public List<String> funGetMIp(){
		List<String> rtnlis=new ArrayList<String>();
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) 
			{
				NetworkInterface intf = en.nextElement();
			    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) 
			    {
			    	InetAddress inetAddress = enumIpAddr.nextElement();
//			    	inetAddress.is
//			    	if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) 
//			    	{
			    		rtnlis.add(inetAddress.getHostAddress());
//			        }
	            }
	        }
	    } catch (SocketException ex) {
	    	
	    }
		return rtnlis;
	}
	public RtnStr xmlToRtnStr(String xml){
		RtnStr mrtn=new RtnStr();
		SAXReader reader; 
		StringReader in;
		Document doc;
		String xpath;
		Element e;
		try {
			reader = new SAXReader();
			in=new StringReader(xml);
			doc = reader.read(in);
	    	xpath = "/rtns/rtn/state";
	     	e = (Element)doc.selectSingleNode(xpath);
	    	mrtn.setState(Boolean.valueOf(e.getTextTrim()));
	    	xpath = "/rtns/rtn/errortext";
	    	e = (Element)doc.selectSingleNode(xpath);
	    	mrtn.setErrortext(e.getTextTrim());
	    	mrtn.setA(((Element)doc.selectSingleNode("/rtns/rtn/a")).getTextTrim());
	    	mrtn.setB(((Element)doc.selectSingleNode("/rtns/rtn/b")).getTextTrim());	    	
	    	mrtn.setC(((Element)doc.selectSingleNode("/rtns/rtn/c")).getTextTrim());	
	    	mrtn.setD(((Element)doc.selectSingleNode("/rtns/rtn/d")).getTextTrim());
	    	mrtn.setE(((Element)doc.selectSingleNode("/rtns/rtn/e")).getTextTrim());
	    	mrtn.setF(((Element)doc.selectSingleNode("/rtns/rtn/f")).getTextTrim());
	    	mrtn.setG(((Element)doc.selectSingleNode("/rtns/rtn/g")).getTextTrim());
	    	mrtn.setH(((Element)doc.selectSingleNode("/rtns/rtn/h")).getTextTrim());
	    	mrtn.setI(((Element)doc.selectSingleNode("/rtns/rtn/i")).getTextTrim());
	    	return mrtn;
		}catch(Exception ex){
			mrtn.setState(false);
			mrtn.setErrortext(ex.toString());
			return mrtn;
		}finally{
			mrtn=null;
			reader=null; 
			in=null;
			doc=null;
			xpath=null;
			e=null;
		}		
	}
	public String listToXmlString(List<Map<String,Object>> result){
		Document doc = DocumentHelper.createDocument();
		Element rows = doc.addElement("rows");
		String skey;
		String svalue;
		Map<String,Object> map;
		Set<String> key;
		Element row,titleElement;
		Iterator<String> itera;
		try{
			for(int i=0;i<result.size();i++){
				map=result.get(i);
				key=map.keySet();
				row=rows.addElement("row");
				itera=key.iterator();
				while(itera.hasNext()){
					skey=itera.next();
					if (map.get(skey)==null)
					{
						svalue="";
					}else{
						svalue=String.valueOf(map.get(skey));
						if(svalue.equals("null")) svalue="";
						svalue=svalue.trim();
					}
					titleElement = row.addElement(skey);
					titleElement.setText(svalue);
				}
			}
			return doc.asXML();
		}finally{
			doc = null;
			rows = null;
			skey = null;
			svalue = null;
			map = null;
			key = null;
			row = null;
			titleElement = null;
			itera = null;
		}
	}
	public String funListToXml(String sroot,List<Map<String,Object>> result){		
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("");
		Element rows = doc.addElement(sroot);
		rows = rows.addElement("rows");
		String skey;
		String svalue;
		Map<String,Object> map;
		Set<String> key;
		Element row,titleElement;
		Iterator<String> itera;
		try{
			for(int i=0;i<result.size();i++){
				map=result.get(i);
				key=map.keySet();
				row=rows.addElement("row");
				itera=key.iterator();
				while(itera.hasNext()){
					skey=itera.next();
					if (map.get(skey)==null)
					{
						svalue="";
					}else{
						svalue=String.valueOf(map.get(skey));
						if(svalue.equals("null")) svalue="";
						svalue=svalue.trim();
					}
					titleElement = row.addElement(skey);
					titleElement.setText(svalue);
				}
			}
			return doc.getRootElement().asXML();
		}finally{
			doc = null;
			rows = null;		
			skey = null;
			svalue = null;
			map = null;
			key = null;
			row = null;
			titleElement = null;
			itera = null;			
		}
	}	
	public boolean funIsNumber(String sval){
		String sgs;
		sgs="^[0-9]+\\.{0,1}[0-9]{0,8}";  //最多8位小数
		Pattern pattern = Pattern.compile(sgs);    
		sgs=null;
		return pattern.matcher(sval).matches();
	}
	public String getintid(String usrid,String szsid){
		Calendar c1 = Calendar.getInstance();
		StringBuffer rtnid=new StringBuffer();
		String rq;
		String ran,suseid;
		try{
			ran="0000"+String.valueOf((int)(Math.random()*1000));
			ran=ran.substring(ran.length() - 1);
			suseid="00000"+szsid;
			suseid=suseid.substring(suseid.length() - 2);
			rq=String.valueOf(c1.getTimeInMillis());
			rq=rq.substring(0,12);
			rtnid=new StringBuffer(rq);		//12	日期	
			rtnid.append(suseid);			//2 	诊所ID
			rtnid.append(ran);				//1		随机码  
			return rtnid.toString();			
		}finally{
			c1=null;
			rtnid=null;
			rq=null;
			ran=null;
			suseid=null;
		}
	}
	public List<Map<String,String>> funMapListToStringMapList(List<Map<String,Object>> result){		
		String skey;
		String svalue;
		Map<String,Object> map;
		Map<String,String> vmap;
		Set<String> key;
		List<Map<String,String>> rtnList=new ArrayList<Map<String,String>>();
		Iterator<String> itera;
		try{
			for(int i=0;i<result.size();i++){
				map=result.get(i);
				key=map.keySet();
				vmap=new HashMap<String,String>();
				itera=key.iterator();
				while(itera.hasNext()){
					skey=itera.next();
					if (map.get(skey)==null)
					{
						svalue="";
					}else{
						svalue=String.valueOf(map.get(skey));
						if(svalue.equals("null")) svalue="";
						svalue=svalue.trim();
					}
					vmap.put(skey,svalue);
				}
				rtnList.add(vmap);
			}
			return rtnList;
		}finally{
			skey=null;
			svalue=null;
			map=null;
			vmap=null;
			key=null;
			rtnList=null;
			itera=null;
		}
	}
	public String funGetHisProperties(String name){
		Properties pro = new Properties();
		String command="";
		FileInputStream fis;
		try {
			String ls_os,lspath;
			ls_os=System.getProperty("os.name");
			ls_os=ls_os.toLowerCase();
			if(!ls_os.equals("linux")){
				lspath="D:/jboss71/ihisconf.properties";
			}else{
				lspath="/home/lxg/jboss5/ihisconf.properties";
			}
			fis = new FileInputStream(lspath);
			pro.load(fis);
			command=pro.getProperty(name);
		} catch (FileNotFoundException e) {
			WriteLogA("[Pfun->funGetHisProperties]:"+e.getMessage());
		} catch (IOException e) {
			WriteLogA("[Pfun->funGetHisProperties]:"+e.getMessage());
		}
		return command;
	}
	public byte[] funReadFile(String spath) throws IOException{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(spath));     
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);     
	    byte[] temp = new byte[1024];
	    byte[] content;
	    int size = 0;
	    try{
		    while ((size = in.read(temp)) != -1) {     
		    	out.write(temp, 0, size);
		    }
		    in.close();
		    content = out.toByteArray();
	        return content;
	    }finally{
	    	in=null;
	    	out=null;
	    	temp=null;
	    	content=null;
	    }
	}
	public String funBusiToJson(RtnStr psend){
		String srtn;
		JSONObject jsonObject2 = new JSONObject();
		try{
			if(psend.getState()){
				jsonObject2.put("state","true");
				jsonObject2.put("A01",psend.getA01());
				jsonObject2.put("A02",psend.getA02());
				jsonObject2.put("A03",psend.getA03());
				jsonObject2.put("A04",psend.getA04());
				jsonObject2.put("A05",psend.getA05());
				jsonObject2.put("A06",psend.getA06());
				jsonObject2.put("A07",psend.getA07());
				jsonObject2.put("A08",psend.getA08());
				jsonObject2.put("A09",psend.getA09());
				jsonObject2.put("A10",psend.getA10());
				jsonObject2.put("A11",psend.getA11());
				jsonObject2.put("A12",psend.getA12());
				jsonObject2.put("A13",psend.getA13());
				jsonObject2.put("A14",psend.getA14());
				jsonObject2.put("A15",psend.getA15());
				jsonObject2.put("A16",psend.getA16());
				jsonObject2.put("A17",psend.getA17());
				jsonObject2.put("A18",psend.getA18());
				jsonObject2.put("A19",psend.getA19());
				jsonObject2.put("A20",psend.getA20());
				jsonObject2.put("A21",psend.getA21());
				jsonObject2.put("A22",psend.getA22());
				jsonObject2.put("A23",psend.getA23());
				jsonObject2.put("A24",psend.getA24());
				jsonObject2.put("A25",psend.getA25());
				jsonObject2.put("A26",psend.getA26());
				jsonObject2.put("A27",psend.getA27());
				jsonObject2.put("A28",psend.getA28());
				jsonObject2.put("A29",psend.getA29());
				jsonObject2.put("A30",psend.getA30());
				jsonObject2.put("A31",psend.getA31());
				jsonObject2.put("A32",psend.getA32());
				jsonObject2.put("A33",psend.getA33());
				jsonObject2.put("A34",psend.getA34());
				jsonObject2.put("A35",psend.getA35());
				jsonObject2.put("A36",psend.getA36());
				jsonObject2.put("A37",psend.getA37());
				jsonObject2.put("A38",psend.getA38());
				jsonObject2.put("A39",psend.getA39());
				jsonObject2.put("A40",psend.getA40());
				jsonObject2.put("A41",psend.getA41());
				jsonObject2.put("A42",psend.getA42());
				jsonObject2.put("A43",psend.getA43());
				jsonObject2.put("A44",psend.getA44());
				jsonObject2.put("A45",psend.getA45());
				jsonObject2.put("A46",psend.getA46());
				jsonObject2.put("A47",psend.getA47());
				jsonObject2.put("A48",psend.getA48());
				jsonObject2.put("A49",psend.getA49());
				jsonObject2.put("A50",psend.getA50());
			}else{
				jsonObject2.put("state","false");
				jsonObject2.put("error",psend.getErrortext());
			}
			srtn=jsonObject2.toString();
			return srtn;
		}finally{
			jsonObject2=null;
			srtn=null;
		}
	}
	
	private String sencoding="UTF-16LE";
	public String rtnToXmlString(RtnStr arg){
		StringBuffer sbuff=new StringBuffer("<?xml version=\"1.0\" encoding=\""+sencoding+"\" standalone=\"no\"?><rtns><rtn>");
		sbuff.append("<state>"+arg.getState()+"</state>");
		sbuff.append("<errortext>"+arg.getErrortext()+"</errortext>");
		sbuff.append("<a>"+arg.getA()+"</a>");
		sbuff.append("<b>"+arg.getB()+"</b>");
		sbuff.append("<c>"+arg.getC()+"</c>");
		sbuff.append("<d>"+arg.getD()+"</d>");
		sbuff.append("<e>"+arg.getE()+"</e>");
		sbuff.append("<f>"+arg.getF()+"</f>");
		sbuff.append("<g>"+arg.getG()+"</g>");
		sbuff.append("<h>"+arg.getH()+"</h>");
		sbuff.append("<i>"+arg.getI()+"</i>");
		sbuff.append("</rtn></rtns>");
		return sbuff.toString();
	}
	
	@SuppressWarnings("unchecked")
	public JsonBean funJsonToBean(String jsonStr){
		JsonBean rtnbean=new JsonBean();
		String skey;
		JSONObject jsonobject;
	    JSONArray array;
	    JSONObject object;
	    Iterator<String> itera;
	    Set<String> set;
	    Map<String,String> vmap;
	    try{	    
			jsonobject= JSONObject.fromObject(jsonStr);
			if(jsonobject.containsKey("arg1")){
				rtnbean.setArg01(jsonobject.getString("arg1"));
			}
		    if(jsonobject.containsKey("arg2")){
		    	rtnbean.setArg02(jsonobject.getString("arg2"));
		    }
	    	if(jsonobject.containsKey("arg3")){
	    		rtnbean.setArg03(jsonobject.getString("arg3"));
	    	}
	    	if(jsonobject.containsKey("arg4")){
	    		rtnbean.setArg04(jsonobject.getString("arg4"));
	    	}
	    	if(jsonobject.containsKey("arg5")){
	    		rtnbean.setArg05(jsonobject.getString("arg5"));
	    	}
	    	if(jsonobject.containsKey("arg6")){
	    		rtnbean.setArg06(jsonobject.getString("arg6"));
	    	}
	    	if(jsonobject.containsKey("arg7")){
	    		rtnbean.setArg07(jsonobject.getString("arg7"));
	    	}
	    	if(jsonobject.containsKey("arg8")){
	    		rtnbean.setArg08(jsonobject.getString("arg8"));
	    	}
		    if(jsonobject.containsKey("arg9")){
		    	rtnbean.setArg09(jsonobject.getString("arg9"));
		    }
		    if(jsonobject.containsKey("arg10")){
		    	rtnbean.setArg10(jsonobject.getString("arg10"));
		    }
		    if(jsonobject.containsKey("arg11")){
		    	rtnbean.setArg11(jsonobject.getString("arg11"));
		    }
		    if(jsonobject.containsKey("arg12")){
		    	rtnbean.setArg12(jsonobject.getString("arg12"));
		    }
		    if(jsonobject.containsKey("arg13")){
		    	rtnbean.setArg13(jsonobject.getString("arg13"));
		    }
		    if(jsonobject.containsKey("arg14")){
		    	rtnbean.setArg14(jsonobject.getString("arg14"));
		    }
		    if(jsonobject.containsKey("arg15")){
		    	rtnbean.setArg15(jsonobject.getString("arg15"));
		    }
		    if(jsonobject.containsKey("arg16")){
		    	rtnbean.setArg16(jsonobject.getString("arg16"));
		    }
		    if(jsonobject.containsKey("arg17")){
		    	rtnbean.setArg17(jsonobject.getString("arg17"));
		    }
		    if(jsonobject.containsKey("arg18")){
		    	rtnbean.setArg18(jsonobject.getString("arg18"));
		    }
		    if(jsonobject.containsKey("arg19")){
		    	rtnbean.setArg19(jsonobject.getString("arg19"));
		    }
		    if(jsonobject.containsKey("arg20")){
		    	rtnbean.setArg20(jsonobject.getString("arg20"));
		    }
	
		    if(jsonobject.containsKey("xml1")){
		    	array = jsonobject.getJSONArray("xml1");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList01().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml2")){
		    	array = jsonobject.getJSONArray("xml2");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList02().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml3")){
		    	array = jsonobject.getJSONArray("xml3");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList03().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml4")){
		    	array = jsonobject.getJSONArray("xml4");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList04().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml5")){
		    	array = jsonobject.getJSONArray("xml5");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList05().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml6")){
		    	array = jsonobject.getJSONArray("xml6");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList06().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml7")){
		    	array = jsonobject.getJSONArray("xml7");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList07().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml8")){
		    	array = jsonobject.getJSONArray("xml8");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList08().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml9")){
		    	array = jsonobject.getJSONArray("xml9");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList09().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml10")){
		    	array = jsonobject.getJSONArray("xml10");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList10().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml11")){
		    	array = jsonobject.getJSONArray("xml11");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList11().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml12")){
		    	array = jsonobject.getJSONArray("xml12");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList12().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml13")){
		    	array = jsonobject.getJSONArray("xml13");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList13().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml14")){
		    	array = jsonobject.getJSONArray("xml14");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList14().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml15")){
		    	array = jsonobject.getJSONArray("xml15");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList15().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml16")){
		    	array = jsonobject.getJSONArray("xml16");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList16().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml17")){
		    	array = jsonobject.getJSONArray("xml17");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList17().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml18")){
		    	array = jsonobject.getJSONArray("xml18");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList18().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml19")){
		    	array = jsonobject.getJSONArray("xml19");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList19().add(vmap);
			    }
		    }
		    if(jsonobject.containsKey("xml20")){
		    	array = jsonobject.getJSONArray("xml20");
			    for (int i = 0; i < array.size(); i++) {
			    	object = (JSONObject)array.get(i);
			    	set=object.keySet();
					itera=set.iterator();
					vmap=new HashMap<String,String>();
					while(itera.hasNext()){
						skey=itera.next();
						vmap.put(skey,object.getString(skey));
					}
					rtnbean.getList20().add(vmap);
			    }
		    }
	    	return rtnbean;
	    }finally{
			rtnbean=null;
			skey=null;
			jsonobject=null;
		    array=null;
		    object=null;
		    itera=null;
		    set=null;
		    vmap=null;
	    }
	}
	
	/**
	 * 暂时未用到
	 * @param sXml
	 * @return
	 * @author LuoLi
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> xmlToList(String sXml){
		if(sXml.equals("") || sXml==null) return null;
		Document document;
		Element response;
		try{
			document = DocumentHelper.parseText(sXml);
			response = document.getRootElement();
			List<Map<String,String>> rtnList=new ArrayList<Map<String,String>>(); 
			List<Element> rows = response.elements("row");
			String key,value;
			for(int i=0;i<rows.size();i++){//所有行
				Element oneRowEle=(Element)rows.get(i);			
				List<Element> oneRow=oneRowEle.elements();
				Map<String,String> rtnmap=new HashMap<String,String>();
				for(int j=0;j<oneRow.size();j++){//行里面的所有元素
					Element e=(Element)oneRow.get(j);//获取中间的一个键值对元素
					key = e.getName();
					value = e.getText();
					if (value==null) value="";
					rtnmap.put(key,value);
				}
				rtnList.add(rtnmap);
			}
			return rtnList;
		} catch (DocumentException e) {
			System.out.println("捕获异常DocumentException");
			return null;
		}finally{
			document = null;
			response = null;
		}
	}

	
	/**
	 * 将含有row01的json字符串转换成list
	 * 如：{
		"state":"true",
		"error":"",
		"rowcount":1,
		"row01":[{"BMBM":"10","BMMC":"儿科","KSFL":"AYZ1"},
		         {"BMBM":"12","BMMC":"整形外科","KSFL":"AY05"}]
		}
	 * @param jsonStr
	 * @return
	 * @author LuoLi
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> jsonToList(String jsonStr){
		List<Map<String,String>> vlist = new ArrayList<Map<String,String>>();
		String skey;
		JSONObject jsonobject;
	    JSONArray array;
	    JSONObject object;
	    Iterator<String> itera;
	    Set<String> set;
	    HashMap<String,String> vmap;
	    jsonobject= JSONObject.fromObject(jsonStr);
		if(jsonobject.containsKey("row01")){
	    	array = jsonobject.getJSONArray("row01");
		    for (int i = 0; i < array.size(); i++) {
		    	object = (JSONObject)array.get(i);
		    	set=object.keySet();
				itera=set.iterator();
				vmap=new HashMap<String,String>();
				while(itera.hasNext()){
					skey=itera.next();
					vmap.put(skey,object.getString(skey));
				}
				vlist.add(vmap);
		    }
		    return vlist;
	    }
		return null;
	}
	
	/**
	 * 将含有row01,row02的json字符串转换成list
	 * 如：{
		"state":"true",
		"error":"",
		"rowcount":1,
		"row01":[{"BMBM":"10","BMMC":"儿科","KSFL":"AYZ1"},
		         {"BMBM":"12","BMMC":"整形外科","KSFL":"AY05"}]
		},
		"row02":[{"BMBM":"10","BMMC":"儿科","KSFL":"AYZ1"},
		         {"BMBM":"12","BMMC":"整形外科","KSFL":"AY05"}]
		}
	 * @param jsonStr
	 * @return
	 * @author LuoLi
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> jsonToList(String jsonStr,String tagRow){
		List<Map<String,String>> vlist = new ArrayList<Map<String,String>>();
		String skey;
		JSONObject jsonobject;
	    JSONArray array;
	    JSONObject object;
	    Iterator<String> itera;
	    Set<String> set;
	    HashMap<String,String> vmap;
	    jsonobject= JSONObject.fromObject(jsonStr);
		if(jsonobject.containsKey(tagRow)){
			String row = jsonobject.get(tagRow).toString();
			if(row.equals("[null]")){//查询为空的情况
				return null;
			}
			
	    	array = jsonobject.getJSONArray(tagRow);//row01
		    for (int i = 0; i < array.size(); i++) {
		    	object = (JSONObject)array.get(i);
		    	set=object.keySet();
				itera=set.iterator();
				vmap=new HashMap<String,String>();
				while(itera.hasNext()){
					skey=itera.next();
					vmap.put(skey,object.getString(skey));
				}
				vlist.add(vmap);
		    }
		    return vlist;
	    }
		return null;
	}
	

	/**
	 * 将List转换成json字符串返回，限制了只能由一个结果集：row01,且加上了 state,error,rowcount 等
	 * @param vlist
	 * @return String
	 * @author LuoLi
	 */
	public String listToJson(List<Map<String,String>> vlist){
		String sJson = "";
	    JSONArray jsonarray = new JSONArray();
	    JSONObject jsonobject = new JSONObject();
	    jsonobject.put("state","true");
	    jsonobject.put("error", "");
	    jsonobject.put("rowcount",1);
	    String key = "";
	    String value = "";
		for(int i=0; i<vlist.size(); i++){
			JSONObject jo = new JSONObject();
			Iterator<Map.Entry<String, String>> entries = vlist.get(i).entrySet().iterator();  
			while (entries.hasNext()) {  
				Map.Entry<String, String> entry = entries.next();
				key = entry.getKey();
				value = entry.getValue();
			    jo.put(key, value);  
			}  
			jsonarray.add(jo);
		}
		jsonobject.put("row01", jsonarray);
		sJson = jsonobject.toString();
		return sJson;
	}
	
	/**
	 * 将list转换成json数组并返回
	 * @param vlist
	 * @return JSONArray
	 * @author luoli
	 */
	public JSONArray listToJsonArray(List<Map<String,String>> vlist){
	    JSONArray jsonarray = new JSONArray();
	    String key = "";
	    String value = "";
		for(int i=0; i<vlist.size(); i++){
			JSONObject jo = new JSONObject();
			Iterator<Map.Entry<String, String>> entries = vlist.get(i).entrySet().iterator();  
			while (entries.hasNext()) {  
				Map.Entry<String, String> entry = entries.next();
				key = entry.getKey();
				value = entry.getValue();
			    jo.put(key, value);  
			}  
			jsonarray.add(jo);
		}
		return jsonarray;
	}
	
	
	/**
	 * <?xml version="1.0" encoding="UTF-8"?><rows><row><oldPsOrdNum></oldPsOrdNum></row></rows>
	 * @param sencoding
	 * @param result
	 * @return Document xml
	 */
	public Document listToXml(String sencoding,List<Map<String,Object>> result){
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding(sencoding); 		//字符集
		Element rows = doc.addElement("rows");
		String skey;
		String svalue;
		Set<String> key;
		Element row,titleElement;
		Iterator<String> itera;
		Map<String,Object> map;
		try{
			for(int i=0;i<result.size();i++){
				map=result.get(i);
				key=map.keySet();
				row=rows.addElement("row");
				itera=key.iterator();
				while(itera.hasNext()){
					skey=itera.next();
					if (map.get(skey)==null)
					{
						svalue="";
					}else{
						svalue=String.valueOf(map.get(skey));
						if(svalue.equals("null")) svalue="";
						svalue=svalue.trim();
					}
					titleElement = row.addElement(skey);
					titleElement.setText(svalue);
				}
			}
			return doc;
		}finally{
			doc=null;
			rows=null;
			skey=null;
			svalue=null;
			key=null;
			row=null;
			titleElement=null;
			itera=null;
			map=null;
		}
	}
	
	/**==================== START 利用Gson库 解析JsonObject对象 =====================*/
	/**
	 * 将List转换成json字符串返回(利用gson高效解决)
	 */
	public String listToJsonByGson(List<Map<String,String>> vlist){
		if(vlist==null || vlist.size()<1) return "[]";
		Gson gson = new Gson();
		String sJson = gson.toJson(vlist);
		return sJson;
	}
	/**
	 * 利用Gson库 解析JsonObject对象  为  List<Map<String,String>>对象
	 */
	public List<Map<String,String>> jsonObjToListGson(JsonObject jsonObj,String tagRow){
		if(jsonObj==null || tagRow==null || tagRow.length()<=0) return null;
		List<Map<String,String>> vlist = null;
		Gson gson = new Gson();
		if(jsonObj.has(tagRow)){
			JsonArray jsonArray = jsonObj.getAsJsonArray(tagRow);
			String rows = jsonArray.toString();
			if(rows.equals("[null]")){//查询为空的情况
				System.out.println(">>>>>>>row01:"+rows);
				jsonArray = null;
				gson = null;
				return null;
			}
			Type type = new TypeToken<ArrayList<Map<String,String>>>(){}.getType();
	 	   	vlist=gson.fromJson(rows, type);
		    return vlist;
	    }
		return null;
	}
	/**==================== END 利用Gson库 解析JsonObject对象 =====================*/
}
