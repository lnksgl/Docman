import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PostPayload} from './add-post/post-payload';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddPostService {
  private url = 'http://localhost:7070/api/';

  constructor(private httpClient: HttpClient) { }

  addPost(postPayload: PostPayload): Observable<any> {
    return this.httpClient.post(this.url + 'posts', postPayload);
  }

  getAllPosts(): Observable<Array<PostPayload>> {
    return this.httpClient.get<Array<PostPayload>>(this.url + 'posts/all');
  }

  getPost(permaLink: number): Observable<PostPayload> {
    return this.httpClient.get<PostPayload>(this.url + 'posts/get/' + permaLink);
  }
}
