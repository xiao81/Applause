package applause.service;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaoxiao on 6/12/17.
 */

public interface TesterMatchingService {
    public User getUser(String country, String device);
    //public List<User> matchTesters(List<String> countryList, List<String> deviceList);
}
