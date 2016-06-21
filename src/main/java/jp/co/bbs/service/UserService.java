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
import jp.co.bbs.entity.Test;
import jp.co.bbs.mapper.TestMapper;

@Service
public class UserService {    
	@Autowired
    private TestMapper testMapper;

    public TestDto getTest(Integer id) {
        TestDto dto = new TestDto();
        Test entity = testMapper.getTest(id);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public List<TestDto> getTestAll() {
        List<Test> testList = testMapper.getUserAll();
        List<TestDto> resultList = convertToDto(testList);
        return resultList;
    }

    private List<TestDto> convertToDto(List<Test> testList) {
        List<TestDto> resultList = new LinkedList<TestDto>();
        for (Test entity : testList) {
            TestDto dto = new TestDto();
            BeanUtils.copyProperties(entity, dto);
            resultList.add(dto);
        }
        return resultList;
    }
    
    public void insert(UserDto dto) {
        testMapper.insert(dto);
    }
    
    public List<BranchDto> getBranches() {
    	 List<BranchDto> branchList = testMapper.branch();
    	 return  branchList;
    }
    
    public List<PositionDto> getPositions() {
    	List<PositionDto> positionList = testMapper.position();
    	return  positionList;
   }
    
    public void delete(int id) {
        testMapper.delete(id);
    }
}
