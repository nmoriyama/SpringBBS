package jp.co.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.bbs.dto.CommentDto;
import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.form.SearchForm;
import jp.co.bbs.service.HomeService;
@Controller
@SessionAttributes("messages")
public class HomeController {
	 @Autowired
	    private HomeService homeService;
    //ユーザー管理
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
    	//投稿の取得
    	List<PostingDto> postings = homeService.getPostings();
    	//コメントの取得
    	List<CommentDto> comments = homeService.getComments();
    	//カテゴリーと最古の投稿日時と最新の投稿日時の取得
    	List<PostingDto> category = homeService.getCategory();
    	List<PostingDto> date = homeService.getDate();
    
    	model.addAttribute("postings", postings);
    	model.addAttribute("comments", comments);
    	model.addAttribute("date", date);
    	model.addAttribute("category", category);
        return "home";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute SearchForm form, Model model) {
    	//検索条件の取得
    	String fromDate = form.getFromYear()+"-"+form.getFromMonth()+"-"+form.getFromDay()+" 00:00:00";
    	String toDate = form.getToYear()+"-"+form.getToMonth()+"-"+form.getToDay()+" 23:59:59";
    	String category = form.getCategory();
    	form.setFromDate(fromDate);
    	form.setToDate(toDate);
		PostingDto dto = new PostingDto();
		dto.setFromDate(fromDate);
		dto.setToDate(toDate);
		dto.setCategory(category);
		//カテゴリー：全て表示を選択の場合
		if (dto.getCategory().isEmpty()) {
			return "redirect:/home";
		}
    	List<PostingDto> postings = homeService.search(dto);
    	List<PostingDto> date = homeService.getDate();
    	List<PostingDto> categorys = homeService.getCategory();
    	List<CommentDto> comments = homeService.getComments();
    	model.addAttribute("postings", postings);
    	model.addAttribute("comments", comments);
    	model.addAttribute("category", categorys);
    	model.addAttribute("date", date);
    	return "home";
    }
}
