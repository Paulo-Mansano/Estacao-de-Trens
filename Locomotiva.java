public class Locomotiva extends Veiculo{
    private int pesoMax;
    private int qntVagoes;


    // Locomotiva sem trem:

    public Locomotiva(int id, int pesoMax, int qntVagoes){
        super(id);
        this.trem = null;
        this.pesoMax = 50;
        this.qntVagoes = 50;
    }

    public void engatarEmTrem(Trem trem) {
        if (this.trem != null) {
            throw new RuntimeException("Esta locomotiva já está engatada em um trem.");
        }
        this.trem = trem;
        trem.adicionarLocomotiva(this);
    }

    public void desengatar(){
        if (this.trem != null) {
            this.trem.removerLocomotiva(this);
            this.trem = null;
        } else {
            System.out.println("Esta locomotiva não está atualmente associada a um trem.");
        }
        System.out.println("Locomotiva removida com sucesso no trem.");
    }
    

    public String getDisponibilidade(){
        return (trem == null) ? "Livre" : "Ocupado";
    }


    public int getPesoMax() {
        return pesoMax;
    }

    public int getQntVagoes() {
        // Verifica se nummaxdevagoes é menor que 50
    if (qntVagoes < 50) {
        // Se for menor que 50, retorna o valor de nummaxdevagoes sem alterações
        return qntVagoes;
    } else { // Se nummaxdevagoes não for menor que 50
        // Verifica se nummaxdevagoes é maior que 50
        if (qntVagoes > 50) {
            // Se for maior que 50, reduz nummaxdevagoes em 10%
            // Note que a redução é realizada através de uma expressão aritmética
            // 10 * nummaxdevagoes / 100 é a representação de 10%
            return qntVagoes - (10 * qntVagoes / 100);  
        }
    }
    // Retorna nummaxdevagoes se não for menor que 50 nem maior que 50
    return qntVagoes;
}



    @Override
    public String toString() {
        return "Locomotiva: id=" + getId() + ", trem=" + (getTrem() != null ? getTrem().getId() : "Nenhum") + ", capacidadeCarga=" + pesoMax + ", nroMaxVagoes=" + qntVagoes;
    }

    @Override
    public String getTipoVeiculo() {
       return "Locomotiva";
    }

}
