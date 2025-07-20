# Gestão de Pedidos de Bar

Um sistema básico para gerenciar pedidos em um bar, desenvolvido em **Java** com **Hibernate ORM**.

## Funcionalidades

- **Itens do cardápio**: cadastro de comidas e bebidas com nome e preço.
- **Comandas**: abertura de mesas, controle de status (pago/não pago).
- **Pedidos**: associação de itens a uma comanda, cálculo de subtotal e total.
- **Operações CRUD**: criar, listar, buscar, atualizar e deletar registros no banco de dados.

## Estrutura do Projeto

- **ItemCardapio** → representa um item do cardápio.
- **Comanda** → representa a comanda de uma mesa.
- **Pedido** → agrupa itens e quantidades para uma comanda.
- **Repositórios** (`ItemCardapioRepository`, `ComandaRepository`, `PedidoRepository`) → camada de persistência.

## Tecnologias Utilizadas

- **Java 21**
- **Hibernate ORM**
- **JPA (Jakarta Persistence)**
- **Maven**

## Como funciona

1. Cria itens do cardápio (ex.: Pizza, Refrigerante, Cerveja).
2. Abre comandas (mesas).
3. Associa pedidos às comandas com itens e quantidades.
4. Calcula subtotal de pedidos e total da mesa.
5. Permite fechar e excluir comandas.
6. Realiza buscas no banco (ex.: itens que contêm "refri").

## Execução

Para rodar o projeto:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass=io.github.guisso.javasepersistencewithhibernateorm.JavaSEPersistenceWithHibernateORM
