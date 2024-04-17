import { User } from "./User";

// Post.ts
export interface Post {
    id: number;
    title: string;
    content: string;
    image: string;
    author: User;
    date : string;
    visibility: boolean;
    tags: string[];
}
