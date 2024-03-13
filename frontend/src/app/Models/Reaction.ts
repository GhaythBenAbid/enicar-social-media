import { TextPost } from "./TextPost";
import { User } from "./User";

// Reaction.ts
export interface Reaction {
    user: User;
    textPost: TextPost;
    date: string;
}
