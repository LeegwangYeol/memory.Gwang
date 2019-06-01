package ui;

import org.apache.commons.lang3.StringUtils;

import dao.PlayerDao;
import dao.TeamDao;
import model.Player;
import model.Team;
import util.MyUtility;

public class UpdatePlayerUi extends AbstractUi{
	private PlayerDao playerDao;	private TeamDao teamDao;
	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	protected void showMenu(String wanted) {
		System.out.println("##############################");
		System.out.println();
		System.out.printf("%s를 입력하고 Enter를 누르세요.", wanted);
		System.out.println("아무것도 입력하지 않고 Enter를 누르면,"
				+ "메뉴로 돌아갑니다.");
	}
	protected Player getPlayer() {
		final String playerId = "선수ID";
		showMenu(playerId);
		String id = getInputedString();
		if(StringUtils.isEmpty(id)) { return null; }
		else if(MyUtility.isNumeric(id, playerId)) {
			Player player=playerDao.getPlayer(Integer.valueOf(id));
			if(player == null) {
				System.out.printf("%s인 선수는 존재하지 않습니다.%n", id);
				return getPlayer();
			}
			return player;
		}
		return getPlayer();
	}
	protected String getName(Player player) {
		final String playerName="선수 명";
		showMenu(playerName);
		System.out.println("기존의 이름:"+player.getName());
		String name = getInputedString();
		if(StringUtils.isEmpty(name)) return null;
		if(MyUtility.isSmallLength(name, playerName, 50)) return name;
		return getName(player);
	}
	protected Team getTeam(Player player) {
		final String teamId="팀 ID";
		showMenu(teamId);
		System.out.printf("원 소속 팀 %d%n", player.getTeam_id());
		String id = getInputedString();
		if(StringUtils.isEmpty(id)) return null;
		if(MyUtility.isNumeric(id, teamId)) {
			Team team = teamDao.getTeam(Integer.valueOf(id));
			if(team == null) {
				System.out.printf("%s인 팀은 존재하지 않습니다.%n", id);
				return getTeam(player);
			}
			return team;
		}
		return getTeam(player);
	}
	@Override
	public void show() {
		Player player = getPlayer();
		if(player == null) return;
		String name = getName(player);
		if(StringUtils.isNotEmpty(name)) player.setName(name);
		Team team = getTeam(player);
		if(team != null) player.setTeam_id(team.getTeam_id());
		playerDao.updatePlayer(player);//player 정보 update
		System.out.printf("%d인 선수의 이름을 %s, 팀을 %d로 수정합니다.%n",
			player.getPlayer_id(),player.getName(),player.getTeam_id());
	}

}











