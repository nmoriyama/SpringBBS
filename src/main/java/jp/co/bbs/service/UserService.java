package jp.co.bbs.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<UserDto> getTestAll() {
        List<User> testList = userMapper.getUserAll();
        List<UserDto> resultList = convertToDto(testList);
        return resultList;
    }

    private List<UserDto> convertToDto(List<User> testList) {
        List<UserDto> resultList = new LinkedList<UserDto>();
        for (User entity : testList) {
        	UserDto dto = new UserDto();
            BeanUtils.copyProperties(entity, dto);
            resultList.add(dto);
        }
        return resultList;
    }
    
    public void insert(UserDto dto) {
        userMapper.insert(dto);
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
    
    //ユーザー削除
    public void delete(int id) {
        userMapper.delete(id);
    }
    
    //ユーザー編集
    public User getUpdateUser(int id) {
    	User user = userMapper.getUpdateUser(id);
		return user;
    }
    
    public void update(UserDto dto) {
    	
    	userMapper.update(dto);
    }
    
    //ログイン
    public User login(UserDto dto){
    	//String encPassword = CipherUtil.encrypt(dto.getPassword());
    	//dto.setPassword(encPassword);
    	User user = userMapper.login(dto);
    	return user;
    	
    }
    
    public void status(UserDto dto) {
    	userMapper.status(dto);
    }
}
