package ui;

import org.apache.commons.lang3.StringUtils;

import dao.PlayerDao;
import dao.TeamDao;
import model.Player;
import model.Team;
import util.MyUtility;

public class InsertPlayerUi extends AbstractUi{
	private TeamDao teamDao;	private PlayerDao playerDao;
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	protected void showMenu(String wanted) {
		System.out.println("-------------------------");
		System.out.println();
		System.out.printf("%s를 입력하고 Enter를 누르세요.", wanted);
		System.out.println("아무것도 입력하지 않고 Enter를 누르면, "
				+ "메뉴로 돌아갑니다.");
	}
	protected void showTeamField(Player player) {
		final String teamId = "팀 ID";
		showMenu(teamId);
		String id = getInputedString();
		if(StringUtils.isEmpty(id)) { return; }
		else if(MyUtility.isNumeric(id, teamId)) {
			Team team=teamDao.getTeam(Integer.valueOf(id));
			if(team == null) {
				System.out.printf("%s인 팀은 존재하지 않습니다.", id);
			}else {
				player.setTeam_id(Integer.valueOf(id));
				showPlayerId(player,team);
			}
		}
	}
	protected void showPlayerId(Player player, Team team) {
		final String playerId = "선수ID";
		showMenu(playerId);
		String id = getInputedString();
		if(StringUtils.isEmpty(id)) { return;}
		else if(MyUtility.isNumeric(id, playerId)){
			player.setPlayer_id(Integer.valueOf(id));
			playerDao.insertPlayer(player);
			System.out.printf("팀 %s에 %s 선수를 등록했습니다.%n", 
				team.getName(),player.getName());
		}
	}
	@Override
	public void show() {
		final String playerName="선수명";
		showMenu(playerName);
		String name = getInputedString();
		if(StringUtils.isEmpty(name)) { return; }
		else if( MyUtility.isSmallLength(name, playerName, 30)) {
			Player player = new Player();
			player.setName(name);
			showTeamField(player);
		}else {
			show();
		}
	}

}
















