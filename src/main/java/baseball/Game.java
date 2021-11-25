package baseball;

import java.util.Arrays;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Game {

	public static void play() {
		int[] answerNumber = generateAnswer();
		System.out.println(Arrays.toString(answerNumber)); // 생성된 난수 확인 위한 코드 추후 제거

		System.out.print(Const.INPUT_MESSAGE);
		String inputNumber = Console.readLine();

		if (!isCorrectInput(inputNumber))
			throw new IllegalArgumentException();
	}

	private static int[] generateAnswer() {
		int[] answer = new int[Const.ANSWER_SIZE];
		for (int i = 0; i < answer.length; i++) {
			insertUniqueNumber(answer, i);
		}

		return answer;
	}

	private static void insertUniqueNumber(int[] arr, int index) {
		while (true) {
			int r = Randoms.pickNumberInRange(Const.MIN_ANSWER_VALUE, Const.MAX_ANSWER_VALUE);

			if (contains(arr, index, r))
				continue;
			else {
				arr[index] = r;
				break;
			}
		}
	}

	private static boolean contains(int[] arr, int index, int num) {
		for (int i = 0; i < index; i++)
			if (arr[i] == num)
				return true;

		return false;
	}

	private static boolean isCorrectInput(String inputText) {

		// 길이가 3이 아님
		if (inputText.length() != Const.ANSWER_SIZE)
			return false;

		// 1~9 숫자가 아님
		for (int i = 0; i < inputText.length(); i++) {
			int n = inputText.charAt(i) - '0';
			boolean isSingleDigit = n <= 9 && n >= 1;
			if (!isSingleDigit)
				return false;
		}

		// 숫자 중복
		for (int i = 0; i < inputText.length(); i++) {
			String prevNums = inputText.substring(0, i);
			String nowNum = Character.toString(inputText.charAt(i));
			if (prevNums.contains(nowNum))
				return false;
		}

		return true;
	}

}
