package br.edu.ifnmg.aluno.pdss8.comanda;

import br.edu.ifnmg.aluno.iro.mesa.Mesa;
import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import br.edu.ifnmg.aluno.iro.mesa.Mesa;
import br.edu.ifnmg.aluno.pdss8.funcionario.Funcionario;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comanda")
public class Comanda extends ProjectEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;  // quem abriu a comanda

    @Column(nullable = false)
    private boolean pago = false;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();

    // Getters e setters
    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) { this.mesa = mesa; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public boolean isPago() { return pago; }
    public void setPago(boolean pago) { this.pago = pago; }

    public List<Pedido> getPedidos() { return pedidos; }

    public void adicionarPedido(Pedido pedido) {
        pedido.setComanda(this);
        pedidos.add(pedido);
    }

    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
        pedido.setComanda(null);
    }

    public double calcularTotal() {
        return pedidos.stream()
                      .mapToDouble(Pedido::calcularSubtotal)
                      .sum();
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + getId() +
                ", mesa=" + (mesa != null ? mesa.getNumero() : "null") +
                ", funcionario=" + (funcionario != null ? funcionario.getNome() : "null") +
                ", pago=" + pago +
                ", pedidos=" + pedidos +
                '}';
    }

    
}

