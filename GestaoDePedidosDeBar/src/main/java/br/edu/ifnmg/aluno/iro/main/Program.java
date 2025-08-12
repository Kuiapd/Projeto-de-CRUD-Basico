package br.edu.ifnmg.aluno.iro.main;


import br.edu.ifnmg.aluno.iro.mesa.Mesa;
import br.edu.ifnmg.aluno.iro.mesa.MesaRepository;
import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapio;
import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapioRepository;
import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import br.edu.ifnmg.aluno.pdss8.comanda.ComandaRepository;
import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import br.edu.ifnmg.aluno.iro.pedido.PedidoRepository;
import br.edu.ifnmg.aluno.pdss8.atendimento.Atendimento;
import br.edu.ifnmg.aluno.pdss8.funcionario.Funcionario;
import br.edu.ifnmg.aluno.pdss8.funcionario.FuncionarioRepository;
import java.util.List;
import java.util.Map;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;


public class Program {

    public static void runSystemBar() {
        // Instancia repositórios (supondo que você tenha repositórios para cada entidade)
        FuncionarioRepository funcionarioRepo = new FuncionarioRepository();
        MesaRepository mesaRepo = new MesaRepository();
        ItemCardapioRepository itemRepo = new ItemCardapioRepository();
        ComandaRepository comandaRepo = new ComandaRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        // Criar entidades básicas
        Funcionario f1 = new Funcionario();
        f1.setNome("Ana Silva");
        f1.setLogin("ana");
        f1.setSenha("1234");
        f1.setCargo("Garçonete");

        Mesa m1 = new Mesa();
        m1.setNumero(10);
        m1.setCapacidade(4);
        m1.setLocalizacao("Sala 1");
        m1.setOcupada(false);

        ItemCardapio i1 = new ItemCardapio();
        i1.setNome("Cerveja");
        i1.setPreco(8.0f);

        // Salvar entidades no banco
        funcionarioRepo.saveOrUpdate(f1);
        mesaRepo.saveOrUpdate(m1);
        itemRepo.saveOrUpdate(i1);

        // Criar comanda ligada ao funcionário e mesa
        Comanda c1 = new Comanda();
        c1.setFuncionario(f1);
        c1.setMesa(m1);
        c1.setPago(false);

        comandaRepo.saveOrUpdate(c1);

        // Criar pedido para a comanda
        Pedido p1 = new Pedido();
        p1.setComanda(c1);
        p1.setFuncionario(f1);
        pedidoRepo.saveOrUpdate(p1);

        // Aqui você pode adicionar itens ao pedido se quiser, usando seu código

        // Finaliza
        // NÃO tem nenhum System.out.println — só salva no banco
    }
}
