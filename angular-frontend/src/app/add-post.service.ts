import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PostPayload} from './add-post/post-payload';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddPostService {
  private url = 'http://localhost:7070/api/v1/posts';

  constructor(private httpClient: HttpClient) { }

  addPost(postPayload: PostPayload): Observable<any> {
    return this.httpClient.post(this.url, postPayload);
  }

  getAllPosts(): Observable<Array<PostPayload>> {
    return this.httpClient.get<Array<PostPayload>>(this.url);
  }

  getPost(permaLink: number): Observable<PostPayload> {
    return this.httpClient.get<PostPayload>(this.url + '/' + permaLink);
  }

  deletePost(permaLink: number): Observable<any> {
    return this.httpClient.delete(this.url + '/' + permaLink);
  }

  updatePost(postPayload: PostPayload): Observable<any> {
    return this.httpClient.put(this.url, postPayload);
  }

  getTitle(search: string): Observable<Array<PostPayload>> {
    return this.httpClient.put<Array<PostPayload>>(this.url + '/' + search, '');
  }
}
