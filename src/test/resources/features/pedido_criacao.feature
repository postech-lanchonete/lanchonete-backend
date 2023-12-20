# coding: utf-8
# language: pt

Funcionalidade: Criação de pedido

  Cenário: Criação de um novo pedido
    Dado que exista um produto com o nome "Batata frita" com id 10
    E que exista um cliente com o cpf "11111111111"
    Quando for requisitado a criacao de um pedido contendo produtos com o id "10" para o cliente com o cpf 11111111111
    Então o status deve ser 201
    E ter dados do pedido criado


  Cenário: Criação de um novo pedido para um cliente inexistente
    Dado que exista um produto com o nome "Batata frita" com id 10
    E que exista um cliente com o cpf "11111111111"
    Quando for requisitado a criacao de um pedido contendo produtos com o id "10" para o cliente com o cpf 11111111112
    Então o status deve ser 400

  Cenário: Criação de um novo pedido com um produto inexistente
    Dado que exista um produto com o nome "Batata frita" com id 10
    E que exista um cliente com o cpf "11111111111"
    Quando for requisitado a criacao de um pedido contendo produtos com o id "100" para o cliente com o cpf 11111111111
    Então o status deve ser 400

  Cenário: Criação de um novo pedido com mais de um produto
    Dado que exista um produto com o nome "Batata frita" com id 10
    E que exista um produto com o nome "Regrigerante" com id 11
    E que exista um cliente com o cpf "11111111111"
    Quando for requisitado a criacao de um pedido contendo produtos com o id "10,11" e para o cliente com o cpf 11111111111
    Então o status deve ser 201
    E ter dados do pedido criado


