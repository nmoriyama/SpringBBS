package jp.co.bbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.mapper.PostingMapper;
@Service
public class PostingService {
	@Autowired
    private PostingMapper postingMapper;
    public List<String> insert(PostingDto dto) {
    	List<String> messages = new ArrayList<String>();
    	if (dto.getSubject().length() == 0) {
			messages.add("件名を入力してください");
		}
		
		if (50 < dto.getSubject().length()) {
			messages.add("件名は50文字以下で入力してください");
		}
		
		if (dto.getCategory().length() == 0) {
			messages.add("カテゴリーを入力してください");
		}
		
		if (10 < dto.getCategory().length()) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}
		
		if (dto.getBody().length() == 0) {
			messages.add("本文を入力してください");
		}
		
		if (1000 < dto.getBody().length()) {
			messages.add("本文は1000文字以下で入力してください");
		}
		
		if (messages.size() == 0) {
			postingMapper.insert(dto);
		}
        return messages;
    }
    
    public List<String> delete(int id) {
    	List<String> messages = new ArrayList<String>();
    	postingMapper.delete(id);
    	messages.add("投稿の削除に成功しました");
    	return messages;
    	
    }
}
