package br.edu.ifnmg.aluno.iro.pedido;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedido")
public class Pedido extends ProjectEntity implements Serializable {

    @Column(name = "comanda")
    private String comanda;

    @Column(name = "lixo")
    private boolean lixo = false;

    public String getComanda() {
        return comanda;
    }

    public void setComanda(String comanda) {
        this.comanda = comanda;
    }

    public boolean isLixo() {
        return lixo;
    }

    public void setLixo(boolean lixo) {
        this.lixo = lixo;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "comanda='" + comanda + '\'' +
                ", lixo=" + lixo +
                '}';
    }
}
