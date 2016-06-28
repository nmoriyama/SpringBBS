package jp.co.bbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.entity.User;
import jp.co.bbs.mapper.UserMapper;
@Service
public class UserService {    
	@Autowired
    private UserMapper userMapper;

	//パスワードの暗号化
	@Autowired
	protected PasswordEncoder passwordEncoder;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	List<String> messages = new ArrayList<String>();
    //ログイン
	public List<String> loginCheck(UserDto dto){
		
		if (dto.getLoginId().isEmpty()){
			messages.add("ログインIDが入力されていません");
		}
		if (dto.getPassword().isEmpty()){
			messages.add("パスワードが入力されていません");
		}
		if (!dto.getLoginId().isEmpty() && !dto.getPassword().isEmpty()) {
			User password = userMapper.getPassword(dto);
			if (password == null) {
				messages.add("ログインできません");
			}
		}
		return messages;
	}
    public User login(UserDto dto){
    	User user = new User();
    	//ログインidのユーザーがいるかいたらパスワードを持ってくる
    	User password = userMapper.getPassword(dto);
		String getPassword = password.getPassword();
		//持ってきたパスワードと入力したパスワードの照合
		if (passwordEncoder.matches(dto.getPassword(), getPassword)) {
			user = userMapper.login(dto);
		}
		return user;	
    }
    
	//ユーザー管理
    public List<UserDto> getAllUsers() {
        List<UserDto> userList = userMapper.getAllUsers();
        return userList;
    }
    
    //支店名
    public List<BranchDto> getBranches() {
    	 List<BranchDto> branchList = userMapper.branch();
    	 return  branchList;
    }
    
    //役職
    public List<PositionDto> getPositions() {
    	List<PositionDto> positionList = userMapper.position();
    	return  positionList;
    }
    
    //ユーザー登録
    public List<String> insert(UserDto dto) {
    	
    	if (dto.getLoginId().isEmpty()){
			messages.add("ログインIDが入力されていません");
		} else if (dto.getLoginId().length() < 6 || dto.getLoginId().length() > 20) {
			messages.add("ログインIDは6文字以上20文字以下にして下さい");
		}
		if (dto.getPassword().isEmpty()){
			messages.add("パスワードが入力されていません");
		} else if (dto.getPassword().length() < 6 || dto.getPassword().length() > 255) {
			messages.add("パスワードは6文字以上255文字以下にして下さい");
		}
		if (!dto.getPassword().equals(dto.getCheckPassword())) {
			messages.add("パスワードが一致しません");
		}
		if (dto.getAccount().isEmpty() == true) {
			messages.add("アカウント名を入力してください");
		} else if (dto.getAccount().length() > 10) {
			messages.add("アカウント名は10文字以下にして下さい");
		}
		if (dto.getBranchId() != 1 && dto.getPositionId() <= 2) {
			messages.add("支店の人は、店長もしくは社員としか登録できません");
		}
		
		if (dto.getBranchId() == 1 && dto.getPositionId() == 3) {
			messages.add("本社の人は、店長として登録できません");
		}
		if (messages.size() == 0) {
			String passwordHash = encoder.encode(dto.getPassword());
			dto.setPasswordHash(passwordHash);
			userMapper.insert(dto);
			
		}
		return messages;
    }
    
    //ユーザー編集
    public User getUpdateUser(int id) {
    	User user = userMapper.getUpdateUser(id);
		return user;
    }
    
    //ユーザー編集
    public void update(UserDto dto) {
    	
    	userMapper.update(dto);
    }
    
	//利用可能か停止か
    public List<String> status(UserDto dto) {
    	userMapper.status(dto);
    	if (dto.getStatus() == "1") {
    		messages.add("ユーザーを停止しました");
        } else {
        	messages.add("ユーザーを利用可能にしました");
        }
    	return messages;
    	
    }
    
    //ユーザー削除
    public List<String> delete(int id) {
    	userMapper.delete(id);
    	messages.add("ユーザーを削除しました");
        return messages;
    }
}
