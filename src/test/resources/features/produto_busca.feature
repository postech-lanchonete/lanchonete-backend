# coding: utf-8
# language: pt

Funcionalidade: Busca de produto

  Cenário: Buscar todos os produtos
    Dado que um usuário queira buscar todos os produtos
    Quando o usuário enviar uma requisição GET para "/v1/produtos" com a categoria desejada
    Então o resultado da busca deve conter um produto com nome "Refrigerante" com o preço 3,00
    E o status deve ser 200

  Cenário: Busca de produto por categoria
    Dado que um usuário queira buscar todos os produtos da categoria "LANCHE"
    Quando o usuário enviar uma requisição GET para "/v1/produtos" com a categoria desejada
    Então o resultado da busca deve conter um produto com nome "Batata frita" com o preço 5,00
    E o status deve ser 200

  Cenário: Busca de produto por categoria inexistente
      Dado que um usuário queira buscar todos os produtos da categoria "INEXISTENTE"
      Quando o usuário enviar uma requisição GET para "/v1/produtos" com a categoria desejada
      Então o resultado da busca deve ser vazio
      E o status deve ser 400
      E conter um erro da mensagem contendo "CategoriaProduto não encontrado para o valor"

  Cenário: Busca de produto por categoria vazia
    Dado que um usuário queira buscar todos os produtos da categoria "SOBREMESA"
    Quando o usuário enviar uma requisição GET para "/v1/produtos" com a categoria desejada
    Então o resultado da busca deve ser uma lista vazia
    E o status deve ser 200

  Cenário: Busca de produto por id
    Dado que um usuário queira buscar o produto com o ID 1
    Quando o usuário enviar uma requisição GET para "/v1/produtos/" e o ID do produto
    Então o resultado da busca deve conter o produto com nome "Batata frita" com o preço 5,00
    E o status deve ser 200

  Cenário: Busca de produto por id
    Dado que um usuário queira buscar o produto com o ID 100
    Quando o usuário enviar uma requisição GET para "/v1/produtos/" e o ID do produto
    Então o resultado da busca deve ser vazio
    E o status deve ser 404
