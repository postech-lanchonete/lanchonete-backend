
# Lanchonete do Bairro

##### versão 1.0.0-POC

<img src="https://img.shields.io/badge/Java-17-important.svg?logo=java" alt="shield referente a versao do java">
<img src="https://img.shields.io/badge/Build Tool-Gradle-green.svg?logo=gradle" alt="shield referente ao uso de gradle">
[<img src="https://img.shields.io/badge/dockerhub-images-blue.svg?logo=docker" alt="shield referente ao docker hub">](https://hub.docker.com/repository/docker/danielmaria/lanchonetebairro-app/general)
<img src="https://img.shields.io/badge/Orchestrated by-Kubernetes-green.svg?logo=kubernetes" alt="shield referente ao uso de kubernetes">

Toda a documentação geral pode ser encontrada nesta página do [Notion](https://danielmariadasilva.notion.site/Lanchonete-do-Bairro-97145985ac3e4b65a077ff13866e66ad), assim como as versōes posteriores.

### Arquitetura limpa

//

### Como rodar o projeto

#### Kubernetes

Uma vez dentro da pasta `infra` do projeto rode o seguinte comando em um terminal:
 
```
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

### Swagger

A documentação técnica da API pode ser acessada na URI [/swagger-ui/index.html](http://localhost:31001/swagger-ui/index.html), quando o sistema está rodando.

### Verificação de integridade (Health Check)

- [/actuator/health](http://localhost:31001/actuator/health): Informações de saúde da aplicação, incluindo o status do banco de dados.
- [/actuator/info](http://localhost:31001/actuator/health): Informações gerais sobre a aplicação, incluindo informações sobre o banco de dados.
- [/actuator/metrics](http://localhost:31001/actuator/health): Métricas da aplicação, como tempo de execução de consultas no banco de dados.
