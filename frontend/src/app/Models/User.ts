import { Field } from "./Field";

// User.ts
export interface User {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    role: string;
    birthDate: string;
    field: Field;
}
