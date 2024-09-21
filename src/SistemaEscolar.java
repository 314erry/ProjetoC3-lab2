import java.util.Scanner;

import cadastro.CadastroAluno;
import usuarios.Aluno;
import usuarios.Professor;
import login.LoginAluno;
import login.LoginProfessor;
import turma.Turma;

public class SistemaEscolar {
    static Scanner input = new Scanner(System.in);
    public static final String LINHA = "~~~~~~~~~~~~~~~~~~~~~~~~"; // Constante para separar o que foi imprimido com uma linha.
    public static void main(String[] args) {
        int opcao = 0;
        // Menu Principal
        do {
            try {
                System.out.println(LINHA);
                System.out.println("Escolha seu tipo de usuário:");
                System.out.print("1. Professor(a)\n2. Aluno(a)\n3. Finalizar Programa\n>>> ");
                opcao = input.nextInt();
                input.nextLine(); // Consumir a quebra de linha após o próximo int

                switch (opcao) {
                    case 1:
                        System.out.println(LINHA);
                        realizarLoginProfessor();
                        break;
                    case 2:
                        System.out.println(LINHA);
                        menuLoginCadastro();
                        break;
                    case 3:
                        
                        Turma.salvarAlunos(); // Sobreescreve os dados antigos dos alunos, e os salva agora alterados (ou não)
                        System.out.println("\u001B[32mSalvando Dados e Finalizando Programa...\u001B[0m");
                        break;
                    default:
                        System.out.println("\u001B[31mOpção inválida. Tente novamente.\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println("\u001B[31mInsira um número inteiro.\u001B[0m");
                input.nextLine(); // Consumir a entrada inválida
            }
        } while (opcao != 3);
    }

    // Realizr login como professor
    public static void realizarLoginProfessor() {
        System.out.print("Email Institucional: ");
        String email = input.next();
        System.out.print("Senha: ");
        String senha = input.next();

        if (LoginProfessor.verificarLogin(email, senha)) {
            Professor professor = new Professor("Professor da Silva", senha, email);
            System.out.println("\u001B[32mLogin efetuado com sucesso!\u001B[0m");
            System.out.println(LINHA);
            professor.menu(input);
        } else {
            System.out.println("\u001B[31mCredenciais inválidas. Tente novamente.\u001B[0m");
        }
    }

    // Menu de escolha entre login ou cadastro de aluno
    public static void menuLoginCadastro() {
        int opcao = 0;
        do {
            try {
                System.out.println("Olá, aluno(a), escolha uma das opções abaixo:");
                System.out.print("1. Login\n2. Cadastro\n3. Voltar pro menu inicial\n>>> ");
                opcao = input.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println(LINHA);
                        realizarLoginAluno();
                        break;
                    case 2:
                        System.out.println(LINHA);
                        realizarCadastroAluno();
                        break;
                    case 3:
                        System.out.println("\u001B[32mVoltando...\u001B[0m");
                        break;
                    default:
                        System.out.println("\u001B[31mOpção inválida. Tente novamente.\u001B[0m");
                        System.out.println(LINHA);
                }
            } catch (Exception e) {
                    System.out.println("\u001B[31mInsira um número inteiro.\u001B[0m");
                    System.out.println(LINHA);
                    input.nextLine(); // Consumir a entrada inválida
            }
        } while (opcao != 3);
    }

    // Realizar login como aluno
    public static void realizarLoginAluno() {
        System.out.print("Matrícula: ");
        int matricula = input.nextInt();
        System.out.print("Senha: ");
        String senha = input.next();

        if (LoginAluno.verificarLogin(matricula, senha)) {
            Aluno aluno = Turma.buscarAlunoPorMatricula(matricula);
            if (aluno != null) {
                System.out.println("\u001B[32mLogin efetuado com sucesso!\u001B[0m");
                System.out.println(LINHA);
                aluno.menu(input);
            } else {
                System.out.println("\u001B[31mAluno não encontrado na turma!\u001B[0m");
                System.out.println(LINHA);
            }
        } else {
            System.out.println("\u001B[31mCredenciais inválidas. Tente novamente.\u001B[0m");
            System.out.println(LINHA);
        }
    }

    // Cadastro no sistema como aluno
    public static void realizarCadastroAluno() {
        input.nextLine();
        System.out.print("Nome de Usuário: ");
        String usuario = input.nextLine();
        System.out.print("Senha: ");
        String senha = input.next();
        System.out.print("Matrícula: ");
        int matricula = input.nextInt();

        CadastroAluno.cadastrarAluno(usuario, matricula, senha);
        System.out.println(LINHA);
    }
}
