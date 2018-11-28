package gcp.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value={"userid","password"})
@Controller
public class MainController {

//    // 增加
//    @RequestMapping(value="/admin/users/delete?id=123", method=RequestMethod.GET)
//    public String deleteUser(@PathVariable("id") Integer userId @ParamVariable("id")) {
//        //删除id为userId的用户
//        //userRepository.delete(userId);
//        //立即刷新
//        //userRepository.flush();
//    	HisBusiSelect hbs = new HisBusiSelect();
//    	//hbs.funSel(checkcode, userid, password, sJson);
//    	//hbs.funBusi(checkcode, scorp, userid, password, address, dbname, busiid, operationtype, sJson);
//        return "redirect:/admin/users";
//    }
//    
//    // 修改
//    @RequestMapping(value="/admin/users/updateP", method=RequestMethod.POST)
//    public String updateUserPost(@ModelAttribute("userP") UserBean user) {
//        // 更新用户信息
//        //userRepository.updateUser();
//        //userRepository.flush(); // 刷新缓冲区
//        return "redirect:/admin/users";
//    }
//
//    // 删除
//    @RequestMapping(value="/admin/users/delete/{id}", method=RequestMethod.GET)
//    public String delete(@PathVariable("id") Integer userId) {
//        //删除id为userId的用户
//        //userRepository.delete(userId);
//        //立即刷新
//        //userRepository.flush();
//        return "redirect:/admin/users";
//    }

    // 查询
    @RequestMapping(value="/admin/users/delete/{id}", method=RequestMethod.GET)
    public String select(@PathVariable("id") Integer userId) {
        return "redirect:/admin/users";
    }
}
