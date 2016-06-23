package jp.co.bbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.CommentDto;
import jp.co.bbs.mapper.CommentMapper;

@Service
public class CommentService {
	@Autowired
    private CommentMapper commentMapper;
    public List<String> comment(CommentDto dto) {
    	List<String> messages = new ArrayList<String>();
		if (dto.getBody().length() == 0) {
			messages.add("コメントを入力してください");
		}
		if (500 < dto.getBody().length()) {
			messages.add("コメントは500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			commentMapper.comment(dto);
		}
		return messages;
    }
}
