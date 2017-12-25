package org.trams.bean;

import java.util.List;

public class WheLearn {
public Integer type;
public List<String> listKeyword;
public List<Program> listProgram;

public Integer getType() {
	return type;
}
public void setType(Integer type) {
	this.type = type;
}
public List<Program> getListProgram() {
	return listProgram;
}
public void setListProgram(List<Program> listProgram) {
	this.listProgram = listProgram;
}
public List<String> getListKeyword() {
	return listKeyword;
}
public void setListKeyword(List<String> listKeyword) {
	this.listKeyword = listKeyword;
}


}
