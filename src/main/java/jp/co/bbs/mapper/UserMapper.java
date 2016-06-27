package jp.co.bbs.mapper;
import java.util.List;

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.entity.User;

public interface UserMapper {
	User login(UserDto dto);
	List<User> getUserAll();
	void insert(UserDto dto);
	List<BranchDto> branch();
	List<PositionDto> position();
	void delete(int id);
	User getUpdateUser(int id);
	void update(UserDto dto);
	User getPassword(UserDto dto);
	void status(UserDto dto);
}
