## O serviço de de mensageria

O serviço de mensageria foi aqui implementado com:
- a bibliteca `stomp.js`, do javascript, do lado do cliente Angular
- o pacote `spring-boot-starter-websocket` do springboot

O efeito esperado para essa implementação simples pode ser visto abaixo. Note que temos duas
janelas abertas para o navegador. Todas as ações executadas em uma delas está sendo automaticamente
detectada pela janela vizinha. Isso está sendo feito porque temos um serviço de mensageria
sendo monitorado pelo cliente.

![image](websocket.gif)

