package usuarios;

import java.util.Scanner;

public class Aluno extends Usuario {
    private int matricula;
    private double nota1;
    private double nota2;
    private double media;
    private int numFalta;
    private int coeficienteFrequencia;
    public static final String LINHA = "~~~~~~~~~~~~~~~~~~~~~~~~"; // Constante para separar o que foi imprimido com uma linha.

    // Construtor para instanciar um novo aluno e escrevê-lo no arquivo turma.txt
    public Aluno(String nome, int matricula, double nota1, double nota2, double media, int numFalta, int coeficienteFrequencia) {
        super(nome);
        this.matricula = matricula;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.media = media;
        this.numFalta = numFalta;
        this.coeficienteFrequencia = coeficienteFrequencia;
    }

    // Construtor para cadastro de aluno pelo sistema (com senha)
    public Aluno(String nome, String senha, int matricula) {
        super(nome, senha);
        this.matricula = matricula;
        this.nota1 = 0.0;
        this.nota2 = 0.0;
        this.media = 0.0;
        this.numFalta = 0;
        this.coeficienteFrequencia = 100;
    }

    // Construtor para cadastro de aluno na Turma (sem senha)
    public Aluno(String nome, int matricula) {
        super(nome);
        this.matricula = matricula;
        this.nota1 = 0.0;
        this.nota2 = 0.0;
        this.media = 0.0;
        this.numFalta = 0;
        this.coeficienteFrequencia = 100;
    }

    // Sobreescrita dos métodos abstratos de Usuário 
    @Override
    public void menu(Scanner input) {
        int opcao = 0;
        do {
            try {
                System.out.println("Olá, " + getNome() + "! O que deseja fazer?");
                System.out.print("1. Consultar Notas\n2. Consultar Frequência\n3. Sair da Conta\n>>> ");
                opcao = input.nextInt();

                switch (opcao) {
                    case 1:
                        consultarNota();
                        break;
                    case 2:
                        consultarFrequencia();
                        break;
                    case 3:
                        System.out.println("\u001B[32mSaindo...\u001B[0m");
                        System.out.println(LINHA);
                        break;
                    default:
                        System.out.println("\u001B[31mOpção inválida!\u001B[0m");
                        System.out.println(LINHA);
                }
            } catch (Exception e) {
                System.out.println("\u001B[31mInsira um número inteiro.\u001B[0m");
                System.out.println(LINHA);
                input.nextLine(); // Consumir a entrada inválida
            }
        } while (opcao != 3);
    }

    @Override
    public void consultarNota() {
        String mensagemAprovaçao = "";
        if (this.media < 6) {
            mensagemAprovaçao = "\u001B[31m [REPROVADO(A)!]\u001B[0m";
        } else {
            mensagemAprovaçao = "\u001B[32m [APROVADO(A)!]\u001B[0m";
        }

        System.out.println(LINHA);
        System.out.println("Boletim de " + getNome() + ":");
        System.out.println("Nota 1: " + getNota1());
        System.out.println("Nota 2: " + getNota2());
        System.out.println("Média: " + getMedia() + mensagemAprovaçao);
        System.out.println(LINHA);
    }

    @Override
    public void consultarFrequencia() {
        System.out.println(LINHA);
        System.out.println("Frequência de " + getNome() + ":");
        System.out.println("Número de Faltas: " + getNumFalta());
        System.out.println("Coeficiente de Frequência: " + getCoeficienteFrequencia() + "%");
        System.out.println(LINHA);
    }

    // Getter e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public int getNumFalta() {
        return numFalta;
    }

    public void setNumFalta(int numFalta) {
        this.numFalta = numFalta;
    }

    public int getCoeficienteFrequencia() {
        return coeficienteFrequencia;
    }

    public void setCoeficienteFrequencia(int coeficienteFrequencia) {
        this.coeficienteFrequencia = coeficienteFrequencia;
    }

    @Override
    public String toString() {
        return "Matricula = " + matricula + ", Nota 1 = " + nota1 + ", Nota 2 = " + nota2 + ", Média = " + media
                + ", Faltas = " + numFalta + ", Coeficiente de Frequencia = " + coeficienteFrequencia + "]";
    }
}
