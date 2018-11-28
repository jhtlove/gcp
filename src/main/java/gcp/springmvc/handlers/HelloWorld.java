package gcp.springmvc.handlers;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value={"names"},types={Integer.class,Double.class})
@Controller
public class HelloWorld {
    /**
     * 1. 使用RequestMapping注解来映射请求的URL
     * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器，会做如下解析
     * 通过prefix+returnVal+suffix 这样的方式得到实际的物理视图，然后会转发操作
     * "/WEB-INF/views/success.jsp"
     * @return
     */
    @RequestMapping(value = "/helloworld", method=RequestMethod.GET)
    public String hello(Model modMap, @RequestParam String xm, @RequestParam int age){
        System.out.println("hello world");
        modMap.addAttribute("xm",xm);
        modMap.addAttribute("age",age);
        return "lxg";
    }
    
    @RequestMapping(value = "/hello1", method=RequestMethod.GET)
    public String hello(Map<String,Object> map){
    	map.put("names", Arrays.asList("caoyc","zhh","cjx"));
        map.put("age", 18);
        map.put("price", 1000000.09);
        return "hello";
    }
}