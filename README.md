# Lanchonete do Bairro
##### versão 1.0.0-POC

Toda a documentação geral pode ser encontrada nesta página do [Notion](https://danielmariadasilva.notion.site/Lanchonete-do-Bairro-97145985ac3e4b65a077ff13866e66ad), assim como as versōes posteriores.

### Swagger

A documentação técnica da API pode ser acessada na URI [/swagger-ui/index.html](http://localhost:31001/swagger-ui/index.html), quando o sistema está rodando.


### Verificação de integridade (Health Check)

- [/actuator/health](http://localhost:31001/actuator/health): Informações de saúde da aplicação, incluindo o status do banco de dados.
- [/actuator/info](http://localhost:31001/actuator/health): Informações gerais sobre a aplicação, incluindo informações sobre o banco de dados.
- [/actuator/metrics](http://localhost:31001/actuator/health): Métricas da aplicação, como tempo de execução de consultas no banco de dados.


### Como rodar o projeto

#### Prerequisitos
- [x] Docker instalado e rodando
- [x] Kubernetes instalado e rodando
- [x] Gradle instalado
- [x] Java 17

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
Este comando irá subir toda a infraestrutura do Kubernetes, que pode ser vizualida na documentação geral, e a aplição estaraá rodando na porta 31001.