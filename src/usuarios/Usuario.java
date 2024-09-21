package usuarios;

import java.util.Scanner;

public abstract class Usuario {
    private String nome;
    private String senha;

    // Construtor
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario() {}
    
    // Métodos abstratos
    public abstract void menu(Scanner input); // Recebe um Scanner como paramêtro para não ter que instanciar fora da Main
    public abstract void consultarNota();
    public abstract void consultarFrequencia();

    // Getter e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
