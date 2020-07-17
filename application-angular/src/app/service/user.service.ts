import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = 'http://localhost:8080/users'; // api rest
  

  // injetando o HttpClient
  constructor(private httpClient: HttpClient) { }

  // Headers
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  // Obtem todas as users
  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }

  // Obtem um user pelo id
  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(this.url + '/' + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // salva user
  saveUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.url, JSON.stringify(user), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  // utualiza user
  updateUser(user: User): Observable<User> {
    return this.httpClient.patch<User>(this.url + '/', JSON.stringify(user), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // deleta user
  deleteUser(user: User) {
    return this.httpClient.delete<User>(this.url + '/' + user.id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorUser = '';
    if (error.error instanceof Error) {
      // Erro ocorreu no lado do client
      errorUser = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorUser = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorUser);
    return throwError(errorUser);
  };

}