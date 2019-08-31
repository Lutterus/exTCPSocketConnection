package Cliente;

import java.io.*;
import java.net.*;

class Main {
	private static BufferedReader inFromServer;
	private static Socket clientSocket;
	private static DataOutputStream outToServer;

	public static void main(String argv[]) throws Exception {

		/* Instancia variaveis */
		instantiate();

		/* Pega a mensagem do cliente */
		String sentence = getMessage();

		/* Envia mensagem para o cliente */
		sendMessage(sentence);

		/* Encerra conexão */
		clientSocket.close();

	}

	private static void sendMessage(String sentence) throws IOException {
		/*
		 * Envia para o servidor. Não esquecer do \n no final para permitir que o
		 * servidor use readLine
		 */
		outToServer.writeBytes(sentence + '\n');

		/* Lê mensagem de resposta do servidor */
		String echo = inFromServer.readLine();

		System.out.println("FROM SERVER: " + echo);

	}

	private static String getMessage() throws IOException {
		/* Permite leitura de dados do teclado */
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		/* Lê uma mensagem digitada pelo usuário */
		System.out.print("Digite uma menssagem: ");
		String sentence = inFromUser.readLine();
		return sentence;
	}

	private static void instantiate() throws UnknownHostException, IOException {

		/* Cria o socket cliente indicando o IP e porta de destino. */
		clientSocket = new Socket("127.0.0.1", 6790);

		/* Cria uma stream de saída para enviar dados para o servidor */
		outToServer = new DataOutputStream(clientSocket.getOutputStream());

		/* Cria uma stream de entrada para receber os dados do servidor */
		inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

	}
}
