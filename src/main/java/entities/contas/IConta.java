package entities.contas;

/**
 * Interface que define o contrato para implementação de contas bancárias.
 * Define as operações básicas que todas as contas devem possuir.
 */
public interface IConta {

    /**
     * Realiza um depósito na conta.
     * @param valor O valor a ser depositado
     */
    void depositar(double valor);

    /**
     * Realiza um saque na conta, se houver saldo disponível.
     * @param valor O valor a ser sacado
     */
    void sacar(double valor);

    /**
     * Realiza uma transferência de valor desta conta para outra conta.
     * @param valor O valor a ser transferido
     * @param contaDestino A conta que receberá a transferência
     */
    void transferir(double valor, IConta contaDestino);

    /**
     * Imprime o extrato da conta com suas informações e saldo.
     */
    void imprimirExtrato();
}
