package entities.contas;

import entities.Banco;
import entities.Cliente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public sealed abstract class Conta implements IConta permits ContaPoupanca, ContaCorrente {
    protected Banco banco;
    @Getter
    protected Cliente cliente;
    protected int numeroAgencia;
    @Getter
    @EqualsAndHashCode.Include
    protected int numeroConta;
    protected double saldo;
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    private static final double SALDO_INICIAL = 0;
    private static final Banco BANCO_PADRAO = new Banco("Standard");


    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.numeroAgencia = AGENCIA_PADRAO;
        this.numeroConta = SEQUENCIAL++;
        this.saldo = SALDO_INICIAL;
        //TO-DO: incluir conta na lista de contas do banco no momento de sua instanciação
        this.banco = BANCO_PADRAO;
    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        if (saldo >= valor && saldo > 0) this.saldo -= valor;
        else throw new IllegalArgumentException("erro: saldo insuficiente.");
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

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
