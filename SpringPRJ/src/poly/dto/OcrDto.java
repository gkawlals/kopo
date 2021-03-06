package poly.dto;

public class OcrDto {
	
	private String save_file_path; // 저장된 이미지 파일의 파일저장 경로 
	private String save_file_name; // 저장된 이미지 파일의 이름  
	private String org_text; // 저장된 이미지로부터 읽은 글씨 
	private String org_file_name ;// 저장전 원래의 파일명 
	private String ext; // 확장자
	
	public String getSave_file_path() {
		return save_file_path;
	}
	public void setSave_file_path(String save_file_path) {
		this.save_file_path = save_file_path;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public String getOrg_text() {
		return org_text;
	}
	public void setOrg_text(String org_text) {
		this.org_text = org_text;
	}
	public String getOrg_file_name() {
		return org_file_name;
	}
	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
}
