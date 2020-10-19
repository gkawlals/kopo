package poly.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.dto.OcrDto;
import poly.service.IOcrService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.FileUtil;

@Controller
public class OcrController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "OcrService")
	private IOcrService ocrService;
	
	final private String FILE_UPLOAD_SAVE_PATH = "/Users/hamjimin/upload"; // 경로에 파일을 저장 
	
	@RequestMapping(value = " ocr/imageFileUpload")
	public String Index() {
		log.info(this.getClass().getName() + " .imageFileUpload!");
		
		return "/ocr/ImageFileUpload";
	}
	
	@RequestMapping(value="orc/getReadForImageText")
	public String getReadForImageText (HttpServletRequest request, HttpServletResponse response, ModelMap model, 
			@RequestParam(value="fileUpload") MultipartFile mf ) throws Exception {
		
				log.info(this.getClass().getName() + " .getReadForImageText start ! ");
				
				//실행결과 
				String res = " ";
				
				//업로드하는 실제 파일명 
				//다운로드 기능 구현시, 임의로 정의되니 파일명을 원래대로 만들어주기 위한 목적 
				String originalFileName = mf.getOriginalFilename();
				
				//파일 확장자 가져오기 
				String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length()).toLowerCase();
				
				//이미지 파일만 실행되도록 함
				if( ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") ||ext.equals("png"))
				{
					//웹서버에 저장되는 이름 
					// 업로드하는 파일 이름에 한글, 특수 문자들이 저장될수 있기 때문에 강제로 영어와 숫자로 구성된 파일명으로 변경해 저장한다. 
					// 리눅스나 유닉스 등 운영체제는 다국어 지원에 취약할수 있다. 
					String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;
					
					//웹서버에 업로드한 파일저장하는 물리적 경로
					String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);
					
					String fullFileInfo = saveFilePath + "/" + saveFileName;
					
					// 정상적으로 작동되는지 로그찍기
					log.info("ext : " + ext);
					log.info("saveFileName : " + saveFileName);
					log.info("saveFilePath : " + saveFilePath);
					log.info("fullFileInfo : " + fullFileInfo);
					
					//업로드 되는 파일을 서버에 저장 
					mf.transferTo(new File(fullFileInfo));
					
					OcrDto pDTO = new OcrDto();

					pDTO.setSave_file_name(saveFileName);
					pDTO.setSave_file_path(saveFilePath);
					
					OcrDto rDTO = ocrService.getReadforImageText(pDTO);
					
					if(rDTO == null ) {
						rDTO = new OcrDto();
					}
					
					res = CmmUtil.nvl(rDTO.getOrg_text());
					
					rDTO = null;
					pDTO = null;
				}else {
					res = "이미지 파일이 아니라 인식이 불가합니다.";
				}
				
				model.addAttribute("res", res);
				
				log.info(this.getClass().getName() + " .getReadForImageText end ! ");
			
		return "/ocr/TextFromImage"; 
	}

}
