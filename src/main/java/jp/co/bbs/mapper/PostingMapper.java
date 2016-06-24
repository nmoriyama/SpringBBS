package jp.co.bbs.mapper;

import java.util.List;

import jp.co.bbs.dto.PostingDto;

public interface PostingMapper {
	List<PostingDto> postings();
	void insert(PostingDto dto);
	void delete(int id);
	List<PostingDto> getDate();
	List<PostingDto> getCategory();
	List<PostingDto> search(PostingDto dto);
}
