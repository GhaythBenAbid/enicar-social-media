import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Post } from 'src/app/Models/Post';
import { User } from 'src/app/Models/User';
import { PostService } from 'src/app/Services/post.service';
import { Tags } from 'src/app/Utils/Tags';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {
  visible: boolean = false;
  tags = Tags.tags;
  loading: boolean = false;
  posts: Post[] = [];
  user : User;

  image: File | undefined;
  imagePreview: string | undefined;
  content: string = "";

  constructor(private postService: PostService , private toast: NgToastService) {
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
    });
  }



  addPost() {
    this.loading = true;
    const currentUser: User | null = JSON.parse(localStorage.getItem('currentUser')!);

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
      this.toast.success({detail:"SUCCESS",summary:'Post Created Successfully!',duration:1000});
      this.visible = false;
      this.loading = false;
      
    });


  }





}
