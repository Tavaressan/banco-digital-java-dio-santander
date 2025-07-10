package entities;

import entities.contas.Conta;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma instituição bancária.
 * Mantém uma lista de todas as contas associadas ao banco.
 */
@Getter
@Setter
public class Banco {
    /** Nome da instituição bancária */
    private String nome;

    /** Lista de contas mantidas pelo banco */
    private List<Conta> contas = new ArrayList<>();

    /**
     * Construtor que cria um novo banco.
     * @param nome O nome do banco
     */
    public Banco(String nome) {
        this.nome = nome;
    }

    /**
     * Adiciona uma nova conta à lista de contas do banco.
     * @param conta A conta a ser adicionada
     */
    public void adicionaConta(Conta conta) {
        contas.add(conta);
    }

    public void mostrarClientes() {
        contas.forEach(c -> System.out.println(c.getCliente()));
    }
}
