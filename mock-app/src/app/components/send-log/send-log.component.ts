import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Log } from '../../model/log';
import { LogService } from '../../services/log.service';
import { FailedLogin } from '../../model/FailedLogin';
import { ErrorLog } from '../../model/ErrorLog';
import { Threat } from '../../model/Threat';
import { SystemTypes } from '../../model/systemTypes';
import { MaliciousIps } from '../../model/MaliciousIps';

@Component({
  selector: 'app-send-log',
  templateUrl: './send-log.component.html',
  styleUrls: ['./send-log.component.css']
})
export class SendLogComponent implements OnInit {

  form: FormGroup;
  logs = [
    "ErrorLog",
    "FailedLogin",
    "SuccessfulLogin",
    "ProfileChange",
    "Log",
    "MaliciousIps",
    "ThreatDetected",
    "ThreatEliminated"
  ]

  systemTypes = [
    "OS", 
    "IS1",
    "IS2",
    "PAYMENT_SYSTEM"
  ]

  maliciousIps =  new Set<String>();

  constructor(private fb: FormBuilder, private logService: LogService) { 
    this.form = this.fb.group({
      log: ['', [Validators.required]],
      ipAddress: ['', [Validators.required]],
      value: ['', [Validators.required]],
      message: ['', [Validators.required]],
      username: ['', [Validators.required]],
      systemType: ['', [Validators.required]],
      threatId: ['', [Validators.required]],
      maliciousIp: ['', [Validators.required]]
    })
  }

  ngOnInit() {
  }

  get log() {
    return this.form.get('log');
  }

  get ipAddress() {
    return this.form.get('ipAddress');
  }

  get value() {
    return this.form.get('value');
  }

  get message() {
    return this.form.get('message');
  }

  get username() {
    return this.form.get('username');
  }

  get systemType() {
    return this.form.get('systemType');
  }

  get threatId() {
    return this.form.get('threatId');
  }

  get maliciousIp() {
    return this.form.get('maliciousIp');
  }

  submit() {
    let newLog;
    switch(this.log.value) {
      case "FailedLogin":
      case "SuccessfulLogin":
      case "ProfileChange":
        newLog = new FailedLogin(this.ipAddress.value, new Date());
        newLog.systemType = this.systemType.value;
        newLog.timestamp = new Date();
        newLog.username = this.username.value;
        break;
      case "Log":
        newLog = new Log(this.ipAddress.value, new Date());
        break;
      case "ErrorLog":
        newLog = new ErrorLog(this.ipAddress.value, new Date());
        newLog.message = this.message.value;
        break;
      case "MaliciousIps":
        newLog = new MaliciousIps(this.ipAddress.value, new Date());
        newLog.maliciousIps = Array.from(this.maliciousIps);
        break;
      case "ThreatDetected":
      case "ThreatEliminated":
        newLog = new Threat(this.ipAddress.value, new Date());
        newLog.threatId = this.threatId.value;
        break;
    }

    var i;
    for (i = 0; i < this.value.value; i++) {
      this.logService.sendLog(newLog, this.log.value);
    }
    if(this.log.value == "MaliciousIps")
      this.logService.sendLog(newLog, this.log.value);
    console.log(newLog)
  }

  addIp() {
    this.maliciousIps.add(this.maliciousIp.value)
    console.log(this.maliciousIps)
  }
}
