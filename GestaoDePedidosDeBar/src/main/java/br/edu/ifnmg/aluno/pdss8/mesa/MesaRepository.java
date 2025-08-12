package br.edu.ifnmg.aluno.pdss8.mesa;

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

    // Busca por número da mesa
    public Mesa findByNumero(int numero) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT m FROM Mesa m WHERE m.numero = :num", Mesa.class)
                .setParameter("num", numero)
                .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Busca todas mesas ocupadas ou não
    public List<Mesa> findByOcupada(boolean ocupada) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT m FROM Mesa m WHERE m.ocupada = :ocupada",
                    Mesa.class)
                    .setParameter("ocupada", ocupada)
                    .getResultList();
        }
    }

    // Busca mesas com capacidade mínima
    public List<Mesa> findByCapacidadeMinima(int capacidadeMinima) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT m FROM Mesa m WHERE m.capacidade >= :capacidadeMinima",
                    Mesa.class)
                    .setParameter("capacidadeMinima", capacidadeMinima)
                    .getResultList();
        }
    }
}
