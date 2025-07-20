package br.edu.ifnmg.aluno.iro.main;


import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapio;
import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapioRepository;
import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import br.edu.ifnmg.aluno.pdss8.comanda.ComandaRepository;
import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import br.edu.ifnmg.aluno.iro.pedido.PedidoRepository;
import java.util.List;
import java.util.Map;


public class Program {

    public static void runSystemBar() {
        // Criação dos repositórios
        ComandaRepository comandaRepo = new ComandaRepository();
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

        ItemCardapio cerveja = new ItemCardapio();
        cerveja.setNome("Cerveja");
        cerveja.setPreco(12.0);
        itemRepo.saveOrUpdate(cerveja);

        System.out.println("Itens do cardápio salvos.");

        System.out.println("\n=== CRIANDO COMANDAS (MESAS) ===");
        Comanda mesa1 = new Comanda();
        mesa1.setNumeroMesa(1);
        mesa1.setPago(false);
        comandaRepo.saveOrUpdate(mesa1);

        Comanda mesa2 = new Comanda();
        mesa2.setNumeroMesa(2);
        mesa2.setPago(false);
        comandaRepo.saveOrUpdate(mesa2);

        System.out.println("Comandas criadas e salvas.");

        System.out.println("\n=== CRIANDO PEDIDOS E ASSOCIANDO A COMANDAS ===");

        // Pedido para mesa 1
        Pedido pedido1 = new Pedido();
        pedido1.setComanda(mesa1);
        pedido1.adicionarItem(pizza, 2);
        pedido1.adicionarItem(refri, 3);
        pedidoRepo.saveOrUpdate(pedido1);

        mesa1.adicionarPedido(pedido1);
        comandaRepo.saveOrUpdate(mesa1);

        // Pedido para mesa 2
        Pedido pedido2 = new Pedido();
        pedido2.setComanda(mesa2);
        pedido2.adicionarItem(cerveja, 4);
        pedido2.adicionarItem(refri, 2);
        pedidoRepo.saveOrUpdate(pedido2);

        mesa2.adicionarPedido(pedido2);
        comandaRepo.saveOrUpdate(mesa2);

        System.out.println("Pedidos criados, associados e salvos.");

        System.out.println("\n=== MOSTRANDO PEDIDOS DA MESA 1 ===");
        Comanda c1 = comandaRepo.findByNumeroMesa(1);
        System.out.println("Mesa " + c1.getNumeroMesa() + ":");
        for (Pedido p : c1.getPedidos()) {
            System.out.println(" Pedido #" + p.getId());
            for (Map.Entry<ItemCardapio, Integer> entry : p.getItens().entrySet()) {
                System.out.println("  - " + entry.getKey().getNome() + ": " + entry.getValue() + "x R$" + entry.getKey().getPreco());
            }
            System.out.printf(" Subtotal pedido: R$ %.2f%n", p.calcularSubtotal());
        }
        System.out.printf("Total da mesa: R$ %.2f%n", c1.calcularTotal());

        System.out.println("\n=== BUSCANDO ITENS DO CARDÁPIO POR NOME (contém 'refri') ===");
        List<ItemCardapio> encontrados = itemRepo.findByNomeContaining("refri");
        for (ItemCardapio ic : encontrados) {
            System.out.println("Item encontrado: " + ic.getNome() + " - R$" + ic.getPreco());
        }

        System.out.println("\n=== LISTANDO COMANDAS NÃO PAGAS ===");
        List<Comanda> comandasNaoPagas = comandaRepo.findByPago(false);
        for (Comanda com : comandasNaoPagas) {
            System.out.println("Mesa " + com.getNumeroMesa() + " - Pago? " + com.isPago());
        }

        System.out.println("\n=== FECHANDO COMANDA DA MESA 1 ===");
        c1.setPago(true);
        comandaRepo.saveOrUpdate(c1);
        System.out.println("Mesa " + c1.getNumeroMesa() + " agora está paga.");

        System.out.println("\n=== LISTANDO COMANDAS PAGAS ===");
        List<Comanda> comandasPagas = comandaRepo.findByPago(true);
        for (Comanda com : comandasPagas) {
            System.out.println("Mesa " + com.getNumeroMesa() + " - Pago? " + com.isPago());
        }

        System.out.println("\n=== DELETANDO COMANDA PAGA DA MESA 1 ===");
        comandaRepo.delete(c1);
        System.out.println("Comanda da mesa 1 deletada.");

        System.out.println("\n=== LISTANDO TODAS AS COMANDAS APÓS DELEÇÃO ===");
        List<Comanda> todas = comandaRepo.findAll();
        for (Comanda com : todas) {
            System.out.println("Mesa " + com.getNumeroMesa() + " - Pago? " + com.isPago());
        }

        System.out.println("\n=== BUSCANDO PEDIDOS PELA COMANDA DA MESA 2 ===");
        List<Pedido> pedidosMesa2 = pedidoRepo.findByComandaId(mesa2.getId());
        for (Pedido p : pedidosMesa2) {
            System.out.println("Pedido #" + p.getId() + " da mesa " + p.getComanda().getNumeroMesa());
            for (Map.Entry<ItemCardapio, Integer> e : p.getItens().entrySet()) {
                System.out.println(" - " + e.getKey().getNome() + ": " + e.getValue());
            }
        }

        System.out.println("\n=== TESTE FINALIZADO ===");
    }
}
