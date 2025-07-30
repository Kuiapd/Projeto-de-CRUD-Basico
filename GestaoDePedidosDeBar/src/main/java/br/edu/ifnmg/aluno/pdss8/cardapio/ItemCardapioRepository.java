/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifnmg.aluno.pdss8.cardapio;

import br.edu.ifnmg.aluno.pdss8.comanda.Comanda;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
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
public class ItemCardapioRepository extends Repository<ItemCardapio> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT i FROM ItemCardapio i WHERE i.lixo = false";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT i FROM ItemCardapio i WHERE i.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM ItemCardapio i WHERE i.id = :id AND i.lixo = false";
    }

    // Busca por nome com LIKE (contém)
    public List<ItemCardapio> findByNomeContaining(String nome) {
        try (var em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(
                    "SELECT i FROM ItemCardapio i WHERE i.nome LIKE :nome",
                    ItemCardapio.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }
    }

    // Métodos para a lixeira
    public void moverParaLixeira(ItemCardapio item) {
        item.setLixo(true);
        saveOrUpdate(item);
    }

    public void moverParaLixeiraId(Long id) {
        ItemCardapio item = findById(id);
        if (item != null) {
            moverParaLixeira(item);
        }
    }

    
}
