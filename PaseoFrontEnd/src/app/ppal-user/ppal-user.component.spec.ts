import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PpalUserComponent } from './ppal-user.component';

describe('PpalUserComponent', () => {
  let component: PpalUserComponent;
  let fixture: ComponentFixture<PpalUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PpalUserComponent]
    });
    fixture = TestBed.createComponent(PpalUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
