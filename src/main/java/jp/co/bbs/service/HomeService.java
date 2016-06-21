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
    	List<PostingDto> PostingList = postingMapper.postings();
    	return  PostingList;
   }
    
    public List<CommentDto> getComments() {
    	List<CommentDto> CommentList = commentMapper.comments();
    	return  CommentList;
   }
}
