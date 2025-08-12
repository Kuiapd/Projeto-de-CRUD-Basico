package br.edu.ifnmg.aluno.pdss8.funcionario;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import static io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory.getEntityManager;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import java.util.List;

public class FuncionarioRepository extends Repository<Funcionario> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT f FROM Funcionario f";
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
}
