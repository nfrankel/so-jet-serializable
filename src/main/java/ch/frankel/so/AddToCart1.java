package ch.frankel.so;

import java.io.Serializable;

public class AddToCart1 implements Serializable {
    private int number;
    private String cart;

    public AddToCart1() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getNumber() {
        return number;
    }

    public AddToCart1(int number, String cart) {
        super();
        this.number = number;
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cart == null) ? 0 : cart.hashCode());
        result = prime * result + number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddToCart1 other = (AddToCart1) obj;
        if (cart == null) {
            if (other.cart != null)
                return false;
        } else if (!cart.equals(other.cart))
            return false;
        if (number != other.number)
            return false;
        return true;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

}