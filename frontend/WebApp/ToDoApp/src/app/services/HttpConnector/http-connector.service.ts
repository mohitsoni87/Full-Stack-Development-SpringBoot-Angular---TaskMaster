import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskInterface } from '../../model/TaskInterface';
import { API_URL } from '../../app.constants';
import { ToDoInterface } from '../../model/ToDoInterface';

@Injectable({
  providedIn: 'root'
})
export class HttpConnectorService {

  constructor(private _httpClient: HttpClient) { }

    getsTasks(userId: string):Observable<TaskInterface[]>{
      return this._httpClient.get<TaskInterface[]>(`${API_URL}/getTasks/${userId}`)
    }

    getsDraftTasks(userId: string):Observable<TaskInterface[]>{
      return this._httpClient.get<TaskInterface[]>(`${API_URL}/getDraftTasks/${userId}`)
    }

    cancelDraftUpdate(taskId: number):Observable<TaskInterface>{
      return this._httpClient.get<TaskInterface>(`${API_URL}/cancelDraftUpdate/${taskId}`)
    }

    createTask(task: TaskInterface): Observable<TaskInterface>{
      return this._httpClient.post<TaskInterface>(`${API_URL}/createTask`, task);
    }

    saveDraftState(draftTask: TaskInterface): Observable<TaskInterface>{
      return this._httpClient.post<TaskInterface>(`${API_URL}/addDraft`, draftTask);
    }

    saveDraft(draftTask: TaskInterface): Observable<TaskInterface>{
      return this._httpClient.post<TaskInterface>(`${API_URL}/saveDraft`, draftTask);
    }

    deleteTask(taskId: number): Observable<TaskInterface>{
      return this._httpClient.delete<TaskInterface>(`${API_URL}/deleteTask/${taskId}`);
    }
}
