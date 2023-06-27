import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        Socket socket = serverSocket.accept();
        System.out.println("Cliente se conectou");

        InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
        PrintStream saida = new PrintStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(inputReader);

        String x;

        while (true) {
            x = reader.readLine();
            System.out.println("Cliente: " + x);
            if (x.equalsIgnoreCase("terminar")) {
                break;
            }

            // Faça o processamento necessário com a mensagem recebida

            // Exemplo de resposta do servidor
            String resposta = "-: " + x;
            saida.println(resposta);

            // Encerra a conversa se o servidor também digitar "terminar"
            if (x.equalsIgnoreCase("terminar")) {
                break;
            }
        }

        // Feche os recursos
        reader.close();
        saida.close();
        inputReader.close();
        socket.close();
        serverSocket.close();
    }
}