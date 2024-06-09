import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AuthenticationService } from '../services/Authentication/authentication.service';
import { UserInterface } from '../model/UserInterface';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports: [ ReactiveFormsModule, NgIf, FormsModule ]
})
export class LoginComponent implements OnInit{

  navigateToRegistration() {
    this.router.navigate(['/registration']);
  }
  
  userForm: FormGroup;
  user: UserInterface;
  invalidLogin = false;
  errorOccurred: Boolean;
  errorMessage: String;
  isUserLoggedIn = false;

  constructor(private router: Router,
    private authenticationService: AuthenticationService,
    private formBuilder: FormBuilder,
  ){}

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({ // Initialize userForm in ngOnInit
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    if(!this.authenticationService.isJWTokenAvailable()){
      //Getting JWT Token
      console.log("Fetching JWT");
      this.authenticationService.getJWToken()
    }

    this.isUserLoggedIn = this.authenticationService.isUserLoggedIn();
    if(this.isUserLoggedIn){
      this.router.navigate(['home', this.authenticationService.getUserName()])
    }
    console.log("Login Component: ", this.isUserLoggedIn);
  }

  submitLogin() {
    const userObject = { ...this.user, ...this.userForm.value}     
    console.log("UserObject: ", userObject);
    this.authenticationService.authenticate(userObject).subscribe({
      next:(user: UserInterface)=>{
        this.invalidLogin = false;
        sessionStorage.setItem("authenticatedUser", this.userForm.value.username);
        console.log(user);
        sessionStorage.setItem("userId", user.id.toString());
        console.log("Correct Credentials");
        this.isUserLoggedIn = true;
        this.router.navigate(["home", this.userForm.value.username])
      },
      error:(err)=>{
        this.invalidLogin = true;
        this.isUserLoggedIn = false;
        if(err.status == 400){
          this.errorMessage = "Invalid Credentials. Please try again"
        }else{
          this.errorMessage = "Internal Server Error. Please try again later."
        }
      }
    })
  }
}
