package org.berendeev.buttonslist.domain.model;

public class Item {
    private int number;
    private float fill;

    public Item(int number, float fill) {
        this.number = number;
        this.fill = fill;
    }

    public int getNumber() {
        return number;
    }

    public float getFill() {
        return fill;
    }

    public Builder toBuilder(){
        return new Builder(this);
    }

    public static class Builder{
        private int number;
        private float fill;

        public Builder(Item item) {
            this.number = item.getNumber();
            this.fill = item.fill;
        }

        public Builder fill(float fill){
            this.fill = fill;
            return this;
        }

        public Builder number(int number){
            this.number = number;
            return this;
        }
        public Item build(){
            return new Item(number, fill);
        }
    }

    @Override public String toString() {
        return "Item(" + number + ", " + fill + ")";
    }
}
