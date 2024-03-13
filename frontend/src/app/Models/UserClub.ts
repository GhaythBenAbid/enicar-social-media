import { Club } from "./Club";
import { User } from "./User";

// UserClub.ts
export interface UserClub {
    user: User;
    club: Club;
    date: string;
}
