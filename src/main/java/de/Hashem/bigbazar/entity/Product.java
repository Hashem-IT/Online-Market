package de.Hashem.bigbazar.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/*
*
* Klasse für Produkten Information
*/

@Entity
public class Product extends BasicEntity {
    @Column(name = "description")
    private String description;

    @Column(name = "heightInMM")
    private BigDecimal heightInMM;

    @Column(name = "kathegory")
    @Enumerated(EnumType.STRING)
    private Kathegory kathegory;

    @Column(name = "lengthInMM")
    private BigDecimal lengthInMM;

    @Column(name = "name")
    private String name;

    @Column(name = "netto")
    private BigDecimal netto;

    @Column(name = "brutto")
    private BigDecimal brutto;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "weightInGram")
    private BigDecimal weightInGram;

    @Column(name = "widthInMM")
    private BigDecimal widthInMM;

    @Column(name = "picture")
    @Lob
    private byte[] picture;

    @Column(name = "count")
    private int count;

    @ManyToOne
    private Customer offerdBy;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getHeightInMM() {
        return heightInMM;
    }

    public void setHeightInMM(BigDecimal heightInMM) {
        this.heightInMM = heightInMM;
    }

    public Kathegory getKathegory() {
        return kathegory;
    }

    public void setKathegory(Kathegory kathegory) {
        this.kathegory = kathegory;
    }

    public BigDecimal getLengthInMM() {
        return lengthInMM;
    }

    public void setLengthInMM(BigDecimal lengthInMM) {
        this.lengthInMM = lengthInMM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    public void setNetto(BigDecimal netto) {
        this.netto = netto;
    }

    public BigDecimal getBrutto() {
        return brutto;
    }

    public void setBrutto(BigDecimal brutto) {
        this.brutto = brutto;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getWeightInGram() {
        return weightInGram;
    }

    public void setWeightInGram(BigDecimal weightInGram) {
        this.weightInGram = weightInGram;
    }

    public BigDecimal getWidthInMM() {
        return widthInMM;
    }

    public void setWidthInMM(BigDecimal widthInMM) {
        this.widthInMM = widthInMM;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Customer getOfferdBy() {
        return offerdBy;
    }

    public void setOfferdBy(Customer offerBy) {
        this.offerdBy = offerBy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        final Product other = (Product) o;
        if (!Objects.equals(getId(), other.getId()))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        if (getId()==null)
            return 0;
        else
            return getId().hashCode();
    }

    public void setKathegory(Enum kathegory2) {
    }
}
