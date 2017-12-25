package org.trams.bean;

import java.util.Date;
import java.util.List;

public class ProgramCalendar {
public Integer date;
public List<ProgramSimple> programSimple;

public Integer getDate() {
	return date;
}
public void setDate(Integer date) {
	this.date = date;
}
public List<ProgramSimple> getProgramSimple() {
	return programSimple;
}
public void setProgramSimple(List<ProgramSimple> programSimple) {
	this.programSimple = programSimple;
}

}
