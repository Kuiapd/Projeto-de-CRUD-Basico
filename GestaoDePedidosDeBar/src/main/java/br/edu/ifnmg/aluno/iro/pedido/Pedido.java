package br.edu.ifnmg.aluno.iro.pedido;

import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedido")
public class Pedido extends ProjectEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comanda_id")
    private Comanda comanda;

    @Column(name = "lixo")
    private boolean lixo = false;

    // Supondo que você tenha um preço para o pedido, por exemplo:
    @Column(name = "preco")
    private double preco;

    // Getter e setters
    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public boolean isLixo() {
        return lixo;
    }

    public void setLixo(boolean lixo) {
        this.lixo = lixo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Método para calcular subtotal do pedido (exemplo simples)
    public double calcularSubtotal() {
        return preco;  // Ajuste para sua regra real, se precisar multiplicar por quantidade, etc.
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + getId() +
                ", comandaId=" + (comanda != null ? comanda.getId() : null) +
                ", preco=" + preco +
                ", lixo=" + lixo +
                '}';
    }
}
