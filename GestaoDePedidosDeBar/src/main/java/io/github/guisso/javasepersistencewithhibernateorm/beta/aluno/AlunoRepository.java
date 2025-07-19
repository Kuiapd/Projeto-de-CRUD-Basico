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

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;

/**
 * Repository for Aluno operations
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1
 * @since 0.1, Jul 7, 2025
 */
public class AlunoRepository
        extends Repository<Aluno> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT a FROM Aluno a";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT a FROM Aluno a WHERE a.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Aluno a WHERE a.id = :id";
    }

}
