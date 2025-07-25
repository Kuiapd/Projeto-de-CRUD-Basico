package br.edu.ifnmg.aluno.pdss8.comanda;

import br.edu.ifnmg.aluno.iro.pedido.Pedido;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comanda")
public class Comanda extends ProjectEntity implements Serializable {

    @Column(nullable = false, unique = true)
    private int numeroMesa;

    @Column(nullable = false)
    private boolean pago = false;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();

    // Getters e Setters
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

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
                "id=" + getId() + // herdado de ProjectEntity
                ", numeroMesa=" + numeroMesa +
                ", pago=" + pago +
                ", pedidos=" + pedidos +
                '}';
    }
}
