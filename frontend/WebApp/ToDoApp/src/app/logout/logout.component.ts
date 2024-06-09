import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/Authentication/authentication.service';

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent implements OnInit{

  constructor(private router: Router,
    private authenticator: AuthenticationService
  ){}

  ngOnInit(): void {
      this.authenticator.logout();
      this.router.navigate(['/'])
      console.log("Router URL:", this.router.url)
  }

}
