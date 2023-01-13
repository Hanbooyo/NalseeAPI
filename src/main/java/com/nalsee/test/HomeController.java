package com.nalsee.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private DAO dao;
	
	@Autowired
	private VO vo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		return "home";
	}
	
	@RequestMapping(value = "/nalsee", method = RequestMethod.GET)
	public ModelAndView nalsee(@RequestParam("location") String location, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ModelAndView mav = new ModelAndView();
		String xx=""; String yy="";
		Date sysdate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatter_t = new SimpleDateFormat("HHmm");
		String date = formatter.format(sysdate); 
		String time = formatter_t.format(sysdate);
		
		
		System.out.println(location + "    " + sysdate);
		int inttime = Integer.parseInt(time);
		
		if(inttime<200) {
			//전일 날짜로 변환
			//date = String.valueOf((Integer.parseInt(date)-1));
			//date = Integer.toString((Integer.parseInt(date)-1));
			time = "2300";
		}else if(inttime<0500) {
			time = "0200";
		}else if(inttime<800) {
			time = "0500";
		}else if(inttime<1100) {
			time = "0800";
		}else if(inttime<1400) {
			time = "1100";
		}else if(inttime<1700) {
			time = "1400";
		}else if(inttime<2000) {
			time = "1700";
		}else if(inttime<2300) {
			time = "2000";
		}else {
			//date = Integer.toString((Integer.parseInt(date)+1));
			time = "2300";
		}
		
		
		if(location.equals("서울")) {
			xx = "60";
			yy = "127";
		}else if(location.equals("대전")) {
			xx = "67";
			yy = "100";
		}else if(location.equals("대구")) {
			xx = "89";
			yy = "90";
		}else if(location.equals("부산")) {
			xx = "98";
			yy = "75";
		}else if(location.equals("광주")) {
			xx = "58";
			yy = "74";
		}else if(location.equals("제주")) {
			xx = "52";
			yy = "38";
		}else if(location.equals("강원도")) {
			xx = "73";
			yy = "134";
		}
		System.out.println(xx + " " + yy + " " + date + " " + time);
		List<VO> VOS = dao.nalseeAPI(xx, yy, date, time);
		mav.addObject("VOS", VOS);
		mav.addObject("location", location);
		return mav;
	}
	

}
