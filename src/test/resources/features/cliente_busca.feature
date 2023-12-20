# coding: utf-8
# language: pt

Funcionalidade: Busca de clientes

  Cenário: Buscar clientes pelo cpf especifico
    Dado que exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando o usuário enviar uma requisição GET para "/v1/clientes" com ao cpf "11111111111"
    Então o resultado da busca deve conter um cliente com os dados
    E o status deve ser 200

  Cenário: Buscar clientes pelo cpf inexistente
    Dado que nao exista um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Quando o usuário enviar uma requisição GET para "/v1/clientes" com ao cpf "11111111111"
    Então o status deve ser 404