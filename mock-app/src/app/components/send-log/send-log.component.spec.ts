import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendLogComponent } from './send-log.component';

describe('SendLogComponent', () => {
  let component: SendLogComponent;
  let fixture: ComponentFixture<SendLogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendLogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
