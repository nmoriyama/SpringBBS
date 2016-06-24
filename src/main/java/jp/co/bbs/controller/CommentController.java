package jp.co.bbs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.bbs.dto.CommentDto;
import jp.co.bbs.form.CommentForm;
import jp.co.bbs.service.CommentService;

@Controller
@SessionAttributes("messages")
public class CommentController {
	
	@Autowired
		private CommentService commentService;
    
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String insert(@ModelAttribute CommentForm form, Model model) {
		CommentDto dto = new CommentDto();
		BeanUtils.copyProperties(form, dto);
		List<String> messages = commentService.comment(dto);
		if (messages.size() == 0) {
			model.addAttribute("messages", "コメントに成功しました");
			return "redirect:/home";
		}
		model.addAttribute("messages", messages);
		return "redirect:/home";
    }
    
    
	@RequestMapping(value = "/commentDelete", method = RequestMethod.POST)
    public String delete(@ModelAttribute CommentForm form, Model model) {
		List<String> messages = new ArrayList<String>();
		commentService.delete(form.getCommentId());
		model.addAttribute("messages", messages);
        return "redirect:/home";
    }
    
}
