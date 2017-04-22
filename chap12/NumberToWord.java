package chap12;

import java.util.*;

public class NumberToWord {
	private static String input;
	private static int num;
	private static String[] units = { "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight",
			" Nine" };
	private static String[] teen = { " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen",
			" Seventeen", " Eighteen", " Nineteen" };
	private static String[] tens = { " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty",
			" Ninety" };
	private static String[] maxs = { "", "", " Hundred", " Thousand", " Lakh", " Crore" }; // Lakh��ʮ��Crore��ǧ��

	public String convertNumberToWords(int n) {
		input = numToString(n); // ���磺input = 12,34,56,7,89
		String converted = "";
		int pos = 1;
		boolean hun = false;
		if (input.length() == 0) {
			converted = "zero";
		}
		while (input.length() > 0) {
			if (num >= 1000000000) {
				converted = "Number should be Less than 1 Bilion";
				break;
			}
			if (pos == 1) // TENS AND UNIT POSITION
			{
				if (input.length() >= 2) // TWO DIGIT NUMBERS
				{
					String temp = input.substring(input.length() - 2, input.length()); // ��ȡ�����λ
					// Examples: "hamburger".substring(4, 8) returns "urge"

					input = input.substring(0, input.length() - 2);
					// ��ȡ��λ�����ϸ���input
					converted += digits(temp); // ��ʮλ��ת�����ַ���������Eighty Nine
				} else if (input.length() == 1) // 1 DIGIT NUMBER
				{
					converted += digits(input); // �Ѹ�λ��ת�����ַ�������
					break; // ֹͣѭ����������ѭ��
				}
				pos++; // ����if������pos == 2����������
			} else if (pos == 2) // HUNDRED POSITION��input = 12,34,56,7
			{
				String temp = input.substring(input.length() - 1, input.length()); // ��ȡ���һλ��ԭ���İ�λ������temp��7
				input = input.substring(0, input.length() - 1); // ��ȡǧλ�����ϸ���input��12,34,56
				if (converted.length() > 0 && digits(temp) != "") {
					converted = (digits(temp) + maxs[pos] + " and") + converted;
					// �����λʮλ��Ϊ�գ��Ұ�λ��Ϊ�գ�����Ҫ��and����Seven Hundred and Eighty Nine
					// :789
					hun = true;
				} else {
					if (digits(temp) == "") { // �����λΪ��
						if (converted.length() > 0) { // �����λʮλ��Ϊ��
							converted = " and" + converted; // �ڸ�λʮλǰ���and
							hun = true;
						} else if (converted.length() == 0) { // �����λʮλҲΪ��
							; // converted����
							hun = true;
						}

					} else {
						converted = digits(temp) + maxs[pos]; // �����λ��Ϊ�գ�����λʮλΪ�ա����磺12,34,56,7,00
						hun = false;
					}
				}
				pos++; // ����if������pos > 2����������
			} else if (pos > 2) // REMAINING NUMBERS PAIRED BY TWO

			{
				if (input.length() >= 2) // EXTRACT 2 DIGITS��input =
											// 12,34,56,(7,89)
				{
					String temp = input.substring(input.length() - 2, input.length()); // ��ȡǧλ����λ����temp��56
					input = input.substring(0, input.length() - 2); // ��ȡLakhλ�����ϸ���input��12,34
					if (!hun && converted.length() > 0) { // �����λ��Ϊ�գ�����λʮλΪ��
						converted = digits(temp) + maxs[pos] + " and" + converted; // 47~53�е���������
						hun = true;
					} else {
						if (converted.length() == 0) { // �����λʮλ��λ��Ϊ��
							if (input.length() == 0) {
								converted = digits(temp) + maxs[pos]; // 00,56,0,00
							} else {
								if (digits(temp) == "") {
									converted = ""; // 34,00,0,00
								} else {
									converted = " and" + digits(temp) + maxs[pos]; // 34,56,000
								}
							}
						} else { // �����λʮλ��λ��Ϊ�գ�hun==true
							converted = digits(temp) + maxs[pos] + converted; // 34,56,7,89
						}
						// hun = true;
					}
					pos++;
				} else if (input.length() == 1) // EXTRACT 1 DIGIT
				{
					if (!hun) {
						converted = digits(input) + maxs[pos] + " and" + converted; // 6,7,00
					} else {
						if (digits(input) == "")
							;
						else
							converted = digits(input) + maxs[pos] + converted;
					}
					break; // ֹͣ�����ѭ��:while(����){if(i==2){break;}}
				}
			}
		}
		return converted;
	}

	private String digits(String temp) // TO RETURN SELECTED NUMBERS IN WORDS
	{
		String converted = "";
		for (int i = temp.length() - 1; i >= 0; i--) {
			int ch = temp.charAt(i) - 48; // �����ַ���ȥ48���ַ�ת��Ϊ��ֵ��12,34,56,7,89
			/*
			 * IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS
			 * ���ʮλ������1���ͷ���20��30��40����
			 */
			if (i == 0 && ch > 1 && temp.length() > 1) {
				converted = tens[ch - 2] + converted;
			}

			/*
			 * IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS
			 * ���ʮλ������1���ͷ���10��11��12����
			 */
			else if (i == 0 && ch == 1 && temp.length() == 2) {
				int sum = 0;
				for (int j = 0; j < 2; j++) {
					sum = (sum * 10) + (temp.charAt(j) - 48); // ���ַ�ת��Ϊ��ֵ
				}
				return teen[sum - 10];
			} else {
				if (ch > 0)
					converted = units[ch] + converted; // ���ظ�λ��
			} // IF SINGLE DIGIT PROVIDED
		}
		return converted;
	}

	// �����ֱ�ɡ������ַ�����
	private String numToString(int x) // CONVERT THE NUMBER TO STRING
	{
		String num = "";
		while (x != 0) {
			num = ((char) ((x % 10) + 48)) + num;
			x /= 10;
		}
		return num;
	}

	private void inputNumber() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("Please enter number to Convert into Words : ");
			num = in.nextInt(); // �������ָ�ֵ��num
		} catch (Exception e) {
			System.out.println("Number should be Less than 1 Bilion  ");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		NumberToWord obj = new NumberToWord();
		obj.inputNumber(); // ����̨�������ָ�ֵ��num
		System.out.println("input in Words : " + obj.convertNumberToWords(num));
		System.out.println("COME ON LIKE IT !!");
	}
}