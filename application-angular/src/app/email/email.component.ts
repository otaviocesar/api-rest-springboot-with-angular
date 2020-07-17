import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.sass']
})
export class EmailComponent implements OnInit {

  emailForm: FormGroup;
  isLoadingResults = false;
  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.emailForm = this.formBuilder.group({
      'idSender' : [null, Validators.required],
      'idTemplate' : [null, Validators.required],
      'sendDate' : [null, Validators.required],
      'schedule' : [null, Validators.required],
      'name' : [null, Validators.required],
      'value' : [null, Validators.required]
    });
  }

  addEmail(form: NgForm) {
    this.isLoadingResults = true;
    this.api.addMessage(form)
      .subscribe(res => {
          const idMessage = res['id'];

        this.api.addMessageParam(idMessage, form)
          .subscribe(res => {
              this.isLoadingResults = false;
            }, (err) => {
              console.log(err);
              this.isLoadingResults = false;
            });  

          this.isLoadingResults = false;
        }, (err) => {
          console.log(err);
          this.isLoadingResults = false;
        });      
  }  
}
