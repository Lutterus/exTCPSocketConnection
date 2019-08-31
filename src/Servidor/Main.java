package Servidor;

import java.io.*;
import java.net.*;

class Main {
	private static BufferedReader inFromClient;
	private static DataOutputStream outToClient;
	private static Socket connectionSocket;

	public static void main(String argv[]) throws Exception {
		/* Cria socket do servidor */
		ServerSocket welcomeSocket = new ServerSocket(6790);

		while (true) {

			/*
			 * Aguarda o recebimento de uma conexão. O servidor fica aguardando neste ponto
			 * até que nova conexão seja aceita.
			 */
			connectionSocket = welcomeSocket.accept();

			/* Instancia variaveis */
			instantiate();

			/* Pega a mensagem do cliente */
			String clientSentence = getMessage();

			/* Pega informacoes do cliente */
			getInfo(clientSentence);

			/* Envia mensagem para o cliente */
			sendMessage(clientSentence);
		}
	}

	private static void sendMessage(String clientSentence) throws IOException {
		/* Adiciona o \n para que o cliente também possa ler usando readLine() */
		String echo = clientSentence + '\n';

		/* Envia mensaqgem para o cliente */
		/* Envia mensagem para o cliente */
		outToClient.writeBytes(echo);

		/* Encerra socket do cliente */
		connectionSocket.close();

	}

	private static void getInfo(String clientSentence) {
		/* Determina o IP e Porta de origem */
		InetAddress IPAddress = connectionSocket.getInetAddress();
		int port = connectionSocket.getPort();

		/* Exibe, IP:port => msg */
		System.out.println(IPAddress.getHostAddress() + ":" + port + " => " + clientSentence);
	}

	private static String getMessage() throws IOException {
		/*
		 * Aguarda o envio de uma mensagem do cliente. Esta mensagem deve ser terminada
		 * em \n ou \r Neste exemplo espera-se que a mensagem seja textual (string).
		 * Para ler dados não textuais tente a chamada read()
		 */
		String mess = inFromClient.readLine();
		return mess;
	}

	private static void instantiate() throws IOException {
		/* Cria uma stream de entrada para receber os dados do cliente */
		inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

		/* Cria uma stream de saída para enviar dados para o cliente */
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());

	}
}
