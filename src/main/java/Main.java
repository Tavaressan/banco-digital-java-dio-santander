import entities.Banco;
import entities.Cliente;
import entities.contas.Conta;
import entities.contas.ContaCorrente;
import entities.contas.ContaPoupanca;
import java.util.Scanner;

public class Main {
    private static final Banco BANCO_PADRAO = new Banco("Standard");
    public static Conta contaAcessada = null;
    public static Scanner scanner = new Scanner(System.in);
    public static boolean menuAtivo = true;
    public static int opcaoMenu;
    public static int opcaoConta;


    public static void main(String[] args) {

        do {
            mostrarMenu();
            opcaoMenu = scanner.nextInt();
            switch (opcaoMenu) {
                case 1 -> {
                    System.out.println("Qual tipo de conta você quer criar? \n 1 - Corrente / 2 - Poupança\n");
                    opcaoConta = scanner.nextInt();
                    scanner.nextLine(); // limpa o buffer
                    switch (opcaoConta) {
                        case 1 -> {
                            System.out.println("Qual é o nome do cliente?");
                            String nomeCliente = scanner.nextLine();
                            Cliente cliente = new Cliente(nomeCliente);
                            ContaCorrente contaCorrente = new ContaCorrente(cliente);
                            BANCO_PADRAO.adicionaConta(contaCorrente);
                            System.out.printf("Conta Corrente criada com sucesso para %s \nNúmero da conta: %s%n\n", nomeCliente, contaCorrente.getNumeroConta());
                        }
                        case 2 -> {
                            System.out.println("Qual é o nome do cliente?");
                            String nomeCliente = scanner.nextLine();
                            Cliente cliente = new Cliente(nomeCliente);
                            ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);
                            BANCO_PADRAO.adicionaConta(contaPoupanca);
                            System.out.printf("Conta Poupança criada com sucesso para %s \nNúmero da conta: %s%n\n", nomeCliente, contaPoupanca.getNumeroConta());
                        }
                    }
                }
                case 2 -> {
                    contaAcessada = buscarConta();
                    if (contaAcessada == null) throw new IllegalArgumentException("erro: Conta inexistente.");
                    System.out.printf("Acesso concluído \nBem vindo(a) %s%n", contaAcessada.getCliente());
                }
                case 3 -> {
                    verificaAcesso();
                    System.out.println("Quanto dinheiro para depósito?");
                    double valorDeposito = scanner.nextDouble();
                    contaAcessada.depositar(valorDeposito);
                    System.out.printf("Depósito de R$ %.2f feito com sucesso.\n", valorDeposito);
                }
                case 4 -> {
                    verificaAcesso();
                    System.out.println("Quanto dinheiro para sacar?");
                    double valorSaque = scanner.nextDouble();
                    contaAcessada.sacar(valorSaque);
                    System.out.printf("Saque de R$ %.2f feito com sucesso.\n", valorSaque);
                }
                case 5 -> {
                    verificaAcesso();
                    System.out.println("Para que conta você quer transferir?");
                    Conta contaDestino = buscarConta();
                    if (contaDestino == null) throw new IllegalArgumentException("erro: Conta inexistente.");
                    if (contaDestino == contaAcessada) throw new IllegalArgumentException("erro: Você não pode transerir valores para a mesma conta");
                    System.out.println("Quanto dinheiro para transferir?");
                    double valorTransferencia = scanner.nextDouble();
                    contaAcessada.transferir(valorTransferencia, contaDestino);
                    System.out.printf("Transferência de R$ %.2f efetuada com sucesso para conta %d.\n", valorTransferencia, contaDestino.getNumeroConta());
                }
                case 6 -> {
                    verificaAcesso();
                    contaAcessada.imprimirExtrato();
                }
                case 7 -> BANCO_PADRAO.mostrarClientes();

                case 0 -> {
                    menuAtivo = false;
                    System.out.println("Você saiu do menu. \nAté a próxima!");
                }

                default -> System.out.println("Opção inválida\n");
            }
        } while (menuAtivo);
    }

    private static void mostrarMenu () {
        System.out.println("1 - Criar conta");
        System.out.println("2 - Acessar conta");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Imprimir Extrato");
        System.out.println("0 - Sair");
        System.out.println("\n<===Extra===>");
        System.out.println("7 - Mostrar Clientes");
    }

    // TO-DO: Implementar verificação por senha
    private static Conta buscarConta () {
        System.out.println("Insira o número da conta: ");
        int numeroConta = scanner.nextInt();

        return BANCO_PADRAO.getContas().stream()
                .filter(c -> numeroConta == c.getNumeroConta())
                .findFirst()
                .orElse(null);
    }

    private static void verificaAcesso () {
        if (contaAcessada == null) throw new IllegalStateException("erro: Nenhuma conta acessada.");
    }
}
