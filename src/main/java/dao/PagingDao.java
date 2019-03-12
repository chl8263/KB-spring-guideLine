package dao;

import java.util.List;

import neo.common.data.DataMap;

public interface PagingDao {

	int getTotalCount();	// 전체 게시글갯수를 가져오는 

	List<DataMap> getPagingData(DataMap map);	// 페이징에 해당하는 게시글을 가져오는 

}
