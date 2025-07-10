package entities.contas;

import entities.Cliente;

public non-sealed class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("<=== Extrato Conta Poupança ===>");
        super.imprimirExtrato();
    }
}
