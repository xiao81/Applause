package applause.web;


import applause.service.TesterMatchingService;
import applause.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesterMatchingServiceController {


    @Autowired
    private TesterMatchingService testerMatchingService;

    @RequestMapping(path = "/tester", method = RequestMethod.GET)
    public User greeting(@RequestParam(value = "country", defaultValue = "ALL") String country,
                         @RequestParam(value = "device", defaultValue = "ALL") String device) {

        return testerMatchingService.getUser(country, device);
    }
}
