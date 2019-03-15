package practice;

public class Sort2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers = {6, 10, 2};
		solution(numbers);
	}

	public static String solution(int[] numbers) {

		String[] strNum = new String[numbers.length];
		for (int i = 0;  i < numbers.length; i++) {
			strNum[i] = numbers + "";	
		}
		for (int i = 0;  i < numbers.length; i++) {
			combination(strNum,i);	
		}
		return null;
	}

	public static void combination(String[] strNum, int i) {
		// TODO Auto-generated method stub
		String before = strNum[i];
		for (int j = i ;j < strNum.length; j++) {
			
		}
	}
	
}
