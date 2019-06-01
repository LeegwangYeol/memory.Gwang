package ui;

import java.util.Scanner;

public abstract class AbstractUi {
	protected String getInputedString() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	abstract public void show();
}
