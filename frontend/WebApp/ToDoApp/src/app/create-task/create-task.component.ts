import { Component } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpConnectorService } from '../services/HttpConnector/http-connector.service';
import { TaskInterface } from '../model/TaskInterface';
import { Task } from '../model/Task';
import { AuthenticationService } from '../services/Authentication/authentication.service';

@Component({
  selector: 'app-create-task',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-task.component.html',
  styleUrl: './create-task.component.css'
})
export class CreateTaskComponent {
  taskName: string = '';
  taskDescription: string = '';

  task: Task = new Task();

  constructor(private route: ActivatedRoute,
    private _httpConnectorService: HttpConnectorService,
    private router: Router,
    private authenticationService: AuthenticationService
  ){}

  onSubmit() {
    if (this.taskName && this.taskDescription) {
      // Handle task creation logic here
      console.log('Task Name:', this.taskName);
      console.log('Task Description:', this.taskDescription);

      this.task.description = this.taskDescription;
      this.task.taskName = this.taskName;
      this.task.userId = this.authenticationService.getUserId();
      console.log(this.task);
      this._httpConnectorService.createTask(this.task).subscribe({
        next:(task: TaskInterface)=>{
          // Reset form fields
          this.taskName = '';
          this.taskDescription = '';
          // Reset form fields
          this.task = new Task();
          this.router.navigate(['']);
        },
        error:err=>{
          //If User not found, then redirect to Registration page so the user can register
          //this.router.navigate(['register']);
          console.log("Error")
        }
      });
    }
  }
}
