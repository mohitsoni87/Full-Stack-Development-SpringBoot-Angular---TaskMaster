import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthenticationService } from '../services/Authentication/authentication.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink, NgIf],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent  implements OnInit{
  isUserLoggedIn = false;


  constructor(private router: Router,
    public authenticator: AuthenticationService
  ){}

  ngOnInit(): void {
    this.isUserLoggedIn = this.authenticator.isUserLoggedIn();
  }
}