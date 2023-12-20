# coding: utf-8
# language: pt

Funcionalidade: Edição de produto

  Cenário: Editar o nome do produto
    Dado que exista um produto com id 1 e o atributo "nome" diferente a "X-Burguer"
    Quando o usuário enviar uma requisição PUT para "/v1/produtos/" com o atributo "nome" igual a "X-Burguer" para o id 1
    Então o resultado da busca deve conter um produto com atributo "nome" igual a "X-Burguer"
    E o status deve ser 202

  Cenário: Editar o preço do produto
    Dado que exista um produto com id 1 e o atributo "preco" diferente a "100.5"
    Quando o usuário enviar uma requisição PUT para "/v1/produtos/" com o atributo "preco" igual a "100.5" para o id 1
    Então o resultado da busca deve conter um produto com atributo "preco" igual a "100.5"
    E o status deve ser 202

  Cenário: Editar a categoria do produto
    Dado que exista um produto com id 1 e o atributo "categoria" diferente a "SOBREMESA"
    Quando o usuário enviar uma requisição PUT para "/v1/produtos/" com o atributo "categoria" igual a "SOBREMESA" para o id 1
    Então o resultado da busca deve conter um produto com atributo "preco" igual a "100.5"
    E o status deve ser 202

  Cenário: Editar a descrição do produto
    Dado que exista um produto com id 1 e o atributo "descricao" diferente a "Descricao 123"
    Quando o usuário enviar uma requisição PUT para "/v1/produtos/" com o atributo "descricao" igual a "Descricao 123" para o id 1
    Então o resultado da busca deve conter um produto com atributo "descricao" igual a "Descricao 123"
    E o status deve ser 202

  Cenário: Editar a imagem do produto
    Dado que exista um produto com id 1 e o atributo "imagem" diferente a "imagem.jpg"
    Quando o usuário enviar uma requisição PUT para "/v1/produtos/" com o atributo "imagem" igual a "imagem.jpg" para o id 1
    Então o resultado da busca deve conter um produto com atributo "imagem" igual a "imagem.jpg"
    E o status deve ser 202
