import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ProductService } from './service/product.service';
import { ApiService } from '../api.service';
import { Product } from './models/product';

@Component({
  selector: 'app-produto-novo',
  templateUrl: './produto-novo.component.html',
  styleUrls: ['./produto-novo.component.sass']
})
export class ProdutoNovoComponent implements OnInit {

  productForm: FormGroup;
  isLoadingResults = false;
  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      'name' : [null, Validators.required],
      'details' : [null, [Validators.required, Validators.minLength(4)]],
      'price' : [null, Validators.required]
    });
  }


  addProduct(form: NgForm) {
    this.isLoadingResults = true;
    this.api.addProduct(form)
      .subscribe(res => {
          const id = res['_id'];
          this.isLoadingResults = false;
          this.router.navigate(['/dashboard/produtos', id]);
        }, (err) => {
          console.log(err);
          this.isLoadingResults = false;
        });
  }
}
