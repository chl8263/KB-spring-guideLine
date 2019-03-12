package service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import dao.PagingDao;
import neo.common.data.DataMap;

@Service
public class PagingService {
	
	@Inject
	private SqlSession session;

	private PagingDao mapper;
	
	public int getTotalCount() {
		
		return mapper.getTotalCount();
	}
	
	public List<DataMap> getPagingData(DataMap map){
		mapper = session.getMapper(PagingDao.class);

		
		return mapper.getPagingData(map);
	}

	public void setDataTable(String countNum,String currentPage) {
		mapper = session.getMapper(PagingDao.class);
		
		System.out.println("************* "+countNum);
		
		int totalCount = mapper.getTotalCount();
		
		int listCount = Integer.parseInt(countNum);
		
		int totalpage = totalCount/listCount;
		
		int pagecount = 10;
		
		if(totalCount % listCount > 0 ) {
			totalpage++;
		}
		
		DataMap map = new DataMap();
		map.put("start", 0);
		map.put("count", listCount);
		List<DataMap> result = mapper.getPagingData(map);
		
		System.out.println("+_+_+_+_+_+_+_"+ result.size());
		
		System.out.println("#$#$#$"+totalCount);
		
		
	}

}
