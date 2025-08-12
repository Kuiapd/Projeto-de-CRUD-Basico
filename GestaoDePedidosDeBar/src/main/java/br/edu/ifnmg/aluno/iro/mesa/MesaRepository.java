/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.aluno.iro.mesa;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import static io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory.getEntityManager;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import java.util.List;
/**
 *
 * @author PABLO DANIEL
 */


public class MesaRepository extends Repository<Mesa> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT m FROM Mesa m";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT m FROM Mesa m WHERE m.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Mesa m WHERE m.id = :id";
    }

    public Mesa findByNumero(int numero) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT m FROM Mesa m LEFT JOIN FETCH m.comandas WHERE m.numero = :num", Mesa.class)
                .setParameter("num", numero)
                .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Mesa> findByOcupada(boolean ocupada) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT m FROM Mesa m WHERE m.ocupada = :ocupada",
                    Mesa.class)
                    .setParameter("ocupada", ocupada)
                    .getResultList();
        }
    }
}
