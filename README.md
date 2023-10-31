# 🍔 Lanchonete do Bairro - Backend

##### versão 1.0.0-POC

<img src="https://img.shields.io/badge/Java-17-important.svg?logo=java" alt="shield referente a versao do java"><img src="https://img.shields.io/badge/Build Tool-Gradle-green.svg?logo=gradle" alt="shield referente ao uso de gradle">[<img src="https://img.shields.io/badge/dockerhub-images-blue.svg?logo=docker" alt="shield referente ao docker hub">](https://hub.docker.com/repository/docker/danielmaria/lanchonetebairro-app/general) <img src="https://img.shields.io/badge/Orchestrated by-Kubernetes-green.svg?logo=kubernetes" alt="shield referente ao uso de kubernetes">

Toda a documentação geral pode ser encontrada nesta página do [Notion](https://danielmariadasilva.notion.site/Lanchonete-do-Bairro-97145985ac3e4b65a077ff13866e66ad), assim como as versōes posteriores.

## Arquitetura limpa

A arquitetura limpa é um conceito de design de software que promove a separação de responsabilidades em camadas bem definidas. A arquitetura limpa visa manter a independência das camadas, facilitando a manutenção, teste e evolução do software.

```markdown
├── main
|  ├── java
|  |  └── br
|  |     └── com
|  |        └── lanchonetebairro
|  |           ├── applicationrules <- 2. Application Business Rules
|  |           |  ├── exceptions
|  |           |  └── usecases
|  |           |        ├── cliente
|  |           |        ├── pedido
|  |           |        └── produto
|  |           ├── enterpriserules <- 1. Enterprise Business Rules
|  |           |  ├── entities
|  |           |  └── enums
|  |           ├── frameworksdrivers <- 4. Frameworks & Drivers
|  |           |  ├── external
|  |           |  |  ├── notificacao
|  |           |  |  └── pagamento
|  |           |  └── web
|  |           └── interfaceadapters <- 3. Interface Adapters
|  |              ├── adapter
|  |              ├── controllers
|  |              ├── dto
|  |              ├── gateways
|  |              ├── handler
|  |              └── repositories
```

As camadas do projeto foram divididas seguindo a logica proposta pela arquitetura limpa, sendo 4 as prinipais assinaladas na árvore de estrutura acima.

1. **Enterprise Business Rules**: Esta camada contém regras de negócios de alto nível e princípios que são específicos para a organização ou domínio de negócios.
2. **Application Business Rules**: Aqui, você encontra as regras de negócios da aplicação, que são específicas para a aplicação em si, independentemente do domínio de negócios. É onde a lógica de negócios central é implementada.
3. **Interface Adapters**: Esta camada lida com a interação entre a aplicação e o mundo exterior. Inclui adaptadores para interfaces de usuário, bancos de dados, serviços externos e qualquer outra coisa que não seja parte da aplicação em si.
4. **Frameworks & Drivers**: É onde você integra frameworks, bibliotecas e drivers externos necessários para executar a aplicação. Isso inclui o banco de dados, frameworks web, ferramentas de comunicação e assim por diante. É a fronteira entre a aplicação e o ambiente externo.

## Pipeline

Quando um desenvolvedor faz um commit no GitHub, a plataforma identifica se a ação deve ser acionada. Se sim, a pipeline inicia com o CI, que executa testes unitários. Se os testes forem bem-sucedidos, a imagem Docker é construída e enviada para o Docker Hub. Após a conclusão bem-sucedida do CI, o CD entra em ação. Ele começa baixando a imagem Docker, remove o contêiner em execução na instância EC2 e substitui-o pela nova imagem. Esse fluxo garante que seu software seja testado automaticamente e implantado de forma eficiente, melhorando a qualidade e a entrega contínua de novas funcionalidades.

![image](https://github.com/postech-lanchonete/lanchonete-backend/assets/20681811/15ad5f82-7b39-43d9-8f4b-b0778f9daeb2)



## Swagger

A documentação técnica da API pode ser acessada na URI [/swagger-ui/index.html](http://localhost:31001/swagger-ui/index.html), quando o sistema está rodando.

## Verificação de integridade (Health Check)

- [/actuator/health](http://localhost:31001/actuator/health): Informações de saúde da aplicação, incluindo o status do banco de dados.
- [/actuator/info](http://localhost:31001/actuator/health): Informações gerais sobre a aplicação, incluindo informações sobre o banco de dados.
- [/actuator/metrics](http://localhost:31001/actuator/health): Métricas da aplicação, como tempo de execução de consultas no banco de dados.
