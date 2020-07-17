import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.sass']
})
export class UsuarioComponent implements OnInit {

  userForm: FormGroup;
  isLoadingResults = false;
  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      'name' : [null, Validators.required],
      'mail' : [null, [Validators.required, Validators.minLength(4)]],
      'address' : [null, Validators.required]
    });
  }

  addUser(form: NgForm) {
    this.isLoadingResults = true;
    this.api.addUser(form)
      .subscribe(res => {
          this.isLoadingResults = false;
          this.router.navigate(['/produtos']);
        }, (err) => {
          console.log(err);
          this.isLoadingResults = false;
        });
  }
}
