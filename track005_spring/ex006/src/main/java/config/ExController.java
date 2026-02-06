package config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ex006.dto.ExDto;
import ex006.service.ExService;

@Controller
public class ExController {

	@Autowired
	ExService service;

//////////////////////////////////////////////////////////////리스트/////////////////////////////////////////////////////////////

	@RequestMapping("/list.user")
	public String list(Model model) {
		model.addAttribute("list", service.selectAll());
		return "ex_board/list";
	}

//////////////////////////////////////////////////////////////가입/////////////////////////////////////////////////////////////

	@RequestMapping(value = "/join.user", method = RequestMethod.GET)
	public String join_get(Model model) {
	    model.addAttribute("mbtiList", getMbtiList());
	    return "ex_board/join";  // 회원가입 페이지로 이동
	}

	private Object getMbtiList() {  return null; }

	@RequestMapping(value = "/join.user", method = RequestMethod.POST)
	public String join_post(ExDto dto, RedirectAttributes rttr) {
	    // 회원가입 처리
	    String result = "회원가입 성공";
	    try {
	        if (service.insert(dto) > 0) {
	            result = "회원가입 성공";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();  // 예외 메시지 로그로 확인
	        rttr.addFlashAttribute("error", "이미 존재하는 이메일입니다.");
	        return "redirect:/join.user";  // 오류 발생 시 회원가입 페이지
	    }

	    rttr.addFlashAttribute("success", result);  // 성공 메시지 전달
	    return "redirect:/login.user";  // 로그인 페이지로 리다이렉트
	    }
	
	@RequestMapping(value = "/joinUpload.user", method = RequestMethod.POST)
	public String joinUpload_post(@RequestParam("file") MultipartFile file,
	                              ExDto dto, RedirectAttributes rttr) {
	    String result = "회원가입 성공";
	    try {
	        // 파일 업로드 + 회원가입 처리
	        if (service.insert2(file, dto) > 0) {
	            result = "회원가입 성공";
	        }
	    } catch (DuplicateKeyException e) {
	        rttr.addFlashAttribute("이미 존재하는 이메일입니다.");
	        return "redirect:/join.user";
	    } catch (Exception e) {
	        e.printStackTrace();
	        rttr.addFlashAttribute("회원가입 중 오류가 발생했습니다.");
	        return "redirect:/join.user";
	    }

	    rttr.addFlashAttribute("success", result);
	    return "redirect:/login.user";
	}

//////////////////////////////////////////////////////////////로그인/////////////////////////////////////////////////////////////

	@RequestMapping(value = "/login.user", method = RequestMethod.GET)
	public String login_get() {
		return "ex_board/login";
	}

	@RequestMapping(value = "/login.user", method = RequestMethod.POST)
	public String login_post(ExDto dto, HttpServletRequest request, RedirectAttributes rttr) {
		ExDto user = service.Login(dto);
		String result = "로그인 실패: 아이디/비밀번호 확인";

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			result = "로그인 성공";
		}

		rttr.addFlashAttribute("success", result);
		return user != null ? "redirect:/mypage.user" : "redirect:/login.user";
	}

//////////////////////////////////////////////////////////////수정/////////////////////////////////////////////////////////////

	@RequestMapping(value = "/edit.user", method = RequestMethod.GET)
	public String edit_get(@RequestParam("appUserId") int appUserId, Model model) {
	    ExDto dto = service.select(appUserId);
	    model.addAttribute("dto", dto);
	    return "ex_board/edit";
	}

	@RequestMapping(value = "/editUpload.user", method = RequestMethod.POST)
	public String edit_post(@RequestParam(value = "file", required = false) MultipartFile file,
	                        ExDto dto, RedirectAttributes rttr) {
	    String result = "비밀번호 확인";
	    try {
	        // 파일이 있으면 업로드 처리
	    	if (file != null && !file.isEmpty()) {
	    	    String fileName = file.getOriginalFilename();
	    	    dto.setUfile(fileName); // DB에 저장할 파일명 세팅
	    	    service.update2(file, dto);  // 파일 포함 업데이트
	    	    result = "수정 성공";
	        } else {
	            if (service.update(dto) > 0) {
	                result = "수정 성공";
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        rttr.addFlashAttribute("error", "회원정보 수정 중 오류가 발생했습니다.");
	        return "redirect:/editUpload.user?appUserId=" + dto.getAppUserId();
	    }

	    rttr.addFlashAttribute("success", result);
	    return "redirect:/mypage.user?appUserId=" + dto.getAppUserId();

	}


//////////////////////////////////////////////////////////////삭제/////////////////////////////////////////////////////////////

	@RequestMapping(value = "/delete.user", method = RequestMethod.GET)
	public String delete_get(@RequestParam("appUserId") int appUserId, Model model) {
		ExDto dto = service.select(appUserId);
		model.addAttribute("dto", dto);
		return "ex_board/delete";
	}

	@RequestMapping(value = "/delete.user", method = RequestMethod.POST)
	public String delete_post(ExDto dto, Model model, HttpSession session) {
		String result = "비밀번호 확인";
		if (service.delete(dto) > 0) {
			result = "탈퇴 성공";
			session.invalidate();
		} else {
			result = "탈퇴 실패: 비밀번호를 확인하세요";
		}
		model.addAttribute("success", result);
		return "ex_board/delete";
	}

//////////////////////////////////////////////////////////////정보/////////////////////////////////////////////////////////////

	@RequestMapping("/mypage.user")
	public String mypage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		ExDto loginUser = (ExDto) session.getAttribute("loginUser");

		if (loginUser != null) {
			ExDto userInfo = service.select(loginUser.getAppUserId());
			model.addAttribute("dto", userInfo);
		} else {
			return "redirect:/login.user";
		}
		return "ex_board/mypage";
	}

//////////////////////////////////////////////////////////////로그아웃/////////////////////////////////////////////////////////////

	@RequestMapping("/logout.user")
	public String logout(HttpServletRequest request, RedirectAttributes rttr) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		rttr.addFlashAttribute("success", "로그아웃 되었습니다.");
		return "redirect:/login.user";
	}
}
