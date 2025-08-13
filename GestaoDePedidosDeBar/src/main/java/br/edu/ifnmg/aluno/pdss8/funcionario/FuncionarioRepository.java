package br.edu.ifnmg.aluno.pdss8.funcionario;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import static io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory.getEntityManager;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import java.util.List;

public class FuncionarioRepository extends Repository<Funcionario> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT f FROM Funcionario f WHERE f.lixo = false";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT f FROM Funcionario f WHERE f.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Funcionario f WHERE f.id = :id";
    }

    // Buscar por CPF (resultado único)
    public Funcionario findByCpf(String cpf) {
    try (var em = DataSourceFactory.getEntityManager()) {
        return em.createQuery(
                "SELECT f FROM Funcionario f WHERE f.cpf = :cpf", Funcionario.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }
}


    // Buscar por nome contendo (resultado múltiplo)
    public List<Funcionario> findByNomeContaining(String nome) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT f FROM Funcionario f WHERE LOWER(f.nome) LIKE LOWER(:nome)", Funcionario.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }
    }

    // Métodos para a lixeira
    public void moverParaLixeira(Funcionario funcionario) {
        funcionario.setLixo(true);
        saveOrUpdate(funcionario);
    }

    public void moverParaLixeiraId(Long id) {
        Funcionario funcionario = findById(id);
        if (funcionario != null) {
            moverParaLixeira(funcionario);
        }
    }

    public void moverParaLixeira(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            moverParaLixeira(funcionario);
        }
    }

    public List<Funcionario> buscarTodosNaLixeira() {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT f FROM Funcionario f WHERE f.lixo = true",
                    Funcionario.class).getResultList();
        }
    }

    public Funcionario buscarNaLixeiraId(Long id) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT f FROM Funcionario f WHERE f.id = :id AND f.lixo = true",
                    Funcionario.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void restaurar(Funcionario funcionario) {
        funcionario.setLixo(false);
        saveOrUpdate(funcionario);
    }

    public void restaurarId(Long id) {
        Funcionario funcionario = findById(id);
        if (funcionario != null) {
            restaurar(funcionario);
        }
    }

    public void excluirDefinitivamente(Funcionario funcionario) {
        delete(funcionario);
    }

    public void excluirDefinitivamenteId(Long id) {
        Funcionario funcionario = findById(id);
        if (funcionario != null) {
            excluirDefinitivamente(funcionario);
        }
    }

    public void esvaziarLixeira() {
        try (var em = DataSourceFactory.getEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Funcionario f WHERE f.lixo = true")
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
