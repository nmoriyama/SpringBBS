package jp.co.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.CommentDto;
import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.mapper.TestMapper;
@Service
public class HomeService {
	@Autowired
    private TestMapper testMapper;
    public List<PostingDto> getPostings() {
    	List<PostingDto> PostingList = testMapper.postings();
    	return  PostingList;
   }
    
    public List<CommentDto> getComments() {
    	List<CommentDto> CommentList = testMapper.comments();
    	return  CommentList;
   }
}
