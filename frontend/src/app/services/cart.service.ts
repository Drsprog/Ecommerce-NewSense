import { Injectable } from '@angular/core';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartKey = 'cart';
  private items: Product[] = [];
  constructor() {
    // Cargar carrito desde localStorage al iniciar
    const storedCart = localStorage.getItem(this.cartKey);
    this.items = storedCart ? JSON.parse(storedCart) : [];
  }

  // Obtener productos del carrito
  getCart(): Product[] {
    return this.items;
  }

  // Agregar producto al carrito
  addToCart(product: Product): void {
    this.items.push(product);
    this.saveCart();
  }

  // Eliminar producto del carrito por id
  removeFromCart(productId: string): void {
    this.items = this.items.filter((p) => p.id !== productId);
    this.saveCart();
  }

  // Limpiar carrito
  clearCart(): void {
    this.items = [];
    this.saveCart();
  }

  // Calcular total
  getTotal(): number {
    return this.items.reduce((sum, p) => sum + p.price, 0);
  }

  // Guardar en localStorage
  private saveCart(): void {
    localStorage.setItem(this.cartKey, JSON.stringify(this.items));
  }
}
