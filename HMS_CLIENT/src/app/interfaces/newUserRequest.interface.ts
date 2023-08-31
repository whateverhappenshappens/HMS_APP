import { Gender } from "../enums/gender.enum";

export interface NewUserRequest {
    name: string;
    age: number;
    attr: string[];
    email: string;
    gender: Gender;
    password: string;
    username: string;
  }
