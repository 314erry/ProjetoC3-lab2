package turma;

import arquivos.ManipularArquivos;
import usuarios.Aluno;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private static List<Aluno> turma = new ArrayList<>();

    // Inicializa o vetor turma com objetos de Aluno retornado pelo método carregarAluno()
    static {
        turma = carregarAlunos();
    }

    // Retorna uma list de Aluno e atribui para turma.
    public static List<Aluno> carregarAlunos() {
        return turma = ManipularArquivos.carregarAlunos("src/arquivos/turma.txt");
    }

    //Chama salvarAluno da classe ManipularArquivos e salva o vetor turma com seus objetos no arquivo turma.txt
    public static void salvarAlunos() {
        ManipularArquivos.salvarAlunos("src/arquivos/turma.txt", turma);
    }

    // Métodos
    public void cadastrarAluno(Aluno aluno) {
        turma.add(aluno);
        System.out.println("\u001B[32mAluno cadastrado na turma com sucesso!\u001B[0m");
    }

    public void excluirAluno(int matricula) {
        Aluno alunoParaRemover = null;
        for (Aluno aluno : turma) {
            if (aluno.getMatricula() == matricula) {
                alunoParaRemover = aluno;
                break;
            }
        }
        if (alunoParaRemover != null) {
            turma.remove(alunoParaRemover);
            System.out.println("\u001B[32mAluno removido com sucesso!\u001B[0m");
        } else {
            System.out.println("\u001B[31mAluno não encontrado!\u001B[0m");
        }
    }

    public void inserirNota(int matricula, double nota1, double nota2) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            aluno.setNota1(nota1);
            aluno.setNota2(nota2);
            aluno.setMedia((nota1 + nota2) / 2);
            System.out.println("\u001B[32mNotas inseridas com sucesso para o aluno: " + aluno.getNome() + "\u001B[0m");
        } else {
            System.out.println("\u001B[31mAluno não encontrado!\u001B[0m");
        }
    }

    public void alterarNota1(int matricula, double nota1) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            aluno.setNota1(nota1);
            aluno.setMedia((nota1 + aluno.getNota2()) / 2);
            System.out.println("\u001B[32mNota 1 alterada com sucesso para o aluno: " + aluno.getNome() + "\u001B[0m");
        } else {
            System.out.println("\u001B[31mAluno não encontrado!\u001B[0m");
        }
    }

    public void alterarNota2(int matricula, double nota2) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            aluno.setNota2(nota2);
            aluno.setMedia((aluno.getNota1() + nota2) / 2);
            System.out.println("\u001B[32mNota 2 alterada com sucesso para o aluno: " + aluno.getNome() + "\u001B[0m");
        } else {
            System.out.println("\u001B[31mAluno não encontrado!\u001B[0m");
        }
    }
    
    public static Aluno buscarAlunoPorMatricula(int matricula) {
        for (Aluno aluno : turma) {
            if (aluno.getMatricula() == matricula) {
                return aluno;
            }
        }
        return null;
    }

    public boolean buscarMatriculaExistente(int matricula) {
        for (Aluno aluno : turma) {
            if (aluno.getMatricula() == matricula) {
                return true; // Matrícula já existe na turma
            }
        }
        return false; // Matrícula não encontrada
    }

    public void inserirFaltas(int matricula, int numFaltas) {
        Aluno aluno = buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            aluno.setNumFalta(numFaltas);
            // Supondo que as aulas totais sejam 100 para simplificação do cálculo do coeficiente de frequência
            aluno.setCoeficienteFrequencia(100 - numFaltas);
            System.out.println("\u001B[32mFaltas inseridas com sucesso para o aluno: " + aluno.getNome()+"\u001B[0m");
        } else {
            System.out.println("\u001B[31mAluno não encontrado!\u001B[0m");
        }
    }

    public void consultarNotaTurma() {
        if (turma.size() == 0) {
            System.out.println("\\u001B[31mNão há alunos cadastrados na turma.\u001B[0m");
        } else {
            double mediaGeral = 0;
            for (Aluno aluno : turma) {
                mediaGeral = mediaGeral + aluno.getMedia();
            }

            mediaGeral = mediaGeral / turma.size();
            String mensagemAprovaçao = "";
            System.out.println("Média Geral da Turma: " + mediaGeral);
            for (Aluno aluno : turma) {
                if (aluno.getMedia() < 6) {
                    mensagemAprovaçao = "\u001B[31m [REPROVADO(A)!]\u001B[0m";
                } else {
                    mensagemAprovaçao = "\u001B[32m [APROVADO(A)!]\u001B[0m";
                }
                System.out.println(aluno.getMatricula() + " - " + aluno.getNome() + " - Nota 1: " + aluno.getNota1() + " | Nota 2: " + aluno.getNota2() + " | Média: " + aluno.getMedia() + mensagemAprovaçao);
            }
        }
    }

    public void consultarFrequenciaTurma() {
        if (turma.size() == 0) {
            System.out.println("\u001B[31mNão há alunos cadastrados na turma.\u001B[0m");
        } else {
            System.out.println("Lista de Frequencia da Turma:");
            for (Aluno aluno : turma) {
                System.out.println(aluno.getMatricula() + " - " + aluno.getNome() + " - Faltas: " + aluno.getNumFalta() + " | Frequência: " + aluno.getCoeficienteFrequencia() + "%");
            }
        }
    }
}