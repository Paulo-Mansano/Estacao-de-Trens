public abstract class Veiculo {
    public int id;
    public Trem trem;

    public Veiculo(int id, Trem trem) {
        this.id = id;
        this.trem = trem;
    }

    // Tava dando erro pra criar o trem então foi preciso criar um construtor sem
    // trem pra fazer funcionar

    public Veiculo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Trem getTrem() {
        return trem;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tipo: " + getTipoVeiculo();
    }

    public abstract String getTipoVeiculo();
}
