package ex006.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ex006.dto.ExDto;

public interface ExService {
	public int insert(ExDto dto);    		
	public int update(ExDto dto);	 	
	public int delete(ExDto dto);	 
	public ExDto Login(ExDto dto);
	public List<ExDto> selectAll();	 		
	public ExDto select(int appUserId);	 	
	public ExDto selectEmail(String email);
	public ExDto join(ExDto dto);	 		
	
	public int countByEmail(String email);
	public int insert2(MultipartFile file, ExDto dto);
	public int update2(MultipartFile file, ExDto dto);
	public int adminDelete(ExDto dto);
	public int adminEdit(ExDto dto);
	
	public List<ExDto> selectSearch(String keword);
}
