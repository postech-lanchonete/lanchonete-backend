# coding: utf-8
# language: pt

Funcionalidade: Clientes

  Cenário: Criação de um novo cliente
    Dado que nao exista um cliente registrado no sistema com o CPF "11111111111"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "p.silva@teste.com"
    Entao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 201

  Cenário: Criação de um novo cliente igual a um existente
    Dado que exista um cliente registrado no sistema com o CPF "11111111111"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "p.silva@teste.com"
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 422

  Cenário: Criação de um novo cliente com um e-mail igual a um existente
    Dado que exista um cliente registrado no sistema com o CPF "11111111111"
    E que o email do cliente seja "p.silva@teste.com"
    Quando for requisitado a criacao de um cliente com o nome igual a "Paulo", sobrenome igual a "Silva" e cpf igual a "11111111112" e email "p.silva@teste.com"
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 422
    E o erro retornado durante a requisicao feito para o endpoint cliente deve conter "E-mail já utilizado. Utilize outro e-mail."

  Cenário: Criação de um novo cliente sem nome
    Dado que nao exista um cliente registrado no sistema com o CPF "11111111112"
    Quando for requisitado a criacao de um cliente com o nome igual a "", sobrenome igual a "Silva" e cpf igual a "11111111112" e email "pedro.silva@teste.com"
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 400
    E o erro retornado durante a requisicao feito para o endpoint cliente deve conter "Nome é mandatorio"

  Cenário: Criação de um novo cliente sem sobrenome
    Dado que nao exista um cliente registrado no sistema com o CPF "11111111112"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "" e cpf igual a "11111111112" e email "pedro.silva@teste.com"
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 400
    E o erro retornado durante a requisicao feito para o endpoint cliente deve conter "Sobrenome é mandatorio"

  Cenário: Criação de um novo cliente sem cpf
    Dado que nao exista um cliente registrado no sistema com o CPF "11111111112"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "" e email "pedro.silva@teste.com"
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 400
    E o erro retornado durante a requisicao feito para o endpoint cliente deve conter "CPF é mandatorio"

  Cenário: Criação de um novo cliente sem email
    Dado que nao exista um cliente registrado no sistema com o CPF "11111111112"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111112" e email ""
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 400
    E o erro retornado durante a requisicao feito para o endpoint cliente deve conter "E-mail é mandatorio"

  Cenário: Criação de um novo cliente com nome, sobrenome e cpf iguais
    Dado que exista um cliente registrado no sistema com o CPF "111111111111"
    Quando for requisitado a criacao de um cliente com o nome igual a "Pedro", sobrenome igual a "Silva" e cpf igual a "11111111111" e email "pedro.silva@teste.com"
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 422
    E o erro retornado durante a requisicao feito para o endpoint cliente deve conter "Não é permitido inserir dois clientes com o mesmo nome, sobrenome e CPF"

  Cenário: Buscar clientes pelo cpf especifico
    Dado que exista um cliente registrado no sistema com o CPF "11111111111"
    Quando for realizado uma busca nos clientes pelo CPF "11111111111"
    Entao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 200

  Cenário: Buscar clientes pelo cpf inexistente
    Dado que nao exista um cliente registrado no sistema com o CPF "inexistente"
    Quando for realizado uma busca nos clientes pelo CPF "inexistente"
    Entao nao deve retornar um cliente
    E o status da busca pelo cliente deve ser igual a 404