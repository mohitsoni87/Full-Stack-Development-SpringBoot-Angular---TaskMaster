import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ToDoInterface } from '../../model/ToDoInterface';
import { UserInterface } from '../../model/UserInterface';
import { API_URL } from '../../app.constants';
import { TokenInterface } from '../../model/TokenInterface';
import { ResponseInterface } from '../../model/ResponseInterface';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


  constructor(private router: Router, private _httpClient: HttpClient) { }

  authenticate(userObject: UserInterface):Observable<UserInterface>{

    //Basic Authentication Header
    /*let reqHeaders = new HttpHeaders({
      'Authorization': this.createAuthenticationHeader()
    })*/

    /*
    //JWT based Authentication is available in CustomHttpInterceptor in services folder
    
    let jwtoken:any;
    console.log(!this.isJWTokenAvailable());
    if(!this.isJWTokenAvailable()){
      jwtoken = this.getJWToken();
      if(jwtoken != null){
        sessionStorage.setItem("JWToken", jwtoken);
      }
    }else{
      jwtoken = sessionStorage.getItem("JWToken");
    }

    let reqHeaders = new HttpHeaders({
      'Authorization': `Bearer ${jwtoken}`,
      'Access-Control-Allow-Origin': 'true'
    })*/

    return this._httpClient.post<UserInterface>(`${API_URL}/user/authenticate`, userObject)
    //return this._httpClient.post<ToDoInterface>(`http://localhost:8080/user/authenticate`, userObject, {headers: reqHeaders})
  }

  registerUser(user: UserInterface){
    return this._httpClient.post<ResponseInterface>(`${API_URL}/user/register`, user)
  }

  userNameTaken(currentUserName: String) {
    return this._httpClient.get(`${API_URL}/user/checkUserName/${currentUserName}`)
  }

  isUserLoggedIn(){
    return !(sessionStorage.getItem("authenticatedUser") === null)
  }

  isJWTokenAvailable(){
    console.log("Method - isJWTokenAvailable");
    return !(sessionStorage.getItem("JWToken") === null)
  }

  getUserName(){
    return sessionStorage.getItem("authenticatedUser");
  }

  getUserId(){
    const userIdString = sessionStorage.getItem('userId');
    if(userIdString == null){
      return 0;
    }
    const userId = parseInt(userIdString, 10);
    return userId;
  }

  getJWToken(){
    if(sessionStorage.getItem("JWToken")){
      return (sessionStorage.getItem("JWToken"));
    }
    let username = 'todo';
    let password = 'todo-task-management'


    //To skip interceptor for the Basic Auth in order to fetch JWT token
    const headers = new HttpHeaders({
      'X-Skip-Interceptor': 'true'
    });

    this._httpClient.post<TokenInterface>(`${API_URL}/authenticate`, {
      username,
      password
    }, {headers}).subscribe({
      next:(token: TokenInterface)=>{
        console.log("Saving token..");
        sessionStorage.setItem("JWToken", token.token);
      },
      error:(err)=>{
      }
    })
    return sessionStorage.getItem("JWToken");
    
  }

  logout(){
    sessionStorage.clear()
  }

  createAuthenticationHeader(){
    let springTechnicalUsername = 'todo';
    let springTechnicalPassword = 'todo-task-management'
    return 'Basic ' + window.btoa(springTechnicalUsername + ':' + springTechnicalPassword);
  }


}
