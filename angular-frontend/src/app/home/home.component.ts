import { Component, OnInit } from '@angular/core';
import {AddPostService} from '../add-post.service';
import {Observable} from 'rxjs';
import {PostPayload} from '../add-post/post-payload';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  page: number = 1;
  post: PostPayload;

  posts: Observable<Array<PostPayload>>;
  constructor(private postService: AddPostService) {
  }

  ngOnInit() {
    this.posts = this.postService.getAllPosts();
  }

  deletePost(id) {
    this.postService.deletePost(id).subscribe();
  }

  search(value: string) {
    if(value === '') {
      this.ngOnInit();
    } else {
      this.posts = this.postService.getTitle(value);
    }
  }
}
