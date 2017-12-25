package org.trams.web.service;

import java.util.List;
import java.util.Map;

import org.trams.bean.Program;

public interface RecommendService { 
	
	public List<Program> getRecommendPrograms(Integer userId, Integer categoryId, Map<String, Object> keywordList, Integer type);
	
	public List<Program> getRecommendProgramsAll();
	
	public List<Program> getRecommendProgramsTeacher();
	
	public Map<String, Object> getKeyWordListByUserId(Integer userId, Integer type);
	
	public List<Program> getRecommendProgramsNotLogin(Map<String, Object> keywordList);
}
