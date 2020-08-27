package kz.bmt.stringmanipulation;

import java.util.Scanner;

public class StringUtil {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		String source;
		int lengthWord;
		String[] words;
		char[] letters;

		Scanner console = new Scanner(System.in);
		System.out.println("Введите пожалуйста слово или предложение из слов: ");
		source = console.nextLine();
		if (source.length() <= 0) {
			System.out.println("Увы вы ничего не ввели, закройте программу и повторите еще раз!");
		} else {

			words = source.split(" ");
			lengthWord = words.length;

			System.out.println();
			System.out.println("Вы ввели слов в колличестве равное: " + lengthWord);
			System.out.println();

			for (int i = 0; i < words.length; i++) {
				System.out.println("Это слово: " + "\"" + words[i] + "\"");
				letters = words[i].toCharArray();
				System.out.println(
						"Колличество символов в слове " + "\"" + words[i] + "\"" + " равно: " + letters.length);
				System.out.println();
			}

		}
	}
}