package ui;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dao.PlayerDao;
import model.Player;
import model.PlayerTeam;
import model.Team;

public class SelectPlayerUi extends AbstractUiTemplate{
	private PlayerDao playerDao;
	private UpdatePlayerUi updatePlayerUi;
	private DeletePlayerUi deletePlayerUi;
	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	public void setUpdatePlayerUi(UpdatePlayerUi updatePlayerUi) {
		this.updatePlayerUi = updatePlayerUi;
	}

	public void setDeletePlayerUi(DeletePlayerUi deletePlayerUi) {
		this.deletePlayerUi = deletePlayerUi;
	}
	protected Integer getTeamId() {
		System.out.println("목록을 출력할 팀의 ID를 입력하고 Enter를 "
				+ "누르세요.");
		String teamId = getInputedString();
		if(StringUtils.isNotEmpty(teamId) && StringUtils.isNumeric(teamId)) {
			return Integer.valueOf(teamId);
		}
		return getTeamId();
	}
	protected void showPlayerList(List<PlayerTeam> playerList) {
		System.out.println("******************************");
		if( ! playerList.isEmpty()) {
			PlayerTeam playerTeam = playerList.get(0);
			Player player = new Player();
			player.setPlayer_id(playerTeam.getPlayer_id());
			player.setName(playerTeam.getPlayerName());
			Team team = new Team();
			team.setTeam_id(playerTeam.getTeam_id());
			team.setName(playerTeam.getTeamName());
			System.out.printf("팀명 : %s%n", team.getName());
		}
		System.out.println("ID  선수이름");
		for(PlayerTeam player : playerList) {
			System.out.printf("%d %s%n", player.getPlayer_id(),
					player.getPlayerName());
		}
	}
	@Override
	protected void showMenu() {
		System.out.println("1. 선수 갱신");
		System.out.println("2. 선수 방출");
		System.out.println("3. 메뉴로 돌아가기");
		System.out.println();
		System.out.println("번호를 입력하고 Enter를 누르세요.");
	}
	@Override
	protected int getMaxMenuNumber() {
		return 3;
	}
	@Override
	protected int getMinMenuNumber() {
		return 1;
	}

	@Override
	protected void execute(int number) {
		switch(number) {
		case 1: updatePlayerUi.show(); break;
		case 2: deletePlayerUi.show(); break;
		default:
		}
	}
	
	public void show() {
		Integer teamId = getTeamId();
		showPlayerList(playerDao.getPlayerList(teamId));
		System.out.println("Enter를 누르세요.");
		getInputedString();
		super.show();
	}

}










