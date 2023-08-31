import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDetailsByIdComponent } from './user-details-by-id.component';

describe('UserDetailsByIdComponent', () => {
  let component: UserDetailsByIdComponent;
  let fixture: ComponentFixture<UserDetailsByIdComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserDetailsByIdComponent]
    });
    fixture = TestBed.createComponent(UserDetailsByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
