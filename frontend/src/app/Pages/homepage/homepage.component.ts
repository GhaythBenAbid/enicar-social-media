import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Post } from 'src/app/Models/Post';
import { User } from 'src/app/Models/User';
import { PostService } from 'src/app/Services/post.service';
import { ReactionService } from 'src/app/Services/reaction.service';
import { Tags } from 'src/app/Utils/Tags';
import {cuss} from 'cuss/ar-latn';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  visible: boolean = false;
  tags = Tags.tags;
  loading: boolean = false;
  posts: any[] = [];
  user: User;

  image: File | undefined;
  imagePreview: string | undefined;
  content: string = "";

  constructor(private postService: PostService, private toast: NgToastService, private reactionService: ReactionService) {
    this.user = JSON.parse(localStorage.getItem('currentUser')!);



  }

  ngOnInit() {
    this.getAllPosts();

  }

  onFileChange(event: any) {
    this.image = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result as string;
    }
    reader.readAsDataURL(this.image!);
  }

  selectTag(tag: any) {
    tag.selected = !tag.selected;
  }

  getAllPosts() {
    this.postService.getAllPosts().subscribe((res: any) => {
      this.posts = res as Post[];
      for (let post of this.posts) {
        post.isLiked = false;
        post.numberOfComments = 0;
        for (let reaction of post.reactions) {
          if (reaction.user.id == this.user.id) {
            post.isLiked = true;
          }
          if(reaction.comment != ""){
            post.numberOfComments += 1;
          }
        }
      }
      console.log(this.posts);
    });
  }



  addPost() {
    this.loading = true;
    const currentUser: User | null = JSON.parse(localStorage.getItem('currentUser')!);

    if (this.checkForProfanity(this.content)) {
      this.toast.error({ detail: "PROFANITY", summary: 'Profanity Detected!', duration: 1000 });
      this.loading = false;
      return;
    }

    const post = {
      content: this.content,
      image: this.image,
      tags: this.tags.filter(tag => tag.selected).map(tag => tag.value),
      author: currentUser?.id,
      date: new Date(),
      visibility: true
    }

    const formData = new FormData();
    formData.append('content', post.content);
    if (post.image) {
      formData.append('image', post.image);
    }
    formData.append('tags', post.tags.toString());
    formData.append('author', post.author!.toString());
    formData.append('date', new Date().toISOString());
    formData.append('visibility', post.visibility.toString());


    this.postService.createPost(formData).subscribe((res: any) => {
      this.getAllPosts();
      this.toast.success({ detail: "SUCCESS", summary: 'Post Created Successfully!', duration: 1000 });
      this.visible = false;
      this.loading = false;

    });
  }

  reactPost(id: any, type: any) {
    if (type) {
      const reaction = {
        post: {
          id: id
        },
        user: {
          id: this.user.id
        },
        type: "LIKE",
        comment: "",
        date: new Date()
      }

      this.reactionService.createReaction(reaction).subscribe((res: any) => {
        this.getAllPosts();
      });
    } else {
      for (let post of this.posts) {
        if (post.id == id) {
          for (let reaction of post.reactions) {
            if (reaction.user.id == this.user.id) {
              this.reactionService.deleteReaction(reaction.id).subscribe((res: any) => {
                this.getAllPosts();
              });
            }
          }
        }
      }



    }

  }

  checkForProfanity(text: string): boolean {
    // Convert the text to lowercase for case-insensitive matching
    const lowercaseText = text.toLowerCase();
  
    // Iterate over the keys in the cuss object to check for profanity
    for (const word in cuss) {
      if (lowercaseText.includes(word)) {
        return true; // Profanity found
      }
    }
  
    return false; // No profanity found
  }








}
