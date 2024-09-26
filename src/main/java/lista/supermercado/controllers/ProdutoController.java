package lista.supermercado.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lista.supermercado.dtos.ProdutoDTO;
import lista.supermercado.entities.Produto;
import lista.supermercado.exception.MercadoException;
import lista.supermercado.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    public final ProdutoService produtoService;

    @Autowired
    public ProdutoController(final ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/inserir")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produto> save(@RequestBody @Valid Produto produto) {
        var newProduto = new Produto();
        try {
            newProduto = produtoService.save(produto);
        } catch (DataIntegrityViolationException e) {
            throw new MercadoException(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(newProduto);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        final var list = produtoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/listarNome")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoDTO>> findAllNome() {
        try {
            final var listNome = produtoService.findAllNome();
            return ResponseEntity.ok().body(listNome);
        } catch (EntityNotFoundException exception) {
            System.out.println("Erro de requisão");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listFIndAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoDTO>> listFIndAll() {
        final var listNome = produtoService.listFIndAll();
        return ResponseEntity.ok().body(listNome);
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdutoDTO> update(@RequestBody Produto produto) {
        final var updateproduto = produtoService.update(produto);
        return ResponseEntity.ok().body(updateproduto);
    }

    @DeleteMapping("/deletar/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteById(@PathVariable UUID id){
        produtoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
