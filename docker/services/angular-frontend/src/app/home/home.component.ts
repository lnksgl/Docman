import {Component, OnInit} from '@angular/core';
import {AddPostService} from '../add-post.service';
import {Observable} from 'rxjs';
import {PostPayload} from '../add-post/post-payload';

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
    this.posts = this.postService.getUsername();
  }

  searchTitle(value: string) {
    if (value === '') {
      this.ngOnInit();

    } else {
      this.posts = this.postService.getTitleUsername(value);
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
