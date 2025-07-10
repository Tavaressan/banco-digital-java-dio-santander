package entities.contas;

import entities.Cliente;

/**
 * Classe que representa uma conta corrente.
 * Estende a classe {@link Conta} e é non-sealed, permitindo futuras extensões.
 */
public non-sealed class ContaCorrente extends Conta {

    /**
     * Construtor que cria uma nova conta corrente.
     * @param cliente O cliente titular da conta corrente
     */
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    /**
     * {@inheritDoc}
     * Sobrescreve o método para adicionar cabeçalho específico de conta corrente
     */
    @Override
    public void imprimirExtrato() {
        System.out.println("<=== Extrato Conta Corrente ===>");
        super.imprimirExtrato();
    }
}
