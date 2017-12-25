package org.trams.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.bean.Program;
import org.trams.data.repository.jpa.PurchaseJpaRepository;
import org.trams.web.service.RecommendService;

@Component
@Transactional
public class RecommendServiceImpl implements RecommendService {
	private final Logger logger = Logger.getLogger("RecommendService");

	@PersistenceContext
	private EntityManager em;

	@Resource
	private PurchaseJpaRepository purchaseJpaRepository;
	
	private static final List<String> TYPE_LIST = new ArrayList<String>(
			Arrays.asList("", "gender", "age", "genitive", "experience", "place_study", "area", "time1", "time2", "fee_study", "study_mode", "size_class"));
	
	
	private Query getRecommendQuery(Integer userId, Integer categoryId, Map<String, Object> keywordList, Integer type){
		
		String select = "SELECT AA.*, (SELECT COUNT(id) FROM purchase WHERE program_id = AA.id AND order_program = 0) as p_count ";
		String fromHead = "FROM (SELECT A.id, A.category_name, A.title , A.summary , A.fee, IFNULL(A.total_people, 0),  A.image_url, A.video_url, ";
		
		String scoreAll = "(A.gender + A.age + A.genitive + A.experience + A.place_study + A.area + A.time1 + A.time2 + A.fee_study + A.study_mode + A.size_class) as score ";
		String scoreTeacher = "(A.gender + A.age + A.genitive + A.experience) as score ";
		String scoreTime = "(A.time1 + A.time2) as score ";
		String scorePrice = "(A.fee_study) as score ";
		String scoreRegion = "(A.place_study + A.area) as score ";
		
		String fromBottom = "FROM (SELECT P.id, P.category_name, P.title, P.summary, P.fee, P.total_people, P.image_url, P.video_url, "
										+ "IF(KP.gender = :gender, 1, 0) as gender, "
										+ "IF(KP.age = :age, 1, 0) as age, "
										+ "IF(KP.genitive = :genitive, 1, 0) as genitive, "
										+ "IF(KP.experience = :experience, 1, 0) as experience, "
										+ "IF(KP.place_study = :place_study, 1, 0) as place_study, "
										+ "IF(KP.area = :area, 1, 0) as area, "
										+ "IF(KP.time1 = :time1, 1, 0) as time1, "
										+ "IF(KP.time2 = :time2, 1, 0) as time2, "
										+ "IF(KP.fee_study = :fee_study, 1, 0) as fee_study, "
										+ "IF(KP.study_mode = :study_mode, 1, 0) as study_mode, "
										+ "IF(KP.size_class = :size_class, 1, 0) as size_class "
				                  + "FROM keyword_program KP, program P "
				                 + "WHERE P.id = KP.program_id "
				                   + "AND P.category_id = :category_id "
				                   + "AND P.is_delete = 0 "
				                   + "AND P.id NOT IN(SELECT program_id FROM purchase WHERE user_id = :user_id AND order_program = 0)) A "
						     + ") AA ";
		String where = "WHERE score > ";
		String other = "ORDER BY p_count DESC, score DESC, id DESC LIMIT 0, 3";
		
		String queryString = select + fromHead;
		
		switch(type){
			case 1: queryString += scoreAll + fromBottom + where + " 2 "; break;
			case 2: queryString += scoreTeacher + fromBottom + where + " 1 "; break;
			case 3: queryString += scoreTime + fromBottom + where + " 0 "; break;
			case 4: queryString += scorePrice + fromBottom + where + " 0 "; break;
			case 5: queryString += scoreRegion + fromBottom + where + " 0 "; break;
		}
		
		queryString += other;
		
		Query query = em.createNativeQuery(queryString);
		
		query.setParameter("gender", keywordList.get("gender"));
		query.setParameter("age", keywordList.get("age"));
		query.setParameter("genitive", keywordList.get("genitive"));
		query.setParameter("experience", keywordList.get("experience"));
		query.setParameter("place_study", keywordList.get("place_study"));
		query.setParameter("area", keywordList.get("area"));
		query.setParameter("time1", keywordList.get("time1"));
		query.setParameter("time2", keywordList.get("time2"));
		query.setParameter("fee_study", keywordList.get("fee_study"));
		query.setParameter("study_mode", keywordList.get("study_mode"));
		query.setParameter("size_class", keywordList.get("size_class"));
		query.setParameter("category_id", categoryId);
		query.setParameter("user_id", userId);
		
		return query;
	}

