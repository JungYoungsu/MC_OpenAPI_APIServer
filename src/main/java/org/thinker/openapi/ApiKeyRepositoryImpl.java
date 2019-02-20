package org.thinker.openapi;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("ApiKeyRepository")
public class ApiKeyRepositoryImpl implements ApiKeyRepository {

	@Autowired
	private SqlSession sqlSession;
    
    @Override
	public void create(ApiKeyVO vo)throws ApiKeyException{
    	System.out.println("@@ apiKeyVO : "+ vo);
		sqlSession.insert("apiKey.insert", vo ); 	
    }

	@Override
	public ApiKeyVO read(String apiKey) throws ApiKeyException{
		return sqlSession.selectOne("apiKey.select",apiKey);
	}


	@Override
	public void update(String apiKey) throws ApiKeyException {
		sqlSession.update("apiKey.updateCnt",apiKey);		
	}
    
	

	
}
