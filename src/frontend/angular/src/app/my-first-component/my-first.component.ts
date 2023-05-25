import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MyCalculatorService} from "../service/my-calculator.service";

@Component({
  selector: 'app-my-first-component',
  templateUrl: './my-first.component.html',
  styleUrls: ['./my-first.component.scss']
})
export class MyFirstComponent {

  value1 = 0;
  value2 = 0;
  result = 0;

  constructor(
    private readonly calculator: MyCalculatorService
  ) {
  }

  sum() {
    this.result = this.calculator.sum(this.value1, this.value2);
  }

  sub() {
    this.result = this.calculator.sub(this.value1, this.value2);
  }

  multiply() {
    this.result = this.calculator.multiply(this.value1, this.value2);
  }

  divide() {
    this.result = this.calculator.divide(this.value1, this.value2);
  }


  // @Input()
  // inputValue: string = 'Hello from itcserver';
  //
  // @Output()
  // childClicked: EventEmitter<void> = new EventEmitter<void>();
  //
  // @Output()
  // elementCreated: EventEmitter<string> = new EventEmitter<string>();
  //
  // displayMsg = false;
  // msgList: Array<string> = [];
  //
  // // msgListComposed: any[] = [];
  //
  // clickMe(): void {
  //   this.msgList.push(this.inputValue);
  //
  //   // this.msgListComposed.push({
  //   //   name: this.inputValue,
  //   //   visible: true
  //   // });
  //
  //   console.log(this.msgList);
  //   this.childClicked.emit();
  //   this.elementCreated.emit(this.inputValue);
  //   this.inputValue = '';
  // }


}
