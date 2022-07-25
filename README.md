# Description
Scalable chat application with websocket communication, STOMP protocol for messaging and server-to-server message dispatch using RabbitMQ


# ChatApplication
Spring boot websocket backend and reactjs client

Server:
    - Spring boot Websocket

Client
    - ReactJS

## ReactJS screen

To start:
    
### Client
        - npm install (in the react-client folder)
        - npm start
    
### Server
        - mvn spring-boot:run (in the spring-ws-server)

### Docker
        - docker run -d --hostname localhost -p 61613:61613 -p 5672:5672 -p 15672:15672 --name websocket-rabbit rabbitmq:3-management
        - rabbitmq-plugins enable rabbitmq_stomp
