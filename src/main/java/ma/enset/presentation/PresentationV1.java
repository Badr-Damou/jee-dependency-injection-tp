package ma.enset.presentation;

import ma.enset.dao.DaoImpl;
import ma.enset.dao.IDao;
import ma.enset.metier.MetierImpl;

public class PresentationV1 {
    public static void main(String[] args) {
        IDao dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);

        System.out.println("Résultat : " + metier.calcul());
    }
}
