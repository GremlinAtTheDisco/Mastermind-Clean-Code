package persistence;

import java.util.List;

public interface ResultDAO {

    public void setResult(int nGuesses, int id);

    public List<Integer> getAllResults(int id);


}
