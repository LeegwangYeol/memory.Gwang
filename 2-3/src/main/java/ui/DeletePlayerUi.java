package ui;

import org.apache.commons.lang3.StringUtils;

import dao.PlayerDao;
import model.Player;
import util.MyUtility;

public class DeletePlayerUi extends AbstractUi{
	private PlayerDao playerDao;
	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	protected void showMenu() {
		System.out.println("=============================");
		System.out.println();
		System.out.println("선수ID를 입력하고 Enter를 누르세요.");
		System.out.println("아무것도 입력하지 않고 Enter를 누르면,"
				+ "메뉴로 돌아갑니다.");
	}
	@Override
	public void show() {
		showMenu();
		String id = getInputedString();
		if(StringUtils.isEmpty(id)) { return; }
		else if(MyUtility.isNumeric(id, "선수ID")) {
			Player player=playerDao.getPlayer(Integer.valueOf(id));
			if(player == null) {
				System.out.printf("%s인 선수는 존재하지 않습니다.%n", id);
				show();
			}else {
				playerDao.deletePlayer(player);
				System.out.printf("%s인 선수를 방출했습니다.%n", id);
			}
		}else { show();}
	}

}




