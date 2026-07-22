package ma.enset.metier;

import ma.enset.dao.IDao;

public class MetierImpl implements IMetier {
    private IDao dao;

    public MetierImpl() {
    }

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("La dépendance DAO n'a pas été injectée");
        }
        return dao.getData() * 2;
    }
}
