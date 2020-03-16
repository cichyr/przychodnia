import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HelloResponse} from '../dto/hello-response';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HelloService {

  constructor(private httpClient: HttpClient) {
  }

  private static readonly HOSTNAME = 'http://localhost:8080';

  getHelloMessage(): Observable<HelloResponse> {
    return this.httpClient.get(HelloService.HOSTNAME + '/api/hello')
      //jeżeli nazwy pól w klasie i jsonie zgadzają się 1:1 to nie trzeba pisać poniższego kodu
      //klasa nie musi odwzorowywać wszystkich pól JSONA - definiujcie pola, które są wam potrzebne
      //klasy na przechwytywanie/wysyłanie danych z BE nazywamy DTO (Data Transfer Object) i na takie klasy zrobię folder specjalny
      .pipe(
        map(
          response => {
            return new HelloResponse(response['hello-message']);
          }
        )
      );
  }


}
