package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CadastrarFormaPagamentoService cadastrarFormaPagamentoService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;
    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CadastrarProdutoService cadastrarProdutoService;

    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository
                .findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));

    }

    @Transactional
    public Pedido emitirPedido(Pedido pedido) {
//        pedidoRepository.detach(pedido);

        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastrarProdutoService.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId()
            );
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    private void validarPedido(Pedido pedido) {
        var cidade = cadastroCidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        var cliente = cadastroUsuarioService.buscarOuFalhar(pedido.getCliente().getId());
        var restaurante = cadastroRestauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
        var formaPagamento = cadastrarFormaPagamentoService
                .buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)){
            throw new NegocioException(String.format("Forma de pagamento '%s' " +
                    "não é aceita por esse restaurante.", formaPagamento.getDescricao()));
        }

    }
}
