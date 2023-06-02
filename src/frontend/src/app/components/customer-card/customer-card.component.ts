import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CustomerDTO} from "../../models/customer-dto";

@Component({
  selector: 'app-customer-card',
  templateUrl: './customer-card.component.html',
  styleUrls: ['./customer-card.component.scss']
})
export class CustomerCardComponent {
  @Input()
  customer: CustomerDTO = {age: 0, email: "", gender: undefined, id: 0, name: "", roles: [], username: ""};

  @Input()
  customerIndex = 0;

  @Output()
  delete: EventEmitter<CustomerDTO> = new EventEmitter<CustomerDTO>();

  get customerImage(): string {
    const gender = this.customer.gender === 'MALE' ? 'men' : 'women'
    return `https://randomuser.me/api/portraits/${gender}/${this.customerIndex}.jpg`;
  }

  onDelete() {
    this.delete.emit(this.customer);
  }
}
