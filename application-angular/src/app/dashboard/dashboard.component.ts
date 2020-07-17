import { Component, OnInit } from '@angular/core';
import { OrderService } from './service/order.service';
import { Order } from './models/order';
import { CartService } from './service/cart.service';
import { Cart } from './models/cart';
import { ProductService } from './service/product.service';
import { Product } from './models/product';
import { UserService } from './service/user.service';
import { User } from './models/user';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.sass']
})
export class DashboardComponent implements OnInit {

  order = {} as Order;
  orders: Order[];

  cart = {} as Cart;
  carts: Cart[];

  product = {} as Product;
  products: Product[];

  user = {} as User;
  users: User[];

  constructor(private orderService: OrderService, private cartService: CartService, private productService: ProductService, private userService: UserService) {}
  
  ngOnInit(): void {
    this.getOrders();
    this.getCarts();
    this.getProducts();
    this.getUsers();
  }

  // defini se uma venda será criada ou atualizada
  saveOrder(form: NgForm) {
    if (this.order.id !== undefined) {
      this.orderService.updateOrder(this.order).subscribe(() => {
        this.cleanForm(form);
      });
    } else {
      this.orderService.saveOrder(this.order).subscribe(() => {
      this.cleanForm(form);
      });
    }
  }

  // Chama o serviço para obtém todos as vendas
  getOrders() {
    this.orderService.getOrders().subscribe((orders: Order[]) => {
    this.orders = orders;
    });
  }

    // Chama o serviço para obtém todos as encomendas 
    getCarts() {
      this.cartService.getCarts().subscribe((carts: Cart[]) => {
      this.carts = carts;
      });
    }

      // Chama o serviço para obtém todos os produtos
    getProducts() {
      this.productService.getProducts().subscribe((products: Product[]) => {
      this.products = products;
      });
    } 
    // Chama o serviço para obtém todos os usuarios
    getUsers() {
      this.userService.getUsers().subscribe((users: User[]) => {
      this.users = users;
      });
    }      

  // deleta um order
  deleteOrder(order: Order) {
    this.orderService.deleteOrder(order).subscribe(() => {
      this.getOrders();
    });
  }

  // copia o orderro para ser editado.
  editOrder(order: Order) {
    this.order = { ...order };
  }

  // limpa o formulario
  cleanForm(form: NgForm) {
    this.getOrders();
    form.resetForm();
    this.order = {} as Order;
  }

}
