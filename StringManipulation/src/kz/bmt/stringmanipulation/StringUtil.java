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
		System.out.println("������� ���������� ����� ��� ����������� �� ����: ");
		source = console.nextLine();
		if (source.length() <= 0) {
			System.out.println("��� �� ������ �� �����, �������� ��������� � ��������� ��� ���!");
		} else {

			words = source.split(" ");
			lengthWord = words.length;

			System.out.println();
			System.out.println("�� ����� ���� � ����������� ������: " + lengthWord);
			System.out.println();

			for (int i = 0; i < words.length; i++) {
				System.out.println("��� �����: " + "\"" + words[i] + "\"");
				letters = words[i].toCharArray();
				System.out.println(
						"����������� �������� � ����� " + "\"" + words[i] + "\"" + " �����: " + letters.length);
				System.out.println();
			}

		}
	}
}