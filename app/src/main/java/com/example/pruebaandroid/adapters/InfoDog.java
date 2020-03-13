package com.example.pruebaandroid.adapters;

public class InfoDog {

    private String infoNombre;
    private int fotoDog;

    public InfoDog(){

    }

    public InfoDog(String infoNombre, int fotoDog) {
        this.infoNombre = infoNombre;
        this.fotoDog = fotoDog;
    }


    public String getInfoNombre() {
        return infoNombre;
    }

    public void setInfoNombre(String infoNombre) {
        this.infoNombre = infoNombre;
    }

    public int getFotoDog() {
        return fotoDog;
    }

    public void setFotoDog(int fotoDog) {
        this.fotoDog = fotoDog;
    }



}
