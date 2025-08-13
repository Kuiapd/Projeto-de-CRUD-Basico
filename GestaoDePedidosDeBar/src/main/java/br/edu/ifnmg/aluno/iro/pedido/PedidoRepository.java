package br.edu.ifnmg.aluno.iro.pedido;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PedidoRepository extends Repository<Pedido> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT p FROM Pedido p WHERE p.lixo = false";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT p FROM Pedido p WHERE p.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Pedido p WHERE p.id = :id AND p.lixo = false";
    }

    public List<Pedido> findByComanda(String comanda) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.comanda = :comanda AND p.lixo = false",
                    Pedido.class)
                .setParameter("comanda", comanda)
                .getResultList();
        }
    }

    // Métodos da lixeira (soft delete)
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
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.lixo = true",
                    Pedido.class)
                .getResultList();
        }
    }

    public Pedido buscarNaLixeiraId(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.id = :id AND p.lixo = true",
                    Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
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
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Pedido p WHERE p.lixo = true")
                .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
