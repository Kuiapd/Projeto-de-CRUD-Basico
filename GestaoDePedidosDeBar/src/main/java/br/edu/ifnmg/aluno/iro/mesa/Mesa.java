/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.aluno.iro.mesa;

import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa 
        extends ProjectEntity 
        implements Serializable {

    @Column(nullable = false, unique = true)
    private int numero;

    @Column(length = 50)
    private String localizacao; // Ex: "Salão", "Varanda"...

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false)
    private boolean ocupada = false;

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comanda> comandas = new ArrayList<>();

    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void adicionarComanda(Comanda comanda) {
        comanda.setMesa(this);
        comandas.add(comanda);
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "numero=" + numero +
                ", localizacao='" + localizacao + '\'' +
                ", capacidade=" + capacidade +
                ", ocupada=" + ocupada +
                '}';
    }
}

