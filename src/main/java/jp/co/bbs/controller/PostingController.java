package jp.co.bbs.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.form.PostingForm;
import jp.co.bbs.service.PostingService;


@Controller
public class PostingController {
    @Autowired
    private PostingService postingService;
    
    @RequestMapping(value = "/posting", method = RequestMethod.GET)
    public String posting(Model model) {
        PostingForm form = new PostingForm();
        model.addAttribute("message", "投稿");
        model.addAttribute("postingForm", form);
        return "posting";
    }
    
    @RequestMapping(value = "/posting", method = RequestMethod.POST)
    public String insert(@ModelAttribute PostingForm form, Model model) {
    	PostingDto dto = new PostingDto();
    	BeanUtils.copyProperties(form, dto);
        postingService.insert(dto);
        return "redirect:/home";
    }
}
