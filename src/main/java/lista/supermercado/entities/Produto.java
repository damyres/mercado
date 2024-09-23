package lista.supermercado.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PRODUTOS")
public class Produto  implements Serializable {

    @Id
    private UUID id;

    private String nome;

    private String descricao;

    private Double preco;


}
