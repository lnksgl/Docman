import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SourcePostsComponent } from './source-posts.component';

describe('SourcePostsComponent', () => {
  let component: SourcePostsComponent;
  let fixture: ComponentFixture<SourcePostsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SourcePostsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SourcePostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
