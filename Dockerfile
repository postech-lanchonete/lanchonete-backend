FROM mariadb:latest

# Variáveis de ambiente do MariaDB
ENV MYSQL_ROOT_PASSWORD=senha

# Copia o arquivo init.sql para o diretório de inicialização do banco de dados
COPY init.sql /docker-entrypoint-initdb.d/

# Configuração de rede
EXPOSE 3306
