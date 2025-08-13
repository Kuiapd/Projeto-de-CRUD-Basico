package br.edu.ifnmg.aluno.iro.main;

import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapio;
import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapioRepository;
import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import br.edu.ifnmg.aluno.pdss8.comanda.ComandaRepository;
import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import br.edu.ifnmg.aluno.iro.pedido.PedidoRepository;

import java.util.List;

public class Program {

    public static void runSystemBar() {
        // Criação dos repositórios
        /*ComandaRepository comandaRepo = new ComandaRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();
        ItemCardapioRepository itemRepo = new ItemCardapioRepository();

        System.out.println("=== CRIANDO ITENS DO CARDÁPIO ===");
        ItemCardapio pizza = new ItemCardapio();
        pizza.setNome("Pizza Calabresa");
        pizza.setPreco(45.0);
        itemRepo.saveOrUpdate(pizza);

        ItemCardapio refri = new ItemCardapio();
        refri.setNome("Refrigerante");
        refri.setPreco(8.0);
        itemRepo.saveOrUpdate(refri);

        System.out.println("Itens do cardápio salvos.");

        System.out.println("\n=== CRIANDO COMANDAS (MESAS) ===");
        Comanda comanda1 = new Comanda();
        comanda1.setNumeroMesa(1);
        comanda1.setPago(false);
        comandaRepo.saveOrUpdate(comanda1);

        Comanda comanda2 = new Comanda();
        comanda2.setNumeroMesa(2);
        comanda2.setPago(false);
        comandaRepo.saveOrUpdate(comanda2);

        System.out.println("Comandas criadas e salvas.");

        System.out.println("\n=== CRIANDO PEDIDOS E ASSOCIANDO ÀS COMANDAS ===");

        // Pedido para comanda "1"
        Pedido pedido1 = new Pedido();
        pedido1.setComanda("1");  // comanda como string
        pedidoRepo.saveOrUpdate(pedido1);

        comanda1.adicionarPedido(pedido1);
        comandaRepo.saveOrUpdate(comanda1);

        // Pedido para comanda "2"
        Pedido pedido2 = new Pedido();
        pedido2.setComanda("2");  // comanda como string
        pedidoRepo.saveOrUpdate(pedido2);

        comanda2.adicionarPedido(pedido2);
        comandaRepo.saveOrUpdate(comanda2);

        System.out.println("Pedidos criados e associados às comandas.");

        System.out.println("\n=== LISTANDO PEDIDOS DA COMANDA 1 ===");
        List<Pedido> pedidosComanda1 = pedidoRepo.findByComanda("1");
        for (Pedido p : pedidosComanda1) {
            System.out.println(p);
        }

        System.out.println("\n=== LISTANDO COMANDAS NÃO PAGAS ===");
        List<Comanda> comandasNaoPagas = comandaRepo.findByPago(false);
        for (Comanda c : comandasNaoPagas) {
            System.out.println("Mesa " + c.getNumeroMesa() + " - Pago? " + c.isPago());
        }

        System.out.println("\n=== FECHANDO COMANDA DA MESA 1 ===");
        comanda1.setPago(true);
        comandaRepo.saveOrUpdate(comanda1);
        System.out.println("Comanda da mesa 1 agora está paga.");

        System.out.println("\n=== LISTANDO COMANDAS PAGAS ===");
        List<Comanda> comandasPagas = comandaRepo.findByPago(true);
        for (Comanda c : comandasPagas) {
            System.out.println("Mesa " + c.getNumeroMesa() + " - Pago? " + c.isPago());
        }*/
    }

    public static void main(String[] args) {
        runSystemBar();
    }
}
