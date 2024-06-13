export interface TaskInterface {
    id: number;
    userId: number;
    taskName: string;
    description: string;
    date: string; // Assuming date will be received as a string (you can adjust this based on your API response)
    status: string;
    active: boolean;
    draftActive: boolean;
    editState: boolean;
    taskId: number;
  }
  
  // Enum for TaskStatus
  export enum TaskStatus {
    // Define your enum values based on your TaskStatus enum in Java
    // Example:
    NotStarted = 'NOT_STARTED',
    INPROGRESS = 'INPROGRESS',
    COMPLETED = 'Completed'
  }
  