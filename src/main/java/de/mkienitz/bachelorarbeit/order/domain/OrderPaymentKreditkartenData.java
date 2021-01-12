package de.mkienitz.bachelorarbeit.order.domain;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class OrderPaymentKreditkartenData {
    @NotNull
    @Schema(required = true, example = "Max Mustermann")
    private String inhaber;

    @NotNull
    @Schema(required = true, example = "4111 1111 1111 1111")
    private String nummer;

    @NotNull
    @Schema(required = true, minLength = 3, maxLength = 3, minimum = "100", maximum = "999", example = "123")
    private String cvcCode;

    @NotNull
    @Schema(required = true, minLength = 2, maxLength = 2, minimum = "01", maximum = "12", example = "01")
    private String gueltigBisMonat;

    @NotNull
    @Schema(required = true, minLength = 2, maxLength = 2, minimum = "00", maximum = "99", example = "25")
    private String gueltigBisJahr;

    public OrderPaymentKreditkartenData() {
    }

    public OrderPaymentKreditkartenData(String inhaber, String nummer, String cvcCode, String gueltigBisMonat, String gueltigBisJahr) {
        this.inhaber = inhaber;
        this.nummer = nummer;
        this.cvcCode = cvcCode;
        this.gueltigBisMonat = gueltigBisMonat;
        this.gueltigBisJahr = gueltigBisJahr;
    }

    public String getInhaber() {
        return inhaber;
    }

    public void setInhaber(String inhaber) {
        this.inhaber = inhaber;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getCvcCode() {
        return cvcCode;
    }

    public void setCvcCode(String cvcCode) {
        this.cvcCode = cvcCode;
    }

    public String getGueltigBisMonat() {
        return gueltigBisMonat;
    }

    public void setGueltigBisMonat(String gueltigBisMonat) {
        this.gueltigBisMonat = gueltigBisMonat;
    }

    public String getGueltigBisJahr() {
        return gueltigBisJahr;
    }

    public void setGueltigBisJahr(String gueltigBisJahr) {
        this.gueltigBisJahr = gueltigBisJahr;
    }

    @Override
    public String toString() {
        return "OrderPaymentKreditkartenData{" +
                "inhaber='" + inhaber + '\'' +
                ", nummer='" + nummer + '\'' +
                ", cvcCode='" + cvcCode + '\'' +
                ", gueltigBisMonat='" + gueltigBisMonat + '\'' +
                ", gueltigBisJahr='" + gueltigBisJahr + '\'' +
                '}';
    }
}
