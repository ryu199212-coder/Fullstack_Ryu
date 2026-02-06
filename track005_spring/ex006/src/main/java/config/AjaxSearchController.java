package config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ex006.service.ExService;

@RestController
public class AjaxSearchController {
	
	@Autowired ExService service;
	
	@RequestMapping("/countByEmail")
	public Map<String, Object> countByEmail(@RequestParam String email){
		Map<String, Object> result = new HashMap<>();
		result.put("cnt", service.countByEmail(email));
		return result;
	}
}
