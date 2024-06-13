import { Component, ElementRef, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/Authentication/authentication.service';
import { HttpConnectorService } from '../services/HttpConnector/http-connector.service';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskInterface, TaskStatus } from '../model/TaskInterface';
import { forkJoin } from 'rxjs';
import { stat } from 'node:fs';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [NgFor, FormsModule, NgIf],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit{

  username: any;
  isUserLoggedIn = false;
  taskToDo: { [key: number]: TaskInterface } = {};
  draftTaskToDo: { [key: number]: TaskInterface } = {};
  activeTaskToDoList: TaskInterface[] = [];
  filteredTaskToDoList: TaskInterface[] = [];
  lastFilter: string = '';
  selectedStatus: 'INPROGRESS';

  static readonly taskStatus = new Map([
    ['NOT_STARTED', 'Not Started'],
    ['INPROGRESS', 'In-Progress'],
    ['COMPLETED', 'Completed']
  ]);


  descriptionBackup: { [key: number]: string } = {};
  

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticator: AuthenticationService,
    private _httpConnectorService: HttpConnectorService,
    private elementRef: ElementRef
  ){}

  ngOnInit(): void {
    this.isUserLoggedIn = this.authenticator.isUserLoggedIn();
    this.username = this.route.snapshot.paramMap.get('username');
    if(this.username == null){
      this.username = this.authenticator.getUserName();
    }
    this.getTasks('INPROGRESS', true);
  }

  getTasks(status: string, isHomePage: Boolean){
    const tasks$ = this._httpConnectorService.getsTasks(this.username);
    const draftTasks$ = this._httpConnectorService.getsDraftTasks(this.username);

    this.taskToDo = {};
    this.draftTaskToDo = {};
  
    forkJoin([tasks$, draftTasks$]).subscribe({
      next: ([tasks, draftTasks]: [TaskInterface[], TaskInterface[]]) => {
        this.populateDictionary(tasks, this.taskToDo);
        this.populateDictionary(draftTasks, this.draftTaskToDo);
        this.generateActiveTaskList(status, isHomePage);
      },
      error: err => {
        console.log("Error");
      }
    });
  }

  generateActiveTaskList(status: string, isHomePage: Boolean){ 
    //Adding default editable value for Original Tasks
    this.activeTaskToDoList = [];

    if(Object.keys(this.taskToDo).length > 0){
      for(const key in this.taskToDo){
        let task = this.taskToDo[key];
        task.editState = false;
        if(task.active){
          this.activeTaskToDoList.push({ ...task });
        }
      }
    }

    if(Object.keys(this.draftTaskToDo).length > 0){
      for(const key in this.draftTaskToDo){
        let draftTask = this.draftTaskToDo[key];
        draftTask.editState = true;
        if(draftTask.draftActive){
          this.activeTaskToDoList.push({ ...draftTask });
        }
      }
    }
    this.onStatusChange(status, isHomePage);

}

onStatusChange(status: string, isHomePage: Boolean) {
  console.log("Selected Status Changed:", status);
  if(this.lastFilter != status || !isHomePage){
    this.lastFilter = status;
    this.refreshFilteredList(status);
  }
}

refreshFilteredList(status: string){
  if(status == 'All'){
    this.filteredTaskToDoList = this.activeTaskToDoList;
  }else{
    this.filteredTaskToDoList = this.activeTaskToDoList.filter(task => task.status === status);
    console.log(this.filteredTaskToDoList)
  }
}

statusDisplay(status: string){
  return HomePageComponent.taskStatus.get(status);
}

  saveTask(task: any) {
    this._httpConnectorService.saveDraftState(task).subscribe({
      next:(task: TaskInterface)=>{
        this._httpConnectorService.saveDraft(task).subscribe({
          next:(task: TaskInterface)=>{
            this.getTasks(this.lastFilter, false);
          },
          error:err=>{
            //If User not found, then redirect to Registration page so the user can register
            //this.router.navigate(['register']);
            console.log("Error")
          }
        });
      },
      error:err=>{
        //If User not found, then redirect to Registration page so the user can register
        //this.router.navigate(['register']);
        console.log("Error")
      }
    });
  }

  deleteTask(taskId: number) {
    this._httpConnectorService.deleteTask(taskId).subscribe({
      next:(task: TaskInterface)=>{
        console.log("Task Deleted: ", task)
        this.getTasks(this.lastFilter, false);
      },
      error:err=>{
        //If User not found, then redirect to Registration page so the user can register
        //this.router.navigate(['register']);
        console.log("Error")
      }
    });
    }

  editTask(task: any) {
    console.log("Edited", task);
    task.editState = true;
  }

  cancelEdit(objectTaskId: number) {
    for(const task of this.activeTaskToDoList){
      if(task.taskId === objectTaskId){
        task.description = this.taskToDo[objectTaskId].description;
        task.editState = false;
        break;
      }
    }

    this._httpConnectorService.cancelDraftUpdate(objectTaskId).subscribe({
      next:(task: TaskInterface)=>{
        console.log("Task saved: ", task)
      },
      error:err=>{
        //If User not found, then redirect to Registration page so the user can register
        //this.router.navigate(['register']);
        console.log("Error")
      }
    });
  }

  populateDictionary(tasks: TaskInterface[], dictionary: { [key: number]: TaskInterface }): void {
    if(tasks != null && tasks.length > 0){
      tasks.forEach(task => {
        dictionary[task.id] = task;
      });
    }
  }

    @HostListener('document:click', ['$event'])
    onClick(event: MouseEvent) {
      // Check if the clicked element is outside the table
      if (!this.isClickedInsideTable(event)) {
        // Handle click outside table logic here
        this.activeTaskToDoList.forEach((task, index)=>{
          if(task.editState){
            this._httpConnectorService.saveDraftState(task).subscribe({
              next:(task: TaskInterface)=>{
                console.log("Task saved: ", task)
              },
              error:err=>{
                //If User not found, then redirect to Registration page so the user can register
                //this.router.navigate(['register']);
                console.log("Error")
              }
            });
          }
        })
        
        console.log('Clicked outside table');
      }
    }
  
    private isClickedInsideTable(event: MouseEvent): boolean {
      const tableElement = this.elementRef.nativeElement.querySelector('.todo-list table');
      const clickedElement = event.target as HTMLElement;
      return tableElement && (tableElement.contains(event.target as Node) || clickedElement.classList.contains('action-icon'));
    }
}
