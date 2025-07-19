/*
 * Copyright (C) 2025 Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.guisso.javasepersistencewithhibernateorm.beta.aluno;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Aluno entity
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1
 * @since 0.1, 2025-06-25
 */
@Entity
public class Aluno
        extends ProjectEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Integer matricula;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = true)
    private LocalDate nascimento;

    @Transient
    private Integer idade;

    private Boolean ativo;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        if (matricula < 0) {
            throw new IllegalArgumentException("A matrícula tem que ser positiva");
        }
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        // TODO E o limite mínimo de caracteres
        if (nome == null || nome.isBlank() || nome.length() > 45) {
            throw new IllegalArgumentException("O nome tem que ter até 45 caracteres");
        }
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        if (nascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser futura");
        }

        this.nascimento = nascimento;

        // nascimento changes and updates idade
        setIdade();
    }

    @PostLoad
    private void setIdade() {
        idade = (int) nascimento.until(LocalDate.now(), ChronoUnit.YEARS);
    }

    public Integer getIdade() {
        return idade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.matricula);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.nascimento);
        hash = 79 * hash + Objects.hashCode(this.ativo);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "Aluno{"
                + "id=" + getId()
                + ", matricula=" + matricula
                + ", nome=" + nome
                + ", nascimento=" + nascimento
                + ", idade=" + idade
                + ", " + (ativo ? "Ativo" : "Inativo")
                + '}';
    }
    //</editor-fold>

}
