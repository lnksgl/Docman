import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {RegisterComponent} from './auth/register/register.component';
import {LoginComponent} from './auth/login/login.component';
import {RegisterSuccessComponent} from './auth/register-success/register-success.component';
import {HeaderComponent} from './header/header.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {Ng2Webstorage} from 'ngx-webstorage';
import {HomeComponent} from './home/home.component';
import {AddPostComponent} from './add-post/add-post.component';
import {EditorModule} from '@tinymce/tinymce-angular';
import {HttpClientInterceptor} from './http-client-interceptor';
import {PostComponent} from './post/post.component';
import {AuthGuard} from './auth.guard';
import {NgxPaginationModule} from 'ngx-pagination';
import {UpdatePostComponent} from './update-post/update-post.component';
import {MatSelectModule} from '@angular/material/select';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { SourcePostsComponent } from './source-posts/source-posts.component';
import {SourceUsersComponent} from './source-users/source-users.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    RegisterSuccessComponent,
    HeaderComponent,
    HomeComponent,
    AddPostComponent,
    PostComponent,
    UpdatePostComponent,
    SourcePostsComponent,
    SourceUsersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2Webstorage.forRoot(),
    RouterModule.forRoot([
      {path: '', component: HomeComponent},
      {path: 'v1/register', component: RegisterComponent},
      {path: 'v1/post/:id', component: PostComponent},
      {path: 'v1/login', component: LoginComponent},
      {path: 'v1/get/:id', component: UpdatePostComponent},
      {path: 'v1/post', component: AddPostComponent, canActivate: [AuthGuard]},
      {path: 'v1/posts', component: SourcePostsComponent},
      {path: 'v1/users', component: SourceUsersComponent},
    ]),
    HttpClientModule,
    EditorModule,
    NgxPaginationModule,
    MatSelectModule,
    BrowserAnimationsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
