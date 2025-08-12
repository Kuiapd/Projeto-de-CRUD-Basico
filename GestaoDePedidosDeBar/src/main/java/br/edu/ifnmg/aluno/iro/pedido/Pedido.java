/*
 * Copyright (C) 2025 PABLO DANIEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTCULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.edu.ifnmg.aluno.iro.pedido;

import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapio;
import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import br.edu.ifnmg.aluno.pdss8.cardapio.ItemCardapio;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Entity
@Table(name = "pedido")
public class Pedido extends ProjectEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comanda_id", nullable = false)
    private Comanda comanda;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pedido_itens", joinColumns = @JoinColumn(name = "pedido_id"))
    @MapKeyJoinColumn(name = "item_cardapio_id")
    @Column(name = "quantidade")
    private Map<ItemCardapio, Integer> itens = new HashMap<>();

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Map<ItemCardapio, Integer> getItens() {
        return itens;
    }

    public void adicionarItem(ItemCardapio item, int quantidade) {
        if (itens == null) {
            itens = new HashMap<>();
        }
        itens.put(item, itens.getOrDefault(item, 0) + quantidade);
    }


    public double calcularSubtotal() {
        return itens.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPreco() * e.getValue())
                .sum();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "mesa=" + (comanda != null ? comanda.getNumeroMesa() : "null") +
                ", itens=" + itens +
                '}';
    }
}