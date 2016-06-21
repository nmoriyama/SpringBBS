package jp.co.bbs.mapper;
import java.util.List;

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.entity.User;

public interface UserMapper {
	User getTest(int id);
	List<User> getUserAll();
	void insert(UserDto dto);
	List<BranchDto> branch();
	List<PositionDto> position();
	void delete(int id);

	
}
