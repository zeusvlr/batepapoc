import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream saida = new PrintStream(socket.getOutputStream());
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        String mensagem;

        while (true) {
            mensagem = teclado.readLine();
            saida.println(mensagem);
            if (mensagem.equalsIgnoreCase("terminar")) {
                break;
            }

            // Receber resposta do servidor
            String resposta = reader.readLine();
            System.out.println("Servidor: " + resposta);

            // Encerra a conversa se o cliente também digitar "terminar"
            if (resposta.equalsIgnoreCase("terminar")) {
                break;
            }
        }

        // Feche os recursos
        teclado.close();
        reader.close();
        saida.close();
        socket.close();
    }
}
