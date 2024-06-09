import { HttpInterceptorFn } from '@angular/common/http';
import { AuthenticationService } from '../Authentication/authentication.service';
import { inject } from '@angular/core';

export const customHttpInterceptorInterceptor: HttpInterceptorFn = (request, next) => {
  /*
  *Basic Authentication
  
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let springTechnicalUsername = 'todo';
    let springTechnicalPassword = 'todo-task-management';
    let AuthorizationHeader =  'Basic ' + window.btoa(springTechnicalUsername + ':' + springTechnicalPassword);

    request = request.clone({
      setHeaders: {
        Authorization: AuthorizationHeader,
        //'Access-Control-Allow-Origin': 'true'
      }
    });
  
    return next.handle(request);
  }*/

  if (request.headers.get("X-Skip-Interceptor")){
    return next(request);
  }
    
    let jwtoken:any;
    const authenticationService = inject(AuthenticationService);

    if(!authenticationService.isJWTokenAvailable()){
      jwtoken = authenticationService.getJWToken();
      if(jwtoken != null){
        sessionStorage.setItem("JWToken", jwtoken);
      }
    }else{
      jwtoken = sessionStorage.getItem("JWToken");
    }

    //console.log("jwtoken", jwtoken);

    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${jwtoken}`
      }
    });
  
    return next(request);
};
