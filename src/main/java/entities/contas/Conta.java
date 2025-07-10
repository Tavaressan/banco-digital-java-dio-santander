package entities.contas;

import entities.Banco;
import entities.Cliente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Classe abstrata que representa uma conta bancária genérica.
 * Implementa as operações básicas definidas na interface {@link IConta}.
 * Esta classe é selada (sealed) e permite apenas {@link ContaPoupanca} e {@link ContaCorrente} como subclasses.
 */
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public sealed abstract class Conta implements IConta permits ContaPoupanca, ContaCorrente {
    /** Banco ao qual a conta pertence */
    protected Banco banco;

    /** Cliente titular da conta */
    @Getter
    protected Cliente cliente;

    /** Número da agência da conta */
    protected int numeroAgencia;

    /** Número único da conta */
    @Getter
    @EqualsAndHashCode.Include
    protected int numeroConta;

    /** Saldo atual da conta */
    protected double saldo;

    /** Número padrão da agência para todas as contas */
    private static final int AGENCIA_PADRAO = 1;

    /** Contador para gerar números únicos de conta */
    private static int SEQUENCIAL = 1;

    /** Valor inicial do saldo para novas contas */
    private static final double SALDO_INICIAL = 0;

    /** Banco padrão para novas contas */
    private static final Banco BANCO_PADRAO = new Banco("Standard");

    /**
     * Construtor que cria uma nova conta para um cliente.
     * @param cliente O cliente titular da conta
     */
    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.numeroAgencia = AGENCIA_PADRAO;
        this.numeroConta = SEQUENCIAL++;
        this.saldo = SALDO_INICIAL;
        //TO-DO: incluir conta na lista de contas do banco no momento de sua instanciação
        this.banco = BANCO_PADRAO;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException se o valor for negativo
     */
    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }
        this.saldo += valor;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException se o saldo for insuficiente ou o valor for negativo
     */
    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo");
        }
        if (saldo >= valor) {
            this.saldo -= valor;
        } else {
            throw new IllegalArgumentException("Erro: saldo insuficiente.");
        }
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException se a conta destino for nula ou o valor for inválido
     */
    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (contaDestino == null) {
            throw new IllegalArgumentException("Conta destino não pode ser nula");
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void imprimirExtrato() {
        System.out.println(
                "Nome: " + cliente.getNome() + '\'' +
                "Banco: " + banco.getNome() + '\'' +
                ", Número da Agencia: " + numeroAgencia + '\'' +
                ", Número da Conta: " + numeroConta + '\'' +
                ", saldo: R$" + saldo + '\n'
        );
    }
}
