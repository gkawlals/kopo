package poly.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDto;
import poly.dto.UserDto;
import poly.persistance.mapper.IUserMapper;
import poly.service.IMailService;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.EncryptUtil;

@Service("UserService")
public class UserService implements IUserService{
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="UserMapper")
	IUserMapper UserMapper;
	
	
	@Resource(name="MailService")
	private IMailService MailService;

	@Override
	public UserDto getLoginInfo(UserDto pDTO) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException  {
		// TODO Auto-generated method stub
		log.info("userlogin start "); 

		UserDto rDTO = UserMapper.getLoginInfo(pDTO);
		
		MailDto mDTO = new MailDto();
		
		mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getEmail())));
		
		mDTO.setTitle("로그인 알림 ");
		
		mDTO.setContent(DateUtil.getDateTime("yyyy.MM.dd 24h:mm:ss")+ " 에 " + CmmUtil.nvl(rDTO.getName()) + " 님이 로그인 하셨습니다. ");
		
		
		MailService.sendMailResult(mDTO);
		//log.info(this.getClass() + pDTO.getUser_no());
		log.info(this.getClass() + pDTO.getPassword());
		log.info(this.getClass() + pDTO.getUser_id());

		log.info("userlogin end ");
		
		return UserMapper.getLoginInfo(pDTO);
	}
	
	@Override
	public int newUser(UserDto uDto) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		int res = 0;
		
		// 들어오는 결과값이 없을때 새로운것을 메모리로 올린다. 
		if(uDto == null) {
			uDto = new UserDto();
		}
		
		//회원가입 중복 방지를 위한 DB조회
		UserDto rDTO = UserMapper.getUserExists(uDto);
		
		//mapper에서 값이 정상적으로 못 넘어오는 경우 대비하기위해 사용
		if(rDTO == null) {
			rDTO = new UserDto();
		}
		
		// 중복된 회원가입이 있는겨우 결과값을 2로 변경하고 더이상 진행되지 않게 한다
		if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			log.info("중복 아이디 입니다.");
			res = 2;
		}else { 
			int success = UserMapper.newUser(uDto);
			
			if(success > 0) {
				res = 1;
				
				MailDto mDto = new MailDto();
				
				mDto.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(uDto.getEmail())));
				
				mDto.setTitle("회원가입을축하드립니다.");
				
				mDto.setContent(CmmUtil.nvl(uDto.getName())+"님의 회원가입을 축하드립니다.");
				
				MailService.sendMailResult(mDto);
			}else {
				res = 0;
			}
		}
		return res;
	}

	@Override
	public List<UserDto> UserList() {

		return UserMapper.UserList();
	}

	@Override
	public UserDto getUserOut(UserDto pDTO) {
		log.info(this.getClass().getName() + " start ! getUserOut ");
		
		
		UserDto rDTO = UserMapper.getUserOut(pDTO);
		
		if(rDTO == null) {
			rDTO = new UserDto();
		}
		
		
		return UserMapper.getUserOut(pDTO);
	}


}
