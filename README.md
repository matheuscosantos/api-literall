# API LiterAll
## Introdução
Essa API realiza a criação e consulta de:
- Eventos literários, 
- Bibliotecas, 
- Sebos e 
- Anuncios de livros para doação

## Tecnologias
Foi construída com o Framework Spring Boot.

## Arquitetura
A aplicação possui as seguintes camadas:
- Model: Implementa as entidades
- Form: Implementa a camada para transformação do JSON recebido pelo front-end no modelo da entidade
- Repository: Implementa a conexão com o banco de dados, através da biblioteca JPA Repository
- Service: Implementa as regras de negócio
- Controller: Implementa os endpoints

## Documentação
A documentação foi criada utilizando a biblioteca Swagger, com deploy no Heroku e pode ser acessada nesse [link](https://literall.herokuapp.com/swagger-ui.html).

## Observações
Essa API será consumida por uma plicação desenvolvida em Flutter que está em desenvolvimento, assim que estiver disponível terá o link compartilhado aqui.
