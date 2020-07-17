import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Cart } from '../models/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  url = 'http://localhost:8080/carts'; // api rest
  

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  // Obtem todas as carts
  getCarts(): Observable<Cart[]> {
    return this.httpClient.get<Cart[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem um cart pelo id
  getCartById(id: number): Observable<Cart> {
    return this.httpClient.get<Cart>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva cart
  saveCart(cart: Cart): Observable<Cart> {
    return this.httpClient.post<Cart>(this.url, JSON.stringify(cart), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // utualiza cart
  updateCart(cart: Cart): Observable<Cart> {
    return this.httpClient.patch<Cart>(this.url + '/', JSON.stringify(cart), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta cart
  deleteCart(cart: Cart) {
    return this.httpClient.delete<Cart>(this.url + '/' + cart.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorCart = '';
    if (error.error instanceof Error) {
      // Erro ocorreu no lado do client
      errorCart = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorCart = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorCart);
    return throwError(errorCart);
  };

}