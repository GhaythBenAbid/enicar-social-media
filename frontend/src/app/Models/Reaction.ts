import { TextPost } from "./TextPost";
import { User } from "./User";

// Reaction.ts
export interface Reaction {
    id: number;
    user: User;
    textPost: TextPost;
    date: string;
}
