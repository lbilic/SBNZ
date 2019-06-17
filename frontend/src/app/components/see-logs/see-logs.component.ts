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
  colNames = ["Log type", "IP Address", "Timestamp"]

  constructor(private fb: FormBuilder, private logService: LogService) { 
    this.myForm = this.fb.group({
      log: ['', [Validators.required]]
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

  submit() {
    if(this.log.value == "") {
      this.tempLogs = this.allLogs
      return
    }
    this.tempLogs = this.allLogs.filter(log => log.logType == this.log.value)
  }
}
