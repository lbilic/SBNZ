import { Component, OnInit } from '@angular/core';
import { QueryService } from 'src/app/services/query.service';

@Component({
  selector: 'app-find-users',
  templateUrl: './find-users.component.html',
  styleUrls: ['./find-users.component.css']
})
export class FindUsersComponent implements OnInit {

  results: any;
  colNames = ["Username", "IP Address"]
  constructor(private queryService: QueryService) { }

  ngOnInit() {
  }

  showUsersWithAntivirus() {
    this.queryService.accountsWithAntivirusAlarms().then(response=>{
      this.results = response;
    })
  }

  showUsersFailedLogins(numberOfIps) {
    console.log(numberOfIps);
    if(!numberOfIps) {
      return;
    }
    this.queryService.accountsWithFailedLogins(numberOfIps).then(response=>{
      this.results = response;
      console.log(numberOfIps);
    })
  }

}
