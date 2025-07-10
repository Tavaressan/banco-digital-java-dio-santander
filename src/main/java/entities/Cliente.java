package entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe que representa um cliente do banco.
 * Utiliza Lombok para geração automática de getters, setters e métodos utilitários.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
    /** Nome do cliente, usado como identificador único */
    @EqualsAndHashCode.Include
    private String nome;

    /**
     * Construtor que cria um novo cliente.
     * @param nome O nome do cliente
     */
    public Cliente(String nome) {
        this.nome = nome;
    }
}
