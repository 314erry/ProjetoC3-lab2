package cadastro;

import java.util.ArrayList;
import java.util.List;

import arquivos.ManipularArquivos;
import login.LoginAluno;

public class CadastroAluno {
    public static void cadastrarAluno(String usuario, int matricula, String senha) {
        if (LoginAluno.getMatriculas().contains(matricula)) { // Verificar se o Aluno já possui um cadastro em LoginAluno
            System.out.println("\u001B[31mAluno(a) já está cadastrado(a) no sistema.\u001B[0m");
        } else {
            LoginAluno.getUsuarios().add(usuario);
            LoginAluno.getMatriculas().add(matricula);
            LoginAluno.getSenhas().add(senha);
            System.out.println("\u001B[32mAluno cadastrado no sistema com sucesso!\u001B[0m");
        
            // Adicionar o login do aluno ao arquivo
            List<String> logins = new ArrayList<>();
            for (int i = 0; i < LoginAluno.getUsuarios().size(); i++) { // vai iterar enquanto i for menor que o tamanho da List Usuarios de LoginAluno
                // vai adicionar cada login em uma linha, separando por ";"
                String linhaLogin = LoginAluno.getUsuarios().get(i) + ";" + LoginAluno.getSenhas().get(i) + ";" + LoginAluno.getMatriculas().get(i);
                logins.add(linhaLogin); // adiciona linhaLogin no final da List logins
            }
            ManipularArquivos.salvarLoginsAlunos("src/arquivos/cadastros_de_aluno.txt", logins); // Salva arquivo a cada cadastro feito.
        }
        
    }


}