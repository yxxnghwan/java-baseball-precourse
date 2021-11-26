package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Game {

	public static void play() {
		List<Integer> answerNumber = generateAnswer();
		//System.out.println(Arrays.toString(answerNumber)); // 생성된 난수 확인 위한 코드 추후 제거

		boolean running = true;
		do {
			System.out.print(Const.INPUT_MESSAGE);
			List<Integer> inputNumber = getInputNumber();

			Hint hint = new Hint(answerNumber, inputNumber);
			System.out.println(hint.getHintMessage());

			if(hint.isAllStrike())
				running = false;

		} while (running);
	}

	public static boolean askNewGameOrExit() {
		System.out.println(Const.NEW_GAME_OR_EXIT);
		String userSelect = Console.readLine();

		if(Const.SELECT_NEW_GAME.equals(userSelect))
			return true;

		if(Const.SELECT_EXIT.equals(userSelect))
			return false;

		throw new IllegalArgumentException();
	}

	private static List<Integer> generateAnswer() {
		List<Integer> answer = new ArrayList<>();
		for (int i = 0; i < Const.ANSWER_SIZE; i++) {
			insertUniqueNumber(answer);
		}

		return answer;
	}

	private static void insertUniqueNumber(List<Integer> answer) {
		while (true) {
			int r = Randoms.pickNumberInRange(Const.MIN_ANSWER_VALUE, Const.MAX_ANSWER_VALUE);

			if (!answer.contains(r)) {
				answer.add(r);
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
			int n = Character.getNumericValue(inputText.charAt(i));
			boolean isSingleDigit = n <= Const.MAX_ANSWER_VALUE && n >= Const.MIN_ANSWER_VALUE;
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

	private static List<Integer> getInputNumber() {
		List<Integer> result = new ArrayList<>();

		String inputNumber = Console.readLine();

		if (!isCorrectInput(inputNumber))
			throw new IllegalArgumentException();

		for (int i = 0; i < inputNumber.length(); i++)
			result.add(Character.getNumericValue(inputNumber.charAt(i)));

		return result;
	}

}
