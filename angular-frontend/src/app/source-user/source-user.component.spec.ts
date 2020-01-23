import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SourceUserComponent} from './source-user.component';

describe('SourceUserComponent', () => {
  let component: SourceUserComponent;
  let fixture: ComponentFixture<SourceUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SourceUserComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SourceUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
