import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomRuleComponent } from './custom-rule.component';

describe('CustomRuleComponent', () => {
  let component: CustomRuleComponent;
  let fixture: ComponentFixture<CustomRuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomRuleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomRuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
