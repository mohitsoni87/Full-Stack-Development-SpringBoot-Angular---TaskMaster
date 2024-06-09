import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FooterComponent } from './footer/footer.component';
import { AuthenticationService } from './services/Authentication/authentication.service';
import { FormBuilder } from '@angular/forms';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, LoginComponent, NavBarComponent, FooterComponent]
})
export class AppComponent {
  title = 'ToDoApp';

  constructor(private router: Router,
    private authenticationService: AuthenticationService,
  ){}

  ngOnInit(): void {
    if(!this.authenticationService.isJWTokenAvailable()){
      //Getting JWT Token
      console.log("Fetching JWT");
      this.authenticationService.getJWToken()
    }
  }


}
