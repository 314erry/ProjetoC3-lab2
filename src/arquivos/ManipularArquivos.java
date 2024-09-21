package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import usuarios.Aluno;

public class ManipularArquivos {
    public static List<Aluno> carregarAlunos(String caminhoArquivo) {
        List<Aluno> alunos = new ArrayList<>();

        // Leitura do Arquivo
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) { // try_catch com recurso para fechar o leitor ao terminar de ler 
            String linha;
            while ((linha = leitor.readLine()) != null) { // Irá ler cada linha do arquivo (um aluno) até que encontre uma linha vazia.
                String[] dados = linha.split(";"); // Irá guardar os dados dos alunos em um vetor de String, separado por ";"
                String nome = dados[0];
                int matricula = Integer.parseInt(dados[1]);
                double nota1 = Double.parseDouble(dados[2]);
                double nota2 = Double.parseDouble(dados[3]);
                double media = Double.parseDouble(dados[4]);
                int numFalta = Integer.parseInt(dados[5]);
                int coeficienteFrequencia = Integer.parseInt(dados[6]);

                // Instancia de um novo objeto de Aluno com os dados
                Aluno aluno = new Aluno(nome, matricula, nota1, nota2, media, numFalta, coeficienteFrequencia);
                alunos.add(aluno); // Adiciona objeto na list de Aluno alunos.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos; // retorna a list de Aluno
    }

    // Escrita do Arquivo
    public static void salvarAlunos(String caminhoArquivo, List<Aluno> alunos) {

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo))) { // try_catch com recurso para fechar o escritor ao terminar de escrever 
            for (Aluno aluno : alunos) { // Para cada aluno na list alunos...
                // Escreve no arquivo os dados novos do aluno.
                escritor.write(aluno.getNome() + ";" + aluno.getMatricula() + ";" + aluno.getNota1() + ";" + aluno.getNota2() + ";" + aluno.getMedia() + ";" + aluno.getNumFalta() + ";" + aluno.getCoeficienteFrequencia());
                escritor.newLine(); // Pula uma linha no arquivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar logins de alunos
    public static List<String> carregarLoginsAlunos(String caminhoArquivo) {
        List<String> logins = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                logins.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logins;
    }

    // Método para salvar logins de alunos
    public static void salvarLoginsAlunos(String caminhoArquivo, List<String> logins) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String login : logins) {
                escritor.write(login);
                escritor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
