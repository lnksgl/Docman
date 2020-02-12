import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PostPayload} from './add-post/post-payload';
import {Observable, of} from 'rxjs';
import {LocalStorageService} from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class AddPostService {
  private url = 'https://localhost:7070/api/v1/posts';

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) { }

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

  getTitle(title: string): Observable<Array<PostPayload>> {
    return this.httpClient.put<Array<PostPayload>>(this.url + '/title/' + title, '');
  }

  getCategory(category: string): Observable<Array<PostPayload>> {
    return this.httpClient.put<Array<PostPayload>>(this.url + '/category/' + category, '');
  }

  getUsername(): Observable<Array<PostPayload>> {
    return this.httpClient.put<Array<PostPayload>>(this.url + '/username/' + this.localStorageService.retrieve('username'), '');
  }

  getTitleUsername(title: string): Observable<Array<PostPayload>> {
  return this.httpClient.put<Array<PostPayload>>(this.url + '/' + title + '?' + this.localStorageService.retrieve('username'), '');
}
}
