/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.guisso.javasepersistencewithhibernateorm.produto;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
/**
 *
 * @author Loiola
 */
public class ItemCardapioRepository 
        extends Repository<ItemCardapio>{
    
    @Override
    public String getJpqlFindAll() {
        return "SELECT i FROM ItemCardapio i";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT i FROM ItemCardapio i WHERE i.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM ItemCardapio i WHERE i.id = :id";
    }
    
}
