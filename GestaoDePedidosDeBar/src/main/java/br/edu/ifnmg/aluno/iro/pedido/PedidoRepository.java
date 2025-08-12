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
        return "SELECT p FROM Pedido p WHERE p.lixo = false";  // filtra só ativos
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT p FROM Pedido p WHERE p.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Pedido p WHERE p.id = :id AND p.lixo = false";
    }
    
    public List<Pedido> findByComandaId(Long comandaId) {
    try (var em = DataSourceFactory.getEntityManager()) {
        return em.createQuery(
            "SELECT p FROM Pedido p JOIN FETCH p.comanda WHERE p.comanda.id = :comandaId",
            Pedido.class)
            .setParameter("comandaId", comandaId)
            .getResultList();
    }
}

    // Métodos para a lixeira
    public void moverParaLixeira(Pedido pedido) {
        pedido.setLixo(true);
        saveOrUpdate(pedido);
    }

    public void moverParaLixeiraId(Long id) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            moverParaLixeira(pedido);
        }
    }

    public void moverParaLixeira(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            moverParaLixeira(pedido);
        }
    }

    public List<Pedido> buscarTodosNaLixeira() {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.lixo = true",
                    Pedido.class).getResultList();
        }
    }

    public Pedido buscarNaLixeiraId(Long id) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.id = :id AND p.lixo = true",
                    Pedido.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void restaurar(Pedido pedido) {
        pedido.setLixo(false);
        saveOrUpdate(pedido);
    }

    public void restaurarId(Long id) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            restaurar(pedido);
        }
    }

    public void excluirDefinitivamente(Pedido pedido) {
        delete(pedido);
    }

    public void excluirDefinitivamenteId(Long id) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            excluirDefinitivamente(pedido);
        }
    }

    public void esvaziarLixeira() {
        try (var em = DataSourceFactory.getEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Pedido p WHERE p.lixo = true")
                .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
