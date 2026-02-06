package config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ex006.dto.ExDto;
import ex006.service.ExService;

@RestController
public class AdminController {
    
    @Autowired 
    private ExService service;
    
    @RequestMapping("/selectAll")
    public List<ExDto> selectAll(){
    	return service.selectAll();
    }
  
    @RequestMapping("/select")
    public Map<String, Object> select(@RequestParam int appUserId) {
    	Map<String,Object> result = new HashMap<>();
    	result.put("result", service.select(appUserId));
        return result;
    }
    
    //http://localhost:8585/spring005_board/adminEdit?appUserId=6&mbtiTypeId=1
	@RequestMapping("/adminEdit") 
	public Map<String, Object> adminEdit(@RequestParam int appUserId,
							             @RequestParam int mbtiTypeId){ 
		Map<String,Object> result = new HashMap<>();
		ExDto dto = new ExDto();
		dto.setAppUserId(appUserId); dto.setMbtiTypeId(mbtiTypeId);
		result.put("result", service.adminEdit(dto));
		return result;
	}
	// http://localhost:8282/ex006_member/adminDelete?appUserId=6
	@RequestMapping("/adminDelete") 
	public Map<String, Object> adminDelete(@RequestParam int appUserId){ 
		Map<String,Object> result = new HashMap<>();
		ExDto dto = new ExDto();
		dto.setAppUserId(appUserId);
		result.put("result", service.adminDelete(dto)); 
		return result;
	}
	
	
}
