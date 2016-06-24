package jp.co.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.CommentDto;
import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.mapper.CommentMapper;
import jp.co.bbs.mapper.PostingMapper;
@Service
public class HomeService {
	@Autowired
    private PostingMapper postingMapper;
	@Autowired
    private CommentMapper commentMapper;
	
    public List<PostingDto> getPostings() {
    	List<PostingDto> postings = postingMapper.postings();
    	return  postings;
   }
    
    public List<CommentDto> getComments() {
    	List<CommentDto> comments = commentMapper.comments();
    	return comments;
   }
    
    public List<PostingDto> getDate() {
    	List<PostingDto> date = postingMapper.getDate();
    	return date;
   }
    
   public List<PostingDto> getCategory() {
	   List<PostingDto> date = postingMapper.getCategory();
	   return date;
   }
   
   public List<PostingDto> search(PostingDto dto) {
	   List<PostingDto> postings = postingMapper.search(dto);
	   return postings;
   }
}
