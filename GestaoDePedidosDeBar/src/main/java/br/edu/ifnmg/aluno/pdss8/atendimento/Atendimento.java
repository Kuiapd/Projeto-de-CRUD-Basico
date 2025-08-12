/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.aluno.pdss8.atendimento;

import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import br.edu.ifnmg.aluno.pdss8.comanda.ComandaRepository;
import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import br.edu.ifnmg.aluno.iro.pedido.PedidoRepository;
import br.edu.ifnmg.aluno.iro.mesa.Mesa;
import br.edu.ifnmg.aluno.iro.mesa.MesaRepository;
import br.edu.ifnmg.aluno.pdss8.funcionario.Funcionario;
/**
 *
 * @author PABLO DANIEL
 */

public class Atendimento {

    private final ComandaRepository comandaRepo = new ComandaRepository();
    private final PedidoRepository pedidoRepo = new PedidoRepository();
    private final MesaRepository mesaRepo = new MesaRepository();

    public void registrarPedido(Comanda comanda, Pedido pedido, Funcionario funcionario) {
        pedido.setFuncionario(funcionario);
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

        Mesa mesa = comanda.getMesa();
        mesa.setOcupada(false);
        mesaRepo.saveOrUpdate(mesa);

        comandaRepo.delete(comanda);
    }

    public Comanda abrirComanda(Mesa mesa, Funcionario funcionario) {
        mesa.setOcupada(true);
        mesaRepo.saveOrUpdate(mesa);

        Comanda comanda = new Comanda();
        comanda.setMesa(mesa);
        comanda.setFuncionario(funcionario);
        comandaRepo.saveOrUpdate(comanda);

        return comanda;
    }
}
