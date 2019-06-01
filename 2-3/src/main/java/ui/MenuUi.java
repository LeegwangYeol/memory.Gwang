package ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MenuUi extends AbstractUiTemplate{
	private SelectTeamUi selectTeamUi;//팀목록 검색
	private InsertPlayerUi insertPlayerUi;//팀검색,선수등록
	private SelectPlayerUi selectPlayerUi;//선수변경,선수방출
	
	public void setSelectPlayerUi(SelectPlayerUi selectPlayerUi) {
		this.selectPlayerUi = selectPlayerUi;
	}
	public void setInsertPlayerUi(InsertPlayerUi insertPlayerUi) {
		this.insertPlayerUi = insertPlayerUi;
	}
	public void setSelectTeamUi(SelectTeamUi selectTeamUi) {
		this.selectTeamUi = selectTeamUi;
	}
	@Override
	protected void showMenu() {
		System.out.println("------------------------------");
		System.out.println("     Sports Utility Ver 1.0");
		System.out.println("");
		System.out.println("1. 종료");
		System.out.println("2. 팀 목록");
		System.out.println("3. 선수 등록");
		System.out.println("4. 선수 목록");
		System.out.println();
		System.out.println("번호를 입력하고 Enter를 누르세요.");
	}
	@Override
	protected int getMaxMenuNumber() {	return 4;	}
	@Override
	protected int getMinMenuNumber() {	return 1;	}
	@Override
	protected void execute(int number) {
		switch(number) {
		case 1: System.out.println("종료되었습니다."); 
				System.exit(0); break;
		case 2: this.selectTeamUi.show(); break;
		case 3: this.insertPlayerUi.show(); break;
		case 4: this.selectPlayerUi.show(); break;
		}
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MenuUi menuUi = context.getBean(MenuUi.class);
		while(true) {
			menuUi.show();
		}
	}
}










