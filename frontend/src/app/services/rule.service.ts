import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Rule } from '../model/rule';

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  private base_url = "http://localhost:8443/";

  constructor(private http: HttpClient) { }

  newRule(rule: Rule) {
    return this.http.post<Rule>(this.base_url + "generate-rule/", rule).toPromise();
  }

  newCustomRule(rule: String, ruleName: String) {
    return this.http.post<String>(this.base_url + "generate-custom-rule/" + ruleName, rule).toPromise();
  }
}
