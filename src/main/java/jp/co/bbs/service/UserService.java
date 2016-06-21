package jp.co.bbs.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.BranchDto;
import jp.co.bbs.dto.PositionDto;
import jp.co.bbs.dto.UserDto;
import jp.co.bbs.dto.test.TestDto;
import jp.co.bbs.entity.User;
import jp.co.bbs.mapper.UserMapper;

@Service
public class UserService {    
	@Autowired
    private UserMapper userMapper;


    public List<TestDto> getTestAll() {
        List<User> testList = userMapper.getUserAll();
        List<TestDto> resultList = convertToDto(testList);
        return resultList;
    }

    private List<TestDto> convertToDto(List<User> testList) {
        List<TestDto> resultList = new LinkedList<TestDto>();
        for (User entity : testList) {
            TestDto dto = new TestDto();
            BeanUtils.copyProperties(entity, dto);
            resultList.add(dto);
        }
        return resultList;
    }
    
    public void insert(UserDto dto) {
        userMapper.insert(dto);
    }
    
    public List<BranchDto> getBranches() {
    	 List<BranchDto> branchList = userMapper.branch();
    	 return  branchList;
    }
    
    public List<PositionDto> getPositions() {
    	List<PositionDto> positionList = userMapper.position();
    	return  positionList;
   }
    
    public void delete(int id) {
        userMapper.delete(id);
    }
}
