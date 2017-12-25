package org.trams.bean.jpa;

public class CommentNotification {
private Integer id;
private String title;
private String FCMToken;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getFCMToken() {
	return FCMToken;
}
public void setFCMToken(String fCMToken) {
	FCMToken = fCMToken;
}

}