	private List<Program> getProgramList(Query query){
		List<Program> list = new ArrayList<Program>();
		try{
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			Iterator<Object> itr = result.iterator();
			
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				Program program = new Program();
				
				program.setId((Integer)obj[0]);
				program.setCategoryName(String.valueOf(obj[1]));
				program.setTitle(String.valueOf(obj[2]));
				program.setSummary(String.valueOf(obj[3]));
				program.setFee((Integer)obj[4]);
				program.setTotalPeople(Integer.parseInt(String.valueOf(obj[5])));
				program.setImageUrl(String.valueOf(obj[6]));
				program.setVideoUrl(String.valueOf(obj[7]));
				
				list.add(program);
			}
			
			return list;
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private Query getKewordQuery(Integer userId, Integer type){
		
		String select = "SELECT A.* FROM( ";
		String subSelect1 = "(SELECT K.id, K.keyword, K.type_id, "
								+ "(SELECT COUNT(P.id) "
								   + "FROM purchase P, keyword_program KP "
								  + "WHERE P.order_program = 0 "
								    + "AND P.program_id = KP.program_id ";
		String subSelectUserId = "AND P.user_id = :userId ";
		String subSelectKeyword1 = "AND KP.";
		String subSelectKeyword2 = " = K.id) as p_count   ";
		
		String from = "FROM keyword K  ";
		String subSelectType = "WHERE K.type_id = ";
		String subSelectOrder = " ORDER BY p_count DESC, priority DESC LIMIT 1) ";
		String union = " UNION ";
		String order = ") A ORDER BY A.p_count DESC, A.id ASC  ";
		
		String queryString = select;
		
		if(userId != 0){
			subSelect1 += subSelectUserId;
		}
		List<Integer> typeIds = getTypeIds(type);
		int listSize = typeIds.size();
		
		for(int i = 0 ; i < listSize ; i++){
			Integer typeId = typeIds.get(i);
			String subSelect = subSelect1;
			subSelect += subSelectKeyword1 + TYPE_LIST.get(typeId) + subSelectKeyword2;
			subSelect += from;
			subSelect += subSelectType + typeId;
			subSelect += subSelectOrder;
			if(i != listSize - 1){
				subSelect += union;
			}
			queryString += subSelect;
		}
		
		queryString += order;
		
		Query query = em.createNativeQuery(queryString);

		if(userId != 0){
			query.setParameter("userId", userId);
		}
		
		return query;
	}
	
	 /*
	  * type
	  * 1: all
	  * 2: based on teacher
	  * 3: based on time
	  * 4: based on price
	  * 5: based on region
	  */
	
	private List<Integer> getTypeIds(Integer type){
		List<Integer> result = new ArrayList<Integer>();
		
		switch(type){
			case 1: result = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11); break;
			case 2: result = Arrays.asList(1, 2, 3, 4); break;
			case 3: result = Arrays.asList(7, 8); break;
			case 4: result = Arrays.asList(9); break;
			case 5: result = Arrays.asList(5, 6); break;
		}
		
		return result;
	}
	
	 /*
	  * type
	  * 1: all
	  * 2: based on teacher
	  * 3: based on time
	  * 4: based on price
	  * 5: based on region
	  */
	
	@Override
	public List<Program> getRecommendPrograms(Integer userId, Integer categoryId, Map<String, Object> keywordList, Integer type) {
		logger.info("category id: " + categoryId);
		logger.info("keyword id list: " + keywordList.toString());
		logger.info("type: " + type);
		
		Query query = getRecommendQuery(userId, categoryId, keywordList, type);
		
		return getProgramList(query);
	}

