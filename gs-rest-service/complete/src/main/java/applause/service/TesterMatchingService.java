package applause.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xiaoxiao on 6/12/17.
 */
public interface TesterMatchingService {
    List<User> getUser(String country, String device);
    //public List<User> matchTesters(List<String> countryList, List<String> deviceList);
}
