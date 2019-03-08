package dao;

import java.util.List;

import neo.common.data.DataMap;

public interface MemberDao {	
	
	void insertMember(DataMap map);

	List<DataMap> findAllMember();

	DataMap getMemberById(int id);

	void updateMember(DataMap map);

	void deleteMember(int memberId);

	
}
