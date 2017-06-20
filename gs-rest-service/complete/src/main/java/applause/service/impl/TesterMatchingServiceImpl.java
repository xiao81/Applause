package applause.service.impl;

import applause.service.TesterMatchingService;
import applause.service.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.*;

@Component
public class TesterMatchingServiceImpl implements TesterMatchingService{

    public List<User> getUser(String country, String deviceString) {

        String CSV_FILES = Paths.get("").toAbsolutePath().toString() + "/src/main/java/applause/data/";
        List<String> deviceList = new LinkedList<>();
        List<String> deviceIDList = new LinkedList<>();
        Set<String> testerIDSet = new HashSet<>();
        HashMap<String, Integer> bugMap= new HashMap<>();
        HashMap<String, String[]> nameMap = new HashMap<>();
        List<User> userList = new LinkedList<>();

        //Create a data source and use it to create a JDBC template.
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:relique:csv:" + CSV_FILES);
        JdbcTemplate template = new JdbcTemplate(ds);
        if (deviceString.equals("ALL") || deviceString.equals("all")) {
            deviceList = template.queryForList("SELECT description FROM devices",String.class);
        } else {
            deviceList = Arrays.asList(deviceString.split("\\s*,\\s*"));
        }

        deviceList.forEach(device -> deviceIDList.addAll(
                template.queryForList("SELECT deviceId FROM devices WHERE description = " + "'" +device + "'", String.class)));



        deviceIDList.forEach(deviceID -> testerIDSet.addAll(template.queryForList(
                "SELECT testerId FROM tester_device WHERE deviceId = " + "'" +deviceID+"'", String.class)));
        if (!country.equals("ALL") && !country.equals("all")) {
            testerIDSet.forEach(testerID -> template.queryForList(
                    "SELECT firstName + ' ' + lastName FROM testers WHERE testerId = " + "'" +testerID+"'" + "AND country = " + "'" +country+"'", String.class).forEach(
                    name -> nameMap.put(testerID, new String[]{name, country})));
        } else {
            testerIDSet.forEach(testerID -> template.queryForList(
                    "SELECT firstName + ' ' + lastName + ',' + country FROM testers WHERE testerId = " + "'" +testerID+"'", String.class).forEach(
                    nameCountry -> nameMap.put(testerID, new String[]{ nameCountry.split(",")[0],nameCountry.split(",")[1]})));
        }


        nameMap.keySet().forEach(testerID -> deviceIDList.forEach(deviceID -> bugMap.put(
                testerID, bugMap.getOrDefault(testerID, 0) +
                        template.queryForList("SELECT bugId FROM bugs WHERE deviceId = " + "'" +deviceID+"'" + "AND testerId = " + "'" +testerID+"'", String.class).size())));

        bugMap.forEach((testerId, bugNum) -> userList.add(new User(nameMap.get(testerId)[1], nameMap.get(testerId)[0], bugNum)));

        System.out.println(deviceList);
        System.out.println(deviceIDList);
        System.out.println(testerIDSet);
        System.out.println(nameMap);
        System.out.println(bugMap);

        userList.sort(Comparator.comparingInt(User::getBugNum).reversed());
        return userList;
    }


}
