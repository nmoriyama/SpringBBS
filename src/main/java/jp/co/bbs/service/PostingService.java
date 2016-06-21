package jp.co.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.mapper.PostingMapper;
@Service
public class PostingService {
	@Autowired
    private PostingMapper postingMapper;
    public void insert(PostingDto dto) {
        postingMapper.insert(dto);
    }
}
