package poly.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDto;
import poly.service.IMailService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;


@Service("MailService")
public class MailService implements IMailService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//private Logger log = Logger.getLogger(this.getClass());
	
	final String host = "smtp.naver.com";
	final String user = "hua4187@naver.com";
	final String password = "";
	
	
	@Override
	public int sendMailResult(MailDto pDTO) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			
		//재대로 시작이 되는지 확인하는 문구 
			System.out.printf("Mail service 시작 ");
			
			
			//res가 성공하면 1, 실패하면 0이다.
			int res = 1;
			
			//DTO의 값이 재대로 안넘어 올수도 있으니 if 문으로 다시 메모리에 올려주기
			if(pDTO == null) {
				pDTO = new MailDto();
			}
			String toMail = CmmUtil.nvl(pDTO.getToMail());
			log.info("content : " + CmmUtil.nvl(pDTO.getContent()));
			
			
			Properties props = new Properties();

			props.put("mail.smtp.host",host);
			props.put("mail.smtp.auth","true");
			
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user,password);
				}
			});
					
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
				//메일제목 받아오기 
				message.setSubject(CmmUtil.nvl(pDTO.getTitle()));
				//메일내용 받아오기 
				message.setSubject(CmmUtil.nvl(pDTO.getContent()));
			}catch (MessagingException e ){
				res = 0;
				log.info("ERROR"+this.getClass().getName()+".doSendMail : "+ e);
				System.out.printf("ERROR");
			}catch (Exception e ){
				res = 0;
				System.out.printf("ERROR");
				log.info("ERROR"+this.getClass().getName()+".doSendMail : "+ e);
			}
			
			log.info(this.getClass().getName()+".doSendMail END!!");
		return res;
	}
	


}
