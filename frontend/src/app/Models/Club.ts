import { Content } from "./Content";
import { User } from "./User";

// Club.ts
export interface Club {
    id: number;
    name: string;
    logo: string;
    banner: string;
    description: string;
    creationYear: number;
    responsible: User;
    content: Content;
}
