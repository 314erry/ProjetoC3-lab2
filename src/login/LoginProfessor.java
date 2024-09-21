package login;

import java.util.ArrayList;
import java.util.List;

public class LoginProfessor {
    private static List<String> nomes = new ArrayList<>();
    private static List<String> senhas = new ArrayList<>();
    private static List<String> emails = new ArrayList<>();

    static { // Inicializando a classe adicionando uma conta de professor genérica pré-definida
        nomes.add("Professor da Silva");
        emails.add("professor@escola.com");
        senhas.add("1234");
    }

    // Faz a verificação das credenciais do professor
    public static boolean verificarLogin(String email, String senha) {
        int indiceEmail = emails.indexOf(email); // Guarda o índice do e-mail digitado no vetor emails.
        // indice 0
        if (indiceEmail != -1 && senhas.get(indiceEmail).equals(senha)) { // Verifica se existe um email e se o indiceEmail, em senhas, é igual a senha digitada
            return true;
        }
        return false;
    }


}
