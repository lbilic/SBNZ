import { Component, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [BsModalService]
})
export class NavbarComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
