import { Routes } from '@angular/router';

import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';
import { LogoutComponent } from './logout/logout.component';
import { authGuard } from './services/Auth/auth.guard';
import { CreateTaskComponent } from './create-task/create-task.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
    {
        path: '', component: HomePageComponent,
        //adding authguard so only logged in users can view their profile
        canActivate: [authGuard],
    },
    {
        path: 'home/:username', component: HomePageComponent,
        canActivate: [authGuard], 
    },
    {
        path: 'registration', component: RegisterComponent,
    },
    {
        path: 'createNewTask', component: CreateTaskComponent,
        canActivate: [authGuard],
    },
    {
        path: 'settings', component: HomePageComponent,
        canActivate: [authGuard],
    },
    {
        path: 'login', component: LoginComponent
    },
    {
        path: 'logout', component: LogoutComponent
    },
    {
        path: '**', component: ErrorComponent
    }
];
