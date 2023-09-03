
# Lanchonete do Bairro

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

## Como rodar o projeto

### Kubernetes

Uma vez dentro da pasta `infra` do projeto rode o seguinte comando em um terminal:
 
```yaml
kubectl apply -f db-svc.yaml
kubectl apply -f db-configmap.yaml
kubectl apply -f db-pod.yaml
kubectl apply -f lanchonetebairro-svc.yaml
kubectl apply -f lanchonetebairro-deployment.yaml
kubectl apply -f lanchonetebairro-hpa.yaml
kubectl get pods --watch

```

Este comando irá subir toda a infraestrutura do Kubernetes, que pode ser visualizada na [documentação geral](https://www.notion.so/danielmariadasilva/Lanchonete-do-Bairro-97145985ac3e4b65a077ff13866e66ad#927eb1f24e804f34b9aa1e8a70c30644).

Como estamos usando minikube, a porta da aplicacao deve ser descoberta antes de podermos usar o sistema. Para isso, rode o comando a seguir que retornará a porta onde a aplicação está rodando:

```
minikube service svc-lanchonetebairro-replica --url
```

Feito isso, entre no swagger utilizando a porta.

## Swagger

A documentação técnica da API pode ser acessada na URI [/swagger-ui/index.html](http://localhost:31001/swagger-ui/index.html), quando o sistema está rodando.

## Verificação de integridade (Health Check)

- [/actuator/health](http://localhost:31001/actuator/health): Informações de saúde da aplicação, incluindo o status do banco de dados.
- [/actuator/info](http://localhost:31001/actuator/health): Informações gerais sobre a aplicação, incluindo informações sobre o banco de dados.
- [/actuator/metrics](http://localhost:31001/actuator/health): Métricas da aplicação, como tempo de execução de consultas no banco de dados.
