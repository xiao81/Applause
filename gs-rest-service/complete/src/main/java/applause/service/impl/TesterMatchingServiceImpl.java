package applause.service.impl;

import applause.service.TesterMatchingService;
import applause.service.User;
//import org.apache.metamodel.DataContext;
//import org.apache.metamodel.csv.CsvDataContext;
//import org.apache.metamodel.data.DataSet;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaoxiao on 6/12/17.
 */

public class TesterMatchingServiceImpl implements TesterMatchingService{

    public User getUser(String country, String device) {
        return new User(country, device);
    }

    /*public List<User> matchTesters(List<String> countryList, List<String> deviceList) {
        DataContext testerDC = new CsvDataContext(new File("/data/testers.csv"));
        DataSet ds = testerDC.query().from(testerDC.getDefaultSchema().getName()).select("firstName").execute();
        ds.toObjectArrays().forEach(name -> System.out.println(name));
        return new LinkedList<>();
    }*/
}
