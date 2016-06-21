package jp.co.bbs.mapper;
import java.util.List;

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.CommentDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.PostingDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.entity.Test;

public interface TestMapper {
	Test getTest(int id);
	List<Test> getUserAll();
	void insert(UserDto dto);
	List<BranchDto> branch();
	List<PositionDto> position();
	void delete(int id);
	List<PostingDto> postings();
	List<CommentDto> comments();
}
