package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.GongDto;
import poly.service.IGongJiService;
import poly.util.CmmUtil;

@Controller
public class GongJiController {
	
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "GongJiService")
	IGongJiService GongJiService;
	
	@RequestMapping(value = "/board/gongji")
	public String GongJi(HttpServletRequest request, ModelMap model) {
		
		List<GongDto> rList = GongJiService.getGongJiList();
		if (rList == null) {
			rList = new ArrayList<>();
		}

		model.addAttribute("rList", rList);

		log.info("GongjiList 불러오기");

		for (GongDto e : rList) {

			log.info("GongJiList " + e.getGong_nm() + "번 ");
			log.info("GongJiTitle : " + e.getGong_tit());

		}	
		log.info("GongjiList 끝내기 ");
		return "/board/gongji";
	}
	
	
	@RequestMapping(value = "board/searchGongji")
	public @ResponseBody List<GongDto> searchGongji (HttpServletRequest request ) 
	{

		log.info("searchList 시작");
		//jsp에서 값을 받아오는 구문
		String Gong_tit = CmmUtil.nvl(request.getParameter("gong_tit"));
		log.info(Gong_tit);
		GongDto pDTO = new GongDto();
		pDTO.setGong_tit(Gong_tit);
		
		List<GongDto> rList = GongJiService.searchGongji(pDTO);

		log.info("SearchList 불러오기 : " + rList.size());

		log.info("searchList 끝");
		
		return rList;
	}

}
