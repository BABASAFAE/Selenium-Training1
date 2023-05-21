package com.sqli.testauto;

public class Product {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    private String type;
    private double quantite;
    private String article;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getproductTitle() {
        return productTitle;
    }

    public void setproductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Product(String name, String type, double quantite, String article, String productTitle) {
        this.name = name;
        this.type = type;
        this.quantite = quantite;
        this.article = article;
        this.productTitle = productTitle;
    }

    private String productTitle;

    @Override
    public String toString() {
        return "Catalogue{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", quantite=" + quantite +
                ", article='" + article + '\'' +
                ", productTitle='" + productTitle + '\'' +
                '}';
    }
}
