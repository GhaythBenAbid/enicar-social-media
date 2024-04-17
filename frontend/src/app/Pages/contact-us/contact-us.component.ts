import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent {

  firstName : string = '';
  lastName : string = '';
  email : string = '';
  phone : string = '';
  message : string = '';

  constructor(private toast : NgToastService) { }


  sendMessage(){
    if (this.firstName == '' || this.lastName == '' || this.email == '' || this.phone == '' || this.message == ''){
      this.toast.error({detail : 'Please fill all fields' , summary : 'Error'});
      return;
    }



    this.toast.success({detail : `Thank you ${this.firstName} ${this.lastName}` , summary : 'We will get back to you as soon as possible'});


    //return to home page
    setTimeout(() => {
      window.location.href = '/';
    }, 2000);
  }

}
