import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private loginService: LoginService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authHeaderString = this.loginService.getAuthenticatedToken();
    if (authHeaderString) {
      req = req.clone({
        setHeaders: {
          Authorization: authHeaderString
        }
      })
    }
    return next.handle(req);

  }
}
