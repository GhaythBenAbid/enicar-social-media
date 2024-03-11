import { Field } from "./Field";

// User.ts
export interface User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    type: string;
    birthDate: string;
    field: Field;
}
