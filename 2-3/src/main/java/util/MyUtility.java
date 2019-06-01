package util;

import org.apache.commons.lang3.StringUtils;

public class MyUtility {
	public static boolean isSmallLength(String name,String fieldName,
					int length) {
		if(name == null) return false;
		if(length < name.length()) {//이름의 길이가 length보다 큰 경우
			System.out.printf("%s는 %d문자 이하로 입력하세요.%n",
					fieldName, length);
			return false;
		}
		return true;
	}
	public static boolean isNumeric(String str, String fieldName) {
		if( !StringUtils.isNumeric(str)) {
			System.out.printf("%s는 숫자로 입력하세요.%n", fieldName);
		}//숫자로 입력하지 않은 경우
		return true;
	}
}









