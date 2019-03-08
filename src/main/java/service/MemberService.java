package service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import neo.common.data.DataMap;

@Service
public class MemberService {

	@Inject
	private SqlSession session;

	private MemberDao mapper;

	public void addMember(DataMap map) {
		mapper = session.getMapper(MemberDao.class);
		mapper.insertMember(map);

	}

	public List<DataMap> findAllMember() {
		mapper = session.getMapper(MemberDao.class);
		return mapper.findAllMember();

	}

	public DataMap getMemberById(int id) {
		mapper = session.getMapper(MemberDao.class);

		return mapper.getMemberById(id);
	}

	public void updateMember(DataMap map) {
		mapper = session.getMapper(MemberDao.class);
		mapper.updateMember(map);
	}

	public void deleteMember(int memberId) {
		mapper = session.getMapper(MemberDao.class);
		mapper.deleteMember(memberId);
	}

}
