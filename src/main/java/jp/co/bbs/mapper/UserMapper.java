package jp.co.bbs.mapper;
import java.util.List;

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.entity.User;

public interface UserMapper {
	//ログイン
	User getPassword(UserDto dto);
	User login(UserDto dto);
	//ユーザー管理
	List<UserDto> getAllUsers();
	//支店名
		List<BranchDto> branch();
	//役職
		List<PositionDto> position();
	//ユーザー登録
	void insert(UserDto dto);
	//ユーザー編集
	User getUpdateUser(int id);
	void update(UserDto dto);
	//利用可能か停止か
	void status(UserDto dto);
	//ユーザー削除
	void delete(int id);
}
