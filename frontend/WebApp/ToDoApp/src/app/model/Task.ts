import { TaskInterface, TaskStatus } from "./TaskInterface";

export class Task implements TaskInterface {
  id: number;
  taskName: string;
  description: string;
  date: string;
  active: boolean;
  draftActive: boolean;
  editState: boolean;
  taskId: number;
  userId: number;
  status: TaskStatus;

  constructor() {
    this.id = 0;
    this.userId = 0;
    this.taskName = '';
    this.description = '';
    this.date = new Date().toISOString(); // Default to current date
    this.active = true;
    this.draftActive = false;
    this.editState = false;
    this.taskId = 0;
    this.status = TaskStatus.InProgress;
  }

}
