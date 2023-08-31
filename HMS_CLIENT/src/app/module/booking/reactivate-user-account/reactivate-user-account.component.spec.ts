import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReactivateUserAccountComponent } from './reactivate-user-account.component';

describe('ReactivateUserAccountComponent', () => {
  let component: ReactivateUserAccountComponent;
  let fixture: ComponentFixture<ReactivateUserAccountComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReactivateUserAccountComponent]
    });
    fixture = TestBed.createComponent(ReactivateUserAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
