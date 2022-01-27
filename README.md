# crudCadastro
API para cadastro de vendedores e clientes

ESTRUTURA DO PROJETO:

Cadastro
 pacotes
    dto
    entities
    exceptions
    services
    repositories
    resources
    
Funcionalidades:   
○ Vendedor:
■ Cadastro
■ Edição
■ Exclusão
■ Listagem dos vendedores do sistema
● Opção de trazer registros paginados
● Retornar vendedores por ordem alfabética
■ Listar por CPF ou CNPJ

○ Cliente:
■ Cadastro
■ Edição
■ Exclusão
■ Listagem dos clientes por vendedores
● Opção de trazer registros paginados
● Retornar clientes por ordem alfabética
● Buscar por Nome ou CPF

REQUISITOS:
o A API é desenvolvida em Java (8 ou superior) utilizando Spring Framework (2.2 ou superior).
o Implementada operações no banco de dados utilizando Spring Data JPA & Hibernate.
o Banco relacional:
▪ PostgreSQL.
o A API segue os padrões REST na construção das rotas e retornos.
o Caso haja alguma particularidade de implementação, instruções para execução do projeto deverão ser enviadas.
	● usado programa Postman pra relação API e Banco de Dados
	Ger, Put, Post, Delete
	URLs como /cliente
		  /vendedor
