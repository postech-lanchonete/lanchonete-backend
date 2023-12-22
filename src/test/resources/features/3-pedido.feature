# coding: utf-8
# language: pt

Funcionalidade: Pedidos

  Cenário: Criação de um novo pedido
    Dado que exista um produto com o nome "Hamburger"
    E que exista um cliente com o cpf "11111111111"
    Quando enviar uma requisição para criar um pedido para o cliente encontrado com o produto encontrado
    Entao deve retornar o pedido
    E o status da requisicao do pedido deve ser igual a 201

  Cenário: Criação de um novo pedido para um cliente inexistente
    Dado que exista um produto com o nome "Hamburger"
    E que nao exista um cliente com o cpf "11111111110"
    Quando enviar uma requisição para criar um pedido para um cliente com o cpf "11111111110" com o produto encontrado
    E o status da requisicao do pedido deve ser igual a 404
    E a mensagem de erro ao criar o pedido contenha "Cliente não encontrado"

  Cenário: Criação de um novo pedido com um produto inexistente
    Dado que se queira criar um pedido com um produto com id inexistente
    E que exista um cliente com o cpf "11111111111"
    Quando enviar uma requisição para criar um pedido para um cliente com o cpf "11111111111" com o produto inexistente
    E o status da requisicao do pedido deve ser igual a 404
    E a mensagem de erro ao criar o pedido contenha "Produto não encontrado com o id"

  Cenário: Mudar status do pedido
    Dado que exista um pedido com o status "RECEBIDO"
    Quando enviar uma requisição para alterar o status deste pedido
    E o status da requisicao do pedido deve ser igual a 202
    E o status deste pedido deve ser igual a "EM_PREPARACAO"

  Cenário: Mudar status do pedido para em preparacao
    Dado que exista um pedido com o status "EM_PREPARACAO"
    Quando enviar uma requisição para alterar o status deste pedido
    E o status da requisicao do pedido deve ser igual a 202
    E o status deste pedido deve ser igual a "PRONTO"

  Cenário: Mudar status do pedido para finalizado
    Dado que exista um pedido com o status "PRONTO"
    Quando enviar uma requisição para alterar o status deste pedido
    E o status da requisicao do pedido deve ser igual a 202
    E o status deste pedido deve ser igual a "FINALIZADO"

  Cenário: Mudar status do pedido para finalizado novamente
    Dado que exista um pedido com o status "FINALIZADO"
    Quando enviar uma requisição para alterar o status deste pedido
    E o status da requisicao do pedido deve ser igual a 202
    E o status deste pedido deve ser igual a "FINALIZADO"

  Cenário: Buscar todos os pedidos
    Dado que se queira buscar os pedidos com o status igual a ""
    Quando for feita uma busca pelos pedidos
    Entao o resultado da busca de pedidos deve conter uma lista com 1 pedidos
    E o status da busca dos pedidos deve ser igual a 200

  Cenário: Busca de pedidos por um status especifico
    Dado que se queira buscar os pedidos com o status igual a "FINALIZADO"
    Quando for feita uma busca pelos pedidos
    Entao o resultado da busca de pedidos deve conter uma lista com 1 pedidos
    E o status da busca dos pedidos deve ser igual a 200

  Cenário: Busca de pedidos por um status sem nenhum pedido
    Dado que se queira buscar os pedidos com o status igual a "RECEBIDO"
    Quando for feita uma busca pelos pedidos
    Entao o resultado da busca de pedidos deve conter uma lista com 0 pedidos
    E o status da busca dos pedidos deve ser igual a 200

  Cenário: Busca de pedido pelo status inexistente
      Dado que se queira buscar os pedidos com o status igual a "INEXISTENTE"
      Quando for feita uma busca pelos pedidos
      Entao o resultado da busca de pedidos deve retornar um erro 400
      E conter um erro da mensagem contendo "StatusDoPedido não encontrado para o valor: INEXISTENTE. Os valores permitidos são: RECEBIDO,EM_PREPARACAO,PRONTO,FINALIZADO"