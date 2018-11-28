package ihis.pubfun;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.*;
import org.dom4j.io.*;

public class Dbo {
	private Document doc;
	public void setDocument(String xml){
		SAXReader reader = new SAXReader();
		StringReader in=new StringReader(xml);			
		try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			System.out.println("Dbo:"+e.toString());
		}
	}
	@SuppressWarnings("rawtypes")
	public List<Map<String,String>> readxmlValue(String node){
		List<Map<String,String>> rtnList=new ArrayList<Map<String,String>>(); 
		Element foo = (Element)doc.selectSingleNode(node);
		List rows = foo.elements();
		String key,value;
		for(int i=0;i<rows.size();i++){
			Element rowselem=(Element)rows.get(i);			
			List row=rowselem.elements();
			Map<String,String> rtnmap=new LinkedHashMap<String,String>();
			for(int j=0;j<row.size();j++){
				Element rowelem=(Element)row.get(j);
				key=rowelem.getName();
				value=rowelem.getTextTrim();
				if (value==null)
					value="";
				rtnmap.put(key,value);
			}
			rtnList.add(rtnmap);
		}
		return rtnList;
	}
}