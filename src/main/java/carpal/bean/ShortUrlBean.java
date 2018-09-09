package carpal.bean;

public class ShortUrlBean {

private	String short_url;
private	String long_url;
private int id;
private boolean status; 
private String createdAt;
private String keyCode;

@Override
public String toString() {
	return "ShortUrlBean [short_url=" + short_url + ", long_url=" + long_url + ", id=" + id + ", status=" + status
			+ ", createdAt=" + createdAt + ", keyCode=" + keyCode + ", oauthtoken=" + oauthtoken + "]";
}
private String oauthtoken;

public String getShort_url() {
	return short_url;
}
public void setShort_url(String short_url) {
	this.short_url = short_url;
}
public String getLong_url() {
	return long_url;
}
public void setLong_url(String long_url) {
	this.long_url = long_url;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public String getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
}
public String getKeyCode() {
	return keyCode;
}
public void setKeyCode(String keyCode) {
	this.keyCode = keyCode;
}
public String getOauthtoken() {
	return oauthtoken;
}
public void setOauthtoken(String oauthtoken) {
	this.oauthtoken = oauthtoken;
}

	
}
