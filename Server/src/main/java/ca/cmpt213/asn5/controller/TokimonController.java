package ca.cmpt213.asn5.controller;

import ca.cmpt213.asn5.model.Tokimon;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TokimonController {
    private final AtomicLong counter = new AtomicLong();
    private List<Tokimon> tokimons = new ArrayList<>();

    //@RequestMapping(value="/tokimon",method=RequestMethod.GET)
    @GetMapping("/api/tokimon/all")
    public List<Tokimon> getTokimons() {
        System.out.println("List of all Tokimons:\n");
        return tokimons;
    }

    @GetMapping("/api/tokimon/{id}")
    public void getToki(@PathVariable String id, HttpServletResponse response) {
        // ... search for toki with id
        // if id doesn't exist return 404
        System.out.println("getting tokimon: " + id);
        boolean check = false;
        long tid = Long.parseLong(id);
        for (Tokimon x : tokimons) {
            if (x.getId() == tid) {
                check = true;
                System.out.println("Toki found!");
                response.setStatus(200);
                break;
            }
        }
        if(!check) {
            System.out.println("Tokimon doesn't exist.");
            response.setStatus(404);
        }
    }


//    @GetMapping("/querystring")
//    public void qstring(@RequestParam(value="name") String n,
//                        @RequestParam(value="strength",defaultValue="0") int s) {
//        System.out.println(n);
//        System.out.println(s);
//    }

    @PostMapping("/api/tokimon/add")
    public void addTokimon(@RequestBody Tokimon newToki, HttpServletResponse response) {
        // ... add the toki to the list
        System.out.println("Enter Tokimon's name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        System.out.println("Enter Tokimon's type: ");
        String type = input.nextLine();

        System.out.println("Enter Tokimon's height: ");
        double height = input.nextDouble();
        boolean done = false;
        while (!done) {
            if (height < 0) {
                System.out.println("Enter a positive number: ");
                height = input.nextDouble();
            } else {
                done = true;
            }
        }

        System.out.println("Enter Tokimon's weight: ");
        int weight = input.nextInt();
        done = false;
        while (!done) {
            if (weight < 0) {
                System.out.println("Enter a positive number: ");
                weight = input.nextInt();
            } else {
                done = true;
            }
        }

        System.out.println("Enter Tokimon's strength: ");
        int strength = input.nextInt();
        done = false;
        while (!done) {
            if (strength < 0) {
                System.out.println("Enter a positive number: ");
                strength = input.nextInt();
            } else {
                done = true;
            }
        }

        System.out.println("Enter Tokimon's colour: ");
        String colour = input.nextLine();

        newToki.setID(counter.incrementAndGet());
        newToki.setName(name);
        newToki.setAbility(type);
        newToki.setHeight(height);
        newToki.setWeight(weight);
        newToki.setStrength(strength);
        newToki.setColour(colour);

        tokimons.add(newToki);

        response.setStatus(201); // created
//        System.out.println(newToki.getStrength());
//        System.out.println(newToki.getName());
    }

    @PostMapping("/api/tokimon/change/{id}")
    public void alterToki(@PathVariable String id, HttpServletResponse response) {
        long tid = Long.parseLong(id);
        int index = 0;
        for (int i = 0; i < tokimons.size(); i++) {
            if (tokimons.get(i).getId() == tid) {
                index = i;
            }
        }

        System.out.println("What attribute do you want to change?");
        Scanner input = new Scanner(System.in);
        String attribute = input.nextLine();

        if (attribute.equals("name")) {
            System.out.println("What do you want to change the name to?");
            String name = input.nextLine();
            tokimons.get(index).setName("name");
            System.out.println("Name changed.");
            response.setStatus(201);

        } else if (attribute.equals("weight")) {
            System.out.println("What do you want to change the weight to?");
            int weight = input.nextInt();
            tokimons.get(index).setWeight(weight);
            System.out.println("Weight changed.");
            response.setStatus(201);

        } else if (attribute.equals("height")) {
            System.out.println("What do you want to change the height to?");
            double height = input.nextDouble();
            tokimons.get(index).setHeight(height);
            System.out.println("Height changed.");
            response.setStatus(201);

        } else if (attribute.equals("ability")) {
            System.out.println("What do you want to change the ability to?");
            String ability = input.nextLine();
            tokimons.get(index).setAbility(ability);
            System.out.println("Ability changed.");
            response.setStatus(201);

        } else if (attribute.equals("strength")) {
            System.out.println("What do you want to change the strength to?");
            int strength = input.nextInt();
            tokimons.get(index).setStrength(strength);
            System.out.println("Strength changed.");
            response.setStatus(201);

        } else if (attribute.equals("colour")) {
            System.out.println("What colour?");
            String colour = input.nextLine();
            tokimons.get(index).setColour(colour);
            System.out.println("Colour set.");
            response.setStatus(201);

        } else {
            System.out.println("Please enter a valid attribute.");
        }
    }

    @DeleteMapping("/api/tokimon/{id}")
    public void deleteToki(@PathVariable String id, HttpServletResponse response) {
        long tid = Long.parseLong(id);
        tokimons.removeIf(x -> x.getId() == tid);
        response.setStatus(204); // no content

    }


    @PostConstruct // gets executed when spring beans are initialized
    public void InIT() {
        System.out.println("POST CONSTRUCT CODE");
        Tokimon t1 = new Tokimon("pikachu", 99);
        Tokimon t2 = new Tokimon("bulbasaur", 76);
        Tokimon t3 = new Tokimon("mewtwo", 200);
        //this code should be in addToki method
        t1.setID(counter.incrementAndGet());
        t2.setID(counter.incrementAndGet());
        t3.setID(counter.incrementAndGet());
        tokimons.add(t1);
        tokimons.add(t2);
        tokimons.add(t3);
    }
}
