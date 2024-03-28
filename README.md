# **Atividade Prática Eureka - PT**
#### Versão em Português 🇧🇷
### [Link do Repositório no GitHub](https://github.com/Paulo-2048/distributed-sum-eureka)

### Este repositório contém um conjunto de aplicações Java Spring Boot com Eureka, que incluem as seguintes aplicações:

1. **distributed-sum-app-server**: Este é o servidor principal responsável pela coordenação das chamadas entre as outras aplicações.
2. **eureka-client-app-a**: Uma aplicação cliente que faz chamadas para a APP B.
3. **eureka-client-app-b**: Uma aplicação cliente que faz chamadas para a APP C e soma o valor retornado com um valor aleatório.
4. **eureka-client-app-c**: Uma aplicação cliente que retorna um número aleatório.

### Funcionamento

- **APP A** faz chamadas para **APP B**, que por sua vez chama **APP C**.
- **APP C** retorna um número aleatório para **APP B**.
- **APP B** soma este valor com um valor aleatório gerado internamente e retorna para **APP A**.

### Verificação de Status

Antes de cada chamada de **APP A** para **APP B** e de **APP B** para **APP C**, é feita uma verificação para garantir que as aplicações estão ativas. Se alguma aplicação estiver inativa, os seguintes valores serão retornados:

- **-1**: Se a **APP B** estiver inativa.
- **-2**: Se a **APP C** estiver inativa.

### Explicação Metódos Comuns do Controller

O arquivo SumAppController.java contém um controller genérico que facilita a comunicação entre as aplicações clientes. Ele oferece os seguintes endpoints:

- **/health**: Retorna o status da aplicação.
- **/discover**: Retorna as aplicações registradas no servidor Eureka.
- **/actuator/info**: Retorna informações da aplicação atual.
- **/receiveCall/{name}**: Recebe chamadas de outras aplicações.
- **/makeCall/{name}**: Realiza chamadas para outras aplicações.
- **/randomCall**: Realiza uma chamada para uma aplicação específica e retorna um número aleatório.

### Explicação Classes Extras

Foram criadas algumas classes extras para facilitar a comunicação entre as aplicações. São elas:

1. **OperationNumbers**: Classe que a abstração dos cálculos realizados pelas aplicações, mantendo seu valor inicial, valor final e histórico de operações.
2. **OperationsEnum**: Enum que define as operações matemáticas que podem ser realizadas pelas aplicações.
3. **OperationIdManager**: Classe que cria os IDs das operações realizadas pelas aplicações.
4. **ApiResponse**: Classe que abstrata relacionada a resposta das aplicações.
5. **ResponseSuccess**: Classe que abstrai a resposta de sucesso das aplicações, contendo uma mensagem e informações sobre o número gerado.
6. **ResponseFail**: Classe que abstrai a resposta de erro das aplicações, contendo uma mensagem e informações adicionais.

### Tecnologias Utilizadas

- Java Spring Boot
- Eureka (para descoberta de serviços)
- Maven

### Desenvolvedor e Disciplina

Este conjunto de aplicações foi desenvolvido por **Paulo Vitor de Santana Santos** para a disciplina de **Sistemas Distribuídos**, ministrada pelo professor **Everton Mendonça de Jesus** na **Universidade Católica de Salvador**.

### Licença

Este projeto está licenciado sob a Apache-2.0.

----
---
---

# **Eureka Practical Activity - EN**
#### English Version
### [GitHub Repository Link](https://github.com/Paulo-2048/distributed-sum-eureka)

### This repository contains a set of Java Spring Boot applications with Eureka, including the following applications:

1. **distributed-sum-app-server**: This is the main server responsible for coordinating calls between the other applications.
2. **eureka-client-app-a**: A client application that makes calls to APP B.
3. **eureka-client-app-b**: A client application that makes calls to APP C and adds the returned value to a random value.
4. **eureka-client-app-c**: A client application that returns a random number.

### Operation

- **APP A** calls **APP B**, which in turn calls **APP C**.
- **APP C** returns a random number to **APP B**.
- **APP B** adds this value to a randomly generated value internally and returns it to **APP A**.

### Status Verification

Before each call from **APP A** to **APP B** and from **APP B** to **APP C**, a check is made to ensure that the applications are active. If any application is inactive, the following values will be returned:

- **-1**: If **APP B** is inactive.
- **-2**: If **APP C** is inactive.

### Common Controller Methods Explanation

The SumAppController.java file contains a generic controller that facilitates communication between client applications. It provides the following endpoints:

- **/health**: Returns the application status.
- **/discover**: Returns the applications registered in the Eureka server.
- **/actuator/info**: Returns information about the current application.
- **/receiveCall/{name}**: Receives calls from other applications.
- **/makeCall/{name}**: Makes calls to other applications.
- **/randomCall**: Makes a call to a specific application and returns a random number.

### Extra Classes Explanation

Some additional classes have been created to facilitate communication between applications. They are:

1. **OperationNumbers**: A class that abstracts the calculations performed by the applications, maintaining their initial value, final value, and operation history.
2. **OperationsEnum**: An enum that defines the mathematical operations that can be performed by the applications.
3. **OperationIdManager**: A class that creates IDs for the operations performed by the applications.
4. **ApiResponse**: A class that abstracts the response from the applications.
5. **ResponseSuccess**: A class that abstracts the successful response from the applications, containing a message and information about the generated number.
6. **ResponseFail**: A class that abstracts the error response from the applications, containing a message and additional information.

### Technologies Used

- Java Spring Boot
- Eureka (for service discovery)
- Maven

### Developer and Course Information

This set of applications was developed by **Paulo Vitor de Santana Santos** for the **Distributed Systems** course, taught by Professor **Everton Mendonça de Jesus** at **Universidade Católica de Salvador**.

### License

This project is licensed under the Apache-2.0 license.