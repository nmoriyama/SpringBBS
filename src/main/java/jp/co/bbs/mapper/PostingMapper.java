package jp.co.bbs.mapper;

import java.util.List;

import jp.co.bbs.dto.PostingDto;

public interface PostingMapper {
	List<PostingDto> postings();
	void insert(PostingDto dto);
}
