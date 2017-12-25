package org.trams.bean;

import java.util.Comparator;

public class KeywordWheaLearn implements Comparator<KeywordWheaLearn> {
	
	private Integer id;
	private String categoryName;
	private String title;
	private String summary;
	private Integer fee;
	private Integer totalPeople;
	private String imageUrl;
	private String videoUrl;
	private String keyword;
	private Integer gender;
	private Integer age;
	private Integer genitive;
	private Integer experience;
	private Integer placeStudy;
	private Integer area;
	private Integer time1;
	private Integer time2;
	private Integer feeStudy;
	private Integer studyMode;
	private Integer sizeClass;
	private Integer whelearnB;
	private Integer whelearnC;
	private Integer whelearnD;
	private Integer whelearnE;
	private Integer whelearnF;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public Integer getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getGenitive() {
		return genitive;
	}
	public void setGenitive(Integer genitive) {
		this.genitive = genitive;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Integer getPlaceStudy() {
		return placeStudy;
	}
	public void setPlaceStudy(Integer placeStudy) {
		this.placeStudy = placeStudy;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getTime1() {
		return time1;
	}
	public void setTime1(Integer time1) {
		this.time1 = time1;
	}
	public Integer getTime2() {
		return time2;
	}
	public void setTime2(Integer time2) {
		this.time2 = time2;
	}
	public Integer getFeeStudy() {
		return feeStudy;
	}
	public void setFeeStudy(Integer feeStudy) {
		this.feeStudy = feeStudy;
	}
	public Integer getStudyMode() {
		return studyMode;
	}
	public void setStudyMode(Integer studyMode) {
		this.studyMode = studyMode;
	}
	public Integer getSizeClass() {
		return sizeClass;
	}
	public void setSizeClass(Integer sizeClass) {
		this.sizeClass = sizeClass;
	}
	public Integer getWhelearnB() {
		return whelearnB;
	}
	public void setWhelearnB(Integer whelearnB) {
		this.whelearnB = whelearnB;
	}
	public Integer getWhelearnC() {
		return whelearnC;
	}
	public void setWhelearnC(Integer whelearnC) {
		this.whelearnC = whelearnC;
	}
	public Integer getWhelearnD() {
		return whelearnD;
	}
	public void setWhelearnD(Integer whelearnD) {
		this.whelearnD = whelearnD;
	}
	public Integer getWhelearnE() {
		return whelearnE;
	}
	public void setWhelearnE(Integer whelearnE) {
		this.whelearnE = whelearnE;
	}
	public Integer getWhelearnF() {
		return whelearnF;
	}
	public void setWhelearnF(Integer whelearnF) {
		this.whelearnF = whelearnF;
	}
	@Override
	public int compare(KeywordWheaLearn o1, KeywordWheaLearn o2) {
		if(o1.getWhelearnB() > o2.getWhelearnB()){
			return 1;
		}
		return 0;
	}
}
