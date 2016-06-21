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

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.dto.test.TestDto;
import jp.co.bbs.form.UserForm;
import jp.co.bbs.service.UserService;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;


    
    //ユーザー管理
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String testAll(Model model) {
        List<TestDto> users = userService.getTestAll();
        model.addAttribute("message", "");
        model.addAttribute("users", users);
        return "management";
    }
    
    //ユーザー登録
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String insert(Model model) {
        UserForm form = new UserForm();
        List<BranchDto> branches = userService.getBranches();
        List<PositionDto> positions = userService.getPositions();
        model.addAttribute("testForm", form);
        model.addAttribute("branches", branches);
        model.addAttribute("positions", positions);
        model.addAttribute("message", "ユーザー登録");
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String insert(@ModelAttribute UserForm form, Model model) {
    	UserDto dto = new UserDto();
    	BeanUtils.copyProperties(form, dto);
        userService.insert(dto);
        
        return "redirect:/management";
    }
    
    //ユーザー削除機能
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute UserForm form, Model model) {
        userService.delete(form.getId());
        
        return "redirect:/management";
    }

    //ユーザー編集
    @RequestMapping(value = "/management/setting/{id}", method = RequestMethod.GET)
    public String testUpdate(Model model, @PathVariable int id) {
    	UserDto user = UserService.getUpdateUser(id);
        model.addAttribute("message", "ユーザー編集");
        model.addAttribute("user", user);
        UserForm form = new UserForm();

        model.addAttribute("testForm", form);
        return "setting";
    }

    @RequestMapping(value = "/management/setting/{id}", method = RequestMethod.POST)
    public String testUpdate(Model model, @ModelAttribute TestForm form) {
        TestDto dto = new TestDto();
        BeanUtils.copyProperties(form, dto);
        int count = testService.updateTest(dto);
        Logger.getLogger(TestController.class).log(Level.INFO, "更新件数は" + count + "件です。");
        return "redirect:/management";
    }
}
