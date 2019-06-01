package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import dao.TeamDao;
import model.Team;

public class TeamDaoImpl implements TeamDao {
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public Team getTeam(Integer teamId) throws DataAccessException {
		return session.selectOne("Test.getTeam", teamId);
	}

	@Override
	public List<Team> getTeamList() throws DataAccessException {
		return session.selectList("Test.getTeamList");
	}

}
















