import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors } from '@angular/common/http';
import { customHttpInterceptorInterceptor } from './services/customHttpInterceptor/custom-http-interceptor.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideClientHydration(), 
    provideHttpClient(withInterceptors([customHttpInterceptorInterceptor])), 
    //{provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true}
  ]
};
