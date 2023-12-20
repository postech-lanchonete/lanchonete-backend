# coding: utf-8
# language: pt

Funcionalidade: Alterar status do pedido

  Cenário: Mudar status do pedido
    Dado que exista um pedido com o id 200
    E que esteja com o status "RECEBIDO"
    Quando o usuário enviar uma requisição PUT para "/v1/pedidos" com o id 200
    Então o status deve ser 202
    E o status deve ser igual a "EM_PREPARACAO"

  Cenário: Mudar status do pedido para em preparacao
    Dado que exista um pedido com o id 200
    E que esteja com o status "EM_PREPARACAO"
    Quando o usuário enviar uma requisição PUT para "/v1/pedidos" com o id 200
    Então o status deve ser 202
    E o status deve ser igual a "PRONTO"

  Cenário: Mudar status do pedido para finalizado
    Dado que exista um pedido com o id 200
    E que esteja com o status "PRONTO"
    Quando o usuário enviar uma requisição PUT para "/v1/pedidos" com o id 200
    Então o status deve ser 202
    E o status deve ser igual a "FINALIZADO"

  Cenário: Mudar status do pedido para finalizado novamente
    Dado que exista um pedido com o id 200
    E que esteja com o status "FINALIZADO"
    Quando o usuário enviar uma requisição PUT para "/v1/pedidos" com o id 200
    Então o status deve ser 202
    E o status deve ser igual a "FINALIZADO"