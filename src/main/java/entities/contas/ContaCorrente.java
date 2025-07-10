package entities.contas;

import entities.Cliente;

public non-sealed class ContaCorrente extends Conta{

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("<=== Extrato Conta Corrente ===>");
        super.imprimirExtrato();
    }
}
