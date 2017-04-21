package chap12;

import java.util.Scanner;
import java.util.Random;

/**********************************************
 * Simple game made by Frekvens1 - Version 1.1 - Self explaining code - Made for
 * newer developers to learn more
 ***********************************************
 * 
 * Changelog
 ***********************************************
 * 
 * 1.1: - Bug fixes - Added more inputs - Increased readability - Optimized some
 * functions - Added changelog and credits
 ***********************************************
 * 
 * Credits - Thanks for testing my code!
 ***********************************************
 * 
 * Shlvam Rawat & Sasha_Kuprii - Shortened the inputs to "r, p, s"
 * 
 * Ivan - Made me convert to switch, as its more correct usage.
 * 
 * Gabriel Carvalho - Bug hunter: "NoSuchElementException" when input was null
 * 
 * Alexandre da Costa Leite - Bug hunter: Changing from && to || in function
 * check for win - Important change if converting this to multiplayer
 ***********************************************
 * 
 * Uploaded 19.07.2016 (DDMMYYYY) Last updated 04.02.2017 (DDMMYYYY)
 **********************************************/

public class RockPaperScissors {
	/*
	 * Valid user input: rock, paper, scissors, scissor r, p, s 石头剪刀布
	 */
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			if (sc.hasNext()) { // Checks for null values
				String userInput = quickFormat(sc.next()); //将缩写转换成正式字符串
				sc.close();
				//如果输入是rock, paper, scissors，就开始游戏。
				if (isValid(userInput)) {
					game(userInput);
				} else {
					displayInputs();
				}
			} else {
				displayInputs(); // Null value, displaying correct inputs
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void game(String user) {
		String computer = computerResults();
		print(user + " vs " + computer + "\n");
		// 将此 String 与另一个 String
		// 进行比较，不考虑大小写。如果两个字符串的长度相等，并且两个字符串中的相应字符都相等（忽略大小写），则认为这两个字符串是相等的。
		if (user.equalsIgnoreCase(computer)) {
			print("Stalemate! No winners.");
		} else {
			// 对比结果
			if (checkWin(user, computer)) {
				print("You won against the computer!");
			} else {
				print("You lost against the computer!");
			}
		}
	}

	/*
	 * 电脑生成随机结果
	 */
	public static String computerResults() {
		String types[] = { "rock", "paper", "scissors" };
		Random rand = new Random();
		int computerChoice = rand.nextInt(3); //返回一个大于等于0小于3的随机数
		return types[computerChoice];
	}

	/*
	 * 将所有的英文字符转换为小写字母之后，再进行判断
	 */
	public static boolean isValid(String input) {
		switch (input.toLowerCase()) {
		case "rock":
			return true;
		case "paper":
			return true;
		case "scissors":
			return true;
		default:
			return false;
		}
	}

	/*
	 * 将输入结果和电脑结果进行对比
	 */
	public static boolean checkWin(String user, String opponent) {
		if ((!isValid(user)) || (!isValid(opponent))) {
			return false; //lost
		}
		String rock = "rock", paper = "paper", scissors = "scissors";
		if ((user.equalsIgnoreCase(rock)) && (opponent.equalsIgnoreCase(scissors))) {
			return true;
		}
		if ((user.equalsIgnoreCase(scissors)) && (opponent.equalsIgnoreCase(paper))) {
			return true;
		}
		if ((user.equalsIgnoreCase(paper)) && (opponent.equalsIgnoreCase(rock))) {
			return true;
		}
		return false;
		// If no possible win, assume loss.
	}

	/**********************************************
	 * Libraries
	 **********************************************/

	public static void displayInputs() {
		// One place to edit it all!
		print("Invalid user input!\nWrite rock, paper or scissors!"); // 有效输入
	}

	public static void print(String text) {
		// Makes printing text easier
		System.out.println(text);
	}

	/*
	 * 快速格式化：将缩写转换成正常字符串
	 */
	public static String quickFormat(String input) {
		// Just some quick function to shorten inputs. —— 适应缩写
		String output = input;
		switch (input.toLowerCase()) {
			case "r":
				output = "rock";
				break;
			case "p":
				output = "paper";
				break;
			case "s":
				output = "scissors";
				break;
			case "scissor":
				output = "scissors";
				break;
		}
		return output;
	}
}