package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import dao.PlayerDao;
import model.Player;
import model.PlayerTeam;

public class PlayerDaoImpl implements PlayerDao {
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public void insertPlayer(Player player) throws DataAccessException {
		session.insert("Test.putPlayer",player);
	}
	@Override
	public List<PlayerTeam> getPlayerList(Integer teamId) throws DataAccessException {
		return session.selectList("Test.getTeamPlayer",teamId);
	}
	@Override
	public Player getPlayer(Integer id) throws DataAccessException {
		PlayerTeam playerTeam=session.selectOne("Test.getPlayerTeam",id);
		if(playerTeam != null) {
			Player player = new Player();
			player.setPlayer_id(playerTeam.getPlayer_id());
			player.setName(playerTeam.getPlayerName());
			player.setTeam_id(playerTeam.getTeam_id());
			return player;
		}else {
			return null;
		}
	}
	@Override
	public void deletePlayer(Player player) throws DataAccessException {
		session.delete("Test.deletePlayer",player);
	}
	@Override
	public void updatePlayer(Player player) throws DataAccessException {
		session.update("Test.updatePlayer",player);
	}

}







