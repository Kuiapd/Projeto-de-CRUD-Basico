package br.edu.ifnmg.aluno.iro.main;

import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapio;
import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapioRepository;
import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import br.edu.ifnmg.aluno.pdss8.comanda.ComandaRepository;
import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import br.edu.ifnmg.aluno.iro.pedido.PedidoRepository;
import br.edu.ifnmg.aluno.pdss8.mesa.Mesa;
import br.edu.ifnmg.aluno.pdss8.mesa.MesaRepository;
import br.edu.ifnmg.aluno.pdss8.funcionario.Funcionario;
import br.edu.ifnmg.aluno.pdss8.funcionario.FuncionarioRepository;

import java.time.LocalDate;
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
        Comanda comanda1 = new Comanda();
        comanda1.setNumeroMesa(1);
        comanda1.setPago(false);
        comandaRepo.saveOrUpdate(comanda1);

        Comanda comanda2 = new Comanda();
        comanda2.setNumeroMesa(2);
        comanda2.setPago(false);
        comandaRepo.saveOrUpdate(comanda2);

        System.out.println("Comandas criadas e salvas.");

        System.out.println("\n=== CRIANDO PEDIDOS E ASSOCIANDO A COMANDAS ===");

        // Pedido para comanda 1
        Pedido pedido1 = new Pedido();
        pedido1.setComanda("1");
        pedidoRepo.saveOrUpdate(pedido1);

        comanda1.adicionarPedido(pedido1);
        comandaRepo.saveOrUpdate(comanda1);

        // Pedido para comanda 2
        Pedido pedido2 = new Pedido();
        pedido2.setComanda("2");
        pedidoRepo.saveOrUpdate(pedido2);

        comanda2.adicionarPedido(pedido2);
        comandaRepo.saveOrUpdate(comanda2);

        System.out.println("Pedidos criados, associados e salvos.");

        System.out.println("\n=== MOSTRANDO PEDIDOS DA MESA 1 ===");
        Comanda c1 = comandaRepo.findByNumeroMesa(1);
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


        // Repositórios para Mesa e Funcionário
        MesaRepository mesaRepo = new MesaRepository();
        FuncionarioRepository funcionarioRepo = new FuncionarioRepository();

        System.out.println("\n=== CRIANDO MESAS ===");
        Mesa mesa1 = new Mesa();
        mesa1.setNumero(1);
        mesa1.setCapacidade(4);
        mesa1.setLocalizacao("Salão principal");
        mesa1.setOcupada(false);
        mesaRepo.saveOrUpdate(mesa1);

        Mesa mesa2 = new Mesa();
        mesa2.setNumero(2);
        mesa2.setCapacidade(6);
        mesa2.setLocalizacao("Área externa");
        mesa2.setOcupada(false);
        mesaRepo.saveOrUpdate(mesa2);

        System.out.println("Mesas criadas e salvas.");

        System.out.println("\n=== CRIANDO FUNCIONÁRIOS ===");
        Funcionario func1 = new Funcionario();
        func1.setNome("Ana Beatriz");
        func1.setCpf("123.456.789-00");
        func1.setDataNascimento(LocalDate.of(1990, 5, 12));
        funcionarioRepo.saveOrUpdate(func1);

        Funcionario func2 = new Funcionario();
        func2.setNome("Carlos Silva");
        func2.setCpf("987.654.321-00");
        func2.setDataNascimento(LocalDate.of(1985, 8, 22));
        funcionarioRepo.saveOrUpdate(func2);

        System.out.println("Funcionários criados e salvos.");

        System.out.println("\n=== LISTANDO MESAS ===");
        List<Mesa> mesas = mesaRepo.findAll();
        for (Mesa m : mesas) {
            System.out.println(m);
        }

        System.out.println("\n=== LISTANDO FUNCIONÁRIOS ===");
        List<Funcionario> funcionarios = funcionarioRepo.findAll();
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }

        System.out.println("\n=== TESTE FINALIZADO ===");
    }
}
