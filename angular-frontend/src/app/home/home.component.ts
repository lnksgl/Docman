import {Component, OnInit} from '@angular/core';
import {AddPostService} from '../add-post.service';
import {Observable} from 'rxjs';
import {PostPayload} from '../add-post/post-payload';
import {Router} from "@angular/router";
import {AuthService} from "../auth/auth.service";

export interface Food {
  value: string;
  viewValue: string;
}

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

  searchTitle(value: string) {
    if (value === '') {
      this.ngOnInit();

    } else {
      this.posts = this.postService.getTitle(value);
    }
  }

  searchCategory(value: string) {
    if (value === undefined) {
      this.ngOnInit();
    } else {
      this.posts = this.postService.getCategory(value);
    }
  }
}
