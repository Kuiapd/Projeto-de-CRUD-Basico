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
    
    // Métodos para a lixeira

    public void moverParaLixeira(Comanda comanda) {
        comanda.setLixo(true);
        saveOrUpdate(comanda);
    }

    public void moverParaLixeiraId(Long id) {
        Comanda comanda = findById(id);
        if (comanda != null) {
            moverParaLixeira(comanda);
        }
    }

    public void moverParaLixeira(List<Comanda> comandas) {
        for (Comanda comanda : comandas) {
            moverParaLixeira(comanda);
        }
    }

    public List<Comanda> buscarTodosNaLixeira() {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.lixo = true",
                    Comanda.class).getResultList();
        }
    }

    public Comanda buscarNaLixeiraId(Long id) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT c FROM Comanda c WHERE c.id = :id AND c.lixo = true",
                    Comanda.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void restaurar(Comanda comanda) {
        comanda.setLixo(false);
        saveOrUpdate(comanda);
    }

    public void restaurarId(Long id) {
        Comanda comanda = findById(id);
        if (comanda != null) {
            restaurar(comanda);
        }
    }

    public void excluirDefinitivamente(Comanda comanda) {
        delete(comanda);
    }

    public void excluirDefinitivamenteId(Long id) {
        Comanda comanda = findById(id);
        if (comanda != null) {
            excluirDefinitivamente(comanda);
        }
    }

    public void esvaziarLixeira() {
        try (var em = DataSourceFactory.getEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Comanda c WHERE c.lixo = true")
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}