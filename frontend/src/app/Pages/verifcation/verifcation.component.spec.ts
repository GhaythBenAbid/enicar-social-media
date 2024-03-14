import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifcationComponent } from './verifcation.component';

describe('VerifcationComponent', () => {
  let component: VerifcationComponent;
  let fixture: ComponentFixture<VerifcationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerifcationComponent]
    });
    fixture = TestBed.createComponent(VerifcationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
