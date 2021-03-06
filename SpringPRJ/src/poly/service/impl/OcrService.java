package poly.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import poly.dto.OcrDto;
import poly.persistance.mapper.IOcrMapper;
import poly.service.IOcrService;
import poly.util.CmmUtil;

public class OcrService implements IOcrService {
	
	@Resource(name= "OcrMapper")
	private IOcrMapper ocrMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public OcrDto getReadforImageText(OcrDto pDTO) throws Exception {
		
		log.info(this.getClass().getName() + " .getDeepRunning start ! ");
		
		File imageFile = new File(CmmUtil.nvl(pDTO.getSave_file_path()) + "/" + CmmUtil.nvl(pDTO.getOrg_file_name()));
		
		// ORC 기술 사용을 위한 Tesseract 플랫폼 객체 생성 
		ITesseract instance = new Tesseract();
		//ORC 분석에 필요한 기준데이터 ( 이미 각 나라의 언어별로 학습시킨 데이터 위치 폴더 )
		// 저장 경로는 물리 경로를 사용해야한다 ( 전체 경로 )
		instance.setDatapath("/Users/hamjimin/tess-data");
		
		//한국어 학습 데이터 선택
		//instance.setLanguage("kor")
		instance.setLanguage("eng");
		
		// 이미지 파일로부터 텍스트 읽기 
		String result = instance.doOCR(imageFile);
		
		// 읽을 파일을 DTO에 저장하기
		pDTO.setOrg_text(result);
		
		log.info("result : " + result);
		
		log.info(this.getClass().getName() + " .getDeepRunning End ! ");
		
		return ocrMapper.getReadforImageText(pDTO);
	}

}
