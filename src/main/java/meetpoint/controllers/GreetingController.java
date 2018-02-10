package meetpoint.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import meetpoint.model.Greeting;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    MongoOperations mongoOps;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {

        Greeting greeting = new Greeting(counter.incrementAndGet(),
                String.format(template, name));

        mongoOps.insert(greeting);

        return greeting;
    }

    @RequestMapping("/pastGreetings")
    public String getGreetings()
    {
        List<Greeting> greetingList =  mongoOps.findAll(Greeting.class);

        String pastGreetings = "Size of greetings: " + greetingList.size() + ", list = ";

        for (Greeting greeting : greetingList)
        {
            pastGreetings = pastGreetings.concat("" + greeting.getContent() + ",");
        }

        return pastGreetings;
    }
}