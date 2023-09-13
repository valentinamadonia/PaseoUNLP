import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PpalAdminComponent } from './ppal-admin.component';

describe('PpalAdminComponent', () => {
  let component: PpalAdminComponent;
  let fixture: ComponentFixture<PpalAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PpalAdminComponent]
    });
    fixture = TestBed.createComponent(PpalAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
