import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Log } from '../model/log';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  private base_url = "http://localhost:8443/";

  constructor(private http: HttpClient) { }

  sendLog(log: Log, logType: String) {
    return this.http.post<Log>(this.base_url + 'insert-log/com.siem.siem.facts.' + logType, log).toPromise();
  }

  getLogs() {
    return this.http.get(this.base_url + 'logs').toPromise();
  }
}
