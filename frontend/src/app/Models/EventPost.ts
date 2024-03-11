import { Post } from "./Post";

// EventPost.ts
export interface EventPost extends Post {
    eventName: string;
    eventLocation: string;
    eventDateTime: string;
    maxAttendees: number;
    isPublic: boolean;
    eventDescription: string;
    event_link: string;
}
