# coding: utf-8
# language: pt

Funcionalidade: Busca de produto

  Cenário: Buscar todos os pedidos
    Dado que um usuário queira buscar todos os pedidos
    E que exista um pedido com o mesmo status e com um produdo chamado "Refrigerante"
    Quando o usuário enviar uma requisição GET para "/v1/pedidos" com a status desejada
    Então o resultado da busca deve conter um pedido com nome "Refrigerante" com o preço 5,00
    E o status deve ser 200

  Cenário: Busca de pedidos pelo status recebido
    Dado que um usuário queira buscar todos os pedidos pelo parametro "status" chamado "RECEBIDO"
    E que exista um pedido com o mesmo status e com um produdo chamado "Refrigerante"
    Quando o usuário enviar uma requisição GET para "/v1/pedidos" com o status desejado
    Então o resultado da busca deve conter um pedido com nome "Refrigerante" com o preço 5,00
    E o status deve ser 200

  Cenário: Busca de pedido pelo status inexistente
      Dado que um usuário queira buscar todos pedidos pelo parametro "status" chamado "INEXISTENTE"
      Quando o usuário enviar uma requisição GET para "/v1/pedidos" com o status desejado
      Então o resultado deve ser vazio
      E o status deve ser 400
      E conter um erro da mensagem contendo "StatusPedido não encontrado para o valor"

  Cenário: Busca de pedido pelo status vazia
    Dado que um usuário queira buscar todos pedidos pelo parametro "status" chamado ""
    Quando o usuário enviar uma requisição GET para "/v1/pedidos" com o status desejado
    Então o resultado deve ser vazio
    E o status deve ser 200
    
