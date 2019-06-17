import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeeLogsComponent } from './see-logs.component';

describe('SeeLogsComponent', () => {
  let component: SeeLogsComponent;
  let fixture: ComponentFixture<SeeLogsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeeLogsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeeLogsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
