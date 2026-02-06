package ex004.dao;

import java.util.List;

import ex004.dto.MilkDto;

@MyDao
public interface MilkDao {
	public int insert(MilkDto dto);
	public List<MilkDto> selectAll();
	public MilkDto select(int mno);
	public int update(MilkDto dto);
	public int delete(int mno); 
}