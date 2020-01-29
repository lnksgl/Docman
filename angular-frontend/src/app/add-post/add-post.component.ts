import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, NgModel} from '@angular/forms';
import {PostPayload} from './post-payload';
import {AddPostService} from '../add-post.service';
import {ActivatedRoute, Router} from '@angular/router';
import {log} from 'util';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  addPostForm: FormGroup;
  postPayload: PostPayload;
  title = new FormControl('');
  category = new FormControl('');
  body = new FormControl('');

  constructor(private addPostService: AddPostService, private router: Router) {
    this.addPostForm = new FormGroup({
      title: this.title,
      category: this.category,
      body: this.body
    });
    this.postPayload = {
      id: '',
      content: '',
      category: '',
      title: '',
      username: ''
    };
  }

  ngOnInit() {
  }

  addPost() {
    this.postPayload.content = this.addPostForm.get('body').value;
    this.postPayload.title = this.addPostForm.get('title').value;
    this.postPayload.category = this.addPostForm.get('category').value;
    this.addPostService.addPost(this.postPayload).subscribe(data => {
      this.router.navigateByUrl('');
    }, error => {
      console.log('Failure Response');
    });
  }

  assertValidate() {
    if (this.addPostForm.get('title').value != '' && this.addPostForm.get('category').value != '' &&
      this.addPostForm.get('body').value != '') {
      return this.addPostForm.get('title').value;
    }
    return '';
  }
}
