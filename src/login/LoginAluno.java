package login;

import java.util.ArrayList;
import java.util.List;

import arquivos.ManipularArquivos;

public class LoginAluno {
    private static List<String> usuarios = new ArrayList<>();
    private static List<String> senhas = new ArrayList<>();
    private static List<Integer> matriculas = new ArrayList<>();

    static {
        carregarDados();
    }

    public static void carregarDados() {
    List<String> logins = ManipularArquivos.carregarLoginsAlunos("src/arquivos/cadastros_de_aluno.txt");
    for (String login : logins) {
        String[] dados = login.split(";");
        if (dados.length == 3) {
            usuarios.add(dados[0]);
            senhas.add(dados[1]);
            matriculas.add(Integer.parseInt(dados[2]));
        }
    }
}

    public static boolean verificarLogin(int matricula, String senha) { // Faz a verificação das credenciais do aluno
        int indiceMatricula = matriculas.indexOf(matricula);
        if (indiceMatricula != -1 && senhas.get(indiceMatricula).equals(senha)) {
            return true;
        }
        return false;
    }

    // Getter e Setters
    public static List<String> getUsuarios() {
        return usuarios;
    }

    public static void setNomes(List<String> nomes) {
        LoginAluno.usuarios = nomes;
    }

    public static List<String> getSenhas() {
        return senhas;
    }

    public static void setSenhas(List<String> senhas) {
        LoginAluno.senhas = senhas;
    }

    public static List<Integer> getMatriculas() {
        return matriculas;
    }

    public static void setMatriculas(List<Integer> matriculas) {
        LoginAluno.matriculas = matriculas;
    }
    
}
