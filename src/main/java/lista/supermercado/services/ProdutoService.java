package lista.supermercado.services;

import lista.supermercado.dtos.ProdutoDTO;
import lista.supermercado.entities.Produto;
import lista.supermercado.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.GenerationType.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto save(final Produto produto) {
        return produtoRepository.save(produto);
    }


    public List<ProdutoDTO> findAll() {
        final var listaProduto = produtoRepository.findAll();
        final var listaProdutoDto = listaProduto.stream().map(produto -> new ProdutoDTO(produto.getDescricao(), produto.getPreco())).toList();
        return listaProdutoDto;
    }

    public List<ProdutoDTO> listFIndAll() {
        final var listarProduto = produtoRepository.findAll();
        return listarProduto.stream().map(ProdutoDTO::new).toList();
    }

    public List<ProdutoDTO> findAllNome() {
        final var listaProduto = produtoRepository.findAll();
        final var listaProdutoDto = listaProduto.stream().map(produto -> new ProdutoDTO(produto.getNome(), produto.getDescricao(), produto.getPreco())).toList();
        return listaProdutoDto;
    }

    public ProdutoDTO update(final Produto produto) {
        final var produtoDaBase = produtoRepository.findById(produto.getId());
        if (produtoDaBase.isEmpty()) {
            throw new RuntimeException("Produto não encontado.");
        }
        produtoDaBase.get().setNome(produto.getNome());
        produtoDaBase.get().setDescricao(produto.getDescricao());
        produtoDaBase.get().setPreco(produto.getPreco());
        produtoDaBase.get().setQuantidade(produto.getQuantidade());
        final var produtoAtualizado =  produtoRepository.saveAndFlush(produtoDaBase.get());
        return new ProdutoDTO(produtoAtualizado);
    }

    public void deleteById(UUID id){
        produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontado."));
        produtoRepository.deleteById(id);
    }

}
