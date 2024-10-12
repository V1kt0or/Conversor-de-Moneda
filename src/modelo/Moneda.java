package modelo;

import java.util.Map;

public class Moneda {

    Double ARS;
    Double BOB;
    Double BRL;

    public Moneda(MonedaDto monedaDto) {
        this.ARS = monedaDto.conversion_rates().get("ARS");
        this.BOB = monedaDto.conversion_rates().get("BOB");
        this.BRL = monedaDto.conversion_rates().get("BRL");
    }

    public Double convDolAArg(Double valor) {
        return valor*ARS;
    }

    public Double convDolABol(Double valor) {
        return valor*BOB;
    }

    public Double convDolABra(Double valor) {
        return valor*BRL;
    }

    public Double convArgADol(Double valor) {
        double res = valor*ARS;
        return  Math.round(res * 10000.0) / 10000.0;

    }

    public Double convBolADol(Double valor) {
        double res = valor*BOB;
        return  Math.round(res * 10000.0) / 10000.0;
    }

    public Double convBraADol(Double valor) {
        double res = valor*BRL;
        return  Math.round(res * 10000.0) / 10000.0;
    }

}
