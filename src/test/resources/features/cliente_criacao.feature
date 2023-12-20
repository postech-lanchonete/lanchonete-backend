# coding: utf-8
# language: pt

Funcionalidade: Criação de cliente

  Cenário: Criação de um novo cliente
    Dado que nao exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "p.silva@teste.com"
    Então o status deve ser 201
    E ter dados do cliente criado

  Cenário: Criação de um novo cliente igual a um existente
    Dado que exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "p.silva@teste.com"
    Então o status deve ser 422

  Cenário: Criação de um novo cliente com um e-mail igual a um existente
    Dado que exista um cliente com o nome igual a "Pedro 2", sobrenome igual a "Silva 2" e cpf igual a "11111111112" e email "pedro.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Então o status deve ser 422

  Cenário: Criação de um novo cliente sem nome
    Dado que nao exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Então o status deve ser 400
    E conter um erro da mensagem contendo "Nome é mandatorio"

  Cenário: Criação de um novo cliente sem sobrenome
    Dado que nao exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Então o status deve ser 400
    E conter um erro da mensagem contendo "Sobrenome é mandatorio"

  Cenário: Criação de um novo cliente sem cpf
    Dado que nao exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "" e email "pedro.silva@teste.com"
    Então o status deve ser 400
    E conter um erro da mensagem contendo "CPF é mandatorio"

  Cenário: Criação de um novo cliente sem email
    Dado que nao exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email ""
    Então o status deve ser 400
    E conter um erro da mensagem contendo "E-mail é mandatorio"