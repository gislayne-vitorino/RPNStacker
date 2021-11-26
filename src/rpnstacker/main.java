package rpnstacker;

import java.util.*;
import java.io.*;

public class main {
	public static void main(String[] args) throws Exception {
		try {
			Scanner in = new Scanner(new FileInputStream("Calc1.stk"));

			Stack pilha = new Stack();
			ArrayList<Token> tokenList = new ArrayList<Token>();

			while (in.hasNext()) {
				String str = in.next();
				tokenList = ListAdd(tokenList, str);
			}

			// acaba a leitura do arquivo
			System.out.println(tokenList.toString()); // Printa a lista de Tokens

			for (int i = 0; i < tokenList.size(); i++) {

				boolean isDigit = tokenList.get(i).type == TokenType.NUM;
				String str = tokenList.get(i).lexeme;

				if (isDigit) {
					pilha.push(str);
				} else {
					int n2 = Integer.parseInt(pilha.pop().toString());
					int n1 = Integer.parseInt(pilha.pop().toString());
					switch (str) {
					case "+":
						pilha.push(n1 + n2);
						break;
					case "-":
						pilha.push(n1 - n2);
						break;
					case "*":
						pilha.push(n1 * n2);
						break;
					case "/":
						pilha.push(n1 / n2);
						break;
					}
				}
			}

			System.out.println("Resultado é: " + pilha.pop());
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		}
	}

	public static boolean isdigit(char chara) { // Verifica se a string é um número
		
		if (Character.toString(chara).matches("[0123456789]")) { // verifica se o char é um digito
			return true;
		}
		return false;
	}

	public static ArrayList<Token> ListAdd(ArrayList<Token> tokenList, String str) throws Exception {
		boolean realdigit = true;
		if (isdigit(str.charAt(0))) {  //Confere se a string é composta apenas por digitos
			for (int i = 0; i < str.length(); i++) {
				realdigit = isdigit(str.charAt(i));
				if (realdigit == false) {
					throw new Exception("Error: Unexpected character: " + str);
				}
			}
			tokenList.add(new Token(TokenType.NUM, str));
		} else { // se for um operando
			switch (str) {
			case "+":
				tokenList.add(new Token(TokenType.PLUS, str));
				break;
			case "-":
				tokenList.add(new Token(TokenType.MINUS, str));
				break;
			case "*":
				tokenList.add(new Token(TokenType.STAR, str));
				break;
			case "/":
				tokenList.add(new Token(TokenType.SLASH, str));
				break;
			default:
				throw new Exception("Error: Unexpected character: " + str);

			}
		}
		return tokenList;
	}
}
