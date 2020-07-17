import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  url = 'http://localhost:8080/orders'; // api rest
  

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  // Obtem todas as orders
  getOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem um order pelo id
  getOrderById(id: number): Observable<Order> {
    return this.httpClient.get<Order>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva order
  saveOrder(order: Order): Observable<Order> {
    return this.httpClient.post<Order>(this.url, JSON.stringify(order), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // utualiza order
  updateOrder(order: Order): Observable<Order> {
    return this.httpClient.patch<Order>(this.url + '/', JSON.stringify(order), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta order
  deleteOrder(order: Order) {
    return this.httpClient.delete<Order>(this.url + '/' + order.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorOrder = '';
    if (error.error instanceof Error) {
      // Erro ocorreu no lado do client
      errorOrder = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorOrder = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorOrder);
    return throwError(errorOrder);
  };

}