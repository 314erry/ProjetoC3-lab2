package usuarios;

import java.util.Scanner;
import turma.Turma;

public class Professor extends Usuario {
    private String emailInstitucional;
    private Turma turma;
    public static final String LINHA = "~~~~~~~~~~~~~~~~~~~~~~~~"; // Constante para separar o que foi imprimido com uma linha.

    // Construtores
    public Professor(String nome, String senha, String emailInstitucional) {
        super(nome, senha);
        this.emailInstitucional = emailInstitucional;
        this.turma = new Turma();
    }

    public Professor() {
        super();
        this.turma = new Turma();
    }

    // Métodos
    private void cadastrarAlunoNaTurma(Scanner input) {
        input.nextLine();
        System.out.println(LINHA);
        System.out.print("Nome do Aluno: ");
        String nome = input.nextLine();
        System.out.print("Matrícula: ");
        int matricula = input.nextInt();


        if (turma.buscarMatriculaExistente(matricula)) {
            System.out.println("\u001B[31mAluno já cadastrado na turma!\u001B[0m");
        } else {
            Aluno aluno = new Aluno(nome, matricula); // Criar um novo aluno com as informações fornecidas
            turma.cadastrarAluno(aluno); // Adicionar aluno à turma
        }
        System.out.println(LINHA);
    }

    private void excluirAlunoDaTurma(Scanner input) {
        input.nextLine();
        System.out.println(LINHA);
        System.out.print("Matrícula: ");
        int matricula = input.nextInt();

        // Excluir aluno da turma
        turma.excluirAluno(matricula);
        System.out.println(LINHA);
    }

    private void inserirNotaAluno(Scanner input) {
        System.out.println(LINHA);
        System.out.print("Matrícula do Aluno: ");
        int matricula = input.nextInt();

        System.out.print("Nota 1: ");
        double nota1 = input.nextDouble();
        System.out.print("Nota 2: ");
        double nota2 = input.nextDouble();

        turma.inserirNota(matricula, nota1, nota2); // Chama o método da Turma para inserir notas
        System.out.println(LINHA);
    }

    private void alterarNotaAluno(Scanner input) {
        System.out.println(LINHA);
        System.out.print("Matrícula do Aluno: ");
        int matricula = input.nextInt();
        System.out.println(LINHA);

        int opcao;
        System.out.print("Qual nota deseja alterar?\n1. Nota 1\n2. Nota 2\n>>> ");
        opcao = input.nextInt();
        System.out.println(LINHA);
        
        try {
            if (opcao == 1) {
                System.out.print("Insira a nova Nota 1: ");
                double nota1 = input.nextDouble();
                turma.alterarNota1(matricula, nota1);
                System.out.println(LINHA);
            } else if (opcao == 2) {
                System.out.print("Insira a nova Nota 2: ");
                double nota2 = input.nextDouble();
                turma.alterarNota2(matricula, nota2);
                System.out.println(LINHA);
            } else {
                System.out.println("\u001B[31mDigite uma opção válida\u001B[0m");
                System.out.println(LINHA);
            }
        } catch (Exception e) {
            System.out.println("\u001B[Insira um número inteiro.\u001B[0m");
            System.out.println(LINHA);
            input.nextLine(); // Consumir a entrada inválida
        }
    }

    private void inserirFaltas(Scanner input) {
        System.out.println(LINHA);
        System.out.print("Matrícula do Aluno: ");
        int matricula = input.nextInt();

        System.out.print("Número de Faltas: ");
        int faltas = input.nextInt();

        turma.inserirFaltas(matricula, faltas); // Chama o método da Turma para inserir faltas
        System.out.println(LINHA);
    }

    // Sobreescrita dos métodos abstratos de Usuário 
    @Override // Sobrescrever
    public void menu(Scanner input) {
        int opcao = 0;
        do {
            try {
                System.out.println("Bem vindo, " + getNome()+ "! O que deseja fazer?");
                System.out.println("1. Cadastrar Aluno na Turma");
                System.out.println("2. Excluir Aluno da Turma");
                System.out.println("3. Inserir Notas");
                System.out.println("4. Alterar Nota(s)");
                System.out.println("5. Consultar Notas e Médias");
                System.out.println("6. Inserir Faltas");
                System.out.println("7. Consultar Faltas e Frequência");
                System.out.println("8. Salvar Dados e Sair");
                System.out.print(">>> ");
                opcao = input.nextInt();
                
                switch (opcao) {
                    case 1:
                        cadastrarAlunoNaTurma(input);
                        break;
                    case 2:
                        excluirAlunoDaTurma(input);
                        break;
                    case 3:
                        inserirNotaAluno(input);
                        break;
                    case 4:
                        alterarNotaAluno(input);
                        break;
                    case 5:
                        consultarNota();
                        break;
                    case 6:
                        inserirFaltas(input);
                        break;
                    case 7:
                        consultarFrequencia();
                        break;
                    case 8:
                        Turma.salvarAlunos(); // Muda os dados antigos dos alunos, e os salva agora alterados (ou não)
                        System.out.println("\u001B[32mSalvando Dados e Saindo...\u001B[0m");
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
        } while (opcao != 8);
    }

    @Override
    public void consultarNota() {
        System.out.println(LINHA);
        turma.consultarNotaTurma();  
        System.out.println(LINHA);  
    }

    @Override
    public void consultarFrequencia() {
        System.out.println(LINHA);
        turma.consultarFrequenciaTurma();
        System.out.println(LINHA);
    }

    @Override
    public String toString() {
        return "Email Institucional = " + emailInstitucional + "]";
    }
}
