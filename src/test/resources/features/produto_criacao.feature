# coding: utf-8
# language: pt

Funcionalidade: Criação de produto

  Cenário: Criação de um novo produto
    Dado que um usuário queira cadastrar um produto com nome "Batata frita" e preço 5,00 e categoria "LANCHE"
    Quando o usuário enviar uma requisição POST para "/v1/produtos" com os dados do produto
    Então o status deve ser 201
    E ter dados do produto criado


  Cenário: Criação de um novo produto com nome já existente
    Dado que um usuário queira cadastrar um produto com nome "Refrigerante" e preço 3,00 e categoria "BEBIDA"
    E já exista um produto com nome "Refrigerante" e preço 3,00 e categoria "BEBIDA"
    Quando o usuário enviar uma requisição POST para "/v1/produtos" com os dados do produto
    Então o status deve ser 422


  Cenário: Criação de um novo produto sem nome
    Dado que um usuário queira cadastrar um produto com nome "" e preço 5,00 e categoria "LANCHE"
    Quando o usuário enviar uma requisição POST para "/v1/produtos" com os dados do produto
    Então o status deve ser 400


  Cenário: Criação de um novo produto com preço inválido
    Dado que um usuário queira cadastrar um produto com nome "Hamburger" e preço -5,00 e categoria "LANCHE"
    Quando o usuário enviar uma requisição POST para "/v1/produtos" com os dados do produto
    Então o status deve ser 400

