// src/app/enums/new-user-response.interface.ts

import { Gender } from "../enums/gender.enum";


export interface NewUserResponse {
  userId: number;
  name: string;
  age: number;
  attr: string[];
  email: string;
  gender: Gender;
}
