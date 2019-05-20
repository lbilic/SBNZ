import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Rule } from '../model/rule';

@Injectable({
  providedIn: 'root'
})
export class RuleService {

  private base_url = "http://localhost:8443/generate-rule/";

  constructor(private http: HttpClient) { }

  newRule(rule: Rule) {
    return this.http.post<Rule>(this.base_url, rule).toPromise();
  }
}