	@Override
	public List<Program> getRecommendProgramsAll() {
		
		String queryString = "SELECT P.id, P.category_name, P.title, P.summary, P.fee, IFNULL(P.total_people, 0),  P.image_url, P.video_url, A.p_count "
							+ "FROM program P, "
								+ "(SELECT program_id, COUNT(id) as p_count "
								+ "FROM purchase "
								+ "WHERE order_program = 0 "
								+ "GROUP BY program_id) A "
							+ "WHERE P.id = A.program_id "
							  + "AND P.is_delete = 0 "
							+ "ORDER BY A.p_count DESC, P.id DESC "
							+ "LIMIT 3;";
		Query query = em.createNativeQuery(queryString);
		
		return getProgramList(query);
	}
	
	@Override
	public List<Program> getRecommendProgramsTeacher() {
		List<Program> list = new ArrayList<Program>();
		List<Integer> topUsers = purchaseJpaRepository.findTop3Users();
		logger.info("topUsers: " + topUsers.toString());
		
		for(Integer userId: topUsers){
			Program program = getRecommendByTeacher(userId);
			if(program != null){
				list.add(program);
			}
		}
		
		return list;
	}
	
	private Program getRecommendByTeacher(Integer userId){
		
		String queryString = "SELECT P.id, P.category_name, P.title, P.summary, P.fee, IFNULL(P.total_people, 0), P.image_url, P.video_url "+
							"FROM program P, "+
								"(SELECT P.program_id, COUNT(P.id) as p_count "+
								"FROM purchase P, program PG "+
								"WHERE P.user_id = :userId "+
								"AND P.order_program = 0 "+
								"AND P.program_id = PG.id "+
								"AND PG.is_delete = 0 "+
								"GROUP BY P.program_id "+
								"ORDER BY p_count DESC, P.program_id DESC LIMIT 1) A "+
							"WHERE P.id = A.program_id";
		
		Query query = em.createNativeQuery(queryString);

		query.setParameter("userId", userId);
		try{
			Object[] obj = (Object[]) query.getSingleResult();
			Program program = new Program();
			
			program.setId((Integer)obj[0]);
			program.setCategoryName(String.valueOf(obj[1]));
			program.setTitle(String.valueOf(obj[2]));
			program.setSummary(String.valueOf(obj[3]));
			program.setFee((Integer)obj[4]);
			program.setTotalPeople(Integer.parseInt(String.valueOf(obj[5])));
			program.setImageUrl(String.valueOf(obj[6]));
			program.setVideoUrl(String.valueOf(obj[7]));
			
			return program;
	
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public Map<String, Object> getKeyWordListByUserId(Integer userId, Integer type) {
		logger.info("userId: " + userId);
		logger.info("type: " + type);
		Map<String, Object> list = new HashMap<String, Object>();
		
		Query query = getKewordQuery(userId, type);
		
		try {
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			Iterator<Object> itr = result.iterator();
			
			Map<String, Object> keywordId = new HashMap<String, Object>();
			List<String> keyword = new ArrayList<String>();
			
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();
				
				keywordId.put(TYPE_LIST.get((Integer)obj[2]), (Integer)obj[0]);
				keyword.add(String.valueOf(obj[1]));
			}
			
			logger.info("keywordId: " + keywordId.toString());
			logger.info("keyword: " + keyword.toString());
			
			list.put("keywordId", keywordId);
			list.put("keyword", keyword);
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Program> getRecommendProgramsNotLogin(Map<String, Object> keywordList) {
		String query1 = "SELECT P.id, P.category_name, P.title, P.summary, P.fee, IFNULL(P.total_people, 0),  P.image_url, P.video_url, A.p_count "
						+ "FROM program P, "
						+ "		(SELECT P.program_id, COUNT(P.id) as p_count "
						+ "		   FROM purchase P, keyword_program KP "
						+ " 	  WHERE P.order_program = 0 "
						+ "			AND P.program_id = KP.program_id ";
		String queryKeyword = " AND KP.";
		String query2 = "		  GROUP BY P.program_id) A "
						+ "WHERE P.id = A.program_id "
						+ "  AND P.is_delete = 0 "
						+ "ORDER BY A.p_count DESC, P.id DESC LIMIT 3";
		
		String queryString = query1;
		Set<String> columns = keywordList.keySet();
		for(String column : columns){
			queryString += queryKeyword + column + " = " + keywordList.get(column);
		}
		queryString +=  query2;
		
		Query query = em.createNativeQuery(queryString);
		
		return getProgramList(query);
	}
}
