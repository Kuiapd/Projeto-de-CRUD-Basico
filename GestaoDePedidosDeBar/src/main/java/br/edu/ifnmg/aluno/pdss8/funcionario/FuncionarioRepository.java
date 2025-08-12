/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.aluno.pdss8.funcionario;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import static io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory.getEntityManager;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;

/**
 *
 * @author PABLO DANIEL
 */

public class FuncionarioRepository extends Repository<Funcionario> {

    @Override
    public String getJpqlFindAll() { return "SELECT f FROM Funcionario f"; }

    @Override
    public String getJpqlFindById() { return "SELECT f FROM Funcionario f WHERE f.id = :id"; }

    @Override
    public String getJpqlDeleteById() { return "DELETE FROM Funcionario f WHERE f.id = :id"; }

    public Funcionario findByLogin(String login) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Funcionario f WHERE f.login = :login", Funcionario.class)
                     .setParameter("login", login)
                     .getSingleResult();
        } finally {
            em.close();
        }
    }
}
