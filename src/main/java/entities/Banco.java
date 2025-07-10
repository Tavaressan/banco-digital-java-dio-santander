package entities;

import entities.contas.Conta;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Banco {
    private String nome;
    private List<Conta> contas = new ArrayList<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public void adicionaConta(Conta conta) {
        contas.add(conta);
    }

    public void mostrarClientes() {
        contas.forEach(c -> System.out.println(c.getCliente()));
    }
}
