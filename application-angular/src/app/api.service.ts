import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Product } from './models/product';
import { User } from './models/user';
import { Order } from './models/order';
import { Cart } from './models/cart';
import { Message } from './models/message';
import { MessageParam } from './models/messageParam';
import { DeliveryJob } from './models/deliveryJob';
import { Recipient } from './models/recipient';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const apiUrl = 'http://localhost:8080/products';
const apiUser = 'http://localhost:8080/users';
const apiOrder = 'http://localhost:8080/orders';
const apiCart = 'http://localhost:8080/carts';
//Email
const apiMessage = 'http://localhost:8080/messages';
const apiDeliveryJob = 'http://localhost:8080/deliveryJob';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getProducts (): Observable<Product[]> {
    return this.http.get<Product[]>(apiUrl)
      .pipe(
        tap(products => console.log('leu os products')),
        catchError(this.handleError('getProducts', []))
      );
  }

  getProduct(id: number): Observable<Product> {
    const url = `${apiUrl}/${id}`;
    return this.http.get<Product>(url).pipe(
      tap(_ => console.log(`leu o product id=${id}`)),
      catchError(this.handleError<Product>(`getProduct id=${id}`))
    );
  }

  addProduct (product): Observable<Product> {
    return this.http.post<Product>(apiUrl, product, httpOptions).pipe(
      // tslint:disable-next-line:no-shadowed-variable
      tap((product: Product) => console.log(`adicionou o product com w/ id=${product.id}`)),
      catchError(this.handleError<Product>('addProduct'))
    );
  }

  addOrder (order): Observable<Order> {
    return this.http.post<Order>(apiOrder, order, httpOptions).pipe(
      tap((order: Order) => console.log(`adicionou o item com id=${order.id}`)),
      catchError(this.handleError<Order>('addOrder'))
    );
  }

  addUser (user): Observable<User> {
    return this.http.post<User>(apiUser, user, httpOptions).pipe(
      tap((user: User) => console.log(`adicionou o usuario com id=${user.id}`)),
      catchError(this.handleError<User>('addUser'))
    );
  }

  addCart (cart): Observable<Cart> {
    return this.http.post<Cart>(apiCart, cart, httpOptions).pipe(
      tap((cart: Cart) => console.log(`adicionou o pedido com id=${cart.id}`)),
      catchError(this.handleError<Cart>('addCart'))
    );
  }

  addMessage (message): Observable<Message> {
    return this.http.post<Message>(apiMessage, message, httpOptions).pipe(
      tap((message: Message) => console.log(`adicionou a message com id=${message.id}`)),
      catchError(this.handleError<Message>('addMessage'))
    );
  }

  addMessageParam (idMessage, message): Observable<MessageParam> {
    const url = `${apiUrl}/${idMessage}/params`;
    return this.http.post<MessageParam>(url, message, httpOptions).pipe(
      tap((messageParam: MessageParam) => console.log(`adicionou a messageParam com id=${messageParam.id}`)),
      catchError(this.handleError<MessageParam>('addMessageParam'))
    );
  }

  updateProduct(id, product): Observable<any> {
    const url = `${apiUrl}/${id}`;
    return this.http.put(url, product, httpOptions).pipe(
      tap(_ => console.log(`atualiza o produco com id=${id}`)),
      catchError(this.handleError<any>('updateProduct'))
    );
  }

  deleteProduct (id): Observable<Product> {
    const url = `${apiUrl}/delete/${id}`;

    return this.http.delete<Product>(url, httpOptions).pipe(
      tap(_ => console.log(`remove o product com id=${id}`)),
      catchError(this.handleError<Product>('deleteProduct'))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}