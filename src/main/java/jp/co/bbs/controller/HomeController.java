package jp.co.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.service.HomeService;
@Controller
public class HomeController {
	 @Autowired
	    private HomeService homeService;
    //ユーザー管理
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
    	List<PostingDto> postings = homeService.getPosting();
    	model.addAttribute("postings", postings);
        model.addAttribute("message", "ログイン中");
        return "home";
    }
}
