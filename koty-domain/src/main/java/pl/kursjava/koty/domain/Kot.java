package pl.kursjava.koty.domain;

import java.util.Date;

public class Kot {
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public Float getWaga() {
        return waga;
    }

    public void setWaga(Float waga) {
        this.waga = waga;
    }

    public String getImieOpiekuna() {
        return imieOpiekuna;
    }

    public void setImieOpiekuna(String imieOpiekuna) {
        this.imieOpiekuna = imieOpiekuna;
    }
    
    private Integer id;
    private String imie;
    private Date dataUrodzenia;
    private Float waga;
    private String imieOpiekuna;
    public String przedstawSie()
    {
        return "Cześć, jestem " + imie + ", urodziłem się " + dataUrodzenia + ", ważę " + waga + " kg, moim opiekunem jest " + imieOpiekuna + ".";
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

