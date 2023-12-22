# coding: utf-8
# language: pt

Funcionalidade: Produtos

  Cenário: Criação de um novo produto
    Dado que nao exista um produto registrado no sistema com o nome "Batata frita"
    Quando for criado um produto o nome igual a "Batata frita"
    E preço igual a 5,00
    E categoria igual a "LANCHE"
    E o usuário enviar uma requisição com os dados do produto
    Entao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 201

  Cenário: Criação de um novo produto com nome já existente
    Dado que exista um produto registrado no sistema com o nome "Batata frita"
    Quando for criado um produto o nome igual a "Batata frita"
    E preço igual a 6,00
    E categoria igual a "SOBREMESA"
    E o usuário enviar uma requisição com os dados do produto
    Entao nao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 422
    E o erro retornado durante a requisicao feito para o endpoint produto deve conter "Não é permitido inserir dois produtos com o mesmo nome"


  Cenário: Criação de um novo produto sem nome
    Quando for criado um produto o nome igual a ""
    E preço igual a 5,00
    E categoria igual a "LANCHE"
    E o usuário enviar uma requisição com os dados do produto
    Entao nao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 400
    E o erro retornado durante a requisicao feito para o endpoint produto deve conter "Nome do produto é obrigatório"

  Cenário: Criação de um novo produto com preço inválido
    Dado que nao exista um produto registrado no sistema com o nome "Hamburger"
    Quando for criado um produto o nome igual a "Hamburger"
    E preço igual a -5,00
    E categoria igual a "LANCHE"
    E o usuário enviar uma requisição com os dados do produto
    Entao nao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 400
    E o erro retornado durante a requisicao feito para o endpoint produto deve conter "Preço do produto deve ser maior que zero"

    ####

  Cenário: Editar o nome do produto
    Dado que exista um produto registrado no sistema com o nome "Batata frita"
    Quando for criado um produto o nome igual a "Hamburger"
    E o usuário enviar uma requisição de edicao com os dados deste novo produto
    Entao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 202

  Cenário: Editar o preço do produto
    Dado que exista um produto registrado no sistema com o nome "Hamburger"
    E o preco do produto seja diferente a 100.5
    Quando for criado um produto o nome igual a "Hamburger"
    E preço igual a 100.5
    E o usuário enviar uma requisição de edicao com os dados deste novo produto
    Entao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 202

  Cenário: Editar a categoria do produto
    Dado que exista um produto registrado no sistema com o nome "Hamburger"
    E categoria do produto seja diferente a "SOBREMESA"
    Quando for criado um produto o nome igual a "Hamburger"
    E categoria igual a "SOBREMESA"
    E o usuário enviar uma requisição de edicao com os dados deste novo produto
    Entao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 202

  Cenário: Editar a descrição do produto
    Dado que exista um produto registrado no sistema com o nome "Hamburger"
    Quando for criado um produto o nome igual a "Hamburger"
    E descricao igual a "Hamburguer de carne"
    E o usuário enviar uma requisição de edicao com os dados deste novo produto
    Entao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 202

  Cenário: Editar a imagem do produto
    Dado que exista um produto registrado no sistema com o nome "Hamburger"
    Quando for criado um produto o nome igual a "Hamburger"
    E imagem igual a "hamburguer.jpg"
    E o usuário enviar uma requisição de edicao com os dados deste novo produto
    Entao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 202

  Cenário: Buscar todos os produtos
    Quando for realizado uma busca nos produtos pela categoria ""
    Entao deve retornar uma lista de produtos com 1 produtos
    E o status da busca pelos produtos deve ser igual a 200
    E o resultado da busca deve conter um produto com nome "Hamburger"

  Cenário: Busca de produto por categoria vazia
    Quando for realizado uma busca nos produtos pela categoria "LANCHE"
    Entao deve retornar uma lista de produtos com 0 produtos
    E o status da busca pelos produtos deve ser igual a 200

  Cenário: Busca de produto por categoria com itens
    Quando for realizado uma busca nos produtos pela categoria "SOBREMESA"
    Entao deve retornar uma lista de produtos com 1 produtos
    E o status da busca pelos produtos deve ser igual a 200
    E o resultado da busca deve conter um produto com nome "Hamburger"

  Cenário: Busca de produto por categoria inexistente
    Quando for realizado uma busca nos produtos pela categoria "INEXISTENTE"
    E o status da busca pelos produtos deve ser igual a 400

  Cenário: Busca de produto por id
    Quando for realizado uma busca nos produtos pelo id 1
    Entao o resultado da busca deve conter o produto com nome "Hamburger"
    E o status da busca pelo produto deve ser igual a 200

  Cenário: Busca de produto por id inexistente
    Quando for realizado uma busca nos produtos pelo id 100
    Entao nao deve retornar um produto
    E o status da busca pelo produto deve ser igual a 404
