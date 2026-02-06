package ex006.dao;

import java.util.List;

import ex006.dto.ExDto;

@MyDao
public interface ExDao {
	public int insert(ExDto dto);
	public List<ExDto> selectAll();
	public ExDto select(int no);
	public ExDto selectEmail(String email);
	public ExDto Login(ExDto dto);
	public int update(ExDto dto);
	public int delete(ExDto dto);
	public ExDto Join(ExDto dto);
	
	public int adminDelete(ExDto dto); // 관리자용 삭제
	public int adminEdit(ExDto dto); // 관리자용 수정
	
	public int countByEmail(String email); // 중복체크
	public int insert2(ExDto dto); // 가입시 프로필사진 등록
	public int update2(ExDto dto); // 마이페이지에서 프로필사진 수정
	
}


