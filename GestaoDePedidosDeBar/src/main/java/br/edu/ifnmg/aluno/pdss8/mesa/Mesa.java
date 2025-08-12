package br.edu.ifnmg.aluno.pdss8.mesa;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "mesa")
public class Mesa extends ProjectEntity implements Serializable {

    @Column(nullable = false, unique = true)
    private int numero;

    @Column(nullable = false)
    private int capacidade; // Quantas pessoas cabem na mesa

    @Column(nullable = false)
    private boolean ocupada = false; // Se a mesa está ocupada ou livre

    @Column(length = 255)
    private String localizacao; // Ex: "Área externa", "Salão principal"

    private boolean lixo = false;
    
    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
    public boolean isLixo() {
        return lixo;
    }

    public void setLixo(boolean lixo) {
        this.lixo = lixo;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + getId() +
                ", numero=" + numero +
                ", capacidade=" + capacidade +
                ", ocupada=" + ocupada +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}
