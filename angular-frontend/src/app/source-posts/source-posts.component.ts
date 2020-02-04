import { Component, OnInit } from '@angular/core';
import {PostPayload} from '../add-post/post-payload';
import {Observable} from 'rxjs';
import {AddPostService} from '../add-post.service';

@Component({
  selector: 'app-source-posts',
  templateUrl: './source-posts.component.html',
  styleUrls: ['./source-posts.component.css']
})
export class SourcePostsComponent implements OnInit {

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
