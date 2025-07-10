package entities.contas;

import entities.Cliente;

/**
 * Classe que representa uma conta poupança.
 * Estende a classe {@link Conta} e é non-sealed, permitindo futuras extensões.
 * Esta conta é destinada a guardar economias e potencialmente receber rendimentos.
 */
public non-sealed class ContaPoupanca extends Conta {

    /**
     * Construtor que cria uma nova conta poupança.
     * @param cliente O cliente titular da conta poupança
     */
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    /**
     * {@inheritDoc}
     * Sobrescreve o método para adicionar cabeçalho específico de conta poupança
     */
    @Override
    public void imprimirExtrato() {
        System.out.println("<=== Extrato Conta Poupança ===>");
        super.imprimirExtrato();
    }
}
