import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  url = 'http://localhost:8080/products'; // api rest
  

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  // Obtem todas as products
  getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem um product pelo id
  getProductById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva product
  saveProduct(product: Product): Observable<Product> {
    return this.httpClient.post<Product>(this.url, JSON.stringify(product), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // utualiza product
  updateProduct(product: Product): Observable<Product> {
    return this.httpClient.patch<Product>(this.url + '/', JSON.stringify(product), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta product
  deleteProduct(product: Product) {
    return this.httpClient.delete<Product>(this.url + '/' + product.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorProduct = '';
    if (error.error instanceof Error) {
      // Erro ocorreu no lado do client
      errorProduct = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorProduct = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorProduct);
    return throwError(errorProduct);
  };

}