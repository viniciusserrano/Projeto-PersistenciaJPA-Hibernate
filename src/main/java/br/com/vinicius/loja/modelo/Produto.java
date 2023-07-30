package br.com.vinicius.loja.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

// Esta anotação serve para mostrar que essa classe é uma entidade da JPA, ou seja, se econtra no banco de dados;
@Entity
@Table(name = "produtos")// QUANDO O NOME DA TABELA NÃO É IGUAL O NOME DA CLASSE
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDate dataCadastro = LocalDate.now();
    //Categoria era um Enum e se eu deixar um enum sem a anotação @Enumerated(EnumType.STRING)
    //, ele vai salvar no banco de dados o enum como int, EX: 1 (seria Celulares), 2 3
    //Dessa maneira com enumType.string ele salva como varchar.OBS: enumType.Ordinal salvaria como int também
    //Como agora categoria virou uma classe a Jpa reconhece que é um relacionamento entre entidades
    //, Sendo assim eu preciso especificar a Cardinalidade desse relacionamento @ManyToOne ou o que precisar ser.
    //IMPORTANTE: Quando for persistir uma entidade vinculada a outra entidade é importante essa "outra" Entidade precisa estar persistida,
    //, exemplo do caso é a categoria seria essa "outra entidade" caso isso não acontecer vou receber a Exception TransientException
    @ManyToOne
    private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto() {

    }
}
