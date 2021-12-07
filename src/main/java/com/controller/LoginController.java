package com.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.LoginService;
import com.service.UserVo;

/**
 * @Class Name  : LoginController.java
 * @Description :  
 * @Modification Information
 *
 *    수정일              수정자         수정내용
 *    -------  -------  -------------------
 *    2021. 12. 7.	JHY    	최초생성
 *
 * @author JHY
 * @since 2021. 12. 7.
 * @version 1.0
 * @see
 */
@Controller
public class LoginController {
	
	@Resource(name="LoginService")
	private LoginService loginService;
	
	/**
	 * login View
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping("/loginView")
	public String loginView(
			@ModelAttribute( "vo" ) UserVo vo,
			ModelMap model) {

		return "loginView";
	}
	
}

