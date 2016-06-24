package jp.co.bbs.mapper;

import java.util.List;

import jp.co.bbs.dto.CommentDto;

public interface CommentMapper {
	List<CommentDto> comments();
	void comment(CommentDto dto);
	void delete(int id);
}
