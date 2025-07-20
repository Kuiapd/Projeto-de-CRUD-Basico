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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.edu.ifnmg.aluno.iro.pedido;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import static io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory.getEntityManager;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.IRepository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;



/**
 *
 * @author PABLO DANIEL
 */
public class PedidoRepository extends Repository<Pedido> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT p FROM Pedido p";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT p FROM Pedido p WHERE p.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Pedido p WHERE p.id = :id";
    }

    // Busca todos os pedidos de uma comanda pelo id da comanda, já carregando a Comanda
    public List<Pedido> findByComandaId(Long comandaId) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT p FROM Pedido p JOIN FETCH p.comanda WHERE p.comanda.id = :comandaId",
                    Pedido.class)
                    .setParameter("comandaId", comandaId)
                    .getResultList();
        }
    }

    public Pedido findByIdWithItens(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT p FROM Pedido p LEFT JOIN FETCH p.itens WHERE p.id = :id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
        } finally {
            em.close();
        }
    }
}