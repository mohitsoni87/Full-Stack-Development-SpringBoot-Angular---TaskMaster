import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/Authentication/authentication.service';
import { UserInterface } from '../model/UserInterface';
import { ResponseInterface } from '../model/ResponseInterface';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registrationForm: FormGroup;
  user: UserInterface;

  usernameTaken: Boolean;
  currentUserName: String;

  constructor(private fb: FormBuilder, private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.registrationForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required]
    });
  }

  onContainerInteraction(event: FocusEvent): void {
    const usernameInput = document.querySelector('[formControlName="username"]');
    if (this.registrationForm.get('username')?.value && !usernameInput?.contains(event.relatedTarget as Node)) {
      if (this.registrationForm.value.username !== this.currentUserName) {
        this.currentUserName = this.registrationForm.value.username;
        this.authenticationService.userNameTaken(this.registrationForm.value.username).subscribe({
          next: (response) => {
            this.usernameTaken = false;
          },
          error: (err) => {
            if (err.status === 400) {
              console.log("400 Error");
              this.usernameTaken = true;
            }
          }
        });
      }
    }
  }
  
  submitRegistration() {
    if (this.registrationForm.valid) {
      // Handle registration logic here
      const user = { ...this.user, ...this.registrationForm.value}     
      console.log('Registration successful', this.registrationForm.value.username);
      console.log('Registration successful', user);
      // Redirect to login page or another page after successful registration
      this.authenticationService.registerUser(user).subscribe({
        next:(userResp: UserInterface)=>{
          sessionStorage.setItem("authenticatedUser", user.username);
          console.log(userResp.id.toString());
          sessionStorage.setItem("userId", userResp.id.toString());
          this.router.navigate(["home", user.username])
        },
        error:(err)=>{
        }
      })
      //this.router.navigate(['/login']);
    } else {
      console.log('Form is invalid');
    }
  }

}
