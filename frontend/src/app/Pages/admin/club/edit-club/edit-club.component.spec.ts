import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditClubComponent } from './edit-club.component';

describe('EditClubComponent', () => {
  let component: EditClubComponent;
  let fixture: ComponentFixture<EditClubComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditClubComponent]
    });
    fixture = TestBed.createComponent(EditClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
