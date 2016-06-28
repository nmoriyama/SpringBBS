package jp.co.bbs.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.entity.User;
import jp.co.bbs.form.UserForm;
import jp.co.bbs.service.UserService;

@Controller
@SessionAttributes("loginUser")

public class UserController {
	
    @Autowired
    private UserService userService;

    //ログイン
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
    	UserForm form = new UserForm();

        model.addAttribute("userForm", form);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute UserForm form, Model model) {
    	UserDto dto = new UserDto();
    	BeanUtils.copyProperties(form, dto);
    	List<String> messages = userService.loginCheck(dto);
    	if (messages.size() != 0) {
    		model.addAttribute("messages", messages);
    		return "login";
    	}
    	User loginUser = userService.login(dto);
        model.addAttribute("loginUser", loginUser);
        return "redirect:/home";
    }
    
    //ユーザー管理
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String AllUsers(@ModelAttribute("loginUser") User user, Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "management";
    }
    
    //ユーザー登録
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String insert(@ModelAttribute UserForm form, Model model) {
    	UserDto dto = new UserDto();
    	BeanUtils.copyProperties(form, dto);
    	List<String> messages = userService.insert(dto);
    	model.addAttribute("messages", messages);
    	if (messages.size() == 0) {
    		return "redirect:/management";
    	}
    	//modelAttribute="checkForm";
    	return "signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String insert(Model model) {
        UserForm form = new UserForm();
        List<BranchDto> branches = userService.getBranches();

        List<PositionDto> positions = userService.getPositions();
        //model.addAttribute("messages", messages);
        model.addAttribute("userForm", form);
        model.addAttribute("branches", branches);
        model.addAttribute("positions", positions);
        return "signup";
    }


    
    //ユーザー編集
    @RequestMapping(value = "/management/setting/{id}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable int id) {
    	User user = userService.getUpdateUser(id);
        model.addAttribute("messages", "ユーザー編集");
        model.addAttribute("user", user);
        UserForm form = new UserForm();
        List<BranchDto> branches = userService.getBranches();
        List<PositionDto> positions = userService.getPositions();
        model.addAttribute("userForm", form);
        model.addAttribute("branches", branches);
        model.addAttribute("positions", positions);
        return "setting";
    }

    @RequestMapping(value = "/management/setting/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute UserForm form, Model model) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(form, dto);
        userService.update(dto);
        return "redirect:/management";
    }
    
    //利用可能か停止か
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public String status(@ModelAttribute UserForm form, Model model) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(form, dto);
        List<String> messages = userService.status(dto);
        model.addAttribute("messages", messages);
        return "redirect:/management";
    }
    
    //ユーザー削除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute UserForm form, Model model) {
        List<String> messages = userService.delete(form.getId());
        model.addAttribute("messages", messages);
        return "redirect:/management";
    }
    
    //ログアウト
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
    	model.addAttribute("loginUser", "null");
        return "redirect:/login";
    }
}
