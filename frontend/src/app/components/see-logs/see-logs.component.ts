import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LogService } from '../../services/log.service';

@Component({
  selector: 'app-see-logs',
  templateUrl: './see-logs.component.html',
  styleUrls: ['./see-logs.component.css']
})
export class SeeLogsComponent implements OnInit {

  myForm: FormGroup;
  logs = [
    "",
    "ErrorLog",
    "FailedLogin",
    "SuccessfulLogin",
    "ProfileChange",
    "Log",
    "MaliciousIps",
    "ThreatDetected",
    "ThreatEliminated"
  ]
  allLogs : any;
  tempLogs: any;
  colNames = ["Log type", "IP Address", "Username", "Timestamp", "Message"]

  constructor(private fb: FormBuilder, private logService: LogService) { 
    this.myForm = this.fb.group({
      log: [''],
      ip: [''],
      username: [''],
      usernameRegex: [''],
      ipRegex: ['']
    })
  }

  ngOnInit() {
    this.logService.getLogs()
      .then(response => {
        this.allLogs = response;
        this.tempLogs = response;
        this.submit();
      });
  }

  get log() {
    return this.myForm.get('log');
  }

  get ip() {
    return this.myForm.get('ip');
  }

  get username() {
    return this.myForm.get('username');
  }

  get usernameRegex() {
    return this.myForm.get('usernameRegex');
  }

  get ipRegex() {
    return this.myForm.get('ipRegex');
  }

  submit() {
    if(!this.log.value && !this.ip.value && !this.username.value) {
      this.tempLogs = this.allLogs
      return
    }

    this.tempLogs = this.allLogs.filter(log => {
      if(this.log.value && this.ip.value && this.username.value) {
        return log.logType === this.log.value && this.findItem(log.ipAddress, this.ip, this.ipRegex.value) && this.findItem(log.username, this.username, this.usernameRegex.value);
      } else if(this.log.value && this.ip.value) {
        return log.logType === this.log.value && this.findItem(log.ipAddress, this.ip, this.ipRegex.value);
      }
      else if(this.log.value && this.username.value) {
          return log.logType === this.log.value && this.findItem(log.username, this.username, this.usernameRegex.value);
      }
      else if(this.ip.value && this.username.value) {
        return this.findItem(log.ipAddress, this.ip, this.ipRegex.value) && this.findItem(log.username, this.username, this.usernameRegex.value)
      }
      else if(this.log.value) {
          return log.logType === this.log.value;
      }
      else if(this.username.value) {
          return this.findItem(log.username, this.username, this.usernameRegex.value);
      } else {
        return this.findItem(log.ipAddress, this.ip, this.ipRegex.value);
      }
    });
  }

  findItem(item, term, isRegex=false) {
    if(isRegex && item) {
      const reg = new RegExp(term.value);
      return item.match(reg) !== null ? item.match(reg).length : false;
    }
    return item ? item.includes(term.value) : false;
  }
}
