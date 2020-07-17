import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.sass']
})
export class CartComponent implements OnInit {

  cartForm: FormGroup;
  isLoadingResults = false;
  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.cartForm = this.formBuilder.group({
      'idUser' : [null, Validators.required],
    });
}

addCart(form: NgForm) {
  this.isLoadingResults = true;
  this.api.addCart(form)
    .subscribe(res => {
        this.isLoadingResults = false;
        this.router.navigate(['/produtos']);
      }, (err) => {
        console.log(err);
        this.isLoadingResults = false;
      });
}
}
