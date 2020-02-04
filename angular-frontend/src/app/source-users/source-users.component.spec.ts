import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SourceUsersComponent } from './source-users.component';

describe('SourceUsersComponent', () => {
  let component: SourceUsersComponent;
  let fixture: ComponentFixture<SourceUsersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SourceUsersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SourceUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
