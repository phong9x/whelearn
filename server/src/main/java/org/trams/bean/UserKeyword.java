package org.trams.bean;

public class UserKeyword {
private Integer id;
private String keyword;
private Long totalCount;
private Integer type;
private Integer priority;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public Long getTotalCount() {
	return totalCount;
}
public void setTotalCount(Long totalCount) {
	this.totalCount = totalCount;
}
public Integer getType() {
	return type;
}
public void setType(Integer type) {
	this.type = type;
}
public Integer getPriority() {
	return priority;
}
public void setPriority(Integer priority) {
	this.priority = priority;
}


}
