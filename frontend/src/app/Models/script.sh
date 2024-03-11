#!/bin/bash

# Create output directory if it doesn't exist
mkdir -p src/main/typescript

# Create Club.ts
echo "// Club.ts" > src/main/typescript/Club.ts
echo "export interface Club {" >> src/main/typescript/Club.ts
echo "    id: number;" >> src/main/typescript/Club.ts
echo "    name: string;" >> src/main/typescript/Club.ts
echo "    logo: string;" >> src/main/typescript/Club.ts
echo "    banner: string;" >> src/main/typescript/Club.ts
echo "    description: string;" >> src/main/typescript/Club.ts
echo "    creationYear: number;" >> src/main/typescript/Club.ts
echo "    responsible: User;" >> src/main/typescript/Club.ts
echo "    content: Content;" >> src/main/typescript/Club.ts
echo "}" >> src/main/typescript/Club.ts

# Repeat the process for other interfaces
# Content.ts
echo "// Content.ts" > src/main/typescript/Content.ts
echo "export interface Content {" >> src/main/typescript/Content.ts
echo "    id: number;" >> src/main/typescript/Content.ts
echo "    content_data: string;" >> src/main/typescript/Content.ts
echo "}" >> src/main/typescript/Content.ts

# Event.ts
echo "// Event.ts" > src/main/typescript/Event.ts
echo "export interface Event {" >> src/main/typescript/Event.ts
echo "    id: number;" >> src/main/typescript/Event.ts
echo "    name: string;" >> src/main/typescript/Event.ts
echo "    description: string;" >> src/main/typescript/Event.ts
echo "    number_places: number;" >> src/main/typescript/Event.ts
echo "    start_date: string;" >> src/main/typescript/Event.ts
echo "    end_date: string;" >> src/main/typescript/Event.ts
echo "    start_time: string;" >> src/main/typescript/Event.ts
echo "    end_time: string;" >> src/main/typescript/Event.ts
echo "    status: string;" >> src/main/typescript/Event.ts
echo "    club: Club;" >> src/main/typescript/Event.ts
echo "}" >> src/main/typescript/Event.ts

# EventPost.ts
echo "// EventPost.ts" > src/main/typescript/EventPost.ts
echo "export interface EventPost extends Post {" >> src/main/typescript/EventPost.ts
echo "    eventName: string;" >> src/main/typescript/EventPost.ts
echo "    eventLocation: string;" >> src/main/typescript/EventPost.ts
echo "    eventDateTime: string;" >> src/main/typescript/EventPost.ts
echo "    maxAttendees: number;" >> src/main/typescript/EventPost.ts
echo "    isPublic: boolean;" >> src/main/typescript/EventPost.ts
echo "    eventDescription: string;" >> src/main/typescript/EventPost.ts
echo "    event_link: string;" >> src/main/typescript/EventPost.ts
echo "}" >> src/main/typescript/EventPost.ts

# Field.ts
echo "// Field.ts" > src/main/typescript/Field.ts
echo "export interface Field {" >> src/main/typescript/Field.ts
echo "    id: number;" >> src/main/typescript/Field.ts
echo "    name: string;" >> src/main/typescript/Field.ts
echo "}" >> src/main/typescript/Field.ts

# MediaPost.ts
echo "// MediaPost.ts" > src/main/typescript/MediaPost.ts
echo "export interface MediaPost extends Post {" >> src/main/typescript/MediaPost.ts
echo "    mediaLink: string;" >> src/main/typescript/MediaPost.ts
echo "}" >> src/main/typescript/MediaPost.ts

# Post.ts
echo "// Post.ts" > src/main/typescript/Post.ts
echo "export interface Post {" >> src/main/typescript/Post.ts
echo "    id: number;" >> src/main/typescript/Post.ts
echo "    title: string;" >> src/main/typescript/Post.ts
echo "}" >> src/main/typescript/Post.ts

# Reaction.ts
echo "// Reaction.ts" > src/main/typescript/Reaction.ts
echo "export interface Reaction {" >> src/main/typescript/Reaction.ts
echo "    id: number;" >> src/main/typescript/Reaction.ts
echo "    user: User;" >> src/main/typescript/Reaction.ts
echo "    textPost: TextPost;" >> src/main/typescript/Reaction.ts
echo "    date: string;" >> src/main/typescript/Reaction.ts
echo "}" >> src/main/typescript/Reaction.ts

# TextPost.ts
echo "// TextPost.ts" > src/main/typescript/TextPost.ts
echo "export interface TextPost extends Post {" >> src/main/typescript/TextPost.ts
echo "    content: string;" >> src/main/typescript/TextPost.ts
echo "}" >> src/main/typescript/TextPost.ts

# User.ts
echo "// User.ts" > src/main/typescript/User.ts
echo "export interface User {" >> src/main/typescript/User.ts
echo "    id: number;" >> src/main/typescript/User.ts
echo "    firstName: string;" >> src/main/typescript/User.ts
echo "    lastName: string;" >> src/main/typescript/User.ts
echo "    email: string;" >> src/main/typescript/User.ts
echo "    password: string;" >> src/main/typescript/User.ts
echo "    type: string;" >> src/main/typescript/User.ts
echo "    birthDate: string;" >> src/main/typescript/User.ts
echo "    field: Field;" >> src/main/typescript/User.ts
echo "}" >> src/main/typescript/User.ts

# UserClub.ts
echo "// UserClub.ts" > src/main/typescript/UserClub.ts
echo "export interface UserClub {" >> src/main/typescript/UserClub.ts
echo "    id: number;" >> src/main/typescript/UserClub.ts
echo "    user: User;" >> src/main/typescript/UserClub.ts
echo "    club: Club;" >> src/main/typescript/UserClub.ts
echo "    date: string;" >> src/main/typescript/UserClub.ts
echo "}" >> src/main/typescript/UserClub.ts

echo "All TypeScript files created successfully."

