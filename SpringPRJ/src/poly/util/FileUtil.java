package poly.util;

import java.io.File;

public class FileUtil {
	
	public static String mkdirForDate ( String uploadDir) {
		
		String path = uploadDir + DateUtil.getDateTime("/yyyy/MM/dd");
		
		File Folder = new File(path);
		
		//파일이 존재 하지 않으면 폴더 생
		if( !Folder.exists()) {
			
			Folder.mkdirs();// 폴더 생성
		}
		
		return path;
	}
	
}
