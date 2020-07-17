import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.sass']
})
export class OrderComponent implements OnInit {

  orderForm: FormGroup;
  isLoadingResults = false;
  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.orderForm = this.formBuilder.group({
      'idProduct' : [null, Validators.required],
      'idCart' : [null, Validators.required]
    });
}

addOrder(form: NgForm) {
  this.isLoadingResults = true;
  this.api.addOrder(form)
    .subscribe(res => {
        this.isLoadingResults = false;
        this.router.navigate(['/produtos']);
      }, (err) => {
        console.log(err);
        this.isLoadingResults = false;
      });
}
}
