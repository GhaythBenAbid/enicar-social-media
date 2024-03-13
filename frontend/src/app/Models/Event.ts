import { Club } from "./Club";

// Event.ts
export interface Event {
    name: string;
    description: string;
    number_places: number;
    start_date: string;
    end_date: string;
    start_time: string;
    end_time: string;
    status: string;
    club: Club;
}
