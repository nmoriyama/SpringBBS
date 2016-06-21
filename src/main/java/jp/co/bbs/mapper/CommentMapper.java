package jp.co.bbs.mapper;

import java.util.List;

import jp.co.bbs.dto.CommentDto;

public interface CommentMapper {
	List<CommentDto> comments();
}
