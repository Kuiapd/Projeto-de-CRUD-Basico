/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.aluno.pdss8.atendimento;

import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import br.edu.ifnmg.aluno.pdss8.comanda.ComandaRepository;
import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import br.edu.ifnmg.aluno.iro.pedido.PedidoRepository;

/**
 *
 * @author PABLO DANIEL
 */
public class Atendimento {

    private final ComandaRepository comandaRepo = new ComandaRepository();
    private final PedidoRepository pedidoRepo = new PedidoRepository();

    public void registrarPedido(Comanda comanda, Pedido pedido) {
        comanda.adicionarPedido(pedido);
        pedido.setComanda(comanda);

        pedidoRepo.saveOrUpdate(pedido);
        comandaRepo.saveOrUpdate(comanda);
    }

    public double fecharComanda(Comanda comanda) {
        Comanda c = comandaRepo.findById(comanda.getId());
        return c.calcularTotal();
    }

    public void pagarComanda(Comanda comanda) {
        comanda.setPago(true);
        comandaRepo.saveOrUpdate(comanda);

        // Deleta a comanda do banco ao pagar
        comandaRepo.delete(comanda);
    }
}