package poly.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import poly.dto.UserDto;


public interface IUserService {
	
	UserDto getLoginInfo(UserDto pDTO) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	int newUser(UserDto uDto) throws Exception;

	List<UserDto> UserList();

	UserDto getUserOut(UserDto pDTO);

}
