import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RuleService } from '../../services/rule.service';

@Component({
  selector: 'app-custom-rule',
  templateUrl: './custom-rule.component.html',
  styleUrls: ['./custom-rule.component.css']
})
export class CustomRuleComponent implements OnInit {
  form: FormGroup;
  constructor(private fb: FormBuilder, private ruleService: RuleService) { 
    this.form = this.fb.group({
      rule: ['', [Validators.required]],
      ruleName: ['', [Validators.required]]
    })
  }

  ngOnInit() {
  }

  get rule() {
    return this.form.get('rule');
  }

  get ruleName() {
    return this.form.get('ruleName');
  }

  submit() {
    this.ruleService.newCustomRule(this.rule.value, this.ruleName.value);
  }
}
