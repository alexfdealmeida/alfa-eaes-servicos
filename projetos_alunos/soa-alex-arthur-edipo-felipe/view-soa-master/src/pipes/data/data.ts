import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'data',
})
export class DataPipe implements PipeTransform {

  transform(data: Date) {

    let dia = this.formateNumero(data.getDate(), 2);
    let mes = this.formateNumero(data.getMonth() + 1, 2);
    let ano = data.getFullYear();

    let hora = this.formateNumero(data.getHours(), 2);
    let minutos = this.formateNumero(data.getMinutes(), 2);
    let segundos = this.formateNumero(data.getSeconds(), 2);

    return `${dia}/${mes}/${ano} ${hora}:${minutos}:${segundos}`;
  }

  private formateNumero(numero: number, tamanho: number) {

    let valorRetorno = "" + numero;

    while (valorRetorno.length < tamanho) {

      valorRetorno = "0" + valorRetorno;
    }

    return valorRetorno;
  }

}
