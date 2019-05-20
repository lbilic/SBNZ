import { Component, OnInit } from '@angular/core';
import { RuleService } from '../../services/rule.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Rule } from '../../model/rule';

@Component({
  selector: 'add-rule',
  templateUrl: './add-rule.component.html',
  styleUrls: ['./add-rule.component.css']
})
export class AddRuleComponent implements OnInit {

  form: FormGroup;
  rules = [
    "Error log",
    "FailedLogin",
    "SuccessfulLogin",
    "Log",
    "MaliciousIps",
    "ThreatDetected",
    "ThreatEliminated"
  ]

  constructor(private fb: FormBuilder, private ruleService: RuleService) { 
    this.form = this.fb.group({
      rule: ['', [Validators.required]],
      field: ['', [Validators.required]],
      value: ['', [Validators.required]],
      interval: ['', [Validators.required]]
    })
  }

  ngOnInit() {
  }

  get rule() {
    return this.form.get('rule');
  }

  get field() {
    return this.form.get('field');
  }

  get value() {
    return this.form.get('value');
  }

  get interval() {
    return this.form.get('interval');
  }

  submit() {
    let newRule = new Rule(this.rule.value, this.field.value, this.value.value, this.interval.value);
    this.ruleService.newRule(newRule);
  }
}
