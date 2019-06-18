import { Component, OnInit } from '@angular/core';
import { LogService } from '../../services/log.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  colNames = ["OS logs", "IS1 logs", "IS2 logs", "PAYMENT_SYSTEM logs", "OS alarms", "IS1 alarms", "IS2 alarms", "PAYMENT_SYSTEM alarms"]
  allLogs : any;
  tempLogs: any;
  startDate : Date;
  endDate : Date;
  OSL : number;
  IS1L : number;
  IS2L : number;
  PSL : number;
  OSA : number;
  IS1A : number;
  IS2A : number;
  PSA : number;
  
  constructor(private logService: LogService) { }

  ngOnInit() {
    this.logService.getLogs()
      .then(response => {
        this.allLogs = response;
      });
  }

  update() {
    this.tempLogs = this.allLogs;
  }

  submit() {
    this.tempLogs = this.allLogs.filter((item: any) => {
      return new Date(item.timestamp) >= this.startDate &&
            new Date(item.timestamp) <= this.endDate
    });
    this.IS1L = this.tempLogs.filter((item: any) => {
      return item.systemType == "IS1"
    }).length

    this.IS2L = this.tempLogs.filter((item: any) => {
      return item.systemType == "IS2"
    }).length

    this.OSL = this.tempLogs.filter((item: any) => {
      return item.systemType == "OS"
    }).length

    this.PSL = this.tempLogs.filter((item: any) => {
      return item.systemType == "PAYMENT_SYSTEM"
    }).length

    this.IS1A = (this.IS1L - this.IS1L % 10) / 10;
    this.IS2A = (this.IS2L - this.IS2L % 10) / 10;
    this.OSA = (this.OSL - this.OSL % 10) / 10;
    this.PSA = (this.PSL - this.PSL % 10) / 10;
  }
}
