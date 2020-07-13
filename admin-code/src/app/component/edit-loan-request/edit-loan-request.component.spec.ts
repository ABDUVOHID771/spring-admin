import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLoanRequestComponent } from './edit-loan-request.component';

describe('EditLoanRequestComponent', () => {
  let component: EditLoanRequestComponent;
  let fixture: ComponentFixture<EditLoanRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditLoanRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditLoanRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
