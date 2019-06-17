import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QueryService {

  private base_url = "http://localhost:8443/";
  constructor(private http: HttpClient) { }

  accountsWithAntivirusAlarms() {
    return this.http.get(this.base_url + 'accounts-with-antivirus-alarms').toPromise();
  }

  accountsWithFailedLogins(numberOfIps) {
    return this.http.get(this.base_url + 'accounts-with-failed-logins?number='+numberOfIps).toPromise();
  }
}
