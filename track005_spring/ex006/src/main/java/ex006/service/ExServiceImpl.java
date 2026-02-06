package ex006.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ex006.dao.ExDao;
import ex006.dto.ExDto;

@Service 
public class ExServiceImpl implements ExService {
    
    @Autowired private ExDao dao;

    @Override public int insert(ExDto dto) { return dao.insert(dto); }
    @Override public int update(ExDto dto) { return dao.update(dto); }
    @Override public int delete(ExDto dto) { return dao.delete(dto); }
    @Override public List<ExDto> selectAll() { return dao.selectAll(); }
    @Override public ExDto select(int appUserId) { return dao.select(appUserId); }
    @Override public ExDto selectEmail(String email) { return dao.selectEmail(email); }
    @Override public ExDto Login(ExDto dto) { return dao.Login(dto); }
    @Override public ExDto join(ExDto dto) { return dao.Join(dto); }
    
    /* 중복검사 */ 
    @Override
    public int countByEmail(String email) {return dao.countByEmail(email);}
    
    /* 어드민 삭제 */
    @Override
    public int adminDelete(ExDto dto) {return dao.adminDelete(dto);}
    
    /* 어드민 수정 */
    @Override
    public int adminEdit(ExDto dto) {return dao.adminEdit(dto);}

    /* upload */
    @Override
    public int insert2(MultipartFile file, ExDto dto) {
        String uploadPath = "C:/file/";
        String fileName;

        try {
            if (file != null && !file.isEmpty()) {
                fileName = file.getOriginalFilename();
                file.transferTo(new File(uploadPath + fileName));
            } else {
                File[] files = new File(uploadPath).listFiles();
                fileName = files[new java.util.Random().nextInt(files.length)].getName();
            } dto.setUfile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } return dao.insert2(dto);
    }


    @Override
    public int update2(MultipartFile file, ExDto dto) {
        if (file != null && !file.isEmpty()) {
        	String fileName = file.getOriginalFilename();
            String uploadPath = "C:/file/";
            File img = new File(uploadPath+fileName);
            try {
                file.transferTo(img);
                dto.setUfile(fileName);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return dao.update2(dto);
    }
	@Override
	public List<ExDto> selectSearch(String keword) {
		return null;
	}
}
