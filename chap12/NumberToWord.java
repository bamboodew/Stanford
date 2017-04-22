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
	private static String[] maxs = { "", "", " Hundred", " Thousand", " Lakh", " Crore" }; // Lakh：十万；Crore：千万

	public String convertNumberToWords(int n) {
		input = numToString(n); // 假如：input = 12,34,56,7,89
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
					String temp = input.substring(input.length() - 2, input.length()); // 截取最后两位
					// Examples: "hamburger".substring(4, 8) returns "urge"

					input = input.substring(0, input.length() - 2);
					// 截取百位及以上赋予input
					converted += digits(temp); // 把十位数转换成字符串描述：Eighty Nine
				} else if (input.length() == 1) // 1 DIGIT NUMBER
				{
					converted += digits(input); // 把个位数转换成字符串描述
					break; // 停止循环，避免死循环
				}
				pos++; // 跳出if，进入pos == 2的条件进程
			} else if (pos == 2) // HUNDRED POSITION：input = 12,34,56,7
			{
				String temp = input.substring(input.length() - 1, input.length()); // 截取最后一位（原来的百位）赋给temp：7
				input = input.substring(0, input.length() - 1); // 截取千位及以上赋予input：12,34,56
				if (converted.length() > 0 && digits(temp) != "") {
					converted = (digits(temp) + maxs[pos] + " and") + converted;
					// 如果个位十位不为空，且百位不为空，就需要“and”；Seven Hundred and Eighty Nine
					// :789
					hun = true;
				} else {
					if (digits(temp) == "") { // 如果百位为空
						if (converted.length() > 0) { // 如果个位十位不为空
							converted = " and" + converted; // 在个位十位前面加and
							hun = true;
						} else if (converted.length() == 0) { // 如果个位十位也为空
							; // converted不变
							hun = true;
						}

					} else {
						converted = digits(temp) + maxs[pos]; // 如果百位不为空，但个位十位为空。例如：12,34,56,7,00
						hun = false;
					}
				}
				pos++; // 跳出if，进入pos > 2的条件进程
			} else if (pos > 2) // REMAINING NUMBERS PAIRED BY TWO

			{
				if (input.length() >= 2) // EXTRACT 2 DIGITS：input =
											// 12,34,56,(7,89)
				{
					String temp = input.substring(input.length() - 2, input.length()); // 截取千位和万位赋给temp：56
					input = input.substring(0, input.length() - 2); // 截取Lakh位及以上赋予input：12,34
					if (!hun && converted.length() > 0) { // 如果百位不为空，但个位十位为空
						converted = digits(temp) + maxs[pos] + " and" + converted; // 47~53行的条件程序
						hun = true;
					} else {
						if (converted.length() == 0) { // 如果百位十位个位都为空
							if (input.length() == 0) {
								converted = digits(temp) + maxs[pos]; // 00,56,0,00
							} else {
								if (digits(temp) == "") {
									converted = ""; // 34,00,0,00
								} else {
									converted = " and" + digits(temp) + maxs[pos]; // 34,56,000
								}
							}
						} else { // 如果百位十位个位不为空，hun==true
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
					break; // 停止最外的循环:while(……){if(i==2){break;}}
				}
			}
		}
		return converted;
	}

	private String digits(String temp) // TO RETURN SELECTED NUMBERS IN WORDS
	{
		String converted = "";
		for (int i = temp.length() - 1; i >= 0; i--) {
			int ch = temp.charAt(i) - 48; // 数字字符减去48将字符转换为数值：12,34,56,7,89
			/*
			 * IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS
			 * 如果十位数大于1，就返回20、30、40……
			 */
			if (i == 0 && ch > 1 && temp.length() > 1) {
				converted = tens[ch - 2] + converted;
			}

			/*
			 * IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS
			 * 如果十位数等于1，就返回10、11、12……
			 */
			else if (i == 0 && ch == 1 && temp.length() == 2) {
				int sum = 0;
				for (int j = 0; j < 2; j++) {
					sum = (sum * 10) + (temp.charAt(j) - 48); // 将字符转换为数值
				}
				return teen[sum - 10];
			} else {
				if (ch > 0)
					converted = units[ch] + converted; // 返回个位数
			} // IF SINGLE DIGIT PROVIDED
		}
		return converted;
	}

	// 将数字变成“数字字符串”
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
			num = in.nextInt(); // 输入数字赋值给num
		} catch (Exception e) {
			System.out.println("Number should be Less than 1 Bilion  ");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		NumberToWord obj = new NumberToWord();
		obj.inputNumber(); // 控制台输入数字赋值给num
		System.out.println("input in Words : " + obj.convertNumberToWords(num));
		System.out.println("COME ON LIKE IT !!");
	}
}