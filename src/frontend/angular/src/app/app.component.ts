import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular';


  // clickCount = 0;
  // lastCreatedElement = '';
  //
  // handleChildClick() {
  //   this.clickCount ++;
  // }
  //
  // displayLastCreatedElement(element: string) {
  //     this.lastCreatedElement = element;
  // }

  constructor(
    private router: Router
  ) {
  }

  navigateToPage3() {
    // localhost:4200/andre/septian
    // this.router.navigate(['andre', 'septian']);

    this.router.navigate(['page-3']);
  }
}
