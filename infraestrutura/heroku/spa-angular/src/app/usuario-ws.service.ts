import { Injectable } from '@angular/core';

import Stomp from 'stompjs';
import SockJS from 'sockjs-client';

import { map } from 'rxjs/operators';
import { Subject } from 'rxjs/Subject';

import { URL } from './core/model/constantes/constantes';

export interface Message {
  isRemoved: boolean
}

@Injectable()
export class UsuarioWsService {

  public messages: Subject<Message> = new Subject<Message>();

  constructor() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection() {
    const ws = new SockJS(URL.baseUrlMensageria);
    const stompClient = Stomp.over(ws);

    stompClient.connect({}, () => {
      stompClient.subscribe(URL.usuariosNotificoes_WebSocket, (message: any) => {
        this.messages.next(<Message>message.body);
      });
    });

    stompClient.reconnect_delay = 5000;
    stompClient.debug = (str: string) => { };
  }

}
