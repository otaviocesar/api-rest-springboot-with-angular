import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { ProdutoNovoComponent } from './produto-novo/produto-novo.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmailComponent } from './email/email.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    data: { title: 'Dashboard' }
  },
  {
    path: 'produtos',
    component: ProdutosComponent,
    data: { title: 'Lista de Produtos' }
  },
  {
    path: 'usuario',
    component: UsuarioComponent,
    data: { title: 'Adicionar Cliente' }
  },
  {
    path: 'order',
    component: OrderComponent,
    data: { title: 'Adicionar Item ao Pedido' }
  },
  {
    path: 'cart',
    component: CartComponent,
    data: { title: 'Adicionar Pedido' }
  },
  {
    path: 'produto-novo',
    component: ProdutoNovoComponent,
    data: { title: 'Adicionar Produto' }
  },
  {
    path: 'email',
    component: EmailComponent,
    data: { title: 'Adicionar Email' }
  },
  { path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
