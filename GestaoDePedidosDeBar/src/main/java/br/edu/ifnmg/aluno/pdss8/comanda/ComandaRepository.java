/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.aluno.pdss8.comanda;

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

public class ComandaRepository extends Repository<Comanda> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT c FROM Comanda c";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT c FROM Comanda c WHERE c.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Comanda c WHERE c.id = :id";
    }

    // Busca por número da mesa
    public Comanda findByNumeroMesa(int numeroMesa) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Comanda c LEFT JOIN FETCH c.pedidos WHERE c.numeroMesa = :num", Comanda.class)
                .setParameter("num", numeroMesa)
                .getSingleResult();
        } finally {
            em.close();
        }
    }
    // Busca todas comandas pagas ou não
    public List<Comanda> findByPago(boolean pago) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.pago = :pago",
                    Comanda.class)
                    .setParameter("pago", pago)
                    .getResultList();
        }
    }
}