import { Component, OnInit } from '@angular/core';
import { LogService } from 'src/app/services/log.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-alarms',
  templateUrl: './alarms.component.html',
  styleUrls: ['./alarms.component.css']
})
export class AlarmsComponent implements OnInit {
  alarms: Array<any> = [];
  constructor(private fb: FormBuilder, private logService: LogService) { }
  ngOnInit() {
    this.logService.getAlarms()
      .then((response: Array<any>) => {
        this.alarms = response.filter((item) => {
          return item.hasOwnProperty('message');
        });
      });
  }


}
